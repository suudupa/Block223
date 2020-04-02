package ca.mcgill.ecse223.block.view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOCurrentBlock;
import ca.mcgill.ecse223.block.controller.TOCurrentlyPlayedGame;

public class GameDisplay extends Canvas {
	public static final int PLAY_AREA_SIDE = Block223Controller.getPlayAreaSide();

	private boolean printData;
	private int finalNumLives;

	public GameDisplay(boolean printData) {
		this.printData = printData;
		setSize(new Dimension(PLAY_AREA_SIDE, PLAY_AREA_SIDE));
		finalNumLives = -2;
	}

	public void update(Graphics g) {
		//Automatically clear canvas
		g.clearRect(0, 0, getWidth(), getHeight());

		//Draws the play area background
		g.setColor(new Color(200,200,200));
		g.fillRect(0, 0, PLAY_AREA_SIDE, PLAY_AREA_SIDE);

		if (finalNumLives != -2) {
			if (finalNumLives > 0) {
				g.setColor(Color.BLACK);
				g.setFont(new Font("Default", Font.BOLD, 20));
				g.drawString("YOU WIN", 130, 235);
			} else {
				g.setColor(Color.BLACK);
				g.setFont(new Font("Default", Font.BOLD, 20));
				g.drawString("Game Over!", 130, 235);
			}
		} else {

			TOCurrentlyPlayedGame game;
			try {
				game = Block223Controller.getCurrentPlayableGame();
			} catch (InvalidInputException e) {
				if (!e.getMessage().equals("A game must be selected to play it.")) {
					e.printStackTrace();
					Block223Page.getPage().showError(e.getMessage(), "OK");
				}
				return;
			}
			if (game == null) {
				g.setColor(Color.BLACK);
				g.setFont(new Font("Default", Font.BOLD, 20));
				g.drawString("PRESS START TO BEGIN TESTING", 20, 20);
				g.setFont(new Font("Default", Font.PLAIN, 12));
				return;
			} else if (game.isPaused()) {
				g.setColor(Color.BLACK);
				g.setFont(new Font("Default", Font.BOLD, 20));
				g.drawString("PAUSED", 130, 235);
				g.setFont(new Font("Default", Font.PLAIN, 12));
				return;
			} else if (game.getLives() == 0) {
				g.setColor(Color.BLACK);
				g.setFont(new Font("Default", Font.BOLD, 20));
				g.drawString("GAME OVER", 20, 20);
				g.setFont(new Font("Default", Font.PLAIN, 12));
				return;
			}



			//Draws paddle:
			g.setColor(Color.BLACK);
			g.fillRect((int)Math.round(game.getCurrentPaddleX()), PLAY_AREA_SIDE - Block223Controller.getPaddleVertDistance() - Block223Controller.getPaddleWidth(),
					(int)Math.round(game.getCurrentPaddleLength()), Block223Controller.getPaddleWidth());
			//Draws the ball:
			g.fillOval( (int) Math.round(game.getCurrentBallX()- Block223Controller.getBallDiameter()/2.),
					(int) Math.round(game.getCurrentBallY() - Block223Controller.getBallDiameter()/2.), 
					Block223Controller.getBallDiameter(), Block223Controller.getBallDiameter());
			//Draws the blocks
			for (TOCurrentBlock b : game.getBlocks()) {
				int size = Block223Controller.getBlockSize();
				g.setColor(new Color(b.getRed(), b.getGreen(), b.getBlue()));
				g.fillRect(b.getX(), b.getY(), size, size);
				g.setColor(Color.BLACK);
				g.drawRect(b.getX(), b.getY(), size, size);
			}

			//Draws the score & level / lives
			if (printData) {
				g.setColor(Color.BLACK);
				g.drawString("LEVEL " + game.getCurrentLevel(), 10, 20);
				g.drawString("SCORE: " + game.getScore(), 10, 40);
				int nLives = game.getLives();
				g.drawString(nLives + " " + (nLives == 1 ? "life": "lives") + " remaining", 10, 60);
			}
		}
	}
	
	public void newGame() {
		finalNumLives = -2;
	}

	public void endGame(int nrOfLives) {
		finalNumLives = nrOfLives;
		repaint();
	}
}
