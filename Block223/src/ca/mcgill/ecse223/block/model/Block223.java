/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.io.Serializable;
import java.util.*;

// line 3 "../../../../../Block223Persistence.ump"
// line 1 "../../../../../Block223PlayMode.ump"
// line 7 "../../../../../Block223.ump"
public class Block223 implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Block223 Associations
  private List<PlayedGame> playedGames;
  private List<HallOfFameEntry> entries;
  private List<User> users;
  private List<UserRole> roles;
  private List<Game> games;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Block223()
  {
    playedGames = new ArrayList<PlayedGame>();
    entries = new ArrayList<HallOfFameEntry>();
    users = new ArrayList<User>();
    roles = new ArrayList<UserRole>();
    games = new ArrayList<Game>();
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
  public HallOfFameEntry getEntry(int index)
  {
    HallOfFameEntry aEntry = entries.get(index);
    return aEntry;
  }

  public List<HallOfFameEntry> getEntries()
  {
    List<HallOfFameEntry> newEntries = Collections.unmodifiableList(entries);
    return newEntries;
  }

  public int numberOfEntries()
  {
    int number = entries.size();
    return number;
  }

  public boolean hasEntries()
  {
    boolean has = entries.size() > 0;
    return has;
  }

  public int indexOfEntry(HallOfFameEntry aEntry)
  {
    int index = entries.indexOf(aEntry);
    return index;
  }
  /* Code from template association_GetMany */
  public User getUser(int index)
  {
    User aUser = users.get(index);
    return aUser;
  }

  public List<User> getUsers()
  {
    List<User> newUsers = Collections.unmodifiableList(users);
    return newUsers;
  }

  public int numberOfUsers()
  {
    int number = users.size();
    return number;
  }

  public boolean hasUsers()
  {
    boolean has = users.size() > 0;
    return has;
  }

  public int indexOfUser(User aUser)
  {
    int index = users.indexOf(aUser);
    return index;
  }
  /* Code from template association_GetMany */
  public UserRole getRole(int index)
  {
    UserRole aRole = roles.get(index);
    return aRole;
  }

  public List<UserRole> getRoles()
  {
    List<UserRole> newRoles = Collections.unmodifiableList(roles);
    return newRoles;
  }

  public int numberOfRoles()
  {
    int number = roles.size();
    return number;
  }

  public boolean hasRoles()
  {
    boolean has = roles.size() > 0;
    return has;
  }

  public int indexOfRole(UserRole aRole)
  {
    int index = roles.indexOf(aRole);
    return index;
  }
  /* Code from template association_GetMany */
  public Game getGame(int index)
  {
    Game aGame = games.get(index);
    return aGame;
  }

  public List<Game> getGames()
  {
    List<Game> newGames = Collections.unmodifiableList(games);
    return newGames;
  }

  public int numberOfGames()
  {
    int number = games.size();
    return number;
  }

  public boolean hasGames()
  {
    boolean has = games.size() > 0;
    return has;
  }

  public int indexOfGame(Game aGame)
  {
    int index = games.indexOf(aGame);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPlayedGames()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public PlayedGame addPlayedGame(String aPlayername, Game aGame)
  {
    return new PlayedGame(aPlayername, aGame, this);
  }

  public boolean addPlayedGame(PlayedGame aPlayedGame)
  {
    boolean wasAdded = false;
    if (playedGames.contains(aPlayedGame)) { return false; }
    Block223 existingBlock223 = aPlayedGame.getBlock223();
    boolean isNewBlock223 = existingBlock223 != null && !this.equals(existingBlock223);
    if (isNewBlock223)
    {
      aPlayedGame.setBlock223(this);
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
    //Unable to remove aPlayedGame, as it must always have a block223
    if (!this.equals(aPlayedGame.getBlock223()))
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
  public static int minimumNumberOfEntries()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public HallOfFameEntry addEntry(int aScore, String aPlayername, Player aPlayer, Game aGame)
  {
    return new HallOfFameEntry(aScore, aPlayername, aPlayer, aGame, this);
  }

  public boolean addEntry(HallOfFameEntry aEntry)
  {
    boolean wasAdded = false;
    if (entries.contains(aEntry)) { return false; }
    Block223 existingBlock223 = aEntry.getBlock223();
    boolean isNewBlock223 = existingBlock223 != null && !this.equals(existingBlock223);
    if (isNewBlock223)
    {
      aEntry.setBlock223(this);
    }
    else
    {
      entries.add(aEntry);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeEntry(HallOfFameEntry aEntry)
  {
    boolean wasRemoved = false;
    //Unable to remove aEntry, as it must always have a block223
    if (!this.equals(aEntry.getBlock223()))
    {
      entries.remove(aEntry);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addEntryAt(HallOfFameEntry aEntry, int index)
  {  
    boolean wasAdded = false;
    if(addEntry(aEntry))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEntries()) { index = numberOfEntries() - 1; }
      entries.remove(aEntry);
      entries.add(index, aEntry);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveEntryAt(HallOfFameEntry aEntry, int index)
  {
    boolean wasAdded = false;
    if(entries.contains(aEntry))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEntries()) { index = numberOfEntries() - 1; }
      entries.remove(aEntry);
      entries.add(index, aEntry);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addEntryAt(aEntry, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfUsers()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public User addUser(String aUsername, UserRole... allRoles)
  {
    return new User(aUsername, this, allRoles);
  }

  public boolean addUser(User aUser)
  {
    boolean wasAdded = false;
    if (users.contains(aUser)) { return false; }
    Block223 existingBlock223 = aUser.getBlock223();
    boolean isNewBlock223 = existingBlock223 != null && !this.equals(existingBlock223);
    if (isNewBlock223)
    {
      aUser.setBlock223(this);
    }
    else
    {
      users.add(aUser);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeUser(User aUser)
  {
    boolean wasRemoved = false;
    //Unable to remove aUser, as it must always have a block223
    if (!this.equals(aUser.getBlock223()))
    {
      users.remove(aUser);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addUserAt(User aUser, int index)
  {  
    boolean wasAdded = false;
    if(addUser(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUsers()) { index = numberOfUsers() - 1; }
      users.remove(aUser);
      users.add(index, aUser);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveUserAt(User aUser, int index)
  {
    boolean wasAdded = false;
    if(users.contains(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUsers()) { index = numberOfUsers() - 1; }
      users.remove(aUser);
      users.add(index, aUser);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addUserAt(aUser, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfRoles()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */


  public boolean addRole(UserRole aRole)
  {
    boolean wasAdded = false;
    if (roles.contains(aRole)) { return false; }
    Block223 existingBlock223 = aRole.getBlock223();
    boolean isNewBlock223 = existingBlock223 != null && !this.equals(existingBlock223);
    if (isNewBlock223)
    {
      aRole.setBlock223(this);
    }
    else
    {
      roles.add(aRole);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeRole(UserRole aRole)
  {
    boolean wasRemoved = false;
    //Unable to remove aRole, as it must always have a block223
    if (!this.equals(aRole.getBlock223()))
    {
      roles.remove(aRole);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addRoleAt(UserRole aRole, int index)
  {  
    boolean wasAdded = false;
    if(addRole(aRole))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRoles()) { index = numberOfRoles() - 1; }
      roles.remove(aRole);
      roles.add(index, aRole);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveRoleAt(UserRole aRole, int index)
  {
    boolean wasAdded = false;
    if(roles.contains(aRole))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRoles()) { index = numberOfRoles() - 1; }
      roles.remove(aRole);
      roles.add(index, aRole);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addRoleAt(aRole, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfGames()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Game addGame(String aName, int aNrBlocksPerLevel, Admin aAdmin, Ball aBall, Paddle aPaddle)
  {
    return new Game(aName, aNrBlocksPerLevel, aAdmin, aBall, aPaddle, this);
  }

  public boolean addGame(Game aGame)
  {
    boolean wasAdded = false;
    if (games.contains(aGame)) { return false; }
    Block223 existingBlock223 = aGame.getBlock223();
    boolean isNewBlock223 = existingBlock223 != null && !this.equals(existingBlock223);
    if (isNewBlock223)
    {
      aGame.setBlock223(this);
    }
    else
    {
      games.add(aGame);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeGame(Game aGame)
  {
    boolean wasRemoved = false;
    //Unable to remove aGame, as it must always have a block223
    if (!this.equals(aGame.getBlock223()))
    {
      games.remove(aGame);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addGameAt(Game aGame, int index)
  {  
    boolean wasAdded = false;
    if(addGame(aGame))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGames()) { index = numberOfGames() - 1; }
      games.remove(aGame);
      games.add(index, aGame);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveGameAt(Game aGame, int index)
  {
    boolean wasAdded = false;
    if(games.contains(aGame))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGames()) { index = numberOfGames() - 1; }
      games.remove(aGame);
      games.add(index, aGame);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addGameAt(aGame, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (playedGames.size() > 0)
    {
      PlayedGame aPlayedGame = playedGames.get(playedGames.size() - 1);
      aPlayedGame.delete();
      playedGames.remove(aPlayedGame);
    }
    
    while (entries.size() > 0)
    {
      HallOfFameEntry aEntry = entries.get(entries.size() - 1);
      aEntry.delete();
      entries.remove(aEntry);
    }
    
    while (users.size() > 0)
    {
      User aUser = users.get(users.size() - 1);
      aUser.delete();
      users.remove(aUser);
    }
    
    while (roles.size() > 0)
    {
      UserRole aRole = roles.get(roles.size() - 1);
      aRole.delete();
      roles.remove(aRole);
    }
    
    while (games.size() > 0)
    {
      Game aGame = games.get(games.size() - 1);
      aGame.delete();
      games.remove(aGame);
    }
    
  }

  // line 11 "../../../../../Block223Persistence.ump"
   public void reinitialize(){
    User.reinitializeUniqueUsername(this.getUsers());
		Game.reinitializeUniqueName(this.getGames());
		List<Block> blocks = new ArrayList<Block>();
		for (Game g : this.getGames()) {
			for (Block b : g.getBlocks()) {
				blocks.add(b);
			}
		}
		Block.reinitializeAutouniqueID(blocks);
		List<PlayedGame> savedGames = new ArrayList<PlayedGame>();
		for (UserRole u : getRoles()) {
			if (u instanceof Player) {
			  for (PlayedGame g : ((Player)u).getPlayedGames()) {
				  savedGames.add(g);
			  }
			}
		}
		PlayedGame.reinitializeAutouniqueID(savedGames);
  }

  // line 6 "../../../../../Block223PlayMode.ump"
   public PlayedGame findPlayableGame(int id){
    for (PlayedGame g : playedGames) {
		  if (g.getId() == id) {
			  return g;
		  }
	  }
	  return null;
  }

  // line 13 "../../../../../Block223.ump"
   public Game findGame(String name){
    for (Game g : games) {
		  if (g.getName().equals(name)) {
			  return g;
		  }
	  }
	  return null;
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 7 "../../../../../Block223Persistence.ump"
  private static final long serialVersionUID = 6181302407834705923L ;

  
}