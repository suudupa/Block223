/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.controller;

// line 32 "../../../../../Block223TransferObjects.ump"
public class TOUserMode
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum Mode { None, Design, Play }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOUserMode Attributes
  private Mode mode;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOUserMode(Mode aMode)
  {
    mode = aMode;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setMode(Mode aMode)
  {
    boolean wasSet = false;
    mode = aMode;
    wasSet = true;
    return wasSet;
  }

  public Mode getMode()
  {
    return mode;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "mode" + "=" + (getMode() != null ? !getMode().equals(this)  ? getMode().toString().replaceAll("  ","    ") : "this" : "null");
  }
}