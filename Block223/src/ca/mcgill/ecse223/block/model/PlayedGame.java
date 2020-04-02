/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.io.Serializable;
import java.awt.geom.*;
import java.util.*;

// line 127 "../../../../../Block223Persistence.ump"
// line 20 "../../../../../Block223PlayMode.ump"
// line 1 "../../../../../Block223StateMachine.ump"
public class PlayedGame implements Serializable
{

  //------------------------
  // STATIC VARIABLES
  //------------------------


  /**
   * at design time, the initial wait time may be adjusted as seen fit
   */
  public static final int INITIAL_WAIT_TIME = 30;
  private static int nextId = 1;
  public static final int NR_LIVES = 3;

  /**
   * the PlayedBall and PlayedPaddle are not in a separate class to avoid the bug in Umple that occurred for the second constructor of Game
   * no direct link to Ball, because the ball can be found by navigating to Game and then Ball
   */
  public static final int BALL_INITIAL_X = Game.PLAY_AREA_SIDE / 2;
  public static final int BALL_INITIAL_Y = Game.PLAY_AREA_SIDE / 2;

  /**
   * pixels moved when right arrow key is pressed
   */
  public static final int PADDLE_MOVE_RIGHT = 5;

  /**
   * pixels moved when left arrow key is pressed
   */
  public static final int PADDLE_MOVE_LEFT = -5;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //PlayedGame Attributes
  private int score;
  private int lives;
  private int currentLevel;
  private double waitTime;
  private String playername;
  private double ballDirectionX;
  private double ballDirectionY;
  private double currentBallX;
  private double currentBallY;
  private double currentPaddleLength;
  private double currentPaddleX;
  private double currentPaddleY;

  //Autounique Attributes
  private int id;

  //PlayedGame State Machines
  public enum PlayStatus { Ready, Moving, Paused, GameOver }
  private PlayStatus playStatus;

  //PlayedGame Associations
  private Player player;
  private Game game;
  private List<PlayedBlockAssignment> blocks;
  private BouncePoint bounce;
  private Block223 block223;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public PlayedGame(String aPlayername, Game aGame, Block223 aBlock223)
  {
    // line 55 "../../../../../Block223PlayMode.ump"
    boolean didAddGameResult = setGame(aGame);
          if (!didAddGameResult)
          {
             throw new RuntimeException("Unable to create playedGame due to game");
          }
    // END OF UMPLE BEFORE INJECTION
    score = 0;
    lives = NR_LIVES;
    currentLevel = 1;
    waitTime = INITIAL_WAIT_TIME;
    playername = aPlayername;
    resetBallDirectionX();
    resetBallDirectionY();
    resetCurrentBallX();
    resetCurrentBallY();
    currentPaddleLength = getGame().getPaddle().getMaxPaddleLength();
    resetCurrentPaddleX();
    currentPaddleY = Game.PLAY_AREA_SIDE - Paddle.VERTICAL_DISTANCE - Paddle.PADDLE_WIDTH;
    id = nextId++;
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create playedGame due to game");
    }
    blocks = new ArrayList<PlayedBlockAssignment>();
    boolean didAddBlock223 = setBlock223(aBlock223);
    if (!didAddBlock223)
    {
      throw new RuntimeException("Unable to create playedGame due to block223");
    }
    setPlayStatus(PlayStatus.Ready);
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

  public boolean setWaitTime(double aWaitTime)
  {
    boolean wasSet = false;
    waitTime = aWaitTime;
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
  /* Code from template attribute_SetDefaulted */
  public boolean setBallDirectionX(double aBallDirectionX)
  {
    boolean wasSet = false;
    ballDirectionX = aBallDirectionX;
    wasSet = true;
    return wasSet;
  }

  public boolean resetBallDirectionX()
  {
    boolean wasReset = false;
    ballDirectionX = getDefaultBallDirectionX();
    wasReset = true;
    return wasReset;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setBallDirectionY(double aBallDirectionY)
  {
    boolean wasSet = false;
    ballDirectionY = aBallDirectionY;
    wasSet = true;
    return wasSet;
  }

  public boolean resetBallDirectionY()
  {
    boolean wasReset = false;
    ballDirectionY = getDefaultBallDirectionY();
    wasReset = true;
    return wasReset;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setCurrentBallX(double aCurrentBallX)
  {
    boolean wasSet = false;
    currentBallX = aCurrentBallX;
    wasSet = true;
    return wasSet;
  }

  public boolean resetCurrentBallX()
  {
    boolean wasReset = false;
    currentBallX = getDefaultCurrentBallX();
    wasReset = true;
    return wasReset;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setCurrentBallY(double aCurrentBallY)
  {
    boolean wasSet = false;
    currentBallY = aCurrentBallY;
    wasSet = true;
    return wasSet;
  }

  public boolean resetCurrentBallY()
  {
    boolean wasReset = false;
    currentBallY = getDefaultCurrentBallY();
    wasReset = true;
    return wasReset;
  }

  public boolean setCurrentPaddleLength(double aCurrentPaddleLength)
  {
    boolean wasSet = false;
    currentPaddleLength = aCurrentPaddleLength;
    wasSet = true;
    return wasSet;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setCurrentPaddleX(double aCurrentPaddleX)
  {
    boolean wasSet = false;
    currentPaddleX = aCurrentPaddleX;
    wasSet = true;
    return wasSet;
  }

  public boolean resetCurrentPaddleX()
  {
    boolean wasReset = false;
    currentPaddleX = getDefaultCurrentPaddleX();
    wasReset = true;
    return wasReset;
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

  public double getWaitTime()
  {
    return waitTime;
  }

  /**
   * added here so that it only needs to be determined once
   */
  public String getPlayername()
  {
    return playername;
  }

  /**
   * 0/0 is the top left corner of the play area, i.e., a directionX/Y of 0/1 moves the ball down in a straight line
   */
  public double getBallDirectionX()
  {
    return ballDirectionX;
  }
  /* Code from template attribute_GetDefaulted */
  public double getDefaultBallDirectionX()
  {
    return getGame().getBall().getMinBallSpeedX();
  }

  public double getBallDirectionY()
  {
    return ballDirectionY;
  }
  /* Code from template attribute_GetDefaulted */
  public double getDefaultBallDirectionY()
  {
    return getGame().getBall().getMinBallSpeedY();
  }

  /**
   * the position of the ball is at the center of the ball
   */
  public double getCurrentBallX()
  {
    return currentBallX;
  }
  /* Code from template attribute_GetDefaulted */
  public double getDefaultCurrentBallX()
  {
    return BALL_INITIAL_X;
  }

  public double getCurrentBallY()
  {
    return currentBallY;
  }
  /* Code from template attribute_GetDefaulted */
  public double getDefaultCurrentBallY()
  {
    return BALL_INITIAL_Y;
  }

  public double getCurrentPaddleLength()
  {
    return currentPaddleLength;
  }

  /**
   * the position of the paddle is at its top left corner
   */
  public double getCurrentPaddleX()
  {
    return currentPaddleX;
  }
  /* Code from template attribute_GetDefaulted */
  public double getDefaultCurrentPaddleX()
  {
    return (Game.PLAY_AREA_SIDE - currentPaddleLength) / 2;
  }

  public double getCurrentPaddleY()
  {
    return currentPaddleY;
  }

  public int getId()
  {
    return id;
  }

  public String getPlayStatusFullName()
  {
    String answer = playStatus.toString();
    return answer;
  }

  public PlayStatus getPlayStatus()
  {
    return playStatus;
  }

  public boolean play()
  {
    boolean wasEventProcessed = false;
    
    PlayStatus aPlayStatus = playStatus;
    switch (aPlayStatus)
    {
      case Ready:
        setPlayStatus(PlayStatus.Moving);
        wasEventProcessed = true;
        break;
      case Paused:
        setPlayStatus(PlayStatus.Moving);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean pause()
  {
    boolean wasEventProcessed = false;
    
    PlayStatus aPlayStatus = playStatus;
    switch (aPlayStatus)
    {
      case Moving:
        setPlayStatus(PlayStatus.Paused);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean move()
  {
    boolean wasEventProcessed = false;
    
    PlayStatus aPlayStatus = playStatus;
    switch (aPlayStatus)
    {
      case Moving:
        if (hitPaddle())
        {
        // line 12 "../../../../../Block223StateMachine.ump"
          doHitPaddleOrWall();
          setPlayStatus(PlayStatus.Moving);
          wasEventProcessed = true;
          break;
        }
        if (isOutOfBoundsAndLastLife())
        {
        // line 13 "../../../../../Block223StateMachine.ump"
          doOutOfBounds();
          setPlayStatus(PlayStatus.GameOver);
          wasEventProcessed = true;
          break;
        }
        if (isOutOfBounds())
        {
        // line 14 "../../../../../Block223StateMachine.ump"
          doOutOfBounds();
          setPlayStatus(PlayStatus.Paused);
          wasEventProcessed = true;
          break;
        }
        if (hitLastBlockAndLastLevel())
        {
        // line 15 "../../../../../Block223StateMachine.ump"
          doHitBlock();
          setPlayStatus(PlayStatus.GameOver);
          wasEventProcessed = true;
          break;
        }
        if (hitLastBlock())
        {
        // line 16 "../../../../../Block223StateMachine.ump"
          doHitBlockNextLevel();
          setPlayStatus(PlayStatus.Ready);
          wasEventProcessed = true;
          break;
        }
        if (hitBlock())
        {
        // line 17 "../../../../../Block223StateMachine.ump"
          doHitBlock();
          setPlayStatus(PlayStatus.Moving);
          wasEventProcessed = true;
          break;
        }
        if (hitWall())
        {
        // line 18 "../../../../../Block223StateMachine.ump"
          doHitPaddleOrWall();
          setPlayStatus(PlayStatus.Moving);
          wasEventProcessed = true;
          break;
        }
        // line 19 "../../../../../Block223StateMachine.ump"
        doHitNothingAndNotOutOfBounds();
        setPlayStatus(PlayStatus.Moving);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void setPlayStatus(PlayStatus aPlayStatus)
  {
    playStatus = aPlayStatus;

    // entry actions and do activities
    switch(playStatus)
    {
      case Ready:
        // line 7 "../../../../../Block223StateMachine.ump"
        doSetup();
        break;
      case GameOver:
        // line 25 "../../../../../Block223StateMachine.ump"
        doGameOver();
        break;
    }
  }
  /* Code from template association_GetOne */
  public Player getPlayer()
  {
    return player;
  }

  public boolean hasPlayer()
  {
    boolean has = player != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Game getGame()
  {
    return game;
  }
  /* Code from template association_GetMany */
  public PlayedBlockAssignment getBlock(int index)
  {
    PlayedBlockAssignment aBlock = blocks.get(index);
    return aBlock;
  }

  public List<PlayedBlockAssignment> getBlocks()
  {
    List<PlayedBlockAssignment> newBlocks = Collections.unmodifiableList(blocks);
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

  public int indexOfBlock(PlayedBlockAssignment aBlock)
  {
    int index = blocks.indexOf(aBlock);
    return index;
  }
  /* Code from template association_GetOne */
  public BouncePoint getBounce()
  {
    return bounce;
  }

  public boolean hasBounce()
  {
    boolean has = bounce != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Block223 getBlock223()
  {
    return block223;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setPlayer(Player aPlayer)
  {
    boolean wasSet = false;
    Player existingPlayer = player;
    player = aPlayer;
    if (existingPlayer != null && !existingPlayer.equals(aPlayer))
    {
      existingPlayer.removePlayedGame(this);
    }
    if (aPlayer != null)
    {
      aPlayer.addPlayedGame(this);
    }
    wasSet = true;
    return wasSet;
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
      existingGame.removePlayedGame(this);
    }
    game.addPlayedGame(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBlocks()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public PlayedBlockAssignment addBlock(int aX, int aY, Block aBlock)
  {
    return new PlayedBlockAssignment(aX, aY, aBlock, this);
  }

  public boolean addBlock(PlayedBlockAssignment aBlock)
  {
    boolean wasAdded = false;
    if (blocks.contains(aBlock)) { return false; }
    PlayedGame existingPlayedGame = aBlock.getPlayedGame();
    boolean isNewPlayedGame = existingPlayedGame != null && !this.equals(existingPlayedGame);
    if (isNewPlayedGame)
    {
      aBlock.setPlayedGame(this);
    }
    else
    {
      blocks.add(aBlock);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBlock(PlayedBlockAssignment aBlock)
  {
    boolean wasRemoved = false;
    //Unable to remove aBlock, as it must always have a playedGame
    if (!this.equals(aBlock.getPlayedGame()))
    {
      blocks.remove(aBlock);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBlockAt(PlayedBlockAssignment aBlock, int index)
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

  public boolean addOrMoveBlockAt(PlayedBlockAssignment aBlock, int index)
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
  /* Code from template association_SetUnidirectionalOptionalOne */
  public boolean setBounce(BouncePoint aNewBounce)
  {
    boolean wasSet = false;
    bounce = aNewBounce;
    wasSet = true;
    return wasSet;
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
      existingBlock223.removePlayedGame(this);
    }
    block223.addPlayedGame(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    if (player != null)
    {
      Player placeholderPlayer = player;
      this.player = null;
      placeholderPlayer.removePlayedGame(this);
    }
    Game placeholderGame = game;
    this.game = null;
    if(placeholderGame != null)
    {
      placeholderGame.removePlayedGame(this);
    }
    while (blocks.size() > 0)
    {
      PlayedBlockAssignment aBlock = blocks.get(blocks.size() - 1);
      aBlock.delete();
      blocks.remove(aBlock);
    }
    
    bounce = null;
    Block223 placeholderBlock223 = block223;
    this.block223 = null;
    if(placeholderBlock223 != null)
    {
      placeholderBlock223.removePlayedGame(this);
    }
  }

  // line 132 "../../../../../Block223Persistence.ump"
   public static  void reinitializeAutouniqueID(List<PlayedGame> games){
    nextId = 0; 
	    for (PlayedGame pg : games) {
	      if (pg.getId() > nextId) {
	        nextId = pg.getId();
	      }
	    }
	    nextId++;
  }


  /**
   * Guards
   */
  // line 32 "../../../../../Block223StateMachine.ump"
   private boolean hitPaddle(){
    BouncePoint bp = calculateBouncePointPaddle();
	setBounce(bp);
	return bp != null;
  }

  // line 38 "../../../../../Block223StateMachine.ump"
   private BouncePoint calculateBouncePointPaddle(){
    double x1 = currentBallX;
		double y1 = currentBallY;
		double x2 = x1+ballDirectionX;
		double y2 = y1+ballDirectionY;
		Line2D ballPath = new Line2D.Double(x1,y1,x2,y2);
		double px = currentPaddleX;
		double py = currentPaddleY;
		double l = currentPaddleLength;
		double w = Paddle.PADDLE_WIDTH;
		double r = Ball.BALL_DIAMETER/2;
		Point2D closestPoint = null;
		BouncePoint.BounceDirection bd = null;
		double closestDist = Double.MAX_VALUE;
		Rectangle2D fullBox = new Rectangle2D.Double(px-r,py-r,l+(2*r),r+w);
		if(fullBox.intersectsLine(ballPath)){
			Line2D A = new Line2D.Double(px,py-r,px+l,py-r);
			Line2D B = new Line2D.Double(px-r,py,px-r,py+w);
			Line2D C = new Line2D.Double(px+l+r,py,px+l+r,py+w);

			for (Line2D line : new Line2D[] {A,B,C}) {
				Point2D intersectionPoint = getIntersectionPoint(line, ballPath);
				if (intersectionPoint != null && intersectionPoint.distance(x1, y1) < closestDist) {
					closestDist = getIntersectionPoint(line, ballPath).distance(x1, y1);
					closestPoint = getIntersectionPoint(line, ballPath);
					if ((closestPoint.getX()>=px && closestPoint.getX()<=(px+l)) && closestPoint.getY()==(py-r)) {
						bd = BouncePoint.BounceDirection.FLIP_Y;
					} else {
						bd = BouncePoint.BounceDirection.FLIP_X;
					}
				}

			}
			List<Point2D> EPts = getIntersectionPoints(ballPath, px,py,r);
			List<Point2D> FPts = getIntersectionPoints(ballPath, px+l, py, r);
			for (Point2D pt : EPts) {
				if (pt.getX() <= px && pt.getY() <= py && pt.distance(x1,y1) < closestDist) {
					closestPoint = pt;
					closestDist = pt.distance(x1,y1);
					bd = ballDirectionX < 0 ? BouncePoint.BounceDirection.FLIP_Y
							: BouncePoint.BounceDirection.FLIP_X;
				}

			}
			for (Point2D pt : FPts) {
				if (pt.getX() > px+l && pt.getY() < py && pt.distance(x1,y1) < closestDist) {
					closestPoint = pt;
					closestDist = pt.distance(x1,y1);
					bd = ballDirectionX < 0 ? BouncePoint.BounceDirection.FLIP_X
							: BouncePoint.BounceDirection.FLIP_Y;
				}

			}
			if (bd == null || closestDist == 0) return null;
			return new BouncePoint(closestPoint.getX(), closestPoint.getY(), bd);
		}
		return null;
  }

  // line 97 "../../../../../Block223StateMachine.ump"
   private Point2D getIntersectionPoint(Line2D a, Line2D b){
    if (a.intersectsLine(b) && slope(a) != slope(b)) {
			double x1 = a.getX1();
			double x2 = b.getX1();
			double y1 = a.getY1();
			double y2 = b.getY1();
			Double m1 = slope(a);
			Double m2 = slope(b);
			double x,y;
			if (m1 == null) {
				x = a.getX1();
				y = m2*(x-x2) + y2;
			} else if (m2 == null) {
				x = b.getX1(); 
				y = m1*(x-x1) + y1;
			} else {
				x  = (m1*x1 - y1 + y2-m2*x2)/(m1-m2);
				y =  m1*(x-x1) + y1;
			}
		    return new Point2D.Double(x,y);
		} else {
			return null;
		}
  }


  /**
   * 
   * Returns the intersections between an arc and a line segment
   * @param l The line
   * @param xc The x of the circle's center
   * @param yc the y of the circle's center
   * @param r the radius of the circle
   * @return a list of points of intersection
   */
  // line 131 "../../../../../Block223StateMachine.ump"
   private List<Point2D> getIntersectionPoints(Line2D l, double xc, double yc, double r){
    List<Point2D> list = new ArrayList<Point2D>();
		Double m = slope(l);
		double xl = l.getX1();
		double yl = l.getY1();
		if (m == null) {
			List<Double> yvals = new ArrayList<Double>();
			double x = xl;
			double radicand = r*r - (x-xc)*(x-xc);
			if (radicand >= 0) {
				yvals.add(yc - Math.sqrt(radicand));
				yvals.add(yc + Math.sqrt(radicand));
			}
			for (Double yval : yvals) {
				if (yval <= Math.min(yl, l.getY2()) && yval >= Math.max(yl, l.getY2())) {
					list.add(new Point2D.Double(x, yval));
				}
			}
		} else {

			List<Double> xvals = new ArrayList<Double>();
			double a = (m*m + 1);
			double b = 2*m*(-m*xl+yl-yc)-2*xc;
			double c = Math.pow(-m*xl+yl-yc,2) - r*r + xc*xc;
			if (b*b - 4*a*c >= 0) {
				xvals.add((-b + Math.sqrt(b*b - 4*a*c) ) / (2*a));
				xvals.add((-b - Math.sqrt(b*b - 4*a*c) ) / (2*a));
			}
			for (Double xval : xvals) {
				if (xval <= Math.max(xl, l.getX2()) && xval >= Math.min(xl, l.getX2())) {
					list.add(new Point2D.Double(xval, m*xval - m*xl + yl));
				}
			}
		}
		return list;
  }

  // line 167 "../../../../../Block223StateMachine.ump"
   private Double slope(Line2D l){
    if (Math.abs(l.getX1() - l.getX2()) > 0.00001) {
			return (l.getY1() - l.getY2())/(l.getX1() - l.getX2());
		} else {
			return null;
		}
  }

  // line 175 "../../../../../Block223StateMachine.ump"
   private boolean isOutOfBoundsAndLastLife(){
    boolean outOfBounds = false;
    if(lives == 1){
    	outOfBounds = isBallOutOfBounds();
    }
    return outOfBounds;
  }

  // line 183 "../../../../../Block223StateMachine.ump"
   private boolean isOutOfBounds(){
    boolean outOfBounds = isBallOutOfBounds();
    return outOfBounds;
  }

  // line 188 "../../../../../Block223StateMachine.ump"
   private boolean isBallOutOfBounds(){
    double x1 = currentBallX;
	double y1 = currentBallY;
	double x2 = currentBallX + ballDirectionX;
	double y2 = currentBallY + ballDirectionY;
	Line2D path = new Line2D.Double(x1,y1,x2,y2);
	double r = Ball.BALL_DIAMETER/2.;
	Line2D D = new Line2D.Double(0,Game.PLAY_AREA_SIDE-r,Game.PLAY_AREA_SIDE,Game.PLAY_AREA_SIDE-r);
	if (D.intersectsLine(path)) {
		return true;
	} else {
	return false;
	}
  }

  // line 204 "../../../../../Block223StateMachine.ump"
   private boolean hitLastBlockAndLastLevel(){
    Game game = getGame();
	   int nrLevels = game.numberOfLevels();
	   setBounce(null);
	   if (nrLevels == currentLevel) {
		   int nrBlocks = numberOfBlocks();
		   if (nrBlocks == 1) {
			   PlayedBlockAssignment block = getBlock(0);
			   BouncePoint bp = calculateBouncePointBlock(block);
			   if (bp != null) {
			   	setBounce(bp);
			   	return true;
			   }
		   }
	   }
	   return false;
  }

  // line 222 "../../../../../Block223StateMachine.ump"
   private boolean hitLastBlock(){
    int nrBlocks = numberOfBlocks();
		setBounce(null);
		if (nrBlocks == 1) {
			PlayedBlockAssignment block = getBlock(0);
			BouncePoint bp = calculateBouncePointBlock(block);
			if (bp!=null) {
				setBounce(bp);
				return true;
			}
		}
		return false;
  }

  // line 236 "../../../../../Block223StateMachine.ump"
   private boolean hitBlock(){
    int nrBlocks = numberOfBlocks();
		setBounce(null);
		for (int i = 0; i < numberOfBlocks(); i++) {
			PlayedBlockAssignment block = getBlock(i);
			BouncePoint bp = calculateBouncePointBlock(block);
			if (bp != null && (bounce == null || isCloser(bp, bounce))) {
				setBounce(bp);
			}
		}
		return getBounce() != null;
  }

  // line 249 "../../../../../Block223StateMachine.ump"
   private boolean isCloser(BouncePoint bp, BouncePoint bounce){
    return Point2D.distance(bp.getX(), bp.getY(), currentBallX, currentBallY)
				< Point2D.distance(bounce.getX(), bounce.getY(), currentBallX, currentBallY);
  }

  // line 254 "../../../../../Block223StateMachine.ump"
   private boolean hitWall(){
    BouncePoint bp = calculateBouncePointWall();
	setBounce(bp);
	return bp != null;
  }


  /**
   * Actions
   */
  // line 262 "../../../../../Block223StateMachine.ump"
   private void doSetup(){
    resetCurrentBallX();
	   resetCurrentBallY();
	   resetBallDirectionX();
	   resetBallDirectionY();
	   resetCurrentPaddleX();
	   getGame();
	   Level level = game.getLevel(currentLevel - 1);
	   for (BlockAssignment a : level.getBlockAssignments()) {
		   PlayedBlockAssignment pblock = 
				   new PlayedBlockAssignment(Game.WALL_PADDING + (Block.SIZE + Game.COLUMNS_PADDING) * (a.getGridHorizontalPosition() - 1),
						   Game.WALL_PADDING + (Block.SIZE + Game.ROW_PADDING) * (a.getGridVerticalPosition() - 1), 
						   a.getBlock(), this);
	   }
	   while (numberOfBlocks() < game.getNrBlocksPerLevel()) {
		   int x = (int)(Math.random() * 15) + 1;
		   int y = (int)(Math.random() * 15) + 1;
		   while (hasBlockAt(x, y)) {
			   x++;
			   if (x > 15) {
				   x = 1;
				   y++;
			   }
			   if (y > 15) {
				   y = 1;
			   }
		   }
		   PlayedBlockAssignment pblock = 
				   new PlayedBlockAssignment(Game.WALL_PADDING + (Block.SIZE + Game.COLUMNS_PADDING) * (x - 1),
						   Game.WALL_PADDING + (Block.SIZE + Game.ROW_PADDING) * (y- 1), 
						   game.getRandomBlock(), this);
	   }
  }

  // line 296 "../../../../../Block223StateMachine.ump"
   private boolean hasBlockAt(int x, int y){
    for (PlayedBlockAssignment pblock : blocks) {
		   if (x == pblock.getX() && y == pblock.getY()) {
			   return true;
		   }
	   }
	   return false;
  }

  // line 306 "../../../../../Block223StateMachine.ump"
   private void doHitPaddleOrWall(){
    bounceBall();
  }

  // line 310 "../../../../../Block223StateMachine.ump"
   private void doOutOfBounds(){
    setLives(lives-1);
    resetCurrentBallX();
    resetCurrentBallY();
    resetBallDirectionX();
    resetBallDirectionY();
    resetCurrentPaddleX();
  }

  // line 319 "../../../../../Block223StateMachine.ump"
   private void doHitBlock(){
    int score = getScore();
		PlayedBlockAssignment pblock = bounce.getHitBlock();
		Block block = pblock.getBlock();
		setScore(score + block.getPoints());
		pblock.delete();
		bounceBall();
  }

  // line 328 "../../../../../Block223StateMachine.ump"
   private void doHitBlockNextLevel(){
    doHitBlock();
		setCurrentLevel(1+ getCurrentLevel());
		setCurrentPaddleLength(getGame().getPaddle().getMaxPaddleLength() -
				(getGame().getPaddle().getMaxPaddleLength() - getGame().getPaddle().getMinPaddleLength())/
				(getGame().numberOfLevels() -1)*(getCurrentLevel()-1));
		setWaitTime(INITIAL_WAIT_TIME* Math.pow(getGame().getBall().getBallSpeedIncreaseFactor(), (getCurrentLevel()-1)));
  }

  // line 337 "../../../../../Block223StateMachine.ump"
   private void doHitNothingAndNotOutOfBounds(){
	   double x = getCurrentBallX();
	   double y = getCurrentBallY();
	   double dx = getBallDirectionX();
	   double dy = getBallDirectionY();
	   setCurrentBallX(x + dx);
	   setCurrentBallY(y + dy);
  }

  // line 346 "../../../../../Block223StateMachine.ump"
   private void doGameOver(){
    Block223 block223 = getBlock223();
    Player p = getPlayer();
    if(p!=null){
    	Game game = getGame();
    	HallOfFameEntry hof = new HallOfFameEntry(score, playername, p, game, block223);
    	game.setMostRecentEntry(hof);
    }
    delete();
  }

  // line 357 "../../../../../Block223StateMachine.ump"
   private BouncePoint calculateBouncePointWall(){
	    double x1 = currentBallX;
		double y1 = currentBallY;
		double x2 = currentBallX + ballDirectionX;
		double y2 = currentBallY + ballDirectionY;
		Line2D path = new Line2D.Double(x1,y1,x2,y2);
		double r = Ball.BALL_DIAMETER/2.;
		Line2D A = new Line2D.Double(r,0,r,Game.PLAY_AREA_SIDE);
		Line2D B = new Line2D.Double(0,r,Game.PLAY_AREA_SIDE,r);
		Line2D C = new Line2D.Double(Game.PLAY_AREA_SIDE-r,0,Game.PLAY_AREA_SIDE-r,Game.PLAY_AREA_SIDE);
		for (Line2D l : new Line2D[] {A,B,C}) {
			if (l.intersectsLine(path)) {
				Point2D p = getIntersectionPoint(path, l);
				if (path.getP1().distance(p) == 0) {
					return null;
				}
				else if (p.distance(r,r) < 0.0001) {
					return new BouncePoint(r,r,BouncePoint.BounceDirection.FLIP_BOTH);
				} else if (p.distance(Game.PLAY_AREA_SIDE - r,r) < 0.0001) {
					Line2D l1 = new Line2D.Double(1.,1.,0.,0.);
					Line2D l2 = new Line2D.Double(0.,0., 0.,1.);
					return new BouncePoint(Game.PLAY_AREA_SIDE-r,r,BouncePoint.BounceDirection.FLIP_BOTH);
				}
			}
		}
		
		for (Line2D l : new Line2D[] {A,B,C}) {
			if (l.intersectsLine(path)) {
				Point2D p = getIntersectionPoint(path, l);
				return new BouncePoint(p.getX(),p.getY(),l.equals(B)
						? BouncePoint.BounceDirection.FLIP_Y : BouncePoint.BounceDirection.FLIP_X);
			}
		}
		return null;
  }

  // line 392 "../../../../../Block223StateMachine.ump"
   private void bounceBall(){
	   	double incomingX = bounce.getX()-currentBallX; 
	    double incomingY = bounce.getY()-currentBallY;
	    double outgoingX = ballDirectionX - incomingX;
	    double outgoingY = ballDirectionY - incomingY;
	    
	    double oldDirX = ballDirectionX;
	    double oldDirY = ballDirectionY;

		if (bounce.getDirection() == BouncePoint.BounceDirection.FLIP_Y) {
			ballDirectionX = ballDirectionX + 
					(ballDirectionX >= 0? 1:-1)*.1*Math.abs(ballDirectionY);
			ballDirectionY = -ballDirectionY;

			currentBallX = bounce.getX() + outgoingX/oldDirY * ballDirectionX;
			currentBallY = bounce.getY() + outgoingY/oldDirY * ballDirectionY;
		}
		else if (bounce.getDirection() == BouncePoint.BounceDirection.FLIP_X) {
			ballDirectionY = ballDirectionY + 
					(ballDirectionY >= 0? 1:-1)*.1*Math.abs(ballDirectionX);
			ballDirectionX = ballDirectionX *-1;

			currentBallX = bounce.getX() + outgoingX/oldDirX*ballDirectionX;
			currentBallY = bounce.getY() + outgoingY/oldDirX*ballDirectionY;
		} else if (bounce.getDirection() == BouncePoint.BounceDirection.FLIP_BOTH) {
			ballDirectionX *= -1;
			ballDirectionY *= -1;
			currentBallX = bounce.getX() + outgoingX;
			currentBallY = bounce.getY() + outgoingY;
		}
		
		//Slows down the ball:
		if (ballDirectionX >= 10 || ballDirectionY >= 10) {
			ballDirectionX /= 10.0;
			ballDirectionY /= 10.0;
		}
			
		//Verifies ball remains within bounds
		double r = Ball.BALL_DIAMETER/2.;
		currentBallY = Math.max(r, currentBallY);
		currentBallX = Math.max(r, currentBallX);
		currentBallX = Math.min(Game.PLAY_AREA_SIDE - r, currentBallX);
  }

  // line 436 "../../../../../Block223StateMachine.ump"
   private BouncePoint calculateBouncePointBlock(PlayedBlockAssignment block){
	    double x1 = currentBallX;
		double y1 = currentBallY;
		double x2 = currentBallX + ballDirectionX;
		double y2 = currentBallY + ballDirectionY;
		Line2D path = new Line2D.Double(x1,y1,x2,y2);
		double bx = block.getX();
		double by = block.getY();
		double s = Block.SIZE;
		double r = Ball.BALL_DIAMETER/2.;
		Point2D closestPoint = null;
		BouncePoint.BounceDirection bd = null;
		double closestDist = Double.MAX_VALUE;
		Rectangle2D.Double fullArea = new Rectangle2D.Double(bx - r, by-r, s + 2*r, r*2 + s);
		if (fullArea.intersectsLine(x1, y1, x2, y2)) {
			Line2D.Double A = new Line2D.Double(bx, by-r,bx+s,by-r);
			Line2D.Double B = new Line2D.Double(bx-r,by,bx-r,by+s);
			Line2D.Double C = new Line2D.Double(bx+s+r,by,bx+s+r,by+s);
			Line2D.Double D = new Line2D.Double(bx,by+s+r,bx+s,by+s+r);
			for (Line2D line : new Line2D[] {A,B,C,D}) {
				if (getIntersectionPoint(line, path) != null && getIntersectionPoint(line, path).distance(x1, y1) < closestDist) {
					closestDist = getIntersectionPoint(line, path).distance(x1, y1);
					closestPoint = getIntersectionPoint(line, path);
					if (line.equals(A) || line.equals(D)) {
						bd = BouncePoint.BounceDirection.FLIP_Y;
					} else {
						bd = BouncePoint.BounceDirection.FLIP_X;
					}
				}
			}

			List<Point2D> EPts = getIntersectionPoints(path, bx,by,r);
			List<Point2D> FPts = getIntersectionPoints(path, bx+s, by, r);
			List<Point2D> GPts = getIntersectionPoints(path, bx,by+s,r);
			List<Point2D> HPts = getIntersectionPoints(path, bx+s, by+s, r);
			for (Point2D pt : EPts) {
				if (pt.getX() < bx && pt.getY() < by && pt.distance(x1,y1) < closestDist) {
					closestPoint = pt;
					closestDist = pt.distance(x1,y1);
					bd = ballDirectionX < 0 ? BouncePoint.BounceDirection.FLIP_Y
							: BouncePoint.BounceDirection.FLIP_X;
				}
			}
			for (Point2D pt : FPts) {
				if (pt.getX() > bx+s && pt.getY() < by && pt.distance(x1,y1) < closestDist) {
					closestPoint = pt;
					closestDist = pt.distance(x1,y1);
					bd = ballDirectionX < 0 ? BouncePoint.BounceDirection.FLIP_X
							: BouncePoint.BounceDirection.FLIP_Y;
				}
			}for (Point2D pt : GPts) {
				if (pt.getX() < bx && pt.getY() > by+s && pt.distance(x1,y1) < closestDist) {
					closestPoint = pt;
					closestDist = pt.distance(x1,y1);
					bd = ballDirectionX < 0 ? BouncePoint.BounceDirection.FLIP_Y
							: BouncePoint.BounceDirection.FLIP_X;
				}
			}for (Point2D pt : HPts) {
				if (pt.getX() > bx+s && pt.getY() > by+s && pt.distance(x1,y1) < closestDist) {
					closestPoint = pt;
					closestDist = pt.distance(x1,y1);
					bd = ballDirectionX < 0 ? BouncePoint.BounceDirection.FLIP_X
							: BouncePoint.BounceDirection.FLIP_Y;
				}
			}
			if (bd == null) return null;
			BouncePoint b = new BouncePoint(closestPoint.getX(), closestPoint.getY(), bd);
			b.setHitBlock(block);
			return b;
		}
		return null;
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "score" + ":" + getScore()+ "," +
            "lives" + ":" + getLives()+ "," +
            "currentLevel" + ":" + getCurrentLevel()+ "," +
            "waitTime" + ":" + getWaitTime()+ "," +
            "playername" + ":" + getPlayername()+ "," +
            "ballDirectionX" + ":" + getBallDirectionX()+ "," +
            "ballDirectionY" + ":" + getBallDirectionY()+ "," +
            "currentBallX" + ":" + getCurrentBallX()+ "," +
            "currentBallY" + ":" + getCurrentBallY()+ "," +
            "currentPaddleLength" + ":" + getCurrentPaddleLength()+ "," +
            "currentPaddleX" + ":" + getCurrentPaddleX()+ "," +
            "currentPaddleY" + ":" + getCurrentPaddleY()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "player = "+(getPlayer()!=null?Integer.toHexString(System.identityHashCode(getPlayer())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "bounce = "+(getBounce()!=null?Integer.toHexString(System.identityHashCode(getBounce())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "block223 = "+(getBlock223()!=null?Integer.toHexString(System.identityHashCode(getBlock223())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 130 "../../../../../Block223Persistence.ump"
  private static final long serialVersionUID = 8597675110221231714L ;

  
}