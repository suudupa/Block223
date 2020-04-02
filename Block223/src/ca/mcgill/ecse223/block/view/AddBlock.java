package ca.mcgill.ecse223.block.view;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOGame;

import javax.swing.JButton;

public class AddBlock extends JPanel implements UpdateablePage, ActionListener, ChangeListener {
	private JLabel gameLabel;
	private JLabel blockLabel;
	private JSpinner redSpinner;
	private JSpinner greenSpinner;
	private JSpinner blueSpinner;
	private JSpinner pointsSpinner;
	private JPanel colorDisplay;
	
	/**
	 * Create the panel.
	 */
	public AddBlock() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{50, 0, 0, 0, 0, 50, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		gameLabel = new JLabel("Game: <GAME NAME>");
		GridBagConstraints gbc_lblGame = new GridBagConstraints();
		gbc_lblGame.gridwidth = 4;
		gbc_lblGame.anchor = GridBagConstraints.WEST;
		gbc_lblGame.insets = new Insets(0, 0, 5, 5);
		gbc_lblGame.gridx = 1;
		gbc_lblGame.gridy = 1;
		add(gameLabel, gbc_lblGame);
		
		blockLabel = new JLabel("New Block: ");
		GridBagConstraints gbc_lblNewBlock = new GridBagConstraints();
		gbc_lblNewBlock.gridwidth = 4;
		gbc_lblNewBlock.anchor = GridBagConstraints.WEST;
		gbc_lblNewBlock.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewBlock.gridx = 1;
		gbc_lblNewBlock.gridy = 2;
		add(blockLabel, gbc_lblNewBlock);
		
		JLabel lblColor = new JLabel("Color: ");
		lblColor.setFont(new Font("Dialog", Font.ITALIC, 12));
		GridBagConstraints gbc_lblColor = new GridBagConstraints();
		gbc_lblColor.gridheight = 2;
		gbc_lblColor.insets = new Insets(0, 0, 5, 5);
		gbc_lblColor.gridx = 1;
		gbc_lblColor.gridy = 3;
		add(lblColor, gbc_lblColor);
		
		JLabel lblRed = new JLabel("Red:");
		lblRed.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_lblRed = new GridBagConstraints();
		gbc_lblRed.anchor = GridBagConstraints.WEST;
		gbc_lblRed.insets = new Insets(0, 0, 5, 5);
		gbc_lblRed.gridx = 2;
		gbc_lblRed.gridy = 3;
		add(lblRed, gbc_lblRed);
		
		JLabel lblGreen = new JLabel("Green:");
		lblGreen.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_lblGreen = new GridBagConstraints();
		gbc_lblGreen.anchor = GridBagConstraints.WEST;
		gbc_lblGreen.insets = new Insets(0, 0, 5, 5);
		gbc_lblGreen.gridx = 3;
		gbc_lblGreen.gridy = 3;
		add(lblGreen, gbc_lblGreen);
		
		JLabel lblBlue = new JLabel("Blue:");
		lblBlue.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_lblBlue = new GridBagConstraints();
		gbc_lblBlue.anchor = GridBagConstraints.WEST;
		gbc_lblBlue.insets = new Insets(0, 0, 5, 5);
		gbc_lblBlue.gridx = 4;
		gbc_lblBlue.gridy = 3;
		add(lblBlue, gbc_lblBlue);
		
		redSpinner = new JSpinner();
		redSpinner.setModel(new SpinnerNumberModel(0, 0, 255, 1));
		GridBagConstraints gbc_redSpinner = new GridBagConstraints();
		gbc_redSpinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_redSpinner.anchor = GridBagConstraints.WEST;
		gbc_redSpinner.insets = new Insets(0, 0, 5, 5);
		gbc_redSpinner.gridx = 2;
		gbc_redSpinner.gridy = 4;
		add(redSpinner, gbc_redSpinner);
		redSpinner.addChangeListener(this);
		
		greenSpinner = new JSpinner();
		greenSpinner.setModel(new SpinnerNumberModel(0, 0, 255, 1));
		GridBagConstraints gbc_spinner_1 = new GridBagConstraints();
		gbc_spinner_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner_1.anchor = GridBagConstraints.WEST;
		gbc_spinner_1.insets = new Insets(0, 0, 5, 5);
		gbc_spinner_1.gridx = 3;
		gbc_spinner_1.gridy = 4;
		add(greenSpinner, gbc_spinner_1);
		greenSpinner.addChangeListener(this);
		
		blueSpinner = new JSpinner();
		blueSpinner.setModel(new SpinnerNumberModel(0, 0, 255, 1));
		GridBagConstraints gbc_spinner_2 = new GridBagConstraints();
		gbc_spinner_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner_2.anchor = GridBagConstraints.WEST;
		gbc_spinner_2.insets = new Insets(0, 0, 5, 5);
		gbc_spinner_2.gridx = 4;
		gbc_spinner_2.gridy = 4;
		add(blueSpinner, gbc_spinner_2);
		blueSpinner.addChangeListener(this);
		
		colorDisplay = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 3;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 2;
		gbc_panel.gridy = 5;
		add(colorDisplay, gbc_panel);
		
		JLabel lblPoints = new JLabel("Points: ");
		lblPoints.setFont(new Font("Dialog", Font.ITALIC, 12));
		GridBagConstraints gbc_lblPoints = new GridBagConstraints();
		gbc_lblPoints.insets = new Insets(0, 0, 5, 5);
		gbc_lblPoints.gridx = 1;
		gbc_lblPoints.gridy = 6;
		add(lblPoints, gbc_lblPoints);
		
		pointsSpinner = new JSpinner();
		pointsSpinner.setModel(new SpinnerNumberModel(1, 1, 1000, 1));
		GridBagConstraints gbc_pointsSpinner = new GridBagConstraints();
		gbc_pointsSpinner.gridwidth = 3;
		gbc_pointsSpinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_pointsSpinner.anchor = GridBagConstraints.WEST;
		gbc_pointsSpinner.insets = new Insets(0, 0, 5, 5);
		gbc_pointsSpinner.gridx = 2;
		gbc_pointsSpinner.gridy = 6;
		add(pointsSpinner, gbc_pointsSpinner);
		
		JButton btnCreateAdd = new JButton("CREATE & ADD");
		btnCreateAdd.setActionCommand("ADD");
		btnCreateAdd.addActionListener(this);
		GridBagConstraints gbc_btnCreateAdd = new GridBagConstraints();
		gbc_btnCreateAdd.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCreateAdd.gridwidth = 4;
		gbc_btnCreateAdd.insets = new Insets(0, 0, 5, 5);
		gbc_btnCreateAdd.gridx = 1;
		gbc_btnCreateAdd.gridy = 7;
		add(btnCreateAdd, gbc_btnCreateAdd);

	}

	@Override
	public void updateContents() {
		try {
			TOGame g = Block223Controller.getCurrentDesignableGame();
			gameLabel.setText("Game: " + g.getName());
			colorDisplay.setBackground(new Color((int)redSpinner.getValue(),(int) greenSpinner.getValue(),(int) blueSpinner.getValue()));
		} catch (InvalidInputException e) {
			Block223Page.getPage().showError(e.getMessage(), "OK");
			Block223Page.getPage().setState(ViewState.WELCOME);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		switch (arg0.getActionCommand()) {
		case "ADD":
			int red = (int) redSpinner.getValue();
			int green = (int) greenSpinner.getValue();
			int blue = (int) blueSpinner.getValue();
			int points = (int) pointsSpinner.getValue();
			try {
				Block223Controller.addBlock(red, green, blue, points);
				Block223Page.getPage().setState(ViewState.EDIT_BLOCKS);
			} catch (InvalidInputException e) {
				Block223Page.getPage().showError(e.getMessage(), "OK");
			}
			break;
		}
		
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		colorDisplay.setBackground(new Color((int)redSpinner.getValue(),(int) greenSpinner.getValue(),(int) blueSpinner.getValue()));
	}

}
