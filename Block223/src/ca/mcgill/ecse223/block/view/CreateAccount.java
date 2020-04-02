package ca.mcgill.ecse223.block.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;



import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPasswordField;

public class CreateAccount extends JPanel implements ActionListener {
	private JTextField textField;
	private JCheckBox checkBox;
	private JPasswordField playerPassword;
	private JPasswordField playerPasswordConfirm;
	private JPasswordField adminPassword;
	private JPasswordField adminPasswordConfirm;
	private JCheckBox adminAccount;
	private List<JComponent> adminComponents;

	/**
	 * Create the panel.
	 */
	public CreateAccount() {
		adminComponents = new ArrayList<JComponent>();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{50, 124, 124, 50, 0};
		gridBagLayout.rowHeights = new int[]{29, 0, 0, 0, 0, 0, 0, 0, 0, 0, 25, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		JLabel lblNewUser = new JLabel("NEW USER");
		lblNewUser.setFont(new Font("Dialog", Font.BOLD, 24));
		GridBagConstraints gbc_lblNewUser = new GridBagConstraints();
		gbc_lblNewUser.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblNewUser.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewUser.gridx = 1;
		gbc_lblNewUser.gridy = 1;
		add(lblNewUser, gbc_lblNewUser);

		JLabel lblUsername = new JLabel("USERNAME:");
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.anchor = GridBagConstraints.WEST;
		gbc_lblUsername.gridx = 1;
		gbc_lblUsername.gridy = 2;
		add(lblUsername, gbc_lblUsername);

		textField = new JTextField();
		textField.setText("");
		textField.setColumns(10);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.anchor = GridBagConstraints.WEST;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 2;
		add(textField, gbc_textField);

		JLabel lblPassword = new JLabel("PLAYER PASSWORD:");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.anchor = GridBagConstraints.WEST;
		gbc_lblPassword.gridx = 1;
		gbc_lblPassword.gridy = 3;
		add(lblPassword, gbc_lblPassword);

		playerPassword = new JPasswordField();
		GridBagConstraints gbc_playerPassword_1 = new GridBagConstraints();
		gbc_playerPassword_1.insets = new Insets(0, 0, 5, 5);
		gbc_playerPassword_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_playerPassword_1.gridx = 2;
		gbc_playerPassword_1.gridy = 3;
		add(playerPassword, gbc_playerPassword_1);

		JLabel lblReenterPassword = new JLabel("RE-ENTER PASSWORD: ");
		GridBagConstraints gbc_lblReenterPassword = new GridBagConstraints();
		gbc_lblReenterPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblReenterPassword.anchor = GridBagConstraints.WEST;
		gbc_lblReenterPassword.gridx = 1;
		gbc_lblReenterPassword.gridy = 4;
		add(lblReenterPassword, gbc_lblReenterPassword);

		playerPasswordConfirm = new JPasswordField();
		GridBagConstraints gbc_playerPasswordConfirm_1 = new GridBagConstraints();
		gbc_playerPasswordConfirm_1.insets = new Insets(0, 0, 5, 5);
		gbc_playerPasswordConfirm_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_playerPasswordConfirm_1.gridx = 2;
		gbc_playerPasswordConfirm_1.gridy = 4;
		add(playerPasswordConfirm, gbc_playerPasswordConfirm_1);

		JCheckBox chckbxMakeAdminAccount = new JCheckBox("Make Admin Account");
		chckbxMakeAdminAccount.setActionCommand("ADMIN");
		chckbxMakeAdminAccount.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					for (JComponent c : adminComponents) {
						c.setVisible(true);
					}
				} else {
					for (JComponent c : adminComponents) {
						c.setVisible(false);
						if (c instanceof JPasswordField) {
							((JPasswordField)c).setText("");
						}
					}
				};
			}
		});
		chckbxMakeAdminAccount.addActionListener(this);
		GridBagConstraints gbc_chckbxMakeAdminAccount = new GridBagConstraints();
		gbc_chckbxMakeAdminAccount.gridwidth = 2;
		gbc_chckbxMakeAdminAccount.anchor = GridBagConstraints.WEST;
		gbc_chckbxMakeAdminAccount.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxMakeAdminAccount.gridx = 1;
		gbc_chckbxMakeAdminAccount.gridy = 5;
		add(chckbxMakeAdminAccount, gbc_chckbxMakeAdminAccount);

		JLabel lblNewLabel = new JLabel("ADMIN PASSWORD:");
		adminComponents.add(lblNewLabel);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 6;
		add(lblNewLabel, gbc_lblNewLabel);

		adminPassword = new JPasswordField();
		adminComponents.add(adminPassword);
		GridBagConstraints gbc_passwordField_1 = new GridBagConstraints();
		gbc_passwordField_1.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField_1.gridx = 2;
		gbc_passwordField_1.gridy = 6;
		add(adminPassword, gbc_passwordField_1);

		JLabel lblConfirmAdminPassword = new JLabel("CONFIRM ADMIN PASSWORD:");
		adminComponents.add(lblConfirmAdminPassword);
		GridBagConstraints gbc_lblConfirmAdminPassword = new GridBagConstraints();
		gbc_lblConfirmAdminPassword.anchor = GridBagConstraints.EAST;
		gbc_lblConfirmAdminPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblConfirmAdminPassword.gridx = 1;
		gbc_lblConfirmAdminPassword.gridy = 7;
		add(lblConfirmAdminPassword, gbc_lblConfirmAdminPassword);

		adminPasswordConfirm = new JPasswordField();
		adminComponents.add(adminPasswordConfirm);
		GridBagConstraints gbc_passwordField_2 = new GridBagConstraints();
		gbc_passwordField_2.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField_2.gridx = 2;
		gbc_passwordField_2.gridy = 7;
		add(adminPasswordConfirm, gbc_passwordField_2);

		checkBox = new JCheckBox("I agree to terms & conditions");
		GridBagConstraints gbc_chckbxIAgreeTo = new GridBagConstraints();
		gbc_chckbxIAgreeTo.anchor = GridBagConstraints.WEST;
		gbc_chckbxIAgreeTo.gridwidth = 2;
		gbc_chckbxIAgreeTo.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxIAgreeTo.gridx = 1;
		gbc_chckbxIAgreeTo.gridy = 8;
		add(checkBox, gbc_chckbxIAgreeTo);

		JButton button_1 = new JButton("Create New Account");
		button_1.setActionCommand("CREATE");
		button_1.addActionListener(this);
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_button_1.anchor = GridBagConstraints.NORTHWEST;
		gbc_button_1.insets = new Insets(0, 0, 5, 5);
		gbc_button_1.gridwidth = 2;
		gbc_button_1.gridx = 1;
		gbc_button_1.gridy = 9;
		add(button_1, gbc_button_1);

		for (JComponent c : adminComponents) {
			c.setVisible(false);
		}
	}



	@Override
	public void actionPerformed(ActionEvent arg0) {
		switch (arg0.getActionCommand()) {
		case "CREATE":
			String enteredUsername = textField.getText();
			String playPwd = new String(playerPassword.getPassword());
			String playPwdConf = new String(playerPasswordConfirm.getPassword());
			String adminPwd = new String(adminPassword.getPassword());
			String adminPwdConf = new String(adminPasswordConfirm.getPassword());
			if (!playPwd.equals(playPwdConf) || !adminPwd.equals(adminPwdConf)) {
				Block223Page.getPage().showError("Passwords must match", "OK");
				return;
			} else if (!checkBox.isSelected()) {
				Block223Page.getPage().showError("Please agree to terms & conditions", "OK");
				return;
			}
			try {
				Block223Controller.register(enteredUsername, playPwd, adminPwd);
				Block223Page.getPage().setState(ViewState.LOGIN);
			} catch (InvalidInputException e) {
				Block223Page.getPage().showError(e.getMessage(), "OK");
			}
			break;
		}
	}

}
