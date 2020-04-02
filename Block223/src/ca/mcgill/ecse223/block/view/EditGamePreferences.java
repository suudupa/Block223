package ca.mcgill.ecse223.block.view;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOGame;

import javax.swing.JButton;
import javax.swing.JTextField;

public class EditGamePreferences extends JPanel implements ActionListener, UpdateablePage {

	private JLabel title;
	private JSpinner numLevels;
	private JSpinner minX;
	private JSpinner minY;
	private JSpinner minPaddleLen;
	private JSpinner maxPaddleLen;
	private JSpinner blocksPerLevel;
	private JSpinner incrFactor;
	private JTextField nameBox;


	/**
	 * Create the panel.
	 */
	public EditGamePreferences() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 49, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 54, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		title = new JLabel("Properties of <GAME NAME>:");
		title.setFont(new Font("Dialog", Font.BOLD, 21));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridwidth = 5;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 1;
		add(title, gbc_lblNewLabel_1);

		JLabel lblNewLabel_8 = new JLabel("Number of levels (1-99)");
		lblNewLabel_8.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_8.gridx = 1;
		gbc_lblNewLabel_8.gridy = 2;
		add(lblNewLabel_8, gbc_lblNewLabel_8);

		numLevels = new JSpinner();
		numLevels.setModel(new SpinnerNumberModel(1, 0, 99, 1));
		GridBagConstraints gbc_numLevels = new GridBagConstraints();
		gbc_numLevels.fill = GridBagConstraints.HORIZONTAL;
		gbc_numLevels.insets = new Insets(0, 0, 5, 5);
		gbc_numLevels.gridx = 2;
		gbc_numLevels.gridy = 2;
		add(numLevels, gbc_numLevels);

		JLabel lblNewLabel = new JLabel("Paddle:");
		lblNewLabel.setFont(new Font("Dialog", Font.ITALIC, 12));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 4;
		gbc_lblNewLabel.gridy = 2;
		add(lblNewLabel, gbc_lblNewLabel);

		JLabel lblNewLabel_9 = new JLabel("Blocks per level");
		lblNewLabel_9.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
		gbc_lblNewLabel_9.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_9.gridx = 1;
		gbc_lblNewLabel_9.gridy = 3;
		add(lblNewLabel_9, gbc_lblNewLabel_9);

		blocksPerLevel = new JSpinner();
		GridBagConstraints gbc_blocksPerLevel = new GridBagConstraints();
		gbc_blocksPerLevel.fill = GridBagConstraints.HORIZONTAL;
		gbc_blocksPerLevel.insets = new Insets(0, 0, 5, 5);
		gbc_blocksPerLevel.gridx = 2;
		gbc_blocksPerLevel.gridy = 3;
		add(blocksPerLevel, gbc_blocksPerLevel);

		JLabel lblNewLabel_2 = new JLabel("Minimum paddle length");
		lblNewLabel_2.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 4;
		gbc_lblNewLabel_2.gridy = 3;
		add(lblNewLabel_2, gbc_lblNewLabel_2);

		minPaddleLen = new JSpinner();
		GridBagConstraints gbc_minPaddleLen = new GridBagConstraints();
		gbc_minPaddleLen.fill = GridBagConstraints.HORIZONTAL;
		gbc_minPaddleLen.insets = new Insets(0, 0, 5, 5);
		gbc_minPaddleLen.gridx = 5;
		gbc_minPaddleLen.gridy = 3;
		add(minPaddleLen, gbc_minPaddleLen);

		JLabel lblNewLabel_3 = new JLabel("Maximum paddle length");
		lblNewLabel_3.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 4;
		gbc_lblNewLabel_3.gridy = 4;
		add(lblNewLabel_3, gbc_lblNewLabel_3);

		maxPaddleLen = new JSpinner();
		GridBagConstraints gbc_maxPaddleLen = new GridBagConstraints();
		gbc_maxPaddleLen.fill = GridBagConstraints.HORIZONTAL;
		gbc_maxPaddleLen.insets = new Insets(0, 0, 5, 5);
		gbc_maxPaddleLen.gridx = 5;
		gbc_maxPaddleLen.gridy = 4;
		add(maxPaddleLen, gbc_maxPaddleLen);

		JLabel lblNewLabel_4 = new JLabel("Ball:");
		lblNewLabel_4.setFont(new Font("Dialog", Font.ITALIC, 12));
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 1;
		gbc_lblNewLabel_4.gridy = 5;
		add(lblNewLabel_4, gbc_lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Speed Increase Factor");
		lblNewLabel_5.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 1;
		gbc_lblNewLabel_5.gridy = 6;
		add(lblNewLabel_5, gbc_lblNewLabel_5);

		incrFactor = new JSpinner();
		incrFactor.setModel(new SpinnerNumberModel(1.0,0.0,null,0.01));
		GridBagConstraints gbc_incrFactor = new GridBagConstraints();
		gbc_incrFactor.fill = GridBagConstraints.HORIZONTAL;
		gbc_incrFactor.insets = new Insets(0, 0, 5, 5);

		gbc_incrFactor.gridx = 2;
		gbc_incrFactor.gridy = 6;
		add(incrFactor, gbc_incrFactor);

		JLabel lblNewLabel_6 = new JLabel("Minumum X Speed\n");
		lblNewLabel_6.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 1;
		gbc_lblNewLabel_6.gridy = 7;
		add(lblNewLabel_6, gbc_lblNewLabel_6);

		minX = new JSpinner();
		GridBagConstraints gbc_minX = new GridBagConstraints();
		gbc_minX.fill = GridBagConstraints.HORIZONTAL;
		gbc_minX.insets = new Insets(0, 0, 5, 5);
		gbc_minX.gridx = 2;
		gbc_minX.gridy = 7;
		add(minX, gbc_minX);

		JLabel lblNewLabel_7 = new JLabel("Minimum Y Speed");
		lblNewLabel_7.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_7.gridx = 1;
		gbc_lblNewLabel_7.gridy = 8;
		add(lblNewLabel_7, gbc_lblNewLabel_7);

		minY = new JSpinner();
		GridBagConstraints gbc_minY = new GridBagConstraints();
		gbc_minY.fill = GridBagConstraints.HORIZONTAL;
		gbc_minY.insets = new Insets(0, 0, 5, 5);
		gbc_minY.gridx = 2;
		gbc_minY.gridy = 8;
		add(minY, gbc_minY);

		JButton btnNewButton = new JButton("SAVE");
		btnNewButton.addActionListener(this);

		JLabel lblGameName = new JLabel("Game Name:");
		lblGameName.setFont(new Font("Dialog", Font.ITALIC, 12));
		GridBagConstraints gbc_lblGameName = new GridBagConstraints();
		gbc_lblGameName.anchor = GridBagConstraints.WEST;
		gbc_lblGameName.insets = new Insets(0, 0, 5, 5);
		gbc_lblGameName.gridx = 4;
		gbc_lblGameName.gridy = 8;
		add(lblGameName, gbc_lblGameName);

		nameBox = new JTextField();
		GridBagConstraints gbc_nameBox = new GridBagConstraints();
		gbc_nameBox.insets = new Insets(0, 0, 5, 5);
		gbc_nameBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameBox.gridx = 4;
		gbc_nameBox.gridy = 9;
		add(nameBox, gbc_nameBox);
		nameBox.setColumns(10);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.gridwidth = 2;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 10;
		add(btnNewButton, gbc_btnNewButton);

		JButton btnRename = new JButton("RENAME");
		btnRename.addActionListener(this);
		GridBagConstraints gbc_btnRename = new GridBagConstraints();
		gbc_btnRename.insets = new Insets(0, 0, 5, 5);
		gbc_btnRename.gridx = 4;
		gbc_btnRename.gridy = 10;
		add(btnRename, gbc_btnRename);

	}

	@Override
	public void updateContents() {
		try {
			TOGame g = Block223Controller.getCurrentDesignableGame();
			numLevels.setValue(Integer.valueOf(g.getNrLevels()));
			minX.setValue(Integer.valueOf(g.getMinBallSpeedX()));
			minY.setValue(Integer.valueOf(g.getMinBallSpeedY()));
			minPaddleLen.setValue(Integer.valueOf(g.getMinPaddleLength()));
			maxPaddleLen.setValue(Integer.valueOf(g.getMaxPaddleLength()));
			blocksPerLevel.setValue(Integer.valueOf(g.getNrBlocksPerLevel()));
			incrFactor.setValue(Double.valueOf(g.getBallSpeedIncreaseFactor()));
			nameBox.setText(g.getName());
			title.setText("Properties of " + g.getName() + ":");
		} catch (InvalidInputException e) {
			Block223Page.getPage().showError(e.getMessage(), "OK");
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			switch (arg0.getActionCommand()) {
			case "RENAME":
				TOGame g = Block223Controller.getCurrentDesignableGame();
				Block223Controller.updateGame(nameBox.getText(), g.getNrLevels(), g.getNrBlocksPerLevel(),
						g.getMinBallSpeedX(), g.getMinBallSpeedY(), g.getBallSpeedIncreaseFactor(),
						g.getMaxPaddleLength(), g.getMinPaddleLength());
				g = Block223Controller.getCurrentDesignableGame();
				title.setText("Properties of " + g.getName() + ":");
				Block223Controller.saveGame();
				break;
			case "SAVE":
				Block223Controller.setGameDetails((Integer) numLevels.getValue(), (Integer) blocksPerLevel.getValue(),
						(Integer) minX.getValue(), (Integer) minY.getValue(), (Double) incrFactor.getValue(),
						(Integer) maxPaddleLen.getValue(), (Integer) minPaddleLen.getValue());
				Block223Controller.saveGame();
				Block223Page.getPage().setState(ViewState.EDIT_GAME);
				break;
			}
		} catch (InvalidInputException e) {
			Block223Page.getPage().showError(e.getMessage(), "OK");
		}
	}

}