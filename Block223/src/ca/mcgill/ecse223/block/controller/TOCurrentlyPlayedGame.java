/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.controller;
import java.util.*;

// line 9 "../../../../../Block223TransferObjectsPlayMode.ump"
public class TOCurrentlyPlayedGame
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOCurrentlyPlayedGame Attributes
  private String gamename;
  private boolean paused;
  private int score;
  private int lives;
  private int currentLevel;
  private String playername;
  private double currentBallX;
  private double currentBallY;
  private double currentPaddleLength;
  private double currentPaddleX;

  //TOCurrentlyPlayedGame Associations
  private List<TOCurrentBlock> blocks;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOCurrentlyPlayedGame(String aGamename, boolean aPaused, int aScore, int aLives, int aCurrentLevel, String aPlayername, double aCurrentBallX, double aCurrentBallY, double aCurrentPaddleLength, double aCurrentPaddleX)
  {
    gamename = aGamename;
    paused = aPaused;
    score = aScore;
    lives = aLives;
    currentLevel = aCurrentLevel;
    playername = aPlayername;
    currentBallX = aCurrentBallX;
    currentBallY = aCurrentBallY;
    currentPaddleLength = aCurrentPaddleLength;
    currentPaddleX = aCurrentPaddleX;
    blocks = new ArrayList<TOCurrentBlock>();
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

  public boolean setPaused(boolean aPaused)
  {
    boolean wasSet = false;
    paused = aPaused;
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

  public boolean setLives(int aLives)
  {
    boolean wasSet = false;
    lives = aLives;
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

  public boolean setPlayername(String aPlayername)
  {
    boolean wasSet = false;
    playername = aPlayername;
    wasSet = true;
    return wasSet;
  }

  public boolean setCurrentBallX(double aCurrentBallX)
  {
    boolean wasSet = false;
    currentBallX = aCurrentBallX;
    wasSet = true;
    return wasSet;
  }

  public boolean setCurrentBallY(double aCurrentBallY)
  {
    boolean wasSet = false;
    currentBallY = aCurrentBallY;
    wasSet = true;
    return wasSet;
  }

  public boolean setCurrentPaddleLength(double aCurrentPaddleLength)
  {
    boolean wasSet = false;
    currentPaddleLength = aCurrentPaddleLength;
    wasSet = true;
    return wasSet;
  }

  public boolean setCurrentPaddleX(double aCurrentPaddleX)
  {
    boolean wasSet = false;
    currentPaddleX = aCurrentPaddleX;
    wasSet = true;
    return wasSet;
  }

  public String getGamename()
  {
    return gamename;
  }

  public boolean getPaused()
  {
    return paused;
  }

  public int getScore()
  {
    return score;
  }

  public int getLives()
  {
    return lives;
  }

  public int getCurrentLevel()
  {
    return currentLevel;
  }

  /**
   * "test run" if the admin is doing a test, otherwise name of the player
   */
  public String getPlayername()
  {
    return playername;
  }

  public double getCurrentBallX()
  {
    return currentBallX;
  }

  public double getCurrentBallY()
  {
    return currentBallY;
  }

  public double getCurrentPaddleLength()
  {
    return currentPaddleLength;
  }

  public double getCurrentPaddleX()
  {
    return currentPaddleX;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isPaused()
  {
    return paused;
  }
  /* Code from template association_GetMany */
  public TOCurrentBlock getBlock(int index)
  {
    TOCurrentBlock aBlock = blocks.get(index);
    return aBlock;
  }

  public List<TOCurrentBlock> getBlocks()
  {
    List<TOCurrentBlock> newBlocks = Collections.unmodifiableList(blocks);
    return newBlocks;
  }

  public int numberOfBlocks()
  {
    int number = blocks.size();
    return number;
  }

  public boolean hasBlocks()
  {
    boolean has = blocks.size() > 0;
    return has;
  }

  public int indexOfBlock(TOCurrentBlock aBlock)
  {
    int index = blocks.indexOf(aBlock);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBlocks()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public TOCurrentBlock addBlock(int aRed, int aGreen, int aBlue, int aPoints, int aX, int aY)
  {
    return new TOCurrentBlock(aRed, aGreen, aBlue, aPoints, aX, aY, this);
  }

  public boolean addBlock(TOCurrentBlock aBlock)
  {
    boolean wasAdded = false;
    if (blocks.contains(aBlock)) { return false; }
    TOCurrentlyPlayedGame existingTOCurrentlyPlayedGame = aBlock.getTOCurrentlyPlayedGame();
    boolean isNewTOCurrentlyPlayedGame = existingTOCurrentlyPlayedGame != null && !this.equals(existingTOCurrentlyPlayedGame);
    if (isNewTOCurrentlyPlayedGame)
    {
      aBlock.setTOCurrentlyPlayedGame(this);
    }
    else
    {
      blocks.add(aBlock);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBlock(TOCurrentBlock aBlock)
  {
    boolean wasRemoved = false;
    //Unable to remove aBlock, as it must always have a tOCurrentlyPlayedGame
    if (!this.equals(aBlock.getTOCurrentlyPlayedGame()))
    {
      blocks.remove(aBlock);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBlockAt(TOCurrentBlock aBlock, int index)
  {  
    boolean wasAdded = false;
    if(addBlock(aBlock))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBlocks()) { index = numberOfBlocks() - 1; }
      blocks.remove(aBlock);
      blocks.add(index, aBlock);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBlockAt(TOCurrentBlock aBlock, int index)
  {
    boolean wasAdded = false;
    if(blocks.contains(aBlock))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBlocks()) { index = numberOfBlocks() - 1; }
      blocks.remove(aBlock);
      blocks.add(index, aBlock);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBlockAt(aBlock, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=blocks.size(); i > 0; i--)
    {
      TOCurrentBlock aBlock = blocks.get(i - 1);
      aBlock.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "gamename" + ":" + getGamename()+ "," +
            "paused" + ":" + getPaused()+ "," +
            "score" + ":" + getScore()+ "," +
            "lives" + ":" + getLives()+ "," +
            "currentLevel" + ":" + getCurrentLevel()+ "," +
            "playername" + ":" + getPlayername()+ "," +
            "currentBallX" + ":" + getCurrentBallX()+ "," +
            "currentBallY" + ":" + getCurrentBallY()+ "," +
            "currentPaddleLength" + ":" + getCurrentPaddleLength()+ "," +
            "currentPaddleX" + ":" + getCurrentPaddleX()+ "]";
  }
}