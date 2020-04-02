/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.io.Serializable;
import java.util.*;

// line 44 "../../../../../Block223Persistence.ump"
// line 130 "../../../../../Block223.ump"
public class Block implements Serializable
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  public static final int MIN_COLOR = 0;
  public static final int MAX_COLOR = 255;
  public static final int MIN_POINTS = 1;
  public static final int MAX_POINTS = 1000;
  public static final int SIZE = 20;
  private static int nextId = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Block Attributes
  private int red;
  private int green;
  private int blue;
  private int points;

  //Autounique Attributes
  private int id;

  //Block Associations
  private List<PlayedBlockAssignment> playedBlockAssignments;
  private Game game;
  private List<BlockAssignment> blockAssignments;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Block(int aRed, int aGreen, int aBlue, int aPoints, Game aGame)
  {
    // line 139 "../../../../../Block223.ump"
    if (aRed < MIN_COLOR || aRed > MAX_COLOR) {
       		throw new RuntimeException("Red must be between 0 and 255.");
       	}
    // END OF UMPLE BEFORE INJECTION
    // line 145 "../../../../../Block223.ump"
    if (aGreen < MIN_COLOR || aGreen > MAX_COLOR) {
       		throw new RuntimeException("Green must be between 0 and 255.");
       	}
    // END OF UMPLE BEFORE INJECTION
    // line 151 "../../../../../Block223.ump"
    if (aBlue < MIN_COLOR || aBlue > MAX_COLOR) {
       		throw new RuntimeException("Blue must be between 0 and 255.");
       	}
    // END OF UMPLE BEFORE INJECTION
    // line 157 "../../../../../Block223.ump"
    if (aPoints < MIN_POINTS || aPoints > MAX_POINTS) {
       		throw new RuntimeException("Points must be between 1 and 1000.");
       	}
    // END OF UMPLE BEFORE INJECTION
    red = aRed;
    green = aGreen;
    blue = aBlue;
    points = aPoints;
    id = nextId++;
    playedBlockAssignments = new ArrayList<PlayedBlockAssignment>();
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create block due to game");
    }
    blockAssignments = new ArrayList<BlockAssignment>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setRed(int aRed)
  {
    boolean wasSet = false;
    // line 167 "../../../../../Block223.ump"
    if (aRed < MIN_COLOR || aRed > MAX_COLOR) {
       		throw new RuntimeException("Red must be between 0 and 255.");
       	}
    // END OF UMPLE BEFORE INJECTION
    red = aRed;
    wasSet = true;
    return wasSet;
  }

  public boolean setGreen(int aGreen)
  {
    boolean wasSet = false;
    // line 172 "../../../../../Block223.ump"
    if (aGreen < MIN_COLOR || aGreen > MAX_COLOR) {
       		throw new RuntimeException("Green must be between 0 and 255.");
       	}
    // END OF UMPLE BEFORE INJECTION
    green = aGreen;
    wasSet = true;
    return wasSet;
  }

  public boolean setBlue(int aBlue)
  {
    boolean wasSet = false;
    // line 177 "../../../../../Block223.ump"
    if (aBlue < MIN_COLOR || aBlue > MAX_COLOR) {
       		throw new RuntimeException("Blue must be between 0 and 255.");
       	}
    // END OF UMPLE BEFORE INJECTION
    blue = aBlue;
    wasSet = true;
    return wasSet;
  }

  public boolean setPoints(int aPoints)
  {
    boolean wasSet = false;
    // line 162 "../../../../../Block223.ump"
    if (aPoints < 1 || aPoints > 1000) {
       		throw new RuntimeException("Points must be between 1 and 1000.");
       	}
    // END OF UMPLE BEFORE INJECTION
    points = aPoints;
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

  public int getId()
  {
    return id;
  }
  /* Code from template association_GetMany */
  public PlayedBlockAssignment getPlayedBlockAssignment(int index)
  {
    PlayedBlockAssignment aPlayedBlockAssignment = playedBlockAssignments.get(index);
    return aPlayedBlockAssignment;
  }

  public List<PlayedBlockAssignment> getPlayedBlockAssignments()
  {
    List<PlayedBlockAssignment> newPlayedBlockAssignments = Collections.unmodifiableList(playedBlockAssignments);
    return newPlayedBlockAssignments;
  }

  public int numberOfPlayedBlockAssignments()
  {
    int number = playedBlockAssignments.size();
    return number;
  }

  public boolean hasPlayedBlockAssignments()
  {
    boolean has = playedBlockAssignments.size() > 0;
    return has;
  }

  public int indexOfPlayedBlockAssignment(PlayedBlockAssignment aPlayedBlockAssignment)
  {
    int index = playedBlockAssignments.indexOf(aPlayedBlockAssignment);
    return index;
  }
  /* Code from template association_GetOne */
  public Game getGame()
  {
    return game;
  }
  /* Code from template association_GetMany */
  public BlockAssignment getBlockAssignment(int index)
  {
    BlockAssignment aBlockAssignment = blockAssignments.get(index);
    return aBlockAssignment;
  }

  public List<BlockAssignment> getBlockAssignments()
  {
    List<BlockAssignment> newBlockAssignments = Collections.unmodifiableList(blockAssignments);
    return newBlockAssignments;
  }

  public int numberOfBlockAssignments()
  {
    int number = blockAssignments.size();
    return number;
  }

  public boolean hasBlockAssignments()
  {
    boolean has = blockAssignments.size() > 0;
    return has;
  }

  public int indexOfBlockAssignment(BlockAssignment aBlockAssignment)
  {
    int index = blockAssignments.indexOf(aBlockAssignment);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPlayedBlockAssignments()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public PlayedBlockAssignment addPlayedBlockAssignment(int aX, int aY, PlayedGame aPlayedGame)
  {
    return new PlayedBlockAssignment(aX, aY, this, aPlayedGame);
  }

  public boolean addPlayedBlockAssignment(PlayedBlockAssignment aPlayedBlockAssignment)
  {
    boolean wasAdded = false;
    if (playedBlockAssignments.contains(aPlayedBlockAssignment)) { return false; }
    Block existingBlock = aPlayedBlockAssignment.getBlock();
    boolean isNewBlock = existingBlock != null && !this.equals(existingBlock);
    if (isNewBlock)
    {
      aPlayedBlockAssignment.setBlock(this);
    }
    else
    {
      playedBlockAssignments.add(aPlayedBlockAssignment);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePlayedBlockAssignment(PlayedBlockAssignment aPlayedBlockAssignment)
  {
    boolean wasRemoved = false;
    //Unable to remove aPlayedBlockAssignment, as it must always have a block
    if (!this.equals(aPlayedBlockAssignment.getBlock()))
    {
      playedBlockAssignments.remove(aPlayedBlockAssignment);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPlayedBlockAssignmentAt(PlayedBlockAssignment aPlayedBlockAssignment, int index)
  {  
    boolean wasAdded = false;
    if(addPlayedBlockAssignment(aPlayedBlockAssignment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPlayedBlockAssignments()) { index = numberOfPlayedBlockAssignments() - 1; }
      playedBlockAssignments.remove(aPlayedBlockAssignment);
      playedBlockAssignments.add(index, aPlayedBlockAssignment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePlayedBlockAssignmentAt(PlayedBlockAssignment aPlayedBlockAssignment, int index)
  {
    boolean wasAdded = false;
    if(playedBlockAssignments.contains(aPlayedBlockAssignment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPlayedBlockAssignments()) { index = numberOfPlayedBlockAssignments() - 1; }
      playedBlockAssignments.remove(aPlayedBlockAssignment);
      playedBlockAssignments.add(index, aPlayedBlockAssignment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPlayedBlockAssignmentAt(aPlayedBlockAssignment, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setGame(Game aGame)
  {
    boolean wasSet = false;
    if (aGame == null)
    {
      return wasSet;
    }

    Game existingGame = game;
    game = aGame;
    if (existingGame != null && !existingGame.equals(aGame))
    {
      existingGame.removeBlock(this);
    }
    game.addBlock(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBlockAssignments()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public BlockAssignment addBlockAssignment(int aGridHorizontalPosition, int aGridVerticalPosition, Level aLevel, Game aGame)
  {
    return new BlockAssignment(aGridHorizontalPosition, aGridVerticalPosition, aLevel, this, aGame);
  }

  public boolean addBlockAssignment(BlockAssignment aBlockAssignment)
  {
    boolean wasAdded = false;
    if (blockAssignments.contains(aBlockAssignment)) { return false; }
    Block existingBlock = aBlockAssignment.getBlock();
    boolean isNewBlock = existingBlock != null && !this.equals(existingBlock);
    if (isNewBlock)
    {
      aBlockAssignment.setBlock(this);
    }
    else
    {
      blockAssignments.add(aBlockAssignment);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBlockAssignment(BlockAssignment aBlockAssignment)
  {
    boolean wasRemoved = false;
    //Unable to remove aBlockAssignment, as it must always have a block
    if (!this.equals(aBlockAssignment.getBlock()))
    {
      blockAssignments.remove(aBlockAssignment);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBlockAssignmentAt(BlockAssignment aBlockAssignment, int index)
  {  
    boolean wasAdded = false;
    if(addBlockAssignment(aBlockAssignment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBlockAssignments()) { index = numberOfBlockAssignments() - 1; }
      blockAssignments.remove(aBlockAssignment);
      blockAssignments.add(index, aBlockAssignment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBlockAssignmentAt(BlockAssignment aBlockAssignment, int index)
  {
    boolean wasAdded = false;
    if(blockAssignments.contains(aBlockAssignment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBlockAssignments()) { index = numberOfBlockAssignments() - 1; }
      blockAssignments.remove(aBlockAssignment);
      blockAssignments.add(index, aBlockAssignment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBlockAssignmentAt(aBlockAssignment, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=playedBlockAssignments.size(); i > 0; i--)
    {
      PlayedBlockAssignment aPlayedBlockAssignment = playedBlockAssignments.get(i - 1);
      aPlayedBlockAssignment.delete();
    }
    Game placeholderGame = game;
    this.game = null;
    if(placeholderGame != null)
    {
      placeholderGame.removeBlock(this);
    }
    for(int i=blockAssignments.size(); i > 0; i--)
    {
      BlockAssignment aBlockAssignment = blockAssignments.get(i - 1);
      aBlockAssignment.delete();
    }
  }

  // line 49 "../../../../../Block223Persistence.ump"
   public static  void reinitializeAutouniqueID(List<Block> blocks){
    nextId = 0; 
	    for (Block b : blocks) {
	      if (b.getId() > nextId) {
	        nextId = b.getId();
	      }
	    }
	    nextId++;
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "red" + ":" + getRed()+ "," +
            "green" + ":" + getGreen()+ "," +
            "blue" + ":" + getBlue()+ "," +
            "points" + ":" + getPoints()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 47 "../../../../../Block223Persistence.ump"
  private static final long serialVersionUID = 5332292624658907512L ;

  
}