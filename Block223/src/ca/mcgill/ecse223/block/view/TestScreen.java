package ca.mcgill.ecse223.block.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class TestScreen extends JPanel implements GameCanvasScreen, ActionListener, UpdateablePage {
	private GameDisplay gd;
	private JButton playButton;
	private boolean gameOver;

	/**
	 * Panel design is implemented.
	 */
	public TestScreen() {
		gameOver = false;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{390, 0};
		gridBagLayout.rowHeights = new int[]{390, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		gd = new GameDisplay(true);
		gd.setVisible(true);
		GridBagConstraints gbc_canvas = new GridBagConstraints();
		gbc_canvas.fill = GridBagConstraints.BOTH;
		gbc_canvas.gridx = 0;
		gbc_canvas.gridy = 0;
		add(gd, gbc_canvas);
		
		// Adding the buttons and setting action listeners.
		playButton = new JButton("START TEST");
		playButton.setActionCommand("PLAY");
		playButton.addActionListener(this);
		GridBagConstraints gbc_btnPlay = new GridBagConstraints();
		gbc_btnPlay.gridx = 0;
		gbc_btnPlay.gridy = 1;
		add(playButton, gbc_btnPlay);
	}


	@Override
	public GameDisplay getGameDisplay() {
		return gd;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand().equals("PLAY")) {
			(new Thread(){
				public void run() {
					try {
						//When the test game pressed, the button is removed and the testGame page is loaded. 
						if (playButton.getText().equals("START TEST")) {
							gd.newGame();
							playButton.setVisible(false);
							Block223Controller.testGame(Block223Page.getPage());
							if (!gameOver) {
								playButton.setText("PLAY");
								playButton.setVisible(true);
							} else {
								playButton.setVisible(false);
							}
						} else {
							//If the button is not pressed, the page remains in the same state, unchanged.
							playButton.setVisible(false);
							Block223Controller.startGame(Block223Page.getPage());
							if (!gameOver) {
								playButton.setText("PLAY");
								playButton.setVisible(true);
							} else {
								playButton.setVisible(false);
							}
						}
						//Invalid input exceptions are taken into account for.
					} catch (InvalidInputException e) {
						Block223Page.getPage().setState(ViewState.EDIT_GAME);
						Block223Page.getPage().showError(e.getMessage(),"OK");
					}
				}}).start();
		}

	}


	@Override
	public void updateContents() {
		playButton.setText("START TEST");
		playButton.setVisible(true);
		gameOver = false;
	}


	public void endGame(int nrOfLives) {
		gameOver = true;
		gd.endGame(nrOfLives);
		playButton.setVisible(false);
	}
}
