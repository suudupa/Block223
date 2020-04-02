package ca.mcgill.ecse223.block.view;

import javax.swing.JPanel;
import java.awt.GridBagLayout;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOBlock;
import ca.mcgill.ecse223.block.controller.TOGridCell;

import javax.swing.JButton;
import javax.swing.JComboBox;

/*
 * NOTE: THIS WILL BE MOST COMPLEX PAGE TO IMPLEMENT
 */
public class EditLevel extends JPanel implements ActionListener, ChangeListener, UpdateablePage {

	private ButtonGroup group;
	private JLabel title;
	private JLabel blockCount; 
	private JLabel blockID;
	private JSpinner x;
	private JSpinner y;
	private JComboBox<Integer> blockSelect;
	private JSpinner newX;
	private JSpinner newY;
	private GridBagConstraints gbc_blockSelectLabel;
	private JPanel blockColorDisp;


	/**
	 * Create the panel.
	 */
	public EditLevel() {

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{50, 45, 104, 0, 58, 23, 0, 50, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		title = new JLabel("Level <Level Num>");
		title.setFont(new Font("Dialog", Font.BOLD, 18));
		GridBagConstraints gbc_lblLevel = new GridBagConstraints();
		gbc_lblLevel.gridwidth = 6;
		gbc_lblLevel.insets = new Insets(0, 0, 10, 5);
		gbc_lblLevel.gridx = 1;
		gbc_lblLevel.gridy = 1;
		add(title, gbc_lblLevel);
		
				JLabel lblBlockPositionSelection = new JLabel("Block Position Selection:");
				lblBlockPositionSelection.setFont(new Font("Dialog", Font.ITALIC, 12));
				GridBagConstraints gbc_lblBlockPositionSelection = new GridBagConstraints();
				gbc_lblBlockPositionSelection.gridwidth = 2;
				gbc_lblBlockPositionSelection.anchor = GridBagConstraints.WEST;
				gbc_lblBlockPositionSelection.insets = new Insets(0, 0, 5, 5);
				gbc_lblBlockPositionSelection.gridx = 1;
				gbc_lblBlockPositionSelection.gridy = 2;
				add(lblBlockPositionSelection, gbc_lblBlockPositionSelection);
		
				JLabel lblX = new JLabel("x:");
				lblX.setFont(new Font("Dialog", Font.PLAIN, 12));
				GridBagConstraints gbc_lblX = new GridBagConstraints();
				gbc_lblX.anchor = GridBagConstraints.EAST;
				gbc_lblX.insets = new Insets(0, 0, 5, 5);
				gbc_lblX.gridx = 1;
				gbc_lblX.gridy = 3;
				add(lblX, gbc_lblX);

		group = new ButtonGroup();
				
						x = new JSpinner();
						GridBagConstraints gbc_spinner = new GridBagConstraints();
						gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
						gbc_spinner.insets = new Insets(0, 0, 5, 5);
						gbc_spinner.gridx = 2;
						gbc_spinner.gridy = 3;
						add(x, gbc_spinner);
						x.addChangeListener(this);
				
						JLabel lblContentsOfxy = new JLabel("Block at (x,y):\n");
						lblContentsOfxy.setFont(new Font("Dialog", Font.BOLD, 12));
						GridBagConstraints gbc_lblContentsOfxy = new GridBagConstraints();
						gbc_lblContentsOfxy.gridwidth = 4;
						gbc_lblContentsOfxy.insets = new Insets(0, 0, 5, 5);
						gbc_lblContentsOfxy.gridx = 3;
						gbc_lblContentsOfxy.gridy = 3;
						add(lblContentsOfxy, gbc_lblContentsOfxy);
		
				JLabel lblY = new JLabel("y:");
				lblY.setFont(new Font("Dialog", Font.PLAIN, 12));
				GridBagConstraints gbc_lblY = new GridBagConstraints();
				gbc_lblY.anchor = GridBagConstraints.EAST;
				gbc_lblY.insets = new Insets(0, 0, 5, 5);
				gbc_lblY.gridx = 1;
				gbc_lblY.gridy = 4;
				add(lblY, gbc_lblY);
		
				y = new JSpinner();
				y.addChangeListener(this);
				GridBagConstraints gbc_spinner_1 = new GridBagConstraints();
				gbc_spinner_1.fill = GridBagConstraints.HORIZONTAL;
				gbc_spinner_1.insets = new Insets(0, 0, 5, 5);
				gbc_spinner_1.gridx = 2;
				gbc_spinner_1.gridy = 4;
				add(y, gbc_spinner_1);
		
				blockID = new JLabel("<block>");
				blockID.setFont(new Font("Dialog", Font.PLAIN, 12));
				GridBagConstraints gbc_lblBlockId = new GridBagConstraints();
				gbc_lblBlockId.gridwidth = 2;
				gbc_lblBlockId.insets = new Insets(0, 0, 5, 5);
				gbc_lblBlockId.gridx = 3;
				gbc_lblBlockId.gridy = 4;
				add(blockID, gbc_lblBlockId);
		
		JButton btnViewAllAssignments = new JButton("VIEW ALL ASSIGNMENTS");
		btnViewAllAssignments.setActionCommand("VIEW");
		btnViewAllAssignments.addActionListener(this);
		
		blockColorDisp = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 6;
		gbc_panel.gridy = 4;
		add(blockColorDisp, gbc_panel);
		GridBagConstraints gbc_btnViewAllAssignments = new GridBagConstraints();
		gbc_btnViewAllAssignments.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnViewAllAssignments.gridwidth = 6;
		gbc_btnViewAllAssignments.insets = new Insets(0, 0, 5, 5);
		gbc_btnViewAllAssignments.gridx = 1;
		gbc_btnViewAllAssignments.gridy = 5;
		add(btnViewAllAssignments, gbc_btnViewAllAssignments);

		JLabel lblActionsOnSelected = new JLabel("Actions On Selected Gridspace:");
		lblActionsOnSelected.setFont(new Font("Dialog", Font.ITALIC, 12));
		GridBagConstraints gbc_lblActionsOnSelected = new GridBagConstraints();
		gbc_lblActionsOnSelected.gridwidth = 2;
		gbc_lblActionsOnSelected.anchor = GridBagConstraints.WEST;
		gbc_lblActionsOnSelected.insets = new Insets(0, 0, 5, 5);
		gbc_lblActionsOnSelected.gridx = 1;
		gbc_lblActionsOnSelected.gridy = 6;
		add(lblActionsOnSelected, gbc_lblActionsOnSelected);

		JButton btnMoveBlock = new JButton("MOVE BLOCK TO");
		btnMoveBlock.addActionListener(this);
		GridBagConstraints gbc_lblBlockId1;

		JButton btnSetBlock = new JButton("SET BLOCK");
		btnSetBlock.addActionListener(this);
		GridBagConstraints gbc_btnSetBlock = new GridBagConstraints();
		gbc_btnSetBlock.gridwidth = 2;
		gbc_btnSetBlock.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSetBlock.insets = new Insets(0, 0, 5, 5);
		gbc_btnSetBlock.gridx = 1;
		gbc_btnSetBlock.gridy = 7;
		add(btnSetBlock, gbc_btnSetBlock);

		JButton btnRemoveBlock = new JButton("REMOVE BLOCK");
		btnRemoveBlock.addActionListener(this);
		
				JLabel blockSelectLabel = new JLabel("Block ID:");
				blockSelectLabel.setFont(new Font("Dialog", Font.ITALIC, 12));
				gbc_blockSelectLabel = new GridBagConstraints();
				gbc_blockSelectLabel.gridwidth = 2;
				gbc_blockSelectLabel.insets = new Insets(0, 0, 5, 5);
				gbc_blockSelectLabel.anchor = GridBagConstraints.EAST;
				gbc_blockSelectLabel.gridx = 3;
				gbc_blockSelectLabel.gridy = 7;
				add(blockSelectLabel, gbc_blockSelectLabel);
		
				blockSelect = new JComboBox<>();
				GridBagConstraints gbc_comboBox = new GridBagConstraints();
				gbc_comboBox.gridwidth = 2;
				gbc_comboBox.insets = new Insets(0, 0, 5, 5);
				gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
				gbc_comboBox.gridx = 5;
				gbc_comboBox.gridy = 7;
				add(blockSelect, gbc_comboBox);
		GridBagConstraints gbc_btnRemoveBlock = new GridBagConstraints();
		gbc_btnRemoveBlock.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRemoveBlock.gridwidth = 6;
		gbc_btnRemoveBlock.insets = new Insets(0, 0, 5, 5);
		gbc_btnRemoveBlock.gridx = 1;
		gbc_btnRemoveBlock.gridy = 8;
		add(btnRemoveBlock, gbc_btnRemoveBlock);
		GridBagConstraints gbc_btnMoveBlock = new GridBagConstraints();
		gbc_btnMoveBlock.gridwidth = 2;
		gbc_btnMoveBlock.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnMoveBlock.insets = new Insets(0, 0, 5, 5);
		gbc_btnMoveBlock.gridx = 1;
		gbc_btnMoveBlock.gridy = 9;
		add(btnMoveBlock, gbc_btnMoveBlock);
		
		JLabel lblX_1 = new JLabel("x:");
		lblX_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_lblX_1 = new GridBagConstraints();
		gbc_lblX_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblX_1.gridx = 3;
		gbc_lblX_1.gridy = 9;
		add(lblX_1, gbc_lblX_1);

		newX = new JSpinner();
		GridBagConstraints gbc_newX = new GridBagConstraints();
		gbc_newX.fill = GridBagConstraints.HORIZONTAL;
		gbc_newX.insets = new Insets(0, 0, 5, 5);
		gbc_newX.gridx = 4;
		gbc_newX.gridy = 9;
		add(newX, gbc_newX);
		
		JLabel lblY_1 = new JLabel("y:");
		lblY_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_lblY_1 = new GridBagConstraints();
		gbc_lblY_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblY_1.gridx = 5;
		gbc_lblY_1.gridy = 9;
		add(lblY_1, gbc_lblY_1);

		newY = new JSpinner();
		GridBagConstraints gbc_newY = new GridBagConstraints();
		gbc_newY.fill = GridBagConstraints.HORIZONTAL;
		gbc_newY.insets = new Insets(0, 0, 5, 5);
		gbc_newY.gridx = 6;
		gbc_newY.gridy = 9;
		add(newY, gbc_newY);


		blockCount = new JLabel("0/15 blocks assigned");
		GridBagConstraints gbc_lblxBlocksAssigned = new GridBagConstraints();
		gbc_lblxBlocksAssigned.gridwidth = 4;
		gbc_lblxBlocksAssigned.insets = new Insets(0, 0, 5, 5);
		gbc_lblxBlocksAssigned.gridx = 1;
		gbc_lblxBlocksAssigned.gridy = 10;
		add(blockCount, gbc_lblxBlocksAssigned);

		JButton btnSave = new JButton("SAVE");
		btnSave.addActionListener(this);
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.gridwidth = 2;
		gbc_btnSave.insets = new Insets(0, 0, 5, 5);
		gbc_btnSave.gridx = 5;
		gbc_btnSave.gridy = 10;
		add(btnSave, gbc_btnSave);

	}

	@Override
	public void updateContents() {
		try {
			List<TOBlock> blocks = Block223Controller.getBlocksOfCurrentDesignableGame();
			blockSelect.removeAllItems();
			for (TOBlock b : blocks) {
				blockSelect.addItem(b.getId());
			}

			title.setText("Level " + Block223Application.getLevel());
			updateBlockCount();
			
			x.setModel(new SpinnerNumberModel(1, 1, Block223Controller.getMaxNumberHorizontalBlocks(), 1));
			newX.setModel(new SpinnerNumberModel(1, 1, Block223Controller.getMaxNumberHorizontalBlocks(), 1));
			y.setModel(new SpinnerNumberModel(1, 1, Block223Controller.getMaxNumberVerticalBlocks(), 1));
			newY.setModel(new SpinnerNumberModel(1, 1, Block223Controller.getMaxNumberVerticalBlocks(), 1));
			

			updateBlockID();

		} catch (InvalidInputException ie) {
			//TODO: HANDLE EXCEPTION
			ie.printStackTrace();
		}

	}

	public void updateBlockCount() {
		try {
			int bpl = Block223Controller.getCurrentDesignableGame().getNrBlocksPerLevel();
			int count = Block223Controller.getBlocksAtLevelOfCurrentDesignableGame(Block223Application.getLevel()).size();
			blockCount.setText(count + "/" + bpl + " blocks assigned.");
		} catch (InvalidInputException e) {
			//TODO: HANDLE EXCEPTION
		}
	}

	public TOGridCell updateBlockID() {
		int horizontal = (Integer) x.getValue();
		int vert = (Integer) y.getValue();
		try {
			for (TOGridCell gc : Block223Controller.getBlocksAtLevelOfCurrentDesignableGame(Block223Application.getLevel())) {
				if (gc.getGridHorizontalPosition() == horizontal 
						&& gc.getGridVerticalPosition() == vert) {
					blockID.setText("Block #"+gc.getId());
					blockColorDisp.setVisible(true);
					blockColorDisp.setBackground(new Color(gc.getRed(), gc.getGreen(), gc.getBlue()));
					return gc;
				}
			}
		} catch (InvalidInputException e) {
			//TODO: HANDLE EXCEPTION
			System.out.println("Block ID Update error");
		}
		blockID.setText("EMPTY");
		blockColorDisp.setVisible(false);
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		int xVal = (Integer) x.getValue();
		int yVal = (Integer) y.getValue();
		int newXVal = (Integer) newX.getValue();
		int newYVal = (Integer) newY.getValue();

		try {
			switch (arg0.getActionCommand()) {
			case "SAVE":
				Block223Page.getPage().setState(ViewState.EDIT_GAME);
				Block223Controller.saveGame();
				break;
			case "REMOVE BLOCK":
				Block223Controller.removeBlock(Block223Application.getLevel(), xVal, yVal);
				updateBlockCount();
				updateBlockID();
				break;
			case "SET BLOCK":
				int id = (int) blockSelect.getSelectedItem();
				Block223Controller.getBlockOfCurrentDesignableGame(id);
				Block223Controller.positionBlock(id, Block223Application.getLevel(), xVal, yVal);
				updateBlockCount();
				updateBlockID();
				break;
			case "MOVE BLOCK TO:":
				Block223Controller.moveBlock(Block223Application.getLevel(), xVal, yVal, newXVal, newYVal);
				updateBlockID();
				break;
			case "VIEW":
				List<TOGridCell> grid = Block223Controller.getBlocksAtLevelOfCurrentDesignableGame(Block223Application.getLevel());
				String s = "";
				for (TOGridCell cell : grid) {
					s += "(" + cell.getGridHorizontalPosition() + ", " + cell.getGridVerticalPosition() + "): ";
					s += "Block #" + cell.getId() + ";  ";
				}
				new Error("--Block Assignments--", s, "OK");
 			}
		} catch (InvalidInputException ie) {
			Block223Page.getPage().showError(ie.getMessage(), "OK");
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		updateBlockID();
	}

}
