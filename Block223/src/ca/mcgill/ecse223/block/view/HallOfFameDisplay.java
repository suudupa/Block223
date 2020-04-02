package ca.mcgill.ecse223.block.view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOHallOfFame;
import ca.mcgill.ecse223.block.controller.TOHallOfFameEntry;

import java.awt.GridBagLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.JList;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HallOfFameDisplay extends JPanel implements ActionListener {
	public static final int DEFAULT_SIZE = 5;
	public static final int SIZE_INCREASE_AMOUNT = 5;
	String gameName;

	DefaultListModel<String> listModel;
	
	/**
	 * Create the panel.
	 */
	public HallOfFameDisplay() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		listModel = new DefaultListModel<String>();
		JList<String> list = new JList<String>(listModel);
		JScrollPane scrollableList = new JScrollPane(list);
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.insets = new Insets(0, 0, 5, 0);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 0;
		gbc_list.gridy = 0;
		add(scrollableList, gbc_list);
		
		JButton btnLoadMore = new JButton("LOAD MORE");
		btnLoadMore.addActionListener(this);
		GridBagConstraints gbc_btnLoadMore = new GridBagConstraints();
		gbc_btnLoadMore.gridx = 0;
		gbc_btnLoadMore.gridy = 1;
		add(btnLoadMore, gbc_btnLoadMore);
	}
	
	public void update() throws InvalidInputException {
		listModel.clear();
		try {
			gameName = Block223Controller.getCurrentPlayableGame().getGamename();
		} catch(Exception e) {}
		TOHallOfFame hof = getHallOfFame(0, DEFAULT_SIZE);
		TOHallOfFameEntry firstEntry = null;
		for (TOHallOfFameEntry entry: hof.getEntries()) {
			if (firstEntry == null) {
				firstEntry = entry;
			}
			listModel.addElement((firstEntry.getPosition() - entry.getPosition() + 1) + ". " + entry.getPlayername() + " -- " + entry.getScore());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("LOAD MORE")) {
			try {
				TOHallOfFame hof = getHallOfFame(0, listModel.getSize() + SIZE_INCREASE_AMOUNT);
				listModel.clear();
				TOHallOfFameEntry firstEntry = null;
				for (TOHallOfFameEntry entry: hof.getEntries()) {
					if (firstEntry == null) {
						firstEntry = entry;
					}
					listModel.addElement((firstEntry.getPosition() - entry.getPosition() + 1) + ". " + entry.getPlayername() + " -- " + entry.getScore());
				}
			} catch (InvalidInputException e1) {
				e1.printStackTrace();
				Block223Page.getPage().setState(ViewState.PLAYER_SCREEN);
				Block223Page.getPage().showError(e1.getMessage(), "OK");
			}
		}
	}
	
	public TOHallOfFame getHallOfFame(int start, int end) throws InvalidInputException {
		TOHallOfFame hof;
		try {
			hof = Block223Controller.getHallOfFame(start, end);
		} catch (Exception e) {
			hof = Block223Controller.getHallOfFameFromName(gameName, start, end);
		}
		return hof;
	}
	

}
