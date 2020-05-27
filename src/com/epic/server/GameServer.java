package com.epic.server;

import java.util.ArrayList;
import java.util.List;

import com.epic.model.Game;
import com.epic.model.Player;

/**
 * Service for retrieving information about games
 *
 */
public class GameServer {
	private List<Game> games = new ArrayList<>();

	/**
	 * Construct a new game and assign a unique code to it.
	 * 
	 * @return
	 */
	public Game createGame() {
		Game newGame = new Game();
		newGame.setGameCode("" + newGame.hashCode());
		games.add(newGame);
		return newGame;
	}

	/**
	 * Login a user to an existing game, identified by the game code.
	 * 
	 * @param loginRequest
	 * @return the game you logged into
	 * @throws LoginException if the game is not found
	 */
	public Game login(LoginRequest loginRequest) throws LoginException {
		Game thisGame = findGame(loginRequest.getGameCode());
		if (thisGame == null) {
			throw new LoginException("Game '" + loginRequest.getGameCode() + "' not found");
		}
		Player p = new Player();
		p.setName(loginRequest.getUsername());
		p.setCurrentGame(thisGame);
		thisGame.addPlayer(p);
		return thisGame;
	}

	/**
	 * Find a game by game code
	 * 
	 * @param gameCode
	 * @return
	 */
	private Game findGame(String gameCode) {
		for (Game g : games) {
			if (g.getGameCode().equals(gameCode)) {
				return g;
			}
		}
		return null;
	}

	/**
	 * Returns the list of players in a game
	 * 
	 * @param gameCode
	 * @return
	 * @throws GameNotFoundException if the game is not found
	 */
	public List<Player> getPlayersInGame(String gameCode) throws GameNotFoundException {
		Game thisGame = findGame(gameCode);
		if (thisGame == null) {
			throw new GameNotFoundException("Game '" + gameCode + "' not found");
		}
		return thisGame.getPlayers();
	}

	/**
	 * End a game by deleting it from the list of active games
	 * 
	 * @param gameCode
	 */
	public void endGame(String gameCode) {
		for (Game g : games) {
			if (g.getGameCode().equals(gameCode)) {
				games.remove(g);
				break;
			}
		}
	}

}
