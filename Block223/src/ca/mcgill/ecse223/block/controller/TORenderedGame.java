/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.controller;
import java.util.*;

// line 37 "../../../../../Block223TransferObjects.ump"
public class TORenderedGame
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TORenderedGame Attributes
  private int score;
  private int numLives;
  private int currentLevel;
  private double paddleX;
  private double ballX;
  private double ballY;
  private double ballSpeedX;
  private double ballSpeedY;

  //TORenderedGame Associations
  private List<TOGridCell> blocks;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TORenderedGame(int aScore, int aNumLives, int aCurrentLevel, double aPaddleX, double aBallX, double aBallY, double aBallSpeedX, double aBallSpeedY)
  {
    score = aScore;
    numLives = aNumLives;
    currentLevel = aCurrentLevel;
    paddleX = aPaddleX;
    ballX = aBallX;
    ballY = aBallY;
    ballSpeedX = aBallSpeedX;
    ballSpeedY = aBallSpeedY;
    blocks = new ArrayList<TOGridCell>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setScore(int aScore)
  {
    boolean wasSet = false;
    score = aScore;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumLives(int aNumLives)
  {
    boolean wasSet = false;
    numLives = aNumLives;
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

  public boolean setPaddleX(double aPaddleX)
  {
    boolean wasSet = false;
    paddleX = aPaddleX;
    wasSet = true;
    return wasSet;
  }

  public boolean setBallX(double aBallX)
  {
    boolean wasSet = false;
    ballX = aBallX;
    wasSet = true;
    return wasSet;
  }

  public boolean setBallY(double aBallY)
  {
    boolean wasSet = false;
    ballY = aBallY;
    wasSet = true;
    return wasSet;
  }

  public boolean setBallSpeedX(double aBallSpeedX)
  {
    boolean wasSet = false;
    ballSpeedX = aBallSpeedX;
    wasSet = true;
    return wasSet;
  }

  public boolean setBallSpeedY(double aBallSpeedY)
  {
    boolean wasSet = false;
    ballSpeedY = aBallSpeedY;
    wasSet = true;
    return wasSet;
  }

  public int getScore()
  {
    return score;
  }

  public int getNumLives()
  {
    return numLives;
  }

  public int getCurrentLevel()
  {
    return currentLevel;
  }

  public double getPaddleX()
  {
    return paddleX;
  }

  public double getBallX()
  {
    return ballX;
  }

  public double getBallY()
  {
    return ballY;
  }

  public double getBallSpeedX()
  {
    return ballSpeedX;
  }

  public double getBallSpeedY()
  {
    return ballSpeedY;
  }
  /* Code from template association_GetMany */
  public TOGridCell getBlock(int index)
  {
    TOGridCell aBlock = blocks.get(index);
    return aBlock;
  }

  public List<TOGridCell> getBlocks()
  {
    List<TOGridCell> newBlocks = Collections.unmodifiableList(blocks);
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

  public int indexOfBlock(TOGridCell aBlock)
  {
    int index = blocks.indexOf(aBlock);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBlocks()
  {
    return 0;
  }
  /* Code from template association_AddManyToOptionalOne */
  public boolean addBlock(TOGridCell aBlock)
  {
    boolean wasAdded = false;
    if (blocks.contains(aBlock)) { return false; }
    TORenderedGame existingTORenderedGame = aBlock.getTORenderedGame();
    if (existingTORenderedGame == null)
    {
      aBlock.setTORenderedGame(this);
    }
    else if (!this.equals(existingTORenderedGame))
    {
      existingTORenderedGame.removeBlock(aBlock);
      addBlock(aBlock);
    }
    else
    {
      blocks.add(aBlock);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBlock(TOGridCell aBlock)
  {
    boolean wasRemoved = false;
    if (blocks.contains(aBlock))
    {
      blocks.remove(aBlock);
      aBlock.setTORenderedGame(null);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBlockAt(TOGridCell aBlock, int index)
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

  public boolean addOrMoveBlockAt(TOGridCell aBlock, int index)
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
    while( !blocks.isEmpty() )
    {
      blocks.get(0).setTORenderedGame(null);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "score" + ":" + getScore()+ "," +
            "numLives" + ":" + getNumLives()+ "," +
            "currentLevel" + ":" + getCurrentLevel()+ "," +
            "paddleX" + ":" + getPaddleX()+ "," +
            "ballX" + ":" + getBallX()+ "," +
            "ballY" + ":" + getBallY()+ "," +
            "ballSpeedX" + ":" + getBallSpeedX()+ "," +
            "ballSpeedY" + ":" + getBallSpeedY()+ "]";
  }
}