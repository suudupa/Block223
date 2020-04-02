/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.controller;

// line 23 "../../../../../Block223TransferObjectsPlayMode.ump"
public class TOCurrentBlock
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOCurrentBlock Attributes
  private int red;
  private int green;
  private int blue;
  private int points;
  private int x;
  private int y;

  //TOCurrentBlock Associations
  private TOCurrentlyPlayedGame tOCurrentlyPlayedGame;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOCurrentBlock(int aRed, int aGreen, int aBlue, int aPoints, int aX, int aY, TOCurrentlyPlayedGame aTOCurrentlyPlayedGame)
  {
    red = aRed;
    green = aGreen;
    blue = aBlue;
    points = aPoints;
    x = aX;
    y = aY;
    boolean didAddTOCurrentlyPlayedGame = setTOCurrentlyPlayedGame(aTOCurrentlyPlayedGame);
    if (!didAddTOCurrentlyPlayedGame)
    {
      throw new RuntimeException("Unable to create block due to tOCurrentlyPlayedGame");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setRed(int aRed)
  {
    boolean wasSet = false;
    red = aRed;
    wasSet = true;
    return wasSet;
  }

  public boolean setGreen(int aGreen)
  {
    boolean wasSet = false;
    green = aGreen;
    wasSet = true;
    return wasSet;
  }

  public boolean setBlue(int aBlue)
  {
    boolean wasSet = false;
    blue = aBlue;
    wasSet = true;
    return wasSet;
  }

  public boolean setPoints(int aPoints)
  {
    boolean wasSet = false;
    points = aPoints;
    wasSet = true;
    return wasSet;
  }

  public boolean setX(int aX)
  {
    boolean wasSet = false;
    x = aX;
    wasSet = true;
    return wasSet;
  }

  public boolean setY(int aY)
  {
    boolean wasSet = false;
    y = aY;
    wasSet = true;
    return wasSet;
  }

  public int getRed()
  {
    return red;
  }

  public int getGreen()
  {
    return green;
  }

  public int getBlue()
  {
    return blue;
  }

  public int getPoints()
  {
    return points;
  }

  public int getX()
  {
    return x;
  }

  public int getY()
  {
    return y;
  }
  /* Code from template association_GetOne */
  public TOCurrentlyPlayedGame getTOCurrentlyPlayedGame()
  {
    return tOCurrentlyPlayedGame;
  }
  /* Code from template association_SetOneToMany */
  public boolean setTOCurrentlyPlayedGame(TOCurrentlyPlayedGame aTOCurrentlyPlayedGame)
  {
    boolean wasSet = false;
    if (aTOCurrentlyPlayedGame == null)
    {
      return wasSet;
    }

    TOCurrentlyPlayedGame existingTOCurrentlyPlayedGame = tOCurrentlyPlayedGame;
    tOCurrentlyPlayedGame = aTOCurrentlyPlayedGame;
    if (existingTOCurrentlyPlayedGame != null && !existingTOCurrentlyPlayedGame.equals(aTOCurrentlyPlayedGame))
    {
      existingTOCurrentlyPlayedGame.removeBlock(this);
    }
    tOCurrentlyPlayedGame.addBlock(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    TOCurrentlyPlayedGame placeholderTOCurrentlyPlayedGame = tOCurrentlyPlayedGame;
    this.tOCurrentlyPlayedGame = null;
    if(placeholderTOCurrentlyPlayedGame != null)
    {
      placeholderTOCurrentlyPlayedGame.removeBlock(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "red" + ":" + getRed()+ "," +
            "green" + ":" + getGreen()+ "," +
            "blue" + ":" + getBlue()+ "," +
            "points" + ":" + getPoints()+ "," +
            "x" + ":" + getX()+ "," +
            "y" + ":" + getY()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "tOCurrentlyPlayedGame = "+(getTOCurrentlyPlayedGame()!=null?Integer.toHexString(System.identityHashCode(getTOCurrentlyPlayedGame())):"null");
  }
}