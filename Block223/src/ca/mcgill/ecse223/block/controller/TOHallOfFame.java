/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.controller;
import java.util.*;

// line 32 "../../../../../Block223TransferObjectsPlayMode.ump"
public class TOHallOfFame
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOHallOfFame Attributes
  private String gamename;

  //TOHallOfFame Associations
  private List<TOHallOfFameEntry> entries;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOHallOfFame(String aGamename)
  {
    gamename = aGamename;
    entries = new ArrayList<TOHallOfFameEntry>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setGamename(String aGamename)
  {
    boolean wasSet = false;
    gamename = aGamename;
    wasSet = true;
    return wasSet;
  }

  public String getGamename()
  {
    return gamename;
  }
  /* Code from template association_GetMany */
  public TOHallOfFameEntry getEntry(int index)
  {
    TOHallOfFameEntry aEntry = entries.get(index);
    return aEntry;
  }

  public List<TOHallOfFameEntry> getEntries()
  {
    List<TOHallOfFameEntry> newEntries = Collections.unmodifiableList(entries);
    return newEntries;
  }

  public int numberOfEntries()
  {
    int number = entries.size();
    return number;
  }

  public boolean hasEntries()
  {
    boolean has = entries.size() > 0;
    return has;
  }

  public int indexOfEntry(TOHallOfFameEntry aEntry)
  {
    int index = entries.indexOf(aEntry);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfEntries()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public TOHallOfFameEntry addEntry(int aPosition, String aPlayername, int aScore)
  {
    return new TOHallOfFameEntry(aPosition, aPlayername, aScore, this);
  }

  public boolean addEntry(TOHallOfFameEntry aEntry)
  {
    boolean wasAdded = false;
    if (entries.contains(aEntry)) { return false; }
    TOHallOfFame existingTOHallOfFame = aEntry.getTOHallOfFame();
    boolean isNewTOHallOfFame = existingTOHallOfFame != null && !this.equals(existingTOHallOfFame);
    if (isNewTOHallOfFame)
    {
      aEntry.setTOHallOfFame(this);
    }
    else
    {
      entries.add(aEntry);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeEntry(TOHallOfFameEntry aEntry)
  {
    boolean wasRemoved = false;
    //Unable to remove aEntry, as it must always have a tOHallOfFame
    if (!this.equals(aEntry.getTOHallOfFame()))
    {
      entries.remove(aEntry);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addEntryAt(TOHallOfFameEntry aEntry, int index)
  {  
    boolean wasAdded = false;
    if(addEntry(aEntry))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEntries()) { index = numberOfEntries() - 1; }
      entries.remove(aEntry);
      entries.add(index, aEntry);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveEntryAt(TOHallOfFameEntry aEntry, int index)
  {
    boolean wasAdded = false;
    if(entries.contains(aEntry))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEntries()) { index = numberOfEntries() - 1; }
      entries.remove(aEntry);
      entries.add(index, aEntry);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addEntryAt(aEntry, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=entries.size(); i > 0; i--)
    {
      TOHallOfFameEntry aEntry = entries.get(i - 1);
      aEntry.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "gamename" + ":" + getGamename()+ "]";
  }
}