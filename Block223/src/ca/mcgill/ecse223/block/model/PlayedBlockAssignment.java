/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.io.Serializable;

// line 142 "../../../../../Block223Persistence.ump"
// line 63 "../../../../../Block223PlayMode.ump"
public class PlayedBlockAssignment implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //PlayedBlockAssignment Attributes
  private int x;
  private int y;

  //PlayedBlockAssignment Associations
  private Block block;
  private PlayedGame playedGame;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public PlayedBlockAssignment(int aX, int aY, Block aBlock, PlayedGame aPlayedGame)
  {
    x = aX;
    y = aY;
    boolean didAddBlock = setBlock(aBlock);
    if (!didAddBlock)
    {
      throw new RuntimeException("Unable to create playedBlockAssignment due to block");
    }
    boolean didAddPlayedGame = setPlayedGame(aPlayedGame);
    if (!didAddPlayedGame)
    {
      throw new RuntimeException("Unable to create block due to playedGame");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

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

  /**
   * the position of a block is at its top left corner
   */
  public int getX()
  {
    return x;
  }

  public int getY()
  {
    return y;
  }
  /* Code from template association_GetOne */
  public Block getBlock()
  {
    return block;
  }
  /* Code from template association_GetOne */
  public PlayedGame getPlayedGame()
  {
    return playedGame;
  }
  /* Code from template association_SetOneToMany */
  public boolean setBlock(Block aBlock)
  {
    boolean wasSet = false;
    if (aBlock == null)
    {
      return wasSet;
    }

    Block existingBlock = block;
    block = aBlock;
    if (existingBlock != null && !existingBlock.equals(aBlock))
    {
      existingBlock.removePlayedBlockAssignment(this);
    }
    block.addPlayedBlockAssignment(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setPlayedGame(PlayedGame aPlayedGame)
  {
    boolean wasSet = false;
    if (aPlayedGame == null)
    {
      return wasSet;
    }

    PlayedGame existingPlayedGame = playedGame;
    playedGame = aPlayedGame;
    if (existingPlayedGame != null && !existingPlayedGame.equals(aPlayedGame))
    {
      existingPlayedGame.removeBlock(this);
    }
    playedGame.addBlock(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Block placeholderBlock = block;
    this.block = null;
    if(placeholderBlock != null)
    {
      placeholderBlock.removePlayedBlockAssignment(this);
    }
    PlayedGame placeholderPlayedGame = playedGame;
    this.playedGame = null;
    if(placeholderPlayedGame != null)
    {
      placeholderPlayedGame.removeBlock(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "x" + ":" + getX()+ "," +
            "y" + ":" + getY()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "block = "+(getBlock()!=null?Integer.toHexString(System.identityHashCode(getBlock())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "playedGame = "+(getPlayedGame()!=null?Integer.toHexString(System.identityHashCode(getPlayedGame())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 145 "../../../../../Block223Persistence.ump"
  private static final long serialVersionUID = 939001747760934442L ;

  
}