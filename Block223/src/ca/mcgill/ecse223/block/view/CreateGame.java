package ca.mcgill.ecse223.block.view;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;

import java.awt.Font;
import javax.swing.JButton;

public class CreateGame extends JPanel implements ActionListener {
	private JTextField txtNameOfGame;

	/**
	 * Create the panel.
	 */
	public CreateGame() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{50, 0, 0, 50, 0};
		gridBagLayout.rowHeights = new int[]{0, 64, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblEnt = new JLabel("Enter Name:");
		lblEnt.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_lblEnt = new GridBagConstraints();
		gbc_lblEnt.anchor = GridBagConstraints.EAST;
		gbc_lblEnt.insets = new Insets(0, 0, 5, 5);
		gbc_lblEnt.gridx = 1;
		gbc_lblEnt.gridy = 1;
		add(lblEnt, gbc_lblEnt);
		
		txtNameOfGame = new JTextField();
		txtNameOfGame.setText("");
		GridBagConstraints gbc_txtNameOfGame = new GridBagConstraints();
		gbc_txtNameOfGame.insets = new Insets(0, 10, 5, 5);
		gbc_txtNameOfGame.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNameOfGame.gridx = 2;
		gbc_txtNameOfGame.gridy = 1;
		add(txtNameOfGame, gbc_txtNameOfGame);
		txtNameOfGame.setColumns(10);
		
		JButton btnNewButton = new JButton("CREATE");
		btnNewButton.setActionCommand("CREATE");
		btnNewButton.addActionListener(this);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridwidth = 2;
		gbc_btnNewButton.insets = new Insets(10, 0, 10, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 2;
		add(btnNewButton, gbc_btnNewButton);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand().equals("CREATE")) {
			try {
				Block223Controller.createGame(txtNameOfGame.getText());
				Block223Controller.selectGame(txtNameOfGame.getText());
				Block223Page.getPage().setState(ViewState.EDIT_GAME);
			} catch (InvalidInputException iie) {
				Block223Page.getPage().showError(iie.getMessage(), "OK");
			}
		}
	}
	


}
