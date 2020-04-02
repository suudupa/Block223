package ca.mcgill.ecse223.block.view;

import javax.swing.JPanel;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOGame;
import ca.mcgill.ecse223.block.controller.TOPlayableGame;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class PlayerScreen extends JPanel implements UpdateablePage, ActionListener {
	
	private JComboBox<String> publishedBox;
	private JComboBox<String> savedBox;

	/**
	 * Create the panel.
	 */
	public PlayerScreen() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{50, 0, 0, 0, 50, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblWelcomeToBlock = new JLabel("WELCOME TO BLOCK 223");
		lblWelcomeToBlock.setFont(new Font("Dialog", Font.BOLD, 21));
		GridBagConstraints gbc_lblWelcomeToBlock = new GridBagConstraints();
		gbc_lblWelcomeToBlock.gridwidth = 3;
		gbc_lblWelcomeToBlock.insets = new Insets(0, 0, 10, 5);
		gbc_lblWelcomeToBlock.gridx = 1;
		gbc_lblWelcomeToBlock.gridy = 1;
		add(lblWelcomeToBlock, gbc_lblWelcomeToBlock);
		
		JLabel lblPublishedGames = new JLabel("Published Games:");
		GridBagConstraints gbc_lblPublishedGames = new GridBagConstraints();
		gbc_lblPublishedGames.insets = new Insets(0, 0, 5, 5);
		gbc_lblPublishedGames.gridx = 1;
		gbc_lblPublishedGames.gridy = 2;
		add(lblPublishedGames, gbc_lblPublishedGames);
		
		JLabel lblSavedGames = new JLabel("Saved Games:");
		GridBagConstraints gbc_lblSavedGames = new GridBagConstraints();
		gbc_lblSavedGames.insets = new Insets(0, 0, 5, 5);
		gbc_lblSavedGames.gridx = 3;
		gbc_lblSavedGames.gridy = 2;
		add(lblSavedGames, gbc_lblSavedGames);
		
		publishedBox = new JComboBox<String>();
		GridBagConstraints gbc_publishedBox_1 = new GridBagConstraints();
		gbc_publishedBox_1.insets = new Insets(0, 0, 5, 5);
		gbc_publishedBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_publishedBox_1.gridx = 1;
		gbc_publishedBox_1.gridy = 3;
		add(publishedBox, gbc_publishedBox_1);
		
		savedBox = new JComboBox<String>();
		GridBagConstraints gbc_savedBox_1 = new GridBagConstraints();
		gbc_savedBox_1.insets = new Insets(0, 0, 5, 5);
		gbc_savedBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_savedBox_1.gridx = 3;
		gbc_savedBox_1.gridy = 3;
		add(savedBox, gbc_savedBox_1);
		
		JButton btnStart = new JButton("START");
		btnStart.addActionListener(this);
		GridBagConstraints gbc_btnStart = new GridBagConstraints();
		gbc_btnStart.insets = new Insets(0, 0, 5, 5);
		gbc_btnStart.gridx = 1;
		gbc_btnStart.gridy = 4;
		add(btnStart, gbc_btnStart);
		
		JButton btnResume = new JButton("RESUME");
		btnResume.addActionListener(this);
		GridBagConstraints gbc_btnResume = new GridBagConstraints();
		gbc_btnResume.insets = new Insets(0, 0, 5, 5);
		gbc_btnResume.gridx = 3;
		gbc_btnResume.gridy = 4;
		add(btnResume, gbc_btnResume);

	}

	@Override
	public void updateContents() {
		List<TOPlayableGame> playableGames;
		try {
			playableGames = Block223Controller.getPlayableGames();
		} catch (Exception e) {
			playableGames = new ArrayList<TOPlayableGame>();
		}
		
		savedBox.removeAllItems();
		publishedBox.removeAllItems();
		for (TOPlayableGame g : playableGames) {
			if (g.getNumber() == -1) {
				publishedBox.addItem(g.getName());
			} else {
				savedBox.addItem(g.getName() + " ID:" + g.getNumber());
			}
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		switch (arg0.getActionCommand()) {
		case "START":
			String game = (String) publishedBox.getSelectedItem();
			try {
				Block223Controller.selectPlayableGame(game, -1);
				Block223Page.getPage().setState(ViewState.GAME_PLAY);
			} catch (InvalidInputException e) {
				Block223Page.getPage().showError(e.getMessage(), "OK");
			}
			break;
		case "RESUME":
			try {
				String selection = (String) savedBox.getSelectedItem();
				int id = -1;
				for (int i = selection.length() - 1; i >= 0; i--) {
					if (selection.charAt(i) == ':') {
						id = Integer.valueOf(selection.substring(i + 1));
						break;
					}
				}
				if (id == -1) {
					return;
				} 
				Block223Controller.selectPlayableGame("", id);
				Block223Page.getPage().setState(ViewState.GAME_PLAY);
			} catch (InvalidInputException e) {
				Block223Page.getPage().showError(e.getMessage(), "OK");
			}
		}
	}

}
