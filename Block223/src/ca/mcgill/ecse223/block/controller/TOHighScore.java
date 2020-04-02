/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.controller;

// line 49 "../../../../../Block223TransferObjects.ump"
public class TOHighScore
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOHighScore Attributes
  private String username;
  private int score;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOHighScore(String aUsername, int aScore)
  {
    username = aUsername;
    score = aScore;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setUsername(String aUsername)
  {
    boolean wasSet = false;
    username = aUsername;
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

  public String getUsername()
  {
    return username;
  }

  public int getScore()
  {
    return score;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "username" + ":" + getUsername()+ "," +
            "score" + ":" + getScore()+ "]";
  }
}