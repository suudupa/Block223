package ca.mcgill.ecse223.block.view;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOCurrentlyPlayedGame;

import javax.swing.JButton;

public class PlayGame extends JPanel implements GameCanvasScreen, ActionListener, UpdateablePage {
	private JLabel livesDisplay;
	private JLabel levelDisplay;
	private JLabel hallOfFameTitle;
	private JLabel scoreDisplay;
	private HallOfFameDisplay hof;
	private GameDisplay gd;
	private JButton playButton;
	/**
	 * Create the panel.
	 */
	public PlayGame() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{395, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 395, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		JLabel lblBlock = new JLabel("BLOCK 223");
		lblBlock.setHorizontalAlignment(SwingConstants.CENTER);
		lblBlock.setFont(new Font("Dialog", Font.BOLD, 21));
		GridBagConstraints gbc_lblBlock = new GridBagConstraints();
		gbc_lblBlock.gridheight = 2;
		gbc_lblBlock.insets = new Insets(5, 0, 5, 5);
		gbc_lblBlock.gridx = 0;
		gbc_lblBlock.gridy = 0;
		add(lblBlock, gbc_lblBlock);

		livesDisplay = new JLabel("Lives: #");
		livesDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_livesDisplay = new GridBagConstraints();
		gbc_livesDisplay.anchor = GridBagConstraints.WEST;
		gbc_livesDisplay.insets = new Insets(5, 0, 5, 5);
		gbc_livesDisplay.gridx = 1;
		gbc_livesDisplay.gridy = 0;
		add(livesDisplay, gbc_livesDisplay);

		levelDisplay = new JLabel("Level: #");
		GridBagConstraints gbc_levelDisplay = new GridBagConstraints();
		gbc_levelDisplay.anchor = GridBagConstraints.WEST;
		gbc_levelDisplay.insets = new Insets(5, 0, 5, 0);
		gbc_levelDisplay.gridx = 2;
		gbc_levelDisplay.gridy = 0;
		add(levelDisplay, gbc_levelDisplay);

		scoreDisplay = new JLabel("Score: ");
		GridBagConstraints gbc_scoreDisplay = new GridBagConstraints();
		gbc_scoreDisplay.anchor = GridBagConstraints.WEST;
		gbc_scoreDisplay.gridwidth = 2;
		gbc_scoreDisplay.insets = new Insets(0, 0, 5, 0);
		gbc_scoreDisplay.gridx = 1;
		gbc_scoreDisplay.gridy = 1;
		add(scoreDisplay, gbc_scoreDisplay);

		playButton = new JButton("PLAY");
		playButton.addActionListener(this);
		GridBagConstraints gbc_playButton = new GridBagConstraints();
		gbc_playButton.insets = new Insets(0, 0, 5, 5);
		gbc_playButton.gridx = 0;
		gbc_playButton.gridy = 2;
		add(playButton, gbc_playButton);

		hallOfFameTitle = new JLabel("HALL OF FAME:");
		GridBagConstraints gbc_hallOfFameTitle = new GridBagConstraints();
		gbc_hallOfFameTitle.gridwidth = 2;
		gbc_hallOfFameTitle.insets = new Insets(0, 0, 5, 5);
		gbc_hallOfFameTitle.gridx = 1;
		gbc_hallOfFameTitle.gridy = 2;
		add(hallOfFameTitle, gbc_hallOfFameTitle);

		gd = new GameDisplay(false);
		gd.setVisible(true);
		GridBagConstraints gbc_gd = new GridBagConstraints();
		gbc_gd.fill = GridBagConstraints.BOTH;
		gbc_gd.insets = new Insets(0, 0, 0, 5);
		gbc_gd.gridx = 0;
		gbc_gd.gridy = 3;
		add(gd, gbc_gd);

		hof = new HallOfFameDisplay();
		GridBagConstraints gbc_HallOfFame = new GridBagConstraints();
		gbc_HallOfFame.gridwidth = 2;
		gbc_HallOfFame.fill = GridBagConstraints.BOTH;
		gbc_HallOfFame.gridx = 1;
		gbc_HallOfFame.gridy = 3;
		add(hof, gbc_HallOfFame);

	}

	@Override
	public GameDisplay getGameDisplay() {
		return gd;
	}

	@Override
	public void updateContents() {
		gd.newGame();
		try {
			hof.update();
			TOCurrentlyPlayedGame game = Block223Controller.getCurrentPlayableGame();
			updateStats();
			String name = game.getGamename();
			hallOfFameTitle.setText("<HTML>Hall Of Fame:<br/>" + name + "</html>");
		} catch (InvalidInputException e) {
			Block223Page.getPage().setState(ViewState.PLAYER_SCREEN);
			Block223Page.getPage().showError(e.getMessage(), "OK");
		}
	}

	public void updateStats() {
		try {
			int lives = 0;
			int score = 0;
			int level = 0;
			TOCurrentlyPlayedGame game = Block223Controller.getCurrentPlayableGame();
			level = game.getCurrentLevel();
			lives = game.getLives();
			score = game.getScore();
			levelDisplay.setText("Level " + level);
			scoreDisplay.setText("Score: " + score);
			livesDisplay.setText("Lives: " + lives);
			if (game.isPaused()) {
				playButton.setVisible(true);
			} else {
				playButton.setVisible(false);
			}
		} catch (InvalidInputException e) {
			e.printStackTrace();
			Block223Page.getPage().setState(ViewState.PLAYER_SCREEN);
			Block223Page.getPage().showError(e.getMessage(), "OK");
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand().equals("PLAY")) {
			(new Thread () {
				public void run() {
					try {
						Block223Controller.startGame(Block223Page.getPage());
					} catch (InvalidInputException e) {
						Block223Page.getPage().setState(ViewState.PLAYER_SCREEN);
						Block223Page.getPage().showError(e.getMessage(), "OK");
					}
				}
			}).start();
		}
	}

	public synchronized void endGame(int nrOfLives) {
		playButton.setVisible(false);
		gd.endGame(nrOfLives);
		try {
			hof.update();
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
