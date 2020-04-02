/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.io.Serializable;
import java.util.*;

// line 100 "../../../../../Block223Persistence.ump"
// line 44 "../../../../../Block223.ump"
public class Player extends UserRole implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Player Associations
  private List<PlayedGame> playedGames;
  private List<HallOfFameEntry> hallOfFameEntries;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Player(String aPassword, Block223 aBlock223)
  {
    super(aPassword, aBlock223);
    playedGames = new ArrayList<PlayedGame>();
    hallOfFameEntries = new ArrayList<HallOfFameEntry>();
  }

  //------------------------
  // INTERFACE
  //------------------------
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPlayedGames()
  {
    return 0;
  }
  /* Code from template association_AddManyToOptionalOne */
  public boolean addPlayedGame(PlayedGame aPlayedGame)
  {
    boolean wasAdded = false;
    if (playedGames.contains(aPlayedGame)) { return false; }
    Player existingPlayer = aPlayedGame.getPlayer();
    if (existingPlayer == null)
    {
      aPlayedGame.setPlayer(this);
    }
    else if (!this.equals(existingPlayer))
    {
      existingPlayer.removePlayedGame(aPlayedGame);
      addPlayedGame(aPlayedGame);
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
    if (playedGames.contains(aPlayedGame))
    {
      playedGames.remove(aPlayedGame);
      aPlayedGame.setPlayer(null);
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
  public HallOfFameEntry addHallOfFameEntry(int aScore, String aPlayername, Game aGame, Block223 aBlock223)
  {
    return new HallOfFameEntry(aScore, aPlayername, this, aGame, aBlock223);
  }

  public boolean addHallOfFameEntry(HallOfFameEntry aHallOfFameEntry)
  {
    boolean wasAdded = false;
    if (hallOfFameEntries.contains(aHallOfFameEntry)) { return false; }
    Player existingPlayer = aHallOfFameEntry.getPlayer();
    boolean isNewPlayer = existingPlayer != null && !this.equals(existingPlayer);
    if (isNewPlayer)
    {
      aHallOfFameEntry.setPlayer(this);
    }
    else
    {
      hallOfFameEntries.add(aHallOfFameEntry);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeHallOfFameEntry(HallOfFameEntry aHallOfFameEntry)
  {
    boolean wasRemoved = false;
    //Unable to remove aHallOfFameEntry, as it must always have a player
    if (!this.equals(aHallOfFameEntry.getPlayer()))
    {
      hallOfFameEntries.remove(aHallOfFameEntry);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addHallOfFameEntryAt(HallOfFameEntry aHallOfFameEntry, int index)
  {  
    boolean wasAdded = false;
    if(addHallOfFameEntry(aHallOfFameEntry))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfHallOfFameEntries()) { index = numberOfHallOfFameEntries() - 1; }
      hallOfFameEntries.remove(aHallOfFameEntry);
      hallOfFameEntries.add(index, aHallOfFameEntry);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveHallOfFameEntryAt(HallOfFameEntry aHallOfFameEntry, int index)
  {
    boolean wasAdded = false;
    if(hallOfFameEntries.contains(aHallOfFameEntry))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfHallOfFameEntries()) { index = numberOfHallOfFameEntries() - 1; }
      hallOfFameEntries.remove(aHallOfFameEntry);
      hallOfFameEntries.add(index, aHallOfFameEntry);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addHallOfFameEntryAt(aHallOfFameEntry, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while( !playedGames.isEmpty() )
    {
      playedGames.get(0).setPlayer(null);
    }
    for(int i=hallOfFameEntries.size(); i > 0; i--)
    {
      HallOfFameEntry aHallOfFameEntry = hallOfFameEntries.get(i - 1);
      aHallOfFameEntry.delete();
    }
    super.delete();
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 103 "../../../../../Block223Persistence.ump"
  private static final long serialVersionUID = 4495546738870249064L ;

  
}