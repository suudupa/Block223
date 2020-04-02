package ca.mcgill.ecse223.block.persistence;

import ca.mcgill.ecse223.block.model.Block223;

public class Block223Persistence {
	private static String filename = "gameData.data";
	
	public static void save(Block223 game) {
		PersistenceObjectStream.serialize(game);
	}
	
	public static Block223 load() {
		PersistenceObjectStream.setFilename(filename);
		Block223 block223 = (Block223) PersistenceObjectStream.deserialize();
		// model cannot be loaded - create empty Block223
		if (block223 == null) {
			block223 = new Block223();
		}
		else {
			block223.reinitialize();
		}
		return block223;
	}
	
	public static void setFilename(String newFilename) {
		filename = newFilename;
	}

}
