/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.io.Serializable;
import java.util.*;

// line 65 "../../../../../Block223Persistence.ump"
// line 15 "../../../../../Block223PlayMode.ump"
// line 48 "../../../../../Block223.ump"
public class Game implements Serializable
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  public static final int MIN_NR_LEVELS = 1;

  /**
   * this is somewhat redundant because the max multiplicity is enforced by Umple
   */
  public static final int MAX_NR_LEVELS = 99;

  /**
   * play area is now constant
   */
  public static final int PLAY_AREA_SIDE = 390;
  public static final int WALL_PADDING = 10;
  public static final int COLUMNS_PADDING = 5;
  public static final int ROW_PADDING = 2;
  private static Map<String, Game> gamesByName = new HashMap<String, Game>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Game Attributes
  private boolean published;
  private String name;
  private int nrBlocksPerLevel;
  private transient Comparator<HallOfFameEntry> hallOfFameEntriesPriority;

  //Game Associations
  private HallOfFameEntry mostRecentEntry;
  private Admin admin;
  private List<Block> blocks;
  private List<Level> levels;
  private List<BlockAssignment> blockAssignments;
  private Ball ball;
  private Paddle paddle;
  private List<PlayedGame> playedGames;
  private List<HallOfFameEntry> hallOfFameEntries;
  private Block223 block223;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Game(String aName, int aNrBlocksPerLevel, Admin aAdmin, Ball aBall, Paddle aPaddle, Block223 aBlock223)
  {
    // line 76 "../../../../../Block223.ump"
    if (aBlock223.findGame(aName) != null) {
    		  throw new RuntimeException("The name of a game must be unique.");
    	  } else if (aName == null || aName.length() == 0) {
    		  throw new RuntimeException("The name of a game must be specified.");
    	  }
    // END OF UMPLE BEFORE INJECTION
    published = false;
    nrBlocksPerLevel = aNrBlocksPerLevel;
    hallOfFameEntriesPriority = 
      new Comparator<HallOfFameEntry>(){
        @Override
        public int compare(HallOfFameEntry arg0, HallOfFameEntry arg1)
        {
          return ((Integer)arg0.getScore()).compareTo(
                 ((Integer)arg1.getScore()));
        }
      };
    if (!setName(aName))
    {
      throw new RuntimeException("Cannot create due to duplicate name");
    }
    boolean didAddAdmin = setAdmin(aAdmin);
    if (!didAddAdmin)
    {
      throw new RuntimeException("Unable to create game due to admin");
    }
    blocks = new ArrayList<Block>();
    levels = new ArrayList<Level>();
    blockAssignments = new ArrayList<BlockAssignment>();
    if (aBall == null || aBall.getGame() != null)
    {
      throw new RuntimeException("Unable to create Game due to aBall");
    }
    ball = aBall;
    if (aPaddle == null || aPaddle.getGame() != null)
    {
      throw new RuntimeException("Unable to create Game due to aPaddle");
    }
    paddle = aPaddle;
    playedGames = new ArrayList<PlayedGame>();
    hallOfFameEntries = new ArrayList<HallOfFameEntry>();
    boolean didAddBlock223 = setBlock223(aBlock223);
    if (!didAddBlock223)
    {
      throw new RuntimeException("Unable to create game due to block223");
    }
  }

  public Game(String aName, int aNrBlocksPerLevel, Admin aAdmin, int aMinBallSpeedXForBall, int aMinBallSpeedYForBall, double aBallSpeedIncreaseFactorForBall, int aMaxPaddleLengthForPaddle, int aMinPaddleLengthForPaddle, Block223 aBlock223)
  {
    // line 76 "../../../../../Block223.ump"
    if (aBlock223.findGame(aName) != null) {
    		  throw new RuntimeException("The name of a game must be unique.");
    	  } else if (aName == null || aName.length() == 0) {
    		  throw new RuntimeException("The name of a game must be specified.");
    	  }
    // END OF UMPLE BEFORE INJECTION
    published = false;
    name = aName;
    nrBlocksPerLevel = aNrBlocksPerLevel;
    hallOfFameEntriesPriority = 
      new Comparator<HallOfFameEntry>(){
        @Override
        public int compare(HallOfFameEntry arg0, HallOfFameEntry arg1)
        {
          return ((Integer)arg0.getScore()).compareTo(
                 ((Integer)arg1.getScore()));
        }
      };
    boolean didAddAdmin = setAdmin(aAdmin);
    if (!didAddAdmin)
    {
      throw new RuntimeException("Unable to create game due to admin");
    }
    blocks = new ArrayList<Block>();
    levels = new ArrayList<Level>();
    blockAssignments = new ArrayList<BlockAssignment>();
    ball = new Ball(aMinBallSpeedXForBall, aMinBallSpeedYForBall, aBallSpeedIncreaseFactorForBall, this);
    paddle = new Paddle(aMaxPaddleLengthForPaddle, aMinPaddleLengthForPaddle, this);
    playedGames = new ArrayList<PlayedGame>();
    hallOfFameEntries = new ArrayList<HallOfFameEntry>();
    boolean didAddBlock223 = setBlock223(aBlock223);
    if (!didAddBlock223)
    {
      throw new RuntimeException("Unable to create game due to block223");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPublished(boolean aPublished)
  {
    boolean wasSet = false;
    published = aPublished;
    wasSet = true;
    return wasSet;
  }

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    // line 95 "../../../../../Block223.ump"
    if (aName == null || aName.length() == 0) {
           throw new RuntimeException("The name of a game must be specified.");
         }
    // END OF UMPLE BEFORE INJECTION
    String anOldName = getName();
    if (hasWithName(aName)) {
      return wasSet;
    }
    name = aName;
    wasSet = true;
    if (anOldName != null) {
      gamesByName.remove(anOldName);
    }
    gamesByName.put(aName, this);
    return wasSet;
  }

  public boolean setNrBlocksPerLevel(int aNrBlocksPerLevel)
  {
    boolean wasSet = false;
    // line 84 "../../../../../Block223.ump"
    if (aNrBlocksPerLevel <= 0) {
    			throw new RuntimeException("The number of blocks per level must be greater than zero.");
    		}
    		for (Level l : levels) {
    			if (l.getBlockAssignments().size() > aNrBlocksPerLevel) {
    				l.clearBlocks();
    			}
    		}
    // END OF UMPLE BEFORE INJECTION
    nrBlocksPerLevel = aNrBlocksPerLevel;
    wasSet = true;
    return wasSet;
  }

  public boolean setHallOfFameEntriesPriority(Comparator<HallOfFameEntry> aHallOfFameEntriesPriority)
  {
    boolean wasSet = false;
    hallOfFameEntriesPriority = aHallOfFameEntriesPriority;
    wasSet = true;
    return wasSet;
  }

  public boolean getPublished()
  {
    return published;
  }

  public String getName()
  {
    return name;
  }
  /* Code from template attribute_GetUnique */
  public static Game getWithName(String aName)
  {
    return gamesByName.get(aName);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithName(String aName)
  {
    return getWithName(aName) != null;
  }

  public int getNrBlocksPerLevel()
  {
    return nrBlocksPerLevel;
  }

  public Comparator<HallOfFameEntry> getHallOfFameEntriesPriority()
  {
    return hallOfFameEntriesPriority;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isPublished()
  {
    return published;
  }
  /* Code from template association_GetOne */
  public HallOfFameEntry getMostRecentEntry()
  {
    return mostRecentEntry;
  }

  public boolean hasMostRecentEntry()
  {
    boolean has = mostRecentEntry != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Admin getAdmin()
  {
    return admin;
  }
  /* Code from template association_GetMany */
  public Block getBlock(int index)
  {
    Block aBlock = blocks.get(index);
    return aBlock;
  }

  public List<Block> getBlocks()
  {
    List<Block> newBlocks = Collections.unmodifiableList(blocks);
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

  public int indexOfBlock(Block aBlock)
  {
    int index = blocks.indexOf(aBlock);
    return index;
  }
  /* Code from template association_GetMany */
  public Level getLevel(int index)
  {
    // line 120 "../../../../../Block223.ump"
    if (index < 0 || index >= numberOfLevels()) {
          		throw new IndexOutOfBoundsException("Level " + (index + 1) + " does not exist for the game.");
          	}
    // END OF UMPLE BEFORE INJECTION
    Level aLevel = levels.get(index);
    return aLevel;
  }

  public List<Level> getLevels()
  {
    List<Level> newLevels = Collections.unmodifiableList(levels);
    return newLevels;
  }

  public int numberOfLevels()
  {
    int number = levels.size();
    return number;
  }

  public boolean hasLevels()
  {
    boolean has = levels.size() > 0;
    return has;
  }

  public int indexOfLevel(Level aLevel)
  {
    int index = levels.indexOf(aLevel);
    return index;
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
  /* Code from template association_GetOne */
  public Ball getBall()
  {
    return ball;
  }
  /* Code from template association_GetOne */
  public Paddle getPaddle()
  {
    return paddle;
  }
  /* Code from template association_GetMany */
  public PlayedGame getPlayedGame(int index)
  {
    PlayedGame aPlayedGame = playedGames.get(index);
    return aPlayedGame;
  }

  public List<PlayedGame> getPlayedGames()
  {
    List<PlayedGame> newPlayedGames = Collections.unmodifiableList(playedGames);
    return newPlayedGames;
  }

  public int numberOfPlayedGames()
  {
    int number = playedGames.size();
    return number;
  }

  public boolean hasPlayedGames()
  {
    boolean has = playedGames.size() > 0;
    return has;
  }

  public int indexOfPlayedGame(PlayedGame aPlayedGame)
  {
    int index = playedGames.indexOf(aPlayedGame);
    return index;
  }
  /* Code from template association_GetMany */
  public HallOfFameEntry getHallOfFameEntry(int index)
  {
    HallOfFameEntry aHallOfFameEntry = hallOfFameEntries.get(index);
    return aHallOfFameEntry;
  }

  public List<HallOfFameEntry> getHallOfFameEntries()
  {
    List<HallOfFameEntry> newHallOfFameEntries = Collections.unmodifiableList(hallOfFameEntries);
    return newHallOfFameEntries;
  }

  public int numberOfHallOfFameEntries()
  {
    int number = hallOfFameEntries.size();
    return number;
  }

  public boolean hasHallOfFameEntries()
  {
    boolean has = hallOfFameEntries.size() > 0;
    return has;
  }

  public int indexOfHallOfFameEntry(HallOfFameEntry aHallOfFameEntry)
  {
    int index = hallOfFameEntries.indexOf(aHallOfFameEntry);
    return index;
  }
  /* Code from template association_GetOne */
  public Block223 getBlock223()
  {
    return block223;
  }
  /* Code from template association_SetUnidirectionalOptionalOne */
  public boolean setMostRecentEntry(HallOfFameEntry aNewMostRecentEntry)
  {
    boolean wasSet = false;
    mostRecentEntry = aNewMostRecentEntry;
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setAdmin(Admin aAdmin)
  {
    boolean wasSet = false;
    if (aAdmin == null)
    {
      return wasSet;
    }

    Admin existingAdmin = admin;
    admin = aAdmin;
    if (existingAdmin != null && !existingAdmin.equals(aAdmin))
    {
      existingAdmin.removeGame(this);
    }
    admin.addGame(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBlocks()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Block addBlock(int aRed, int aGreen, int aBlue, int aPoints)
  {
    return new Block(aRed, aGreen, aBlue, aPoints, this);
  }

  public boolean addBlock(Block aBlock)
  {
    boolean wasAdded = false;
    if (blocks.contains(aBlock)) { return false; }
    Game existingGame = aBlock.getGame();
    boolean isNewGame = existingGame != null && !this.equals(existingGame);
    if (isNewGame)
    {
      aBlock.setGame(this);
    }
    else
    {
      blocks.add(aBlock);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBlock(Block aBlock)
  {
    boolean wasRemoved = false;
    //Unable to remove aBlock, as it must always have a game
    if (!this.equals(aBlock.getGame()))
    {
      blocks.remove(aBlock);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBlockAt(Block aBlock, int index)
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

  public boolean addOrMoveBlockAt(Block aBlock, int index)
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
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfLevelsValid()
  {
    boolean isValid = numberOfLevels() >= minimumNumberOfLevels() && numberOfLevels() <= maximumNumberOfLevels();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfLevels()
  {
    return 1;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfLevels()
  {
    return 99;
  }
  /* Code from template association_AddMNToOnlyOne */
  public Level addLevel()
  {
    if (numberOfLevels() >= maximumNumberOfLevels())
    {
      return null;
    }
    else
    {
      return new Level(this);
    }
  }

  public boolean addLevel(Level aLevel)
  {
    boolean wasAdded = false;
    if (levels.contains(aLevel)) { return false; }
    if (numberOfLevels() >= maximumNumberOfLevels())
    {
      return wasAdded;
    }

    Game existingGame = aLevel.getGame();
    boolean isNewGame = existingGame != null && !this.equals(existingGame);

    if (isNewGame && existingGame.numberOfLevels() <= minimumNumberOfLevels())
    {
      return wasAdded;
    }

    if (isNewGame)
    {
      aLevel.setGame(this);
    }
    else
    {
      levels.add(aLevel);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeLevel(Level aLevel)
  {
    boolean wasRemoved = false;
    //Unable to remove aLevel, as it must always have a game
    if (this.equals(aLevel.getGame()))
    {
      return wasRemoved;
    }

    //game already at minimum (1)
    if (numberOfLevels() <= minimumNumberOfLevels())
    {
      return wasRemoved;
    }
    levels.remove(aLevel);
    wasRemoved = true;
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addLevelAt(Level aLevel, int index)
  {  
    boolean wasAdded = false;
    if(addLevel(aLevel))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLevels()) { index = numberOfLevels() - 1; }
      levels.remove(aLevel);
      levels.add(index, aLevel);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveLevelAt(Level aLevel, int index)
  {
    boolean wasAdded = false;
    if(levels.contains(aLevel))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLevels()) { index = numberOfLevels() - 1; }
      levels.remove(aLevel);
      levels.add(index, aLevel);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addLevelAt(aLevel, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBlockAssignments()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public BlockAssignment addBlockAssignment(int aGridHorizontalPosition, int aGridVerticalPosition, Level aLevel, Block aBlock)
  {
    return new BlockAssignment(aGridHorizontalPosition, aGridVerticalPosition, aLevel, aBlock, this);
  }

  public boolean addBlockAssignment(BlockAssignment aBlockAssignment)
  {
    boolean wasAdded = false;
    if (blockAssignments.contains(aBlockAssignment)) { return false; }
    Game existingGame = aBlockAssignment.getGame();
    boolean isNewGame = existingGame != null && !this.equals(existingGame);
    if (isNewGame)
    {
      aBlockAssignment.setGame(this);
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
    //Unable to remove aBlockAssignment, as it must always have a game
    if (!this.equals(aBlockAssignment.getGame()))
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPlayedGames()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public PlayedGame addPlayedGame(String aPlayername, Block223 aBlock223)
  {
    return new PlayedGame(aPlayername, this, aBlock223);
  }

  public boolean addPlayedGame(PlayedGame aPlayedGame)
  {
    boolean wasAdded = false;
    if (playedGames.contains(aPlayedGame)) { return false; }
    Game existingGame = aPlayedGame.getGame();
    boolean isNewGame = existingGame != null && !this.equals(existingGame);
    if (isNewGame)
    {
      aPlayedGame.setGame(this);
    }
    else
    {
      playedGames.add(aPlayedGame);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePlayedGame(PlayedGame aPlayedGame)
  {
    boolean wasRemoved = false;
    //Unable to remove aPlayedGame, as it must always have a game
    if (!this.equals(aPlayedGame.getGame()))
    {
      playedGames.remove(aPlayedGame);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPlayedGameAt(PlayedGame aPlayedGame, int index)
  {  
    boolean wasAdded = false;
    if(addPlayedGame(aPlayedGame))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPlayedGames()) { index = numberOfPlayedGames() - 1; }
      playedGames.remove(aPlayedGame);
      playedGames.add(index, aPlayedGame);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePlayedGameAt(PlayedGame aPlayedGame, int index)
  {
    boolean wasAdded = false;
    if(playedGames.contains(aPlayedGame))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPlayedGames()) { index = numberOfPlayedGames() - 1; }
      playedGames.remove(aPlayedGame);
      playedGames.add(index, aPlayedGame);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPlayedGameAt(aPlayedGame, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfHallOfFameEntries()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public HallOfFameEntry addHallOfFameEntry(int aScore, String aPlayername, Player aPlayer, Block223 aBlock223)
  {
    return new HallOfFameEntry(aScore, aPlayername, aPlayer, this, aBlock223);
  }

  public boolean addHallOfFameEntry(HallOfFameEntry aHallOfFameEntry)
  {
    boolean wasAdded = false;
    if (hallOfFameEntries.contains(aHallOfFameEntry)) { return false; }
    Game existingGame = aHallOfFameEntry.getGame();
    boolean isNewGame = existingGame != null && !this.equals(existingGame);
    if (isNewGame)
    {
      aHallOfFameEntry.setGame(this);
    }
    else
    {
      hallOfFameEntries.add(aHallOfFameEntry);
    }
    wasAdded = true;
    if(wasAdded)
        Collections.sort(hallOfFameEntries, hallOfFameEntriesPriority);
    
    return wasAdded;
  }

  public boolean removeHallOfFameEntry(HallOfFameEntry aHallOfFameEntry)
  {
    boolean wasRemoved = false;
    //Unable to remove aHallOfFameEntry, as it must always have a game
    if (!this.equals(aHallOfFameEntry.getGame()))
    {
      hallOfFameEntries.remove(aHallOfFameEntry);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  /* Code from template association_SetOneToMany */
  public boolean setBlock223(Block223 aBlock223)
  {
    boolean wasSet = false;
    if (aBlock223 == null)
    {
      return wasSet;
    }

    Block223 existingBlock223 = block223;
    block223 = aBlock223;
    if (existingBlock223 != null && !existingBlock223.equals(aBlock223))
    {
      existingBlock223.removeGame(this);
    }
    block223.addGame(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    gamesByName.remove(getName());
    mostRecentEntry = null;
    Admin placeholderAdmin = admin;
    this.admin = null;
    if(placeholderAdmin != null)
    {
      placeholderAdmin.removeGame(this);
    }
    while (blocks.size() > 0)
    {
      Block aBlock = blocks.get(blocks.size() - 1);
      aBlock.delete();
      blocks.remove(aBlock);
    }
    
    while (levels.size() > 0)
    {
      Level aLevel = levels.get(levels.size() - 1);
      aLevel.delete();
      levels.remove(aLevel);
    }
    
    while (blockAssignments.size() > 0)
    {
      BlockAssignment aBlockAssignment = blockAssignments.get(blockAssignments.size() - 1);
      aBlockAssignment.delete();
      blockAssignments.remove(aBlockAssignment);
    }
    
    Ball existingBall = ball;
    ball = null;
    if (existingBall != null)
    {
      existingBall.delete();
    }
    Paddle existingPaddle = paddle;
    paddle = null;
    if (existingPaddle != null)
    {
      existingPaddle.delete();
    }
    for(int i=playedGames.size(); i > 0; i--)
    {
      PlayedGame aPlayedGame = playedGames.get(i - 1);
      aPlayedGame.delete();
    }
    for(int i=hallOfFameEntries.size(); i > 0; i--)
    {
      HallOfFameEntry aHallOfFameEntry = hallOfFameEntries.get(i - 1);
      aHallOfFameEntry.delete();
    }
    Block223 placeholderBlock223 = block223;
    this.block223 = null;
    if(placeholderBlock223 != null)
    {
      placeholderBlock223.removeGame(this);
    }
  }

  // line 71 "../../../../../Block223Persistence.ump"
   public static  void reinitializeUniqueName(List<Game> games){
    gamesByName = new HashMap<String, Game>();
	  for (Game g : games) {
		  gamesByName.put(g.name, g);
		  g. hallOfFameEntriesPriority = 
      new Comparator<HallOfFameEntry>(){
        @Override
        public int compare(HallOfFameEntry arg0, HallOfFameEntry arg1)
        {
          return ((Integer)arg0.getScore()).compareTo(
                 ((Integer)arg1.getScore()));
        }
      };
	  }
  }

  // line 66 "../../../../../Block223.ump"
   public int getMaxNumberHorizontalBlocks(){
    int maxNumberOfHorizontalBlocks = (PLAY_AREA_SIDE - 2*WALL_PADDING - Block.SIZE) / (Block.SIZE + COLUMNS_PADDING) + 1;
	  	return maxNumberOfHorizontalBlocks;
  }

  // line 71 "../../../../../Block223.ump"
   public int getMaxNumberVerticalBlocks(){
    int maxNumberOfVerticalBlocks = (PLAY_AREA_SIDE - WALL_PADDING - Block.SIZE - Paddle.PADDLE_WIDTH - Paddle.VERTICAL_DISTANCE - Ball.BALL_DIAMETER) / (Block.SIZE + ROW_PADDING) + 1;
	  	return maxNumberOfVerticalBlocks;
  }

  // line 101 "../../../../../Block223.ump"
   public Block findBlock(int id){
    List<Block> blocks = getBlocks();
	  for (Block block : blocks) {
		  int blockId = block.getId();
		  if (blockId == id) {
			  return block;
		  }
	  }
	  return null;
  }

  // line 111 "../../../../../Block223.ump"
   public Block getBlockWithColor(int red, int green, int blue){
    for (Block b : blocks) {
		  if (b.getRed() == red && b.getGreen() == green && b.getBlue() == blue) {
			  return b;
		  }
	  }
	  return null;
  }

  // line 125 "../../../../../Block223.ump"
   public Block getRandomBlock(){
    return blocks.get((int)(Math.random() * blocks.size()));
  }


  public String toString()
  {
    return super.toString() + "["+
            "published" + ":" + getPublished()+ "," +
            "name" + ":" + getName()+ "," +
            "nrBlocksPerLevel" + ":" + getNrBlocksPerLevel()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "hallOfFameEntriesPriority" + "=" + (getHallOfFameEntriesPriority() != null ? !getHallOfFameEntriesPriority().equals(this)  ? getHallOfFameEntriesPriority().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "mostRecentEntry = "+(getMostRecentEntry()!=null?Integer.toHexString(System.identityHashCode(getMostRecentEntry())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "admin = "+(getAdmin()!=null?Integer.toHexString(System.identityHashCode(getAdmin())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "ball = "+(getBall()!=null?Integer.toHexString(System.identityHashCode(getBall())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "paddle = "+(getPaddle()!=null?Integer.toHexString(System.identityHashCode(getPaddle())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "block223 = "+(getBlock223()!=null?Integer.toHexString(System.identityHashCode(getBlock223())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 68 "../../../../../Block223Persistence.ump"
  private static final long serialVersionUID = -210105651472293481L ;

  
}