/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.controller;

// line 3 "../../../../../Block223TransferObjectsPlayMode.ump"
public class TOPlayableGame
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOPlayableGame Attributes
  private String name;
  private int number;
  private int currentLevel;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOPlayableGame(String aName, int aNumber, int aCurrentLevel)
  {
    name = aName;
    number = aNumber;
    currentLevel = aCurrentLevel;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumber(int aNumber)
  {
    boolean wasSet = false;
    number = aNumber;
    wasSet = true;
    return wasSet;
  }

  public boolean setCurrentLevel(int aCurrentLevel)
  {
    boolean wasSet = false;
    currentLevel = aCurrentLevel;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  /**
   * this is the id of a game that can be continued and -1 if not.
   */
  public int getNumber()
  {
    return number;
  }

  /**
   * this is the current level of a game that can be continued and 0 if not
   */
  public int getCurrentLevel()
  {
    return currentLevel;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "number" + ":" + getNumber()+ "," +
            "currentLevel" + ":" + getCurrentLevel()+ "]";
  }
}