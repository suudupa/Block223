/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.controller;

// line 37 "../../../../../Block223TransferObjectsPlayMode.ump"
public class TOHallOfFameEntry
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOHallOfFameEntry Attributes
  private int position;
  private String playername;
  private int score;

  //TOHallOfFameEntry Associations
  private TOHallOfFame tOHallOfFame;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOHallOfFameEntry(int aPosition, String aPlayername, int aScore, TOHallOfFame aTOHallOfFame)
  {
    position = aPosition;
    playername = aPlayername;
    score = aScore;
    boolean didAddTOHallOfFame = setTOHallOfFame(aTOHallOfFame);
    if (!didAddTOHallOfFame)
    {
      throw new RuntimeException("Unable to create entry due to tOHallOfFame");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPosition(int aPosition)
  {
    boolean wasSet = false;
    position = aPosition;
    wasSet = true;
    return wasSet;
  }

  public boolean setPlayername(String aPlayername)
  {
    boolean wasSet = false;
    playername = aPlayername;
    wasSet = true;
    return wasSet;
  }

  public boolean setScore(int aScore)
  {
    boolean wasSet = false;
    score = aScore;
    wasSet = true;
    return wasSet;
  }

  public int getPosition()
  {
    return position;
  }

  public String getPlayername()
  {
    return playername;
  }

  public int getScore()
  {
    return score;
  }
  /* Code from template association_GetOne */
  public TOHallOfFame getTOHallOfFame()
  {
    return tOHallOfFame;
  }
  /* Code from template association_SetOneToMany */
  public boolean setTOHallOfFame(TOHallOfFame aTOHallOfFame)
  {
    boolean wasSet = false;
    if (aTOHallOfFame == null)
    {
      return wasSet;
    }

    TOHallOfFame existingTOHallOfFame = tOHallOfFame;
    tOHallOfFame = aTOHallOfFame;
    if (existingTOHallOfFame != null && !existingTOHallOfFame.equals(aTOHallOfFame))
    {
      existingTOHallOfFame.removeEntry(this);
    }
    tOHallOfFame.addEntry(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    TOHallOfFame placeholderTOHallOfFame = tOHallOfFame;
    this.tOHallOfFame = null;
    if(placeholderTOHallOfFame != null)
    {
      placeholderTOHallOfFame.removeEntry(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "position" + ":" + getPosition()+ "," +
            "playername" + ":" + getPlayername()+ "," +
            "score" + ":" + getScore()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "tOHallOfFame = "+(getTOHallOfFame()!=null?Integer.toHexString(System.identityHashCode(getTOHallOfFame())):"null");
  }
}