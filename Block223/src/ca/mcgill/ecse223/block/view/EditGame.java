package ca.mcgill.ecse223.block.view;

import javax.swing.JPanel;

import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOGame;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class EditGame extends JPanel implements ActionListener, UpdateablePage {

	private JLabel title;

	private JComboBox<Integer> levels;

	/**
	 * Create the panel.
	 */
	public EditGame() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{50, 0, 45, 0, 50, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		title = new JLabel("EDIT <GAME NAME>");
		title.setFont(new Font("Dialog", Font.BOLD, 21));
		GridBagConstraints gbc_lblEdit = new GridBagConstraints();
		gbc_lblEdit.gridwidth = 3;
		gbc_lblEdit.insets = new Insets(0, 0, 10, 5);
		gbc_lblEdit.gridx = 1;
		gbc_lblEdit.gridy = 1;
		add(title, gbc_lblEdit);

		JButton btnEditProperties = new JButton("EDIT PROPERTIES");
		btnEditProperties.addActionListener(this);
		GridBagConstraints gbc_btnEditProperties = new GridBagConstraints();
		gbc_btnEditProperties.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEditProperties.insets = new Insets(0, 0, 5, 5);
		gbc_btnEditProperties.gridx = 1;
		gbc_btnEditProperties.gridy = 2;
		add(btnEditProperties, gbc_btnEditProperties);

		JLabel lblOr = new JLabel("OR");
		lblOr.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		GridBagConstraints gbc_lblOr = new GridBagConstraints();
		gbc_lblOr.gridheight = 2;
		gbc_lblOr.insets = new Insets(0, 0, 5, 5);
		gbc_lblOr.gridx = 2;
		gbc_lblOr.gridy = 2;
		add(lblOr, gbc_lblOr);

		JButton btnEditLevel = new JButton("EDIT LEVEL");
		btnEditLevel.setActionCommand("LEVEL");
		btnEditLevel.addActionListener(this);
		GridBagConstraints gbc_btnEditLevel = new GridBagConstraints();
		gbc_btnEditLevel.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEditLevel.insets = new Insets(0, 0, 5, 5);
		gbc_btnEditLevel.gridx = 3;
		gbc_btnEditLevel.gridy = 2;
		add(btnEditLevel, gbc_btnEditLevel);

		JButton btnAddBlocks = new JButton("ADD & EDIT BLOCKS");
		btnAddBlocks.setActionCommand("BLOCKS");
		btnAddBlocks.addActionListener(this);
		GridBagConstraints gbc_btnAddBlocks = new GridBagConstraints();
		gbc_btnAddBlocks.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAddBlocks.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddBlocks.gridx = 1;
		gbc_btnAddBlocks.gridy = 3;
		add(btnAddBlocks, gbc_btnAddBlocks);

		levels = new JComboBox<Integer>();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 3;
		gbc_comboBox.gridy = 3;
		add(levels, gbc_comboBox);

		JButton btnNewButton = new JButton("DELETE GAME");
		btnNewButton.setActionCommand("DELETE");
		btnNewButton.addActionListener(this);

		JButton btnSaveGame = new JButton("SAVE GAME");
		btnSaveGame.addActionListener(this);
		GridBagConstraints gbc_btnSaveGame = new GridBagConstraints();
		gbc_btnSaveGame.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSaveGame.insets = new Insets(10, 0, 5, 5);
		gbc_btnSaveGame.gridx = 1;
		gbc_btnSaveGame.gridy = 4;
		add(btnSaveGame, gbc_btnSaveGame);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(10, 0, 5, 5);
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 4;
		add(btnNewButton, gbc_btnNewButton);

		JButton btnNewButton_1 = new JButton("PUBLISH GAME");
		btnNewButton_1.addActionListener(this);

		JButton btnTestGame = new JButton("TEST GAME");
		GridBagConstraints gbc_btnTestGame = new GridBagConstraints();
		gbc_btnTestGame.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnTestGame.gridwidth = 3;
		gbc_btnTestGame.insets = new Insets(0, 0, 5, 5);
		gbc_btnTestGame.gridx = 1;
		gbc_btnTestGame.gridy = 5;
		add(btnTestGame, gbc_btnTestGame);
		btnTestGame.addActionListener(this);
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.gridwidth = 3;
		gbc_btnNewButton_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_1.insets = new Insets(10, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 6;
		add(btnNewButton_1, gbc_btnNewButton_1);

	}
	
	@Override
	public void updateContents() {
		String name = "";
		try {
			TOGame g = Block223Controller.getCurrentDesignableGame();
			levels.removeAllItems();
			for (int i = 1; i <= g.getNrLevels(); i++) {
				levels.addItem(i);
			}
			name = g.getName();

		} catch (InvalidInputException e) {
			Block223Page.getPage().showError(e.getMessage(), "OK");
		}
		title.setText("EDIT " + name);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (levels.getSelectedItem() != null)
			Block223Application.setLevel((Integer) levels.getSelectedItem());

		switch (arg0.getActionCommand()) {
		case "DELETE":
			try {
				Block223Controller.deleteGame(Block223Controller.getCurrentDesignableGame().getName());
				Block223Page.getPage().setState(ViewState.WELCOME);
			} catch (InvalidInputException e) {
				Block223Page.getPage().showError(e.getMessage(), "OK");
			}
			break;
		case "BLOCKS":
			Block223Page.getPage().setState(ViewState.EDIT_BLOCKS);
			break;
		case "LEVEL":
			Block223Page.getPage().setState(ViewState.EDIT_LEVEL);
			break;
		case "EDIT PROPERTIES":
			Block223Page.getPage().setState(ViewState.EDIT_GAME_PREFS);
			break;
		case "SAVE GAME":
			try {
				Block223Controller.saveGame();
				new Error("GAME SAVED", "Your game has been successfully saved!", "OK");
			} catch (InvalidInputException e) {
				Block223Page.getPage().showError(e.getMessage(), "OK");
			}
			break;
		case "PUBLISH GAME":
			try {
				Block223Controller.publishGame();
				Block223Controller.saveGame();
				Block223Page.getPage().setState(ViewState.WELCOME);
			} catch (InvalidInputException e) {
				Block223Page.getPage().showError(e.getMessage(), "OK");
			}
			break;
		case "TEST GAME":
			Block223Page.getPage().setState(ViewState.TEST_GAME);
			break;
		}

	}

}
