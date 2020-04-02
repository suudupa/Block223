package ca.mcgill.ecse223.block.view;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.SwingConstants;

import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOGame;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JButton;

public class WelcomeScreen extends JPanel implements ActionListener, UpdateablePage {
	private JLabel welcomeLabel;
	private JComboBox<String> comboBox;
	
	/**
	 * Create the panel.
	 */
	public WelcomeScreen() {
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{50, 167, 0, 50, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 35, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		welcomeLabel = new JLabel("WELCOME <USER>!");
		welcomeLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		GridBagConstraints gbc_welcomeLabel = new GridBagConstraints();
		gbc_welcomeLabel.gridwidth = 2;
		gbc_welcomeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_welcomeLabel.gridx = 1;
		gbc_welcomeLabel.gridy = 1;
		add(welcomeLabel, gbc_welcomeLabel);
		
		JLabel selectLabel = new JLabel("Select a Game to Edit");
		selectLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		selectLabel.setFont(new Font("Dialog", Font.ITALIC, 12));
		GridBagConstraints gbc_selectLabel = new GridBagConstraints();
		gbc_selectLabel.gridwidth = 2;
		gbc_selectLabel.insets = new Insets(0, 0, 5, 5);
		gbc_selectLabel.gridx = 1;
		gbc_selectLabel.gridy = 2;
		add(selectLabel, gbc_selectLabel);
		
		comboBox = new JComboBox<String>();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 3;
		add(comboBox, gbc_comboBox);
		
		JButton editButton = new JButton("EDIT GAME");
		editButton.setActionCommand("EDIT");
		editButton.addActionListener(this);
		GridBagConstraints gbc_editButton = new GridBagConstraints();
		gbc_editButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_editButton.insets = new Insets(0, 0, 5, 5);
		gbc_editButton.gridx = 2;
		gbc_editButton.gridy = 3;
		editButton.addActionListener(this);
		add(editButton, gbc_editButton);
		
		JLabel lblOr = new JLabel("or");
		lblOr.setFont(new Font("Dialog", Font.ITALIC, 12));
		lblOr.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblOr = new GridBagConstraints();
		gbc_lblOr.gridwidth = 2;
		gbc_lblOr.insets = new Insets(0, 0, 5, 5);
		gbc_lblOr.gridx = 1;
		gbc_lblOr.gridy = 4;
		add(lblOr, gbc_lblOr);
		
		JButton createButton = new JButton("CREATE NEW GAME");
		GridBagConstraints gbc_createButton = new GridBagConstraints();
		gbc_createButton.gridwidth = 2;
		gbc_createButton.insets = new Insets(0, 0, 5, 5);
		gbc_createButton.gridx = 1;
		gbc_createButton.gridy = 5;
		add(createButton, gbc_createButton);
		createButton.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "CREATE NEW GAME":
			Block223Page.getPage().setState(ViewState.CREATE_GAME);
			break;
		case "EDIT":
			String name = (String) comboBox.getSelectedItem();
			try {
				Block223Controller.selectGame(name);
				Block223Page.getPage().setState(ViewState.EDIT_GAME);
			} catch (InvalidInputException ie) {
				Block223Page.getPage().showError(ie.getMessage(), "OK");
			}
			break;
		}
		
	}

	@Override
	public void updateContents() {
		String name = Block223Controller.getCurrentUsername();
		if (name != null)
			welcomeLabel.setText("WELCOME " + name.toUpperCase() + "!");
		List<TOGame> games;
		try {
			games = Block223Controller.getDesignableGames();
		} catch (Exception e) {
			games = new ArrayList<TOGame>();
		}
		comboBox.removeAllItems();
		for (TOGame g : games) {
			comboBox.addItem(g.getName());
		}
	}

}
