package com.epic.model;

/**
 * Represents a player in a game
 *
 */
public class Player {

	private Game currentGame;
	private String name;

	public Game getCurrentGame() {
		return currentGame;
	}

	public String getName() {
		return name;
	}

	public void setCurrentGame(Game currentGame) {
		this.currentGame = currentGame;
	}

	public void setName(String name) {
		this.name = name;
	}
}
