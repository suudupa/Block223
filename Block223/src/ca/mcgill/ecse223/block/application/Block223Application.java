package ca.mcgill.ecse223.block.application;

import java.util.LinkedList;
import java.util.Queue;
import javax.swing.UnsupportedLookAndFeelException;
import ca.mcgill.ecse223.block.model.*;
import ca.mcgill.ecse223.block.persistence.Block223Persistence;
import ca.mcgill.ecse223.block.view.Block223Page;

public class Block223Application {

	private static Block223 block223;
	private static Game currentGame; 
	private static UserRole currentUserRole;
	private static PlayedGame currentPlayedGame;
	private static int currentLevel = -1;
	private static Queue<Integer> keyStrokes = new LinkedList<Integer>();
	
	/*
	 * To display the user's name in the UI
	 */
	private static String currentUsername;
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		
		 for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
             if ("GTK+".equals(info.getName())) {
                 javax.swing.UIManager.setLookAndFeel(info.getClassName());
                 break;
             }
         }
		
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Block223Page().setVisible(true); // UI
			}
		});
	}
	
	public static Block223 getBlock223() {
		if (block223 == null) {
			block223 = new Block223();
		}
		return block223;
	}
	
	public static Block223 resetBlock223() {
		block223 = Block223Persistence.load();
		return block223;
	}
	
	public static void setCurrentUserRole(UserRole aUserRole) {
		currentUserRole = aUserRole;
	}
	
	public static UserRole getCurrentUserRole() {
		return currentUserRole;
	}
	
	public static void setCurrentGame(Game aGame) {
		currentGame = aGame;
	}
	
	public static Game getCurrentGame() {
		return currentGame;
	}
	
	public static void setCurrentPlayableGame(PlayedGame g) {
		currentPlayedGame = g;
	}
	
	public static PlayedGame getCurrentPlayableGame() {
		return currentPlayedGame;
	}
	
	public static void setCurrentUsername(String s) {
		currentUsername = s;
	}
	
	public static String getCurrentUsername() {
		return currentUsername;
	}
	
	public static void setLevel(int x) {
		currentLevel = x;
	}
	
	public static int getLevel() {
		return currentLevel;
	}
	
	public static void addKeyStroke(int k) {
		keyStrokes.add(k);
	}
	
	public static Queue<Integer> getKeyStrokes() {
		Queue<Integer> clonedQ = new LinkedList<Integer>();
		for (int i : keyStrokes) {
			clonedQ.add(i);
		}
		keyStrokes.clear();
		return clonedQ;
	}
	
}
