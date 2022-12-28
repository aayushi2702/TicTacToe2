package com.tictactoe.game.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.tictactoe.game.enums.Player;
import com.tictactoe.game.service.impl.GameBoard;
import com.tictcatoe.game.exceptions.InvalidTurnException;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceTests {

	private GameService gameService;
	private GameBoard gameBoard;

	@Before
	public void setUp() {
		gameBoard = new GameBoard();
		gameService = new GameService(gameBoard);
	}

	@Test
	public void playerXShouldAlwaysGoFirst() {
		assertThat(gameService.playGame(Player.X, 0, 0)).isEqualTo("Player X moved first");
	}

	@Test(expected = InvalidTurnException.class)
	public void shouldThrowInvalidTurnExceptionIfOMovesFirst() {
		gameService.playGame(Player.O, 0, 1);
	}
}