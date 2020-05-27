package com.epic.model;

import java.util.ArrayList;
import java.util.List;

public class Game {

	private String gameCode;

	private String gameName;

	private List<Player> players = new ArrayList<>();

	public String getGameCode() {
		return gameCode;
	}

	public String getGameName() {
		return gameName;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setGameCode(String gameCode) {
		this.gameCode = gameCode;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public void addPlayer(Player p) {
		players.add(p);
	}

}
