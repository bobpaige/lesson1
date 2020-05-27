package com.epic.server;

public class LoginRequest {
	private String gameCode;
	private String username;

	public String getGameCode() {
		return gameCode;
	}

	public String getUsername() {
		return username;
	}

	public void setGameCode(String gameCode) {
		this.gameCode = gameCode;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
