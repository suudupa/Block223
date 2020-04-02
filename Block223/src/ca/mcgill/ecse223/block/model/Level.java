/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.io.Serializable;
import java.util.*;

// line 88 "../../../../../Block223Persistence.ump"
// line 183 "../../../../../Block223.ump"
public class Level implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Level Associations
  private Game game;
  private List<BlockAssignment> blockAssignments;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Level(Game aGame)
  {
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create level due to game");
    }
    blockAssignments = new ArrayList<BlockAssignment>();
  }

  //------------------------
  // INTERFACE
  //------------------------
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
  /* Code from template association_SetOneToAtMostN */
  public boolean setGame(Game aGame)
  {
    boolean wasSet = false;
    //Must provide game to level
    if (aGame == null)
    {
      return wasSet;
    }

    //game already at maximum (99)
    if (aGame.numberOfLevels() >= Game.maximumNumberOfLevels())
    {
      return wasSet;
    }
    
    Game existingGame = game;
    game = aGame;
    if (existingGame != null && !existingGame.equals(aGame))
    {
      boolean didRemove = existingGame.removeLevel(this);
      if (!didRemove)
      {
        game = existingGame;
        return wasSet;
      }
    }
    game.addLevel(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBlockAssignments()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public BlockAssignment addBlockAssignment(int aGridHorizontalPosition, int aGridVerticalPosition, Block aBlock, Game aGame)
  {
    return new BlockAssignment(aGridHorizontalPosition, aGridVerticalPosition, this, aBlock, aGame);
  }

  public boolean addBlockAssignment(BlockAssignment aBlockAssignment)
  {
    boolean wasAdded = false;
    if (blockAssignments.contains(aBlockAssignment)) { return false; }
    Level existingLevel = aBlockAssignment.getLevel();
    boolean isNewLevel = existingLevel != null && !this.equals(existingLevel);
    if (isNewLevel)
    {
      aBlockAssignment.setLevel(this);
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
    //Unable to remove aBlockAssignment, as it must always have a level
    if (!this.equals(aBlockAssignment.getLevel()))
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
    Game placeholderGame = game;
    this.game = null;
    if(placeholderGame != null)
    {
      placeholderGame.removeLevel(this);
    }
    for(int i=blockAssignments.size(); i > 0; i--)
    {
      BlockAssignment aBlockAssignment = blockAssignments.get(i - 1);
      aBlockAssignment.delete();
    }
  }

  // line 187 "../../../../../Block223.ump"
   public void clearBlocks(){
    blockAssignments.clear();
  }

  // line 191 "../../../../../Block223.ump"
   public BlockAssignment findBlockAssignment(int horizontal, int vertical){
    List<BlockAssignment> l = getBlockAssignments();
		for (BlockAssignment b : l) {
			if (b.getGridHorizontalPosition() == horizontal && b.getGridVerticalPosition() == vertical) {
				return b;
			}
		}
		return null;
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 91 "../../../../../Block223Persistence.ump"
  private static final long serialVersionUID = -7956760334392179806L ;

  
}