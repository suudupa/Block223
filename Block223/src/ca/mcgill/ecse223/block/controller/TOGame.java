/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.controller;

// line 3 "../../../../../Block223TransferObjects.ump"
public class TOGame
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOGame Attributes
  private String name;
  private int nrLevels;
  private int nrBlocksPerLevel;
  private int minBallSpeedX;
  private int minBallSpeedY;
  private double ballSpeedIncreaseFactor;
  private int maxPaddleLength;
  private int minPaddleLength;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOGame(String aName, int aNrLevels, int aNrBlocksPerLevel, int aMinBallSpeedX, int aMinBallSpeedY, double aBallSpeedIncreaseFactor, int aMaxPaddleLength, int aMinPaddleLength)
  {
    name = aName;
    nrLevels = aNrLevels;
    nrBlocksPerLevel = aNrBlocksPerLevel;
    minBallSpeedX = aMinBallSpeedX;
    minBallSpeedY = aMinBallSpeedY;
    ballSpeedIncreaseFactor = aBallSpeedIncreaseFactor;
    maxPaddleLength = aMaxPaddleLength;
    minPaddleLength = aMinPaddleLength;
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

  public boolean setNrLevels(int aNrLevels)
  {
    boolean wasSet = false;
    nrLevels = aNrLevels;
    wasSet = true;
    return wasSet;
  }

  public boolean setNrBlocksPerLevel(int aNrBlocksPerLevel)
  {
    boolean wasSet = false;
    nrBlocksPerLevel = aNrBlocksPerLevel;
    wasSet = true;
    return wasSet;
  }

  public boolean setMinBallSpeedX(int aMinBallSpeedX)
  {
    boolean wasSet = false;
    minBallSpeedX = aMinBallSpeedX;
    wasSet = true;
    return wasSet;
  }

  public boolean setMinBallSpeedY(int aMinBallSpeedY)
  {
    boolean wasSet = false;
    minBallSpeedY = aMinBallSpeedY;
    wasSet = true;
    return wasSet;
  }

  public boolean setBallSpeedIncreaseFactor(double aBallSpeedIncreaseFactor)
  {
    boolean wasSet = false;
    ballSpeedIncreaseFactor = aBallSpeedIncreaseFactor;
    wasSet = true;
    return wasSet;
  }

  public boolean setMaxPaddleLength(int aMaxPaddleLength)
  {
    boolean wasSet = false;
    maxPaddleLength = aMaxPaddleLength;
    wasSet = true;
    return wasSet;
  }

  public boolean setMinPaddleLength(int aMinPaddleLength)
  {
    boolean wasSet = false;
    minPaddleLength = aMinPaddleLength;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public int getNrLevels()
  {
    return nrLevels;
  }

  public int getNrBlocksPerLevel()
  {
    return nrBlocksPerLevel;
  }

  public int getMinBallSpeedX()
  {
    return minBallSpeedX;
  }

  public int getMinBallSpeedY()
  {
    return minBallSpeedY;
  }

  public double getBallSpeedIncreaseFactor()
  {
    return ballSpeedIncreaseFactor;
  }

  public int getMaxPaddleLength()
  {
    return maxPaddleLength;
  }

  public int getMinPaddleLength()
  {
    return minPaddleLength;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "nrLevels" + ":" + getNrLevels()+ "," +
            "nrBlocksPerLevel" + ":" + getNrBlocksPerLevel()+ "," +
            "minBallSpeedX" + ":" + getMinBallSpeedX()+ "," +
            "minBallSpeedY" + ":" + getMinBallSpeedY()+ "," +
            "ballSpeedIncreaseFactor" + ":" + getBallSpeedIncreaseFactor()+ "," +
            "maxPaddleLength" + ":" + getMaxPaddleLength()+ "," +
            "minPaddleLength" + ":" + getMinPaddleLength()+ "]";
  }
}