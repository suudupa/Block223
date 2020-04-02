package ca.mcgill.ecse223.block.controller;

import java.util.List;
import java.util.ArrayList;
import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.TOUserMode.Mode;
import ca.mcgill.ecse223.block.model.PlayedGame.PlayStatus;
import ca.mcgill.ecse223.block.persistence.Block223Persistence;
import ca.mcgill.ecse223.block.view.Block223PlayModeInterface;
import ca.mcgill.ecse223.block.model.*;

public class Block223Controller {

	public static final int SLEEP_TIME = 300;

	//public Block223Controller(){
	//}

	// ****************************
	// Modifier methods
	// ****************************
	public static void createGame(String name) throws InvalidInputException {
		Block223 block223 = Block223Application.getBlock223();
		UserRole admin = Block223Application.getCurrentUserRole();
		if (!(admin instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to create a game.");
		}
		try {
			new Game(name, 1, (Admin) admin, 1, 1, 1.0, 10, 10, block223);
		} catch (RuntimeException re) {
			throw new InvalidInputException(re.getMessage());
		}

	}

	public static void setGameDetails(int nrLevels, int nrBlocksPerLevel, int minBallSpeedX, int minBallSpeedY,
			Double ballSpeedIncreaseFactor, int maxPaddleLength, int minPaddleLength) throws InvalidInputException {
		UserRole admin = Block223Application.getCurrentUserRole();
		Game game = Block223Application.getCurrentGame();
		if (!(admin instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to define game settings.");
		} 
		if (game == null) {
			throw new InvalidInputException("A game must be selected to define game settings.");
		}
		if (!game.getAdmin().equals(admin)) {
			throw new InvalidInputException("Only the admin who created the game can define its game settings.");
		}
		if (nrLevels > Game.MAX_NR_LEVELS || nrLevels < Game.MIN_NR_LEVELS) {
			throw new InvalidInputException("The number of levels must be between " +
					Game.MIN_NR_LEVELS + " and " + Game.MAX_NR_LEVELS +".");
		}
		
		int maxNumBlocksSet = 0;
		for (Level l : game.getLevels()) {
			if (l.getBlockAssignments().size() > maxNumBlocksSet) {
				maxNumBlocksSet = l.getBlockAssignments().size();
			}
		}
		if (maxNumBlocksSet > nrBlocksPerLevel) {
			throw new InvalidInputException("The maximum number of blocks per level cannot be less than the number of existing blocks in a level.");
		}

		Ball ball = game.getBall();
		try {
			game.setNrBlocksPerLevel(nrBlocksPerLevel);
			ball.setMinBallSpeedX(minBallSpeedX);
			ball.setMinBallSpeedY(minBallSpeedY);
			ball.setBallSpeedIncreaseFactor(ballSpeedIncreaseFactor);

			Paddle paddle = game.getPaddle();
			paddle.setMaxPaddleLength(maxPaddleLength);
			paddle.setMinPaddleLength(minPaddleLength);
		} catch(RuntimeException re) {
			throw new InvalidInputException(re.getMessage());
		}

		List<Level> levels = game.getLevels();
		int size = levels.size();

		while (nrLevels > size) {
			game.addLevel();
			size = levels.size();
		}

		while (nrLevels < size) {
			Level level = game.getLevel(game.numberOfLevels() - 1);
			level.delete();
			size = levels.size();
		}
	}

	public static void deleteGame(String name) throws InvalidInputException {
		UserRole admin = Block223Application.getCurrentUserRole();
		if (!(admin instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to delete a game.");
		}

		Game game = Block223Application.getBlock223().findGame(name);
		if (game != null && !game.getAdmin().equals(admin)) {
			throw new InvalidInputException("Only the admin who created the game can delete the game.");
		}
		if(game != null && game.isPublished()) {
			throw new InvalidInputException("A published game cannot be deleted.");
		}
		if (game != null) {
			Block223 block223 = game.getBlock223();
			game.delete();
			Block223Persistence.save(block223);  
		}
	}

	public static void selectGame(String name) throws InvalidInputException {
		UserRole admin = Block223Application.getCurrentUserRole();
		if (!(admin instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to select a game.");
		}
		Game game = Block223Application.getBlock223().findGame(name);
		if (game == null) {
			throw new InvalidInputException("A game with name " + name + " does not exist.");
		}
		if (!game.getAdmin().equals(admin)) {
			throw new InvalidInputException("Only the admin who created the game can select the game.");
		}
		if(game.isPublished()) {
			throw new InvalidInputException("A published game cannot be changed.");
		}
		Block223Application.setCurrentGame(game);
	}



	public static void updateGame(String name, int nrLevels, int nrBlocksPerLevel, int minBallSpeedX, int minBallSpeedY,
			Double ballSpeedIncreaseFactor, int maxPaddleLength, int minPaddleLength) throws InvalidInputException {

		/*
		 * This is semi redundant because it is covered in setDetails.
		 * However, it is necessary to check that the admin is able to modify
		 * the game before allowing the name to be changed. We could switch
		 * the order so that setDetails is called first, but this wouldn't
		 * follow the flowchart.
		 */
		UserRole admin = Block223Application.getCurrentUserRole();
		Game game = Block223Application.getCurrentGame();
		if (!(admin instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to define game settings.");
		} 
		if (game == null) {
			throw new InvalidInputException("A game must be selected to define game settings.");
		}
		if (!game.getAdmin().equals(admin)) {
			throw new InvalidInputException("Only the admin who created the game can define its game settings.");
		}
		

		String currentName = game.getName();
		if (!currentName.equals(name)) {
			if (Block223Application.getBlock223().findGame(name) != null) {
	             throw new InvalidInputException("The name of a game must be unique.");
			}
			try {
				if (!game.setName(name)) {
					throw new InvalidInputException("The name of the game must be unique.");
				}
			} catch (RuntimeException re) {
				throw new InvalidInputException(re.getMessage());
			}
		}
		setGameDetails(nrLevels, nrBlocksPerLevel, minBallSpeedX, minBallSpeedY, 
				ballSpeedIncreaseFactor, maxPaddleLength, minPaddleLength);
	}

	public static void addBlock(int red, int green, int blue, int points) throws InvalidInputException {
		UserRole admin = Block223Application.getCurrentUserRole();
		if (!(admin instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to add a block.");
		}
		Game game = Block223Application.getCurrentGame();
		if (game == null) {
			throw new InvalidInputException("A game must be selected to add a block.");
		}
		if (!game.getAdmin().equals(admin)) {
			throw new InvalidInputException("Only the admin who created the game can add a block.");
		}
		for (Block b : game.getBlocks()) {
			if (b.getRed() == red && b.getGreen() == green && b.getBlue() == blue) {
				throw new InvalidInputException("A block with the same color already exists for the game.");
			}
		}
		try {
			new Block(red, green, blue, points, game);
		}
		catch (RuntimeException re){
			throw new InvalidInputException(re.getMessage());
		}
	}

	public static void deleteBlock(int id) throws InvalidInputException {
		UserRole admin = Block223Application.getCurrentUserRole();
		if (!(admin instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to delete a block.");
		}
		Game game = Block223Application.getCurrentGame();
		if (game == null) {
			throw new InvalidInputException("A game must be selected to delete a block.");
		}
		if (!game.getAdmin().equals(admin)) {
			throw new InvalidInputException("Only the admin who created the game can delete a block.");
		}
		Block block = game.findBlock(id);
		if (block != null) {
			block.delete();
		}
	}

	public static void updateBlock(int id, int red, int green, int blue, int points) throws InvalidInputException {
		Game game = Block223Application.getCurrentGame();
		UserRole admin = Block223Application.getCurrentUserRole();
		if (!(admin instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to update a block.");
		} 
		if (game == null) {
			throw new InvalidInputException("A game must be selected to update a block.");
		}
		if (!game.getAdmin().equals(admin)) {
			throw new InvalidInputException("Only the admin who created the game can update a block.");
		}
		
		Block block = game.findBlock(id);
		if (game.getBlockWithColor(red, green, blue) != null && !game.getBlockWithColor(red, green, blue).equals(block)) {
			throw new InvalidInputException("A block with the same color already exists for the game.");
		}
		if (block == null) {
			throw new InvalidInputException("The block does not exist.");
		}
		try {
			block.setRed(red);
			block.setGreen(green);
			block.setBlue(blue);
			block.setPoints(points);
		} catch (RuntimeException re) {
			throw new InvalidInputException(re.getMessage());
		}
	}

	public static void positionBlock(int id, int level, int gridHorizontalPosition, int gridVerticalPosition)
			throws InvalidInputException {
		Game game = Block223Application.getCurrentGame();
		UserRole admin = Block223Application.getCurrentUserRole();
		if (!(admin instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to position a block.");
		} 
		if (game == null) {
			throw new InvalidInputException("A game must be selected to position a block.");
		}
		if (!game.getAdmin().equals(admin)) {
			throw new InvalidInputException("Only the admin who created the game can position a block.");
		}

		Level aLevel;
		try {
			aLevel = game.getLevel(level-1);
		}
		catch (IndexOutOfBoundsException re) {
			throw new InvalidInputException(re.getMessage());
		}
		if (aLevel.numberOfBlockAssignments() == game.getNrBlocksPerLevel()) {
			throw new InvalidInputException("The number of blocks has reached the maximum number (" + game.getNrBlocksPerLevel() + ") allowed for this game.");
		}
		if (aLevel.findBlockAssignment(gridHorizontalPosition, gridVerticalPosition)!=null) {
			throw new InvalidInputException("A block already exists at location " + gridHorizontalPosition + "/" + gridVerticalPosition + ".");
		}
		Block block = game.findBlock(id);
		if (block == null) {
			throw new InvalidInputException("The block does not exist.");
		}
		try {
			new BlockAssignment(gridHorizontalPosition, gridVerticalPosition, aLevel, block, game);
		} 
		catch (RuntimeException re){
			throw new InvalidInputException(re.getMessage());
		}
	}

	public static void moveBlock(int level, int oldGridHorizontalPosition, int oldGridVerticalPosition,
			int newGridHorizontalPosition, int newGridVerticalPosition) throws InvalidInputException {
		Game game = Block223Application.getCurrentGame();
		UserRole admin = Block223Application.getCurrentUserRole();
		if (!(admin instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to move a block.");
		} 
		if (game == null) {
			throw new InvalidInputException("A game must be selected to move a block.");
		}
		if (!game.getAdmin().equals(admin)) {
			throw new InvalidInputException("Only the admin who created the game can move a block.");
		}
		Level aLevel;
		try {
			aLevel = game.getLevel(level-1);
		}
		catch (IndexOutOfBoundsException re) {
			throw new InvalidInputException(re.getMessage());
		}
		if (newGridHorizontalPosition < 1 || newGridHorizontalPosition > game.getMaxNumberHorizontalBlocks()) {
	    	throw new InvalidInputException("The horizontal position must be between 1 and " + game.getMaxNumberHorizontalBlocks() + ".");
	    }
	    if (newGridVerticalPosition < 1 || newGridVerticalPosition > game.getMaxNumberVerticalBlocks()) {
	    	throw new InvalidInputException("The vertical position must be between 1 and " + game.getMaxNumberVerticalBlocks() + ".");
	    }
		if(aLevel.findBlockAssignment(newGridHorizontalPosition, newGridVerticalPosition) != null) {
			throw new InvalidInputException("A block already exists at location " + newGridHorizontalPosition + "/" + newGridVerticalPosition + ".");
		}
		BlockAssignment assignment = aLevel.findBlockAssignment(oldGridHorizontalPosition, oldGridVerticalPosition);
		if(assignment == null) {
			throw new InvalidInputException("A block does not exist at location " + oldGridHorizontalPosition + "/" + oldGridVerticalPosition + ".");
		}
		try {
			assignment.setGridHorizontalPosition(newGridHorizontalPosition);
			assignment.setGridVerticalPosition(newGridVerticalPosition);
		}
		catch (RuntimeException re) {
			throw new InvalidInputException(re.getMessage());
		}
	}

	public static void removeBlock(int level, int gridHorizontalPosition, int gridVerticalPosition)
			throws InvalidInputException {
		UserRole admin = Block223Application.getCurrentUserRole();
		Game game = Block223Application.getCurrentGame();

		if(!(admin instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to remove a block.");
		}
		if (game == null) {
			throw new InvalidInputException("A game must be selected to remove a block.");
		}
		if (!game.getAdmin().equals(admin)) {
			throw new InvalidInputException("Only the admin who created the game can remove a block.");
		}
		Level aLevel;
		try {
			aLevel = game.getLevel(level-1);
		}
		catch (IndexOutOfBoundsException re) {
			throw new InvalidInputException(re.getMessage());
		}
		BlockAssignment assignment = aLevel.findBlockAssignment(gridHorizontalPosition, gridVerticalPosition);
		if(assignment == null) {
			return;
			//throw new InvalidInputException("A block does not exist at location " + gridHorizontalPosition + "/" + gridVerticalPosition + ".");
		}
		try {
			assignment.delete();
		}
		catch (RuntimeException re) {
			throw new InvalidInputException(re.getMessage());
		}
	}

	public static void saveGame() throws InvalidInputException {
		Block223 block223 = Block223Application.getBlock223();
		UserRole admin = Block223Application.getCurrentUserRole();

		if(!(admin instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to save a game.");
		}
		Game game = Block223Application.getCurrentGame();
		if(game == null) {
			throw new InvalidInputException("A game must be selected to save it.");
		}
		if(!game.getAdmin().equals(admin)) {
			throw new InvalidInputException("Only the admin who created the game can save it.");
		}
		try {
			Block223Persistence.save(block223);
		}catch(RuntimeException re) {
			throw new InvalidInputException(re.getMessage());
		}
	}


	public static void register(String username, String playerPassword, String adminPassword)
			throws InvalidInputException {
		Block223 block223 = Block223Application.getBlock223();
		if(Block223Application.getCurrentUserRole() != null) {
			throw new InvalidInputException("Cannot register a new user while a user is logged in.");
		}
		if(username == null || username.length() == 0) {
			throw new InvalidInputException("The username must be specified.");
		}
		
		if(playerPassword == null || playerPassword.length() == 0) {
			throw new InvalidInputException("The player password needs to be specified.");
		}
		if(adminPassword != null && adminPassword.contentEquals(playerPassword)) {
			throw new InvalidInputException("The passwords have to be different.");	
		}
		Player player = new Player(playerPassword, block223);
		User user;
		try {
			user = new User(username, block223,player);
		} catch (RuntimeException re) {
			throw new InvalidInputException("The username has already been taken.");
		}
		if(adminPassword != null && adminPassword.length() != 0) {
			Admin admin = new Admin(adminPassword,block223);
			user.addRole(admin);	
		}
		Block223Persistence.save(block223);
	}

	public static void login(String username, String password) throws InvalidInputException {
		Block223Application.resetBlock223();
		UserRole currentUser = Block223Application.getCurrentUserRole();
		if(currentUser != null || currentUser instanceof Admin || currentUser instanceof Player) {
			throw new InvalidInputException("Cannot login a user while a user is already logged in");
		}

		User user = User.getWithUsername(username);
		if(user == null) {
			throw new InvalidInputException("The username and password do not match.");
		}

		List<UserRole> roles =  user.getRoles();

		for (UserRole role : roles) {
			String rolePassword = role.getPassword();
			if (rolePassword.equals(password)) {
				Block223Application.setCurrentUserRole(role);
				Block223Application.setCurrentUsername(username);
				return;
			}
		}

		throw new InvalidInputException("The username and password do not match.");
	}

	public static void logout() {
		Block223Application.setCurrentUserRole(null);
		Block223Application.setCurrentUsername("");
	}

	// ****************************
	// Query methods
	// ****************************
	public static List<TOGame> getDesignableGames() throws InvalidInputException {
		Block223 block223 = Block223Application.getBlock223();
		UserRole admin = Block223Application.getCurrentUserRole();
		if (!(admin instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to access game information.");
		}
		List<TOGame> result = new ArrayList<TOGame>();
		List<Game> games = block223.getGames();
		for (Game game : games) {
			Admin gameAdmin = game.getAdmin();
			if (gameAdmin.equals(admin) && !game.isPublished()) {
				TOGame to = new TOGame(game.getName(), game.getLevels().size() , game.getNrBlocksPerLevel(), 
						game.getBall().getMinBallSpeedX(), game.getBall().getMinBallSpeedY(),
						game.getBall().getBallSpeedIncreaseFactor(), game.getPaddle().getMaxPaddleLength(), game.getPaddle().getMinPaddleLength());
				result.add(to);
			}
		}
		return result;
	}

	public static TOGame getCurrentDesignableGame() throws InvalidInputException {
		Game game = Block223Application.getCurrentGame();
		UserRole admin = Block223Application.getCurrentUserRole();
		if (!(admin instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to access game information.");
		} 
		if (game == null) {
			throw new InvalidInputException("A game must be selected to access its information.");
		}
		if (!game.getAdmin().equals(admin)) {
			throw new InvalidInputException("Only the admin who created the game can access its information.");
		}
		TOGame to = new TOGame(game.getName(), game.getLevels().size() , game.getNrBlocksPerLevel(), 
				game.getBall().getMinBallSpeedX(), game.getBall().getMinBallSpeedY(),
				game.getBall().getBallSpeedIncreaseFactor(), game.getPaddle().getMaxPaddleLength(),
				game.getPaddle().getMinPaddleLength());
		return to;
	}

	public static List<TOBlock> getBlocksOfCurrentDesignableGame() throws InvalidInputException {
		UserRole admin = Block223Application.getCurrentUserRole();
		if (!(admin instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to access game information.");
		}
		Game game = Block223Application.getCurrentGame();
		if (game == null) {
			throw new InvalidInputException("A game must be selected to access its information.");
		}
		if (!game.getAdmin().equals(admin)) {
			throw new InvalidInputException("Only the admin who created the game can access its information.");
		}
		ArrayList<TOBlock> result = new ArrayList<TOBlock>();
		for (Block block: game.getBlocks()) {
			TOBlock to = new TOBlock(block.getId(), block.getRed(), block.getGreen(), block.getBlue(), block.getPoints());
			result.add(to);
		}
		return result;
	}

	public static TOBlock getBlockOfCurrentDesignableGame(int id) throws InvalidInputException {
		UserRole admin = Block223Application.getCurrentUserRole();
		if (!(admin instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to access game information.");
		}
		Game game = Block223Application.getCurrentGame();
		if (game == null) {
			throw new InvalidInputException("A game must be selected to access its information.");
		}
		if (!game.getAdmin().equals(admin)) {
			throw new InvalidInputException("Only the admin who created the game can access its information.");
		}
		Block block = game.findBlock(id);
		if (block == null) {
			throw new InvalidInputException("The block does not exist.");
		}
		TOBlock to = new TOBlock(block.getId(), block.getRed(),
				block.getGreen(), block.getBlue(), block.getPoints());
		return to;
	}

	public static List<TOGridCell> getBlocksAtLevelOfCurrentDesignableGame(int level) throws InvalidInputException {
		UserRole admin = Block223Application.getCurrentUserRole();
		if (!(admin instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to access game information.");
		}
		Game game = Block223Application.getCurrentGame();
		if (game == null) {
			throw new InvalidInputException("A game must be selected to access its information.");
		}
		if (!game.getAdmin().equals(admin)) {
			throw new InvalidInputException("Only the admin who created the game can access its information.");
		}
		List<TOGridCell> result = new ArrayList<TOGridCell>();
		Level aLevel;
		try {
			aLevel = game.getLevel(level-1);
		}
		catch (IndexOutOfBoundsException re) {
			throw new InvalidInputException(re.getMessage());
		}
		List<BlockAssignment> assignments = aLevel.getBlockAssignments();

		for (BlockAssignment assignment: assignments) {
			TOGridCell to = new TOGridCell(assignment.getGridHorizontalPosition(), assignment.getGridVerticalPosition(), assignment.getBlock().getId(),
					assignment.getBlock().getRed(), assignment.getBlock().getGreen(), assignment.getBlock().getBlue(), assignment.getBlock().getPoints());
			result.add(to);
		}
		return result;
	}

	public static TOUserMode getUserMode() {
		UserRole userRole = Block223Application.getCurrentUserRole();
		if(userRole == null) {
			TOUserMode to = new TOUserMode(TOUserMode.Mode.None);
			return to;
		}
		if(userRole instanceof Player) {

			TOUserMode to = new TOUserMode(Mode.Play);
			return to;
		}
		if(userRole instanceof Admin) {
			TOUserMode to = new TOUserMode(TOUserMode.Mode.Design);
			return to;
		}
		return null;
	}


	public static String getCurrentUsername() {
		return Block223Application.getCurrentUsername();
	}

	public static int getMaxNumberHorizontalBlocks() {
		return Block223Application.getCurrentGame().getMaxNumberHorizontalBlocks();
	}

	public static int getMaxNumberVerticalBlocks() {
		return Block223Application.getCurrentGame().getMaxNumberVerticalBlocks();
	}
	

	/**
	 * *****************************
	 * INTERFACE FOR PLAYING A GAME
	 * *****************************
	 */
	// play mode

	public static void selectPlayableGame(String name, int id) throws InvalidInputException  {

		Block223 block223 = Block223Application.getBlock223();
		Game game = block223.findGame(name);
		PlayedGame pgame;
		UserRole player = Block223Application.getCurrentUserRole();
		if (!(player instanceof Player)) {
			throw new InvalidInputException("Player privileges are required to play a game.");
		}
		if (game != null) {
			String username = player.findUsername();
			pgame = new PlayedGame(username, game, block223);
			pgame.setPlayer((Player) player);
		} else {
			pgame = block223.findPlayableGame(id);
		}
		if (pgame == null) {
			throw new InvalidInputException("The game does not exist.");
		} else if (!pgame.getPlayer().equals(player)) {
			throw new InvalidInputException("Only the player that started a game can continue the game.");
		} else {
			Block223Application.setCurrentPlayableGame(pgame);	
		}
		
	}

	public static void startGame(Block223PlayModeInterface ui) throws InvalidInputException {
		UserRole player = Block223Application.getCurrentUserRole();
		PlayedGame game = Block223Application.getCurrentPlayableGame();
		if (game == null) {
			throw new InvalidInputException("A game must be selected to play it.");
		}
		if (player == null || (game.getPlayer() != null && player instanceof Admin)) {
			throw new InvalidInputException("Player privileges are required to play a game.");
		}
		if (player instanceof Admin && !game.getGame().getAdmin().equals(player)) {
			throw new InvalidInputException("Only the admin of a game can test the game.");
		} 
		if (game.getPlayer() == null && player instanceof Player) {
			throw new InvalidInputException("Admin privileges are required to test a game.");
		}
		game.play();
		ui.takeInputs();
		while (game.getPlayStatus() == PlayStatus.Moving) {
			String userInputs = ui.takeInputs();
			updatePaddlePosition(userInputs);
			game.move();
			if (userInputs.contains(" ")) {
				game.pause();
			}
			try {
				Thread.sleep((long) game.getWaitTime());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			ui.refresh();
		}
		if (game.getPlayStatus() == PlayStatus.GameOver) {
			int numLives = game.getLives();
			ui.endGame(numLives, null);
			Block223Application.setCurrentPlayableGame(null);
		} else if (game.getPlayer() != null){
			game.setBounce(null);
			Block223 block223 = Block223Application.getBlock223();
			Block223Persistence.save(block223);
		}
	}
	
	private static void updatePaddlePosition(String input) {
		PlayedGame g = Block223Application.getCurrentPlayableGame();
		for (char c : input.toCharArray()) {
			double x = g.getCurrentPaddleX();
			if (c == 'l' && x + PlayedGame.PADDLE_MOVE_LEFT >= 0) {
				g.setCurrentPaddleX(x + PlayedGame.PADDLE_MOVE_LEFT);
			} else if (c == 'r' && x + PlayedGame.PADDLE_MOVE_RIGHT + g.getCurrentPaddleLength() <= Game.PLAY_AREA_SIDE) {
				g.setCurrentPaddleX(x + PlayedGame.PADDLE_MOVE_RIGHT);
			}
		}
	}

	public static void testGame(Block223PlayModeInterface ui) throws InvalidInputException {
		UserRole admin = Block223Application.getCurrentUserRole();
		if (!(admin instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to test a game.");
		}
		Game game = Block223Application.getCurrentGame();
		if (game == null) {
			throw new InvalidInputException("A game must be selected to test it.");
		}
		if (!game.getAdmin().equals(admin)) {
			throw new InvalidInputException("Only the admin who created the game can test it.");
		}
		if (game.getBlocks().size() == 0) {
			throw new InvalidInputException("Cannot test a game with no blocks!");
		}
		//Implementing findUsername in the User Class
		String username = admin.findUsername();
		Block223 block223 = Block223Application.getBlock223();
		PlayedGame pgame = new PlayedGame(username,game,block223);
		pgame.setPlayer(null);
		Block223Application.setCurrentPlayableGame(pgame);
		startGame(ui);
	}

	public static void publishGame () throws InvalidInputException {
		UserRole admin = Block223Application.getCurrentUserRole();
		if (!(admin instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to publish a game.");
		}
		Game game = Block223Application.getCurrentGame();
		if (game == null) {
			throw new InvalidInputException("A game must be selected to publish it.");
		}
		if (!game.getAdmin().equals(admin)) {
			throw new InvalidInputException("Only the admin who created the game can publish it.");
		}
		if (game.getBlocks().size() == 0) {
			throw new InvalidInputException("At least one block must be defined for a game to be published.");
		}
		game.setPublished(true);
	}
	
	
	// ****************************
	// Game Play Query methods
	// ****************************
	public static List<TOPlayableGame> getPlayableGames() throws InvalidInputException {
		Block223 block223 = Block223Application.getBlock223();
		UserRole player = Block223Application.getCurrentUserRole();
		if (!(player instanceof Player)) {
			throw new InvalidInputException("Player privileges are required to play a game.");
		}
		List<TOPlayableGame> result = new ArrayList<TOPlayableGame>();
		for (Game game : block223.getGames()) {
			boolean published = game.isPublished();
			if (published) {
				TOPlayableGame to = new TOPlayableGame(game.getName(), -1, 0);
				result.add(to);
			}
		}
		for (PlayedGame game : ((Player) player).getPlayedGames()) {
			TOPlayableGame to = new TOPlayableGame(game.getGame().getName(), 
					game.getId(), game.getCurrentLevel());
			result.add(to);
		}
		return result;
	}

	public static TOCurrentlyPlayedGame getCurrentPlayableGame() throws InvalidInputException {
		PlayedGame pgame = Block223Application.getCurrentPlayableGame();
		UserRole player = Block223Application.getCurrentUserRole();
		if (pgame == null || pgame.getGame() == null) {
			throw new InvalidInputException("A game must be selected to play it.");
		}
		if (player == null || (player instanceof Admin && pgame.getPlayer() != null)) {
			throw new InvalidInputException("Player privileges are required to play a game.");
		}
		if (player instanceof Admin && !Block223Application.getCurrentGame().getAdmin().equals(player)) {
			throw new InvalidInputException("Only the admin of a game can test the game.");
		}
		if (player instanceof Player && pgame.getPlayer() == null) {
			throw new InvalidInputException("Admin privileges are required to test a game.");
		}
		
		boolean paused = pgame.getPlayStatus() == PlayStatus.Ready ||
				pgame.getPlayStatus() == PlayStatus.Paused;
		TOCurrentlyPlayedGame result = new TOCurrentlyPlayedGame(
				pgame.getGame().getName(), 
				paused,
				pgame.getScore(), 
				pgame.getLives(), pgame.getCurrentLevel(), pgame.getPlayername(),
				pgame.getCurrentBallX(), pgame.getCurrentBallY(), pgame.getCurrentPaddleLength(),
				pgame.getCurrentPaddleX());
		List<PlayedBlockAssignment> blocks = pgame.getBlocks();
		for (PlayedBlockAssignment pblock : blocks) {
			if (pblock != null && pblock.getBlock() != null) {
			TOCurrentBlock to = new TOCurrentBlock(pblock.getBlock().getRed(),
					pblock.getBlock().getGreen(), pblock.getBlock().getBlue(),
					pblock.getBlock().getPoints(), pblock.getX(), pblock.getY(),
					result);
			}
		}
		return result;
	}
	
	/**
	 * Enables you to get the hall of fame for a game just given it's name
	 * (it need not be selected).
	 * @param name The name of the game
	 * @param start
	 * @param end
	 * @return
	 */
	public static TOHallOfFame getHallOfFameFromName(String name, int start, int end)  throws InvalidInputException {
		UserRole player = Block223Application.getCurrentUserRole();
		if (!(player instanceof Player)) {
			throw new InvalidInputException("Player privileges are required to access a game's hall of fame.");
		}
		Game game = Block223Application.getBlock223().findGame(name);
		if (game == null) {
			throw new InvalidInputException("The game does not exist.");
		}
		else if (!game.getPublished()) {
			throw new InvalidInputException("Only published games have a hall of fame");
		}
		TOHallOfFame result = new TOHallOfFame(name);
		if (start < 1) {
			start = 1;
		}
		if (end > game.numberOfHallOfFameEntries()) {
			end = game.numberOfHallOfFameEntries();
		}
		start = game.numberOfHallOfFameEntries() - start;
		end = game.numberOfHallOfFameEntries() - end;
		for (int i = start; i >= end; i--) {
			TOHallOfFameEntry to = new TOHallOfFameEntry(i+1, game.getHallOfFameEntry(i).getPlayername(), game.getHallOfFameEntry(i).getScore(), result);
		}
		return result;
	}

	public static TOHallOfFame getHallOfFame(int start, int end) throws InvalidInputException {
		UserRole player = Block223Application.getCurrentUserRole();
		if (!(player instanceof Player)) {
			throw new InvalidInputException("Player privileges are required to access a game's hall of fame.");
		}
		PlayedGame pgame = Block223Application.getCurrentPlayableGame();
		if (pgame == null) {
			throw new InvalidInputException("A game must be selected to view its hall of fame.");
		}
		Game game = pgame.getGame();
		String name = game.getName();
		TOHallOfFame result = new TOHallOfFame(name);
		if (start < 1) {
			start = 1;
		}
		if (end > game.numberOfHallOfFameEntries()) {
			end = game.numberOfHallOfFameEntries();
		}
		start = game.numberOfHallOfFameEntries() - start;
		end = game.numberOfHallOfFameEntries() - end;
		for (int i = start; i >= end; i--) {
			TOHallOfFameEntry to = new TOHallOfFameEntry(i+1, game.getHallOfFameEntry(i).getPlayername(), game.getHallOfFameEntry(i).getScore(), result);
		}
		return result;
	}

	public static TOHallOfFame getHallOfFameWithMostRecentEntry(int numberOfEntries) throws InvalidInputException {
		if (!(Block223Application.getCurrentUserRole() instanceof Player)) {
			throw new InvalidInputException("Player privileges are required to access a game's hall of fame.");
		}
		PlayedGame pgame = Block223Application.getCurrentPlayableGame();
		if (pgame == null) {
			throw new InvalidInputException("A game must be selected to view its hall of fame.");
		}
		Game game = pgame.getGame();
		TOHallOfFame result = new TOHallOfFame(game.getName());
		HallOfFameEntry mostRecent = game.getMostRecentEntry(); 
		int indexR = game.indexOfHallOfFameEntry(mostRecent);
		int start = indexR + numberOfEntries/2;
		if (start > game.numberOfHallOfFameEntries() - 1) {
			start = game.numberOfHallOfFameEntries() - 1;
		}
		int end = start - numberOfEntries + 1;
		if (end < 0) {
			end = 0;
		}
		
		for (int i = start; i >= end; i--) {
			TOHallOfFameEntry to = new TOHallOfFameEntry(i+1, game.getHallOfFameEntry(i).getPlayername(), game.getHallOfFameEntry(i).getScore(), result);
		}
		return result;
	}
	
	/*
	 * ACCESSORS FOR CONSTANTS IN MODEL
	 */
	public static int getPlayAreaSide() {
		return Game.PLAY_AREA_SIDE;
	}

	public static int getPaddleVertDistance() {
		return Paddle.VERTICAL_DISTANCE;
	}

	public static int getPaddleWidth() {
		return Paddle.PADDLE_WIDTH;
	}

	public static int getBallDiameter() {
		return Ball.BALL_DIAMETER;
	}

	public static int getBlockSize() {
		return Block.SIZE;
	}

}