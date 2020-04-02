package ca.mcgill.ecse223.block.view;

import javax.swing.JPanel;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOBlock;

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

public class EditBlocks extends JPanel implements ActionListener, UpdateablePage {
	private int selectedId;
	private JComboBox<Integer> blocks;
	private JLabel screenTitle;
	
	/**
	 * Create the panel.
	 */
	public EditBlocks() {
		selectedId = -1;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{50, 0, 0, 0, 50, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		screenTitle = new JLabel("<GAME NAME>'s Blocks");
		screenTitle.setFont(new Font("Dialog", Font.BOLD, 21));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridwidth = 3;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 1;
		add(screenTitle, gbc_lblNewLabel_1);
		
		JLabel lblSelectBlocks = new JLabel("Select A Block");
		lblSelectBlocks.setFont(new Font("Dialog", Font.ITALIC, 12));
		GridBagConstraints gbc_lblSelectBlocks = new GridBagConstraints();
		gbc_lblSelectBlocks.gridwidth = 3;
		gbc_lblSelectBlocks.insets = new Insets(0, 0, 5, 5);
		gbc_lblSelectBlocks.gridx = 1;
		gbc_lblSelectBlocks.gridy = 2;
		add(lblSelectBlocks, gbc_lblSelectBlocks);
		
		blocks = new JComboBox<Integer>();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 3;
		gbc_comboBox.insets = new Insets(0, 0, 10, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 3;
		add(blocks, gbc_comboBox);
		
		JButton btnEditBlock = new JButton("EDIT BLOCK");
		btnEditBlock.setActionCommand("EDIT");
		btnEditBlock.addActionListener(this);
		GridBagConstraints gbc_btnEditBlock = new GridBagConstraints();
		gbc_btnEditBlock.anchor = GridBagConstraints.WEST;
		gbc_btnEditBlock.insets = new Insets(0, 0, 5, 5);
		gbc_btnEditBlock.gridx = 1;
		gbc_btnEditBlock.gridy = 4;
		add(btnEditBlock, gbc_btnEditBlock);
		
		JLabel lblOr = new JLabel("OR");
		lblOr.setFont(new Font("Dialog", Font.ITALIC, 12));
		GridBagConstraints gbc_lblOr = new GridBagConstraints();
		gbc_lblOr.insets = new Insets(0, 0, 5, 5);
		gbc_lblOr.gridx = 2;
		gbc_lblOr.gridy = 4;
		add(lblOr, gbc_lblOr);
		
		JButton btnDeleteBlock = new JButton("DELETE BLOCK");
		btnDeleteBlock.setActionCommand("DELETE");
		btnDeleteBlock.addActionListener(this);
		GridBagConstraints gbc_btnDeleteBlock = new GridBagConstraints();
		gbc_btnDeleteBlock.anchor = GridBagConstraints.EAST;
		gbc_btnDeleteBlock.insets = new Insets(0, 0, 5, 5);
		gbc_btnDeleteBlock.gridx = 3;
		gbc_btnDeleteBlock.gridy = 4;
		add(btnDeleteBlock, gbc_btnDeleteBlock);
		
		JButton btnAddNewBlock = new JButton("ADD NEW BLOCK");
		btnAddNewBlock.setActionCommand("NEW");
		btnAddNewBlock.addActionListener(this);
		GridBagConstraints gbc_btnAddNewBlock = new GridBagConstraints();
		gbc_btnAddNewBlock.gridwidth = 3;
		gbc_btnAddNewBlock.insets = new Insets(20, 0, 5, 5);
		gbc_btnAddNewBlock.gridx = 1;
		gbc_btnAddNewBlock.gridy = 5;
		add(btnAddNewBlock, gbc_btnAddNewBlock);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		try {
			selectedId = Integer.parseInt(blocks.getSelectedItem().toString());
		} catch (Exception e) {
			selectedId = -1;
		}
		
		switch (arg0.getActionCommand()) {
		case "NEW":
			Block223Page.getPage().setState(ViewState.ADD_BLOCK);
			break;
		case "EDIT":
			if (selectedId == -1) {
				Block223Page.getPage().showError("No block selected", "OK");
				break;
			}
			Block223Page.getPage().setState(ViewState.UPDATE_BLOCK);
			break;
		case "DELETE":
			try {
				Block223Controller.deleteBlock(selectedId);
				updateContents();
			} catch (InvalidInputException e) {
				Block223Page.getPage().showError(e.getMessage(), "OK");
			}
			break;
		}
		
	}
	
	public int getSelection() {
		return selectedId;
	}

	@Override
	public void updateContents() {
		String gameName = "<GAME NAME>";
		try {
			gameName = Block223Controller.getCurrentDesignableGame().getName();
		} catch (Exception e) {
			
		}
		screenTitle.setText(gameName + "'s Blocks");
		
		List<TOBlock> blockList;
		try {
			blockList = Block223Controller.getBlocksOfCurrentDesignableGame();
		} catch (Exception e) {
			blockList = new ArrayList<>();
		}
		blocks.removeAllItems();
		for (TOBlock b : blockList) {
			blocks.addItem(b.getId());
		}
		
	}
}
