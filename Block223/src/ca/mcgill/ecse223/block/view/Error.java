package ca.mcgill.ecse223.block.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

public class Error extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public Error(String title, String message, String cancel) {
		message = breakify(message);
		setBounds(150, 150, 350, 180);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 191, 65, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 15, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblWarning = new JLabel(title);
			lblWarning.setFont(new Font("Dialog", Font.BOLD, 21));
			lblWarning.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gbc_lblWarning = new GridBagConstraints();
			gbc_lblWarning.insets = new Insets(0, 0, 5, 5);
			gbc_lblWarning.anchor = GridBagConstraints.NORTHWEST;
			gbc_lblWarning.gridx = 1;
			gbc_lblWarning.gridy = 1;
			contentPanel.add(lblWarning, gbc_lblWarning);
		}
		{
			JLabel lblThisIsA = new JLabel(message);
			GridBagConstraints gbc_lblThisIsA = new GridBagConstraints();
			gbc_lblThisIsA.gridwidth = 2;
			gbc_lblThisIsA.anchor = GridBagConstraints.WEST;
			gbc_lblThisIsA.insets = new Insets(0, 0, 5, 5);
			gbc_lblThisIsA.gridx = 1;
			gbc_lblThisIsA.gridy = 2;
			contentPanel.add(lblThisIsA, gbc_lblThisIsA);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton(cancel);
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(this);
				buttonPane.add(cancelButton);
			}
		}
		
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
	/**
	 * Create the dialog.
	 */
	public Error(String message, String cancel) {
		message = breakify(message);
		setBounds(150, 150, 350, 180);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 191, 65, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 15, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblWarning = new JLabel("WARNING");
			lblWarning.setFont(new Font("Dialog", Font.BOLD, 21));
			lblWarning.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gbc_lblWarning = new GridBagConstraints();
			gbc_lblWarning.insets = new Insets(0, 0, 5, 5);
			gbc_lblWarning.anchor = GridBagConstraints.NORTHWEST;
			gbc_lblWarning.gridx = 1;
			gbc_lblWarning.gridy = 1;
			contentPanel.add(lblWarning, gbc_lblWarning);
		}
		{
			JLabel lblThisIsA = new JLabel(message);
			GridBagConstraints gbc_lblThisIsA = new GridBagConstraints();
			gbc_lblThisIsA.gridwidth = 2;
			gbc_lblThisIsA.anchor = GridBagConstraints.WEST;
			gbc_lblThisIsA.insets = new Insets(0, 0, 5, 5);
			gbc_lblThisIsA.gridx = 1;
			gbc_lblThisIsA.gridy = 2;
			contentPanel.add(lblThisIsA, gbc_lblThisIsA);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton(cancel);
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(this);
				buttonPane.add(cancelButton);
			}
		}
		
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.dispose();	
	}
	
	/**
	 * Puts breaks in a string every so often
	 * @param s
	 * @return
	 */
	public static String breakify(String s) {
		String retVal = "";
		String[] words = s.split(" ");
		int length = 0;
		for (String word : words) {
			if (length + word.length() > 30) {
				retVal += "<br />" + word + " ";
				length = word.length();
			} else {
				retVal += word + " ";
				length += word.length();
			}
		}
		return "<html>" + retVal + "</html>";
	}

}
