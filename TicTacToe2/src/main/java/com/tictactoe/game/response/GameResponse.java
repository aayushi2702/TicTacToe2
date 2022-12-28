package com.tictactoe.game.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tictactoe.game.enums.Player;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GameResponse {

	private String status;
	private Player nextPlayer;
	private Player currentPlayer;
	private String result;

	public GameResponse(String result, String status) {
		this.result = result;
		this.status = status;
	}

	public String getResult() {
		return result;
	}

	public GameResponse(String status, Player nextPlayer, Player currentPlayer) {
		this.status = status;
		this.nextPlayer = nextPlayer;
		this.currentPlayer = currentPlayer;
	}

	public String getStatus() {
		return status;
	}

	public Player getNextPlayer() {
		return nextPlayer;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}
}