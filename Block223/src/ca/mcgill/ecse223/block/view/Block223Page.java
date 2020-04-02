package ca.mcgill.ecse223.block.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOCurrentlyPlayedGame;
import ca.mcgill.ecse223.block.controller.TOHallOfFameEntry;
import ca.mcgill.ecse223.block.controller.TOPlayableGame;
import ca.mcgill.ecse223.block.model.Game;

import javax.swing.border.LineBorder;

public class Block223Page extends JFrame implements ActionListener, KeyListener, Block223PlayModeInterface {
	public static final  Map<ViewState, JPanel> FRAMES = new HashMap<>();
	private JPanel panel;
	private JButton backButton;
	private ViewState state;
	private String input; 
	private static Block223Page instance = null;
	static {
		FRAMES.put(ViewState.LOGIN, new Login());
		FRAMES.put(ViewState.WELCOME, new WelcomeScreen());
		FRAMES.put(ViewState.CREATE_ACCOUNT, new CreateAccount());
		FRAMES.put(ViewState.CREATE_GAME, new CreateGame());
		FRAMES.put(ViewState.EDIT_BLOCKS, new EditBlocks());
		FRAMES.put(ViewState.EDIT_GAME_PREFS, new EditGamePreferences());
		FRAMES.put(ViewState.EDIT_LEVEL, new EditLevel());
		FRAMES.put(ViewState.EDIT_GAME, new EditGame());
		FRAMES.put(ViewState.ADD_BLOCK, new AddBlock());
		FRAMES.put(ViewState.UPDATE_BLOCK, new UpdateBlock());
		FRAMES.put(ViewState.PLAYER_SCREEN, new PlayerScreen());
		FRAMES.put(ViewState.TEST_GAME, new TestScreen());
		FRAMES.put(ViewState.GAME_PLAY, new PlayGame());
	}

	/**
	 * Create the frame.
	 */
	public Block223Page() {
		Block223Controller.logout();
		setTitle("BLOCK 223");
		instance = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, Block223Controller.getPlayAreaSide() + 100, Block223Controller.getPlayAreaSide() + 160);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{20, 0, 20, 0};
		gridBagLayout.rowHeights = new int[]{20, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);

		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(192, 192, 192), 3, true));
		GridBagLayout panelLayout = new GridBagLayout();
		panelLayout.columnWidths = new int[]{220, 0};
		panelLayout.rowHeights = new int[]{10, 0};
		panelLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		panelLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel.setLayout(panelLayout);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 10, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		getContentPane().add(panel, gbc_panel);

		backButton = new JButton("BACK");
		GridBagConstraints gbc_btnBack = new GridBagConstraints();
		gbc_btnBack.fill = GridBagConstraints.VERTICAL;
		gbc_btnBack.anchor = GridBagConstraints.EAST;
		gbc_btnBack.insets = new Insets(0, 0, 10, 5);
		gbc_btnBack.gridx = 1;
		gbc_btnBack.gridy = 2;
		getContentPane().add(backButton, gbc_btnBack);
		backButton.addActionListener(this);

		for (JPanel jp : FRAMES.values()) {
			panel.add(jp);
			jp.setVisible(false);
		}

		setState(ViewState.LOGIN);

		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);

		input = "";
	}

	public void setState(ViewState vs) {
		if (FRAMES.containsKey(vs) && FRAMES.get(vs) != null) {
			if (FRAMES.containsKey(state) && FRAMES.get(state) != null) {
				FRAMES.get(state).setVisible(false);
			}
			state = vs;
			if (FRAMES.get(vs) instanceof UpdateablePage) {
				((UpdateablePage) FRAMES.get(vs)).updateContents();
			}
			FRAMES.get(vs).setVisible(true);
			setBounds((int)this.getBounds().getX(),(int)this.getBounds().getY(), (int)vs.width(), (int)vs.height());
			setBackButton(vs.backText());
		}
	}

	/**
	 * Sets the back button to either not exist
	 * if an empty string is passed, or changes
	 * what the button says
	 * @param contents
	 */
	public void setBackButton(String contents) {
		if (contents == null || contents.length() == 0) {
			backButton.setVisible(false);
		} else {
			backButton.setText(contents);
			backButton.setVisible(true);
		}
	}

	public static Block223Page getPage() {
		return instance;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource().equals(backButton)) {
			switch (state) {
			case WELCOME:
				Block223Controller.logout();
				setState(ViewState.LOGIN);
				break;
			case ADD_BLOCK:
				setState(ViewState.EDIT_BLOCKS);
				break;
			case CREATE_ACCOUNT:
				setState(ViewState.LOGIN);
				break;
			case CREATE_GAME:
				setState(ViewState.WELCOME);
				break;
			case EDIT_BLOCKS:
				setState(ViewState.EDIT_GAME);
				break;
			case EDIT_GAME:
				setState(ViewState.WELCOME);
				break;
			case EDIT_GAME_PREFS:
				setState(ViewState.EDIT_GAME);
				break;
			case EDIT_LEVEL:
				setState(ViewState.EDIT_GAME);
				break;
			case UPDATE_BLOCK:
				setState(ViewState.EDIT_BLOCKS);
				break;
			case PLAYER_SCREEN:
				Block223Controller.logout();
				setState(ViewState.LOGIN);
				break;
			case TEST_GAME:
				setState(ViewState.EDIT_GAME);
				break;
			case GAME_PLAY:
				setState(ViewState.PLAYER_SCREEN);
				break;
			}
		}
	}

	/**
	 * Displays a popup window with an error.
	 * @param errorMessage The error message to display
	 * @param closeButtonText The text to appear on the button that closes the window
	 */
	public void showError(String errorMessage, String closeButtonText) {
		new Error(errorMessage, closeButtonText);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			input += 'l';
			break;
		case KeyEvent.VK_RIGHT:
			input += 'r';
			break;
		case KeyEvent.VK_SPACE:
			input += ' ';
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public String takeInputs() {
		String temp = input;
		input = "";
		return temp;
	}

	@Override
	public void refresh() {
		TOCurrentlyPlayedGame g;
		try {
			g = Block223Controller.getCurrentPlayableGame();
		} catch (InvalidInputException e) {
			return;
		}
		
		if (state == ViewState.TEST_GAME ) {
			if (g!= null) {
				if (g.isPaused()) {
					if (!backButton.isVisible()) {
						backButton.setVisible(true);
					}
				} else if (!g.isPaused()){
					if (backButton.isVisible()) {
						backButton.setVisible(false); 
					}
				}
				((GameCanvasScreen)FRAMES.get(state)).getGameDisplay().repaint();
			}
		}
		else if (state == ViewState.GAME_PLAY) {
			((PlayGame)FRAMES.get(ViewState.GAME_PLAY)).updateStats();
			((GameCanvasScreen)FRAMES.get(state)).getGameDisplay().repaint();
		}
	}

	@Override
	public synchronized void endGame(int nrOfLives, TOHallOfFameEntry hof) {
		if (state == ViewState.TEST_GAME) {
			((TestScreen)FRAMES.get(state)).endGame(nrOfLives);
		} else if (state == ViewState.GAME_PLAY) {

			((PlayGame)FRAMES.get(state)).endGame(nrOfLives);
		}
		backButton.setVisible(true);
	}

}
