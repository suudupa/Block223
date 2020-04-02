package ca.mcgill.ecse223.block.persistence;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Code taken directily from the persistence tutorial
 */
public class PersistenceObjectStream {
	
	private static String filename = "gameData.data";

	public static void serialize(Object object) {
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(object);
			out.close();
			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Could not save data to file '" + filename + "'.");
		}

	}

	public static Object deserialize() {
		Object o = null;
		ObjectInputStream in;
		try {
			FileInputStream fileIn = new FileInputStream(filename);
			in = new ObjectInputStream(fileIn);
			o = in.readObject();
			in.close();
			fileIn.close();
		} catch (Exception e) {
			e.printStackTrace();
			o = null;
		}
		return o;
	}
	
	public static void setFilename(String newFilename) {
		filename = newFilename;
	}

}
