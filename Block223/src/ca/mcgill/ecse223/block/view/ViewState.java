package ca.mcgill.ecse223.block.view;

import java.awt.Dimension;

import ca.mcgill.ecse223.block.controller.Block223Controller;

public enum ViewState {
	LOGIN(""), 
	WELCOME("Logout"),
	ADD_BLOCK,
	CREATE_ACCOUNT,
	CREATE_GAME,
	EDIT_BLOCKS,
	EDIT_GAME,
	EDIT_GAME_PREFS,
	EDIT_LEVEL,
	UPDATE_BLOCK,
	GAME_PLAY(new Dimension(650, 700)),
	PLAYER_SCREEN("Logout"),
	TEST_GAME;
	
	public static final String DEFAULT_BACK_TEXT = "Back";
	
	private String backText;
	private Dimension dim;
	
	private ViewState(String backText) {
		this.backText = backText;
		this.dim = new Dimension(Block223Controller.getPlayAreaSide() + 100, 
				Block223Controller.getPlayAreaSide() + 160);
	}
	
	private ViewState() {
		this.backText = DEFAULT_BACK_TEXT;
		this.dim = new Dimension(Block223Controller.getPlayAreaSide() + 100, 
				Block223Controller.getPlayAreaSide() + 160);
	}
	
	private ViewState(Dimension d) {
		this.backText = DEFAULT_BACK_TEXT;
		this.dim = d;
	}
	
	public String backText() {
		return backText;
	}
	
	public double width() {
		return this.dim.getWidth();
	}
	
	public double height() {
		return this.dim.getHeight();
	}
	
	public Dimension dim() {
		return dim;
	}
}