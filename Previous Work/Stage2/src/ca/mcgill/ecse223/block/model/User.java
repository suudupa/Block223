/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.util.*;

// line 9 "../../../../../Block223.ump"
public class User
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, User> usersByUsername = new HashMap<String, User>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //User Attributes
  private String username;

  //User Associations
  private List<UserRole> roles;
  private Block223 block223;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public User(String aUsername, Block223 aBlock223, UserRole... allRoles)
  {
    if (!setUsername(aUsername))
    {
      throw new RuntimeException("Cannot create due to duplicate username");
    }
    roles = new ArrayList<UserRole>();
    boolean didAddRoles = setRoles(allRoles);
    if (!didAddRoles)
    {
      throw new RuntimeException("Unable to create User, must have 1 to 2 roles");
    }
    boolean didAddBlock223 = setBlock223(aBlock223);
    if (!didAddBlock223)
    {
      throw new RuntimeException("Unable to create user due to block223");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setUsername(String aUsername)
  {
    boolean wasSet = false;
    String anOldUsername = getUsername();
    if (hasWithUsername(aUsername)) {
      return wasSet;
    }
    username = aUsername;
    wasSet = true;
    if (anOldUsername != null) {
      usersByUsername.remove(anOldUsername);
    }
    usersByUsername.put(aUsername, this);
    return wasSet;
  }

  public String getUsername()
  {
    return username;
  }
  /* Code from template attribute_GetUnique */
  public static User getWithUsername(String aUsername)
  {
    return usersByUsername.get(aUsername);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithUsername(String aUsername)
  {
    return getWithUsername(aUsername) != null;
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
  /* Code from template association_GetOne */
  public Block223 getBlock223()
  {
    return block223;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfRoles()
  {
    return 1;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfRoles()
  {
    return 2;
  }
  /* Code from template association_AddUnidirectionalMN */
  public boolean addRole(UserRole aRole)
  {
    boolean wasAdded = false;
    if (roles.contains(aRole)) { return false; }
    if (numberOfRoles() < maximumNumberOfRoles())
    {
      roles.add(aRole);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean removeRole(UserRole aRole)
  {
    boolean wasRemoved = false;
    if (!roles.contains(aRole))
    {
      return wasRemoved;
    }

    if (numberOfRoles() <= minimumNumberOfRoles())
    {
      return wasRemoved;
    }

    roles.remove(aRole);
    wasRemoved = true;
    return wasRemoved;
  }
  /* Code from template association_SetUnidirectionalMN */
  public boolean setRoles(UserRole... newRoles)
  {
    boolean wasSet = false;
    ArrayList<UserRole> verifiedRoles = new ArrayList<UserRole>();
    for (UserRole aRole : newRoles)
    {
      if (verifiedRoles.contains(aRole))
      {
        continue;
      }
      verifiedRoles.add(aRole);
    }

    if (verifiedRoles.size() != newRoles.length || verifiedRoles.size() < minimumNumberOfRoles() || verifiedRoles.size() > maximumNumberOfRoles())
    {
      return wasSet;
    }

    roles.clear();
    roles.addAll(verifiedRoles);
    wasSet = true;
    return wasSet;
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
      existingBlock223.removeUser(this);
    }
    block223.addUser(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    usersByUsername.remove(getUsername());
    roles.clear();
    Block223 placeholderBlock223 = block223;
    this.block223 = null;
    if(placeholderBlock223 != null)
    {
      placeholderBlock223.removeUser(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "username" + ":" + getUsername()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "block223 = "+(getBlock223()!=null?Integer.toHexString(System.identityHashCode(getBlock223())):"null");
  }
}