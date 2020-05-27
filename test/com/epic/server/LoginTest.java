package com.epic.server;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.epic.model.Game;
import com.epic.model.Player;

class LoginTest {
	private static GameServer gameServer;
	private String gameCode;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		gameServer = new GameServer();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * Set up the test by creating a new game.
	 * 
	 * Keep a reference to that new game so we can use it in the test.
	 * 
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() {
		// create a game for the player to login to
		Game newGame = gameServer.createGame();
		gameCode = newGame.getGameCode();
	}

	@AfterEach
	void tearDown() {
		// remove this game from the game server
		gameServer.endGame(gameCode);
	}

	/**
	 * Simulate a player login, where they provide their name and the game code
	 * 
	 * @throws LoginException
	 * @throws GameNotFoundException
	 */
	@Test
	void testGoodLogin() {
		// login the user
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUsername("Joe");
		loginRequest.setGameCode(gameCode);

		// verify we got a game back
		Game game = gameServer.login(loginRequest);
		assertNotNull(game, "No game returned");
	}

	/**
	 * Login to the game, then check that our player is in the list of active
	 * players
	 * 
	 * @throws GameNotFoundException
	 */
	@Test
	void testActivePlayers() {
		// login the user
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUsername("Joe");
		loginRequest.setGameCode(gameCode);
		gameServer.login(loginRequest);

		// list the players in a game
		List<Player> players = gameServer.getPlayersInGame(gameCode);
		assertNotNull(players, "No player list returned");
		assertEquals(false, players.isEmpty(), "No players returned");

		// verify our user is in the game
		boolean found = false;
		for (Player p : players) {
			if (p.getName().equals("Joe")) {
				found = true;
			}
		}
		assertEquals(true, found, "Player not found in the game");
	}

}
