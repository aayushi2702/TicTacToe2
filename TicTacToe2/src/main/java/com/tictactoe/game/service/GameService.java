package com.tictactoe.game.service;

import org.springframework.stereotype.Service;

import com.tictactoe.game.enums.Player;
import com.tictactoe.game.enums.Position;
import com.tictactoe.game.response.GameResponse;
import com.tictactoe.game.service.impl.GameBoard;
import com.tictcatoe.game.exceptions.InvalidTurnException;

@Service
public class GameService {

	private static final int ZERO = 0;
	private final GameBoard gameBoard;

	public GameService(GameBoard gameBoard) {
		this.gameBoard = gameBoard;
	}

	public GameResponse playGame(Player player, int position) {

		if (isFirstTurn() && isPlayerO(player)) {
			throw new InvalidTurnException("Player X should move first");
		}
		gameBoard.setPlayerInPosition(Position.getRowColumnValueOfPosition(position), player);
		return new GameResponse("GAME_IN_PROGRESS", getNextPlayer(player), player);
	}

	private boolean isPlayerO(Player player) {
		return player == Player.O;
	}

	private Player getNextPlayer(Player player) {
		return player == Player.X ? Player.O : Player.X;
	}

	private boolean isFirstTurn() {
		return gameBoard.getCountOfPositionsOccupied() == ZERO;
	}
}