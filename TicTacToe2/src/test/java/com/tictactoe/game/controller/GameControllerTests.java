package com.tictactoe.game.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.tictactoe.game.enums.Player;
import com.tictactoe.game.enums.Position;
import com.tictactoe.game.response.GameResponse;
import com.tictactoe.game.service.GameService;
import com.tictcatoe.game.exceptions.InvalidPositionException;
import com.tictcatoe.game.exceptions.InvalidTurnException;
import com.tictcatoe.game.exceptions.PositionOccupiedException;

@RunWith(SpringRunner.class)
@WebMvcTest
class GameControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private GameService gameService;

	@Test
	void playGameAPIShouldGive200Response() throws Exception {
		when(gameService.playGame(Player.X, Position.ONE.getValue()))
				.thenReturn(new GameResponse("GAME_IN_PROGRESS", Player.O, Player.X));
		mockMvc.perform(post("/tictactoe/play/{player}/{position}", Player.X, Position.ONE.getValue()))
				.andExpect(status().isOk());
	}

	@Test
	public void shouldShow403HttpStatusWhenInValidExceptionIsThrown() throws Exception {
		when(gameService.playGame(Player.O, Position.TWO.getValue()))
				.thenThrow(new InvalidTurnException("Player X should move first"));
		mockMvc.perform(post("/tictactoe/play/{player}/{position}", Player.O, Position.TWO.getValue()))
				.andExpect(status().isForbidden());
	}

	@Test
	public void shouldShow403HttpStatusWhenPositionOccupiedExceptionIsThrown() throws Exception {
		when(gameService.playGame(Player.X, Position.FIVE.getValue()))
				.thenThrow(new PositionOccupiedException("Position %s is already occupied"));
		mockMvc.perform(post("/tictactoe/play/{player}/{position}", Player.X, Position.FIVE.getValue()))
				.andExpect(status().isForbidden());
	}

	@Test
	public void shouldShow403HttpStatusWhenInvalidPositionExceptionIsThrown() throws Exception {
		when(gameService.playGame(Player.X, Position.DEFAULT.getValue()))
				.thenThrow(new InvalidPositionException("Position %s is already occupied"));
		mockMvc.perform(post("/tictactoe/play/{player}/{position}", Player.X, Position.DEFAULT.getValue()))
				.andExpect(status().isForbidden());
	}

	@Test
	public void resetGameHandlerAPIFound() throws Exception {
		mockMvc.perform(put("/tictactoe/resetgame")).andExpect(status().isOk());
	}

}