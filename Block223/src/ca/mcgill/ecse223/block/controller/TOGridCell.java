/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.controller;

// line 22 "../../../../../Block223TransferObjects.ump"
public class TOGridCell
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOGridCell Attributes
  private int gridHorizontalPosition;
  private int gridVerticalPosition;
  private int id;
  private int red;
  private int green;
  private int blue;
  private int points;

  //TOGridCell Associations
  private TORenderedGame tORenderedGame;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOGridCell(int aGridHorizontalPosition, int aGridVerticalPosition, int aId, int aRed, int aGreen, int aBlue, int aPoints)
  {
    gridHorizontalPosition = aGridHorizontalPosition;
    gridVerticalPosition = aGridVerticalPosition;
    id = aId;
    red = aRed;
    green = aGreen;
    blue = aBlue;
    points = aPoints;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setGridHorizontalPosition(int aGridHorizontalPosition)
  {
    boolean wasSet = false;
    gridHorizontalPosition = aGridHorizontalPosition;
    wasSet = true;
    return wasSet;
  }

  public boolean setGridVerticalPosition(int aGridVerticalPosition)
  {
    boolean wasSet = false;
    gridVerticalPosition = aGridVerticalPosition;
    wasSet = true;
    return wasSet;
  }

  public boolean setId(int aId)
  {
    boolean wasSet = false;
    id = aId;
    wasSet = true;
    return wasSet;
  }

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

  public int getGridHorizontalPosition()
  {
    return gridHorizontalPosition;
  }

  public int getGridVerticalPosition()
  {
    return gridVerticalPosition;
  }

  public int getId()
  {
    return id;
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
  /* Code from template association_GetOne */
  public TORenderedGame getTORenderedGame()
  {
    return tORenderedGame;
  }

  public boolean hasTORenderedGame()
  {
    boolean has = tORenderedGame != null;
    return has;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setTORenderedGame(TORenderedGame aTORenderedGame)
  {
    boolean wasSet = false;
    TORenderedGame existingTORenderedGame = tORenderedGame;
    tORenderedGame = aTORenderedGame;
    if (existingTORenderedGame != null && !existingTORenderedGame.equals(aTORenderedGame))
    {
      existingTORenderedGame.removeBlock(this);
    }
    if (aTORenderedGame != null)
    {
      aTORenderedGame.addBlock(this);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    if (tORenderedGame != null)
    {
      TORenderedGame placeholderTORenderedGame = tORenderedGame;
      this.tORenderedGame = null;
      placeholderTORenderedGame.removeBlock(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "gridHorizontalPosition" + ":" + getGridHorizontalPosition()+ "," +
            "gridVerticalPosition" + ":" + getGridVerticalPosition()+ "," +
            "id" + ":" + getId()+ "," +
            "red" + ":" + getRed()+ "," +
            "green" + ":" + getGreen()+ "," +
            "blue" + ":" + getBlue()+ "," +
            "points" + ":" + getPoints()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "tORenderedGame = "+(getTORenderedGame()!=null?Integer.toHexString(System.identityHashCode(getTORenderedGame())):"null");
  }
}