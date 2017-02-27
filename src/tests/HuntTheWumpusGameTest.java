package tests;
/*
 * File: HuntTheWumpusGameTest.java
 * Author: Songzhe Zhu
 * Purpose: This is the Junit Test for the game
 */
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import model.Board;
import model.Direction;
import model.Game;
import model.Hunter;

public class HuntTheWumpusGameTest {
	// set up the static game for testing
	// [B] [B] [B] [ ] [ ] [ ] [ ] [ ] [ ] [ ]
	// [B] [W] [B] [B] [O] [ ] [ ] [ ] [ ] [ ]
	// [B] [B] [B] [ ] [ ] [ ] [ ] [ ] [ ] [ ]
	// [ ] [B] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]
	// [ ] [G] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]
	// [S] [P] [S] [ ] [ ] [ ] [ ] [ ] [ ] [ ]
	// [ ] [S] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]
	// [ ] [ ] [ ] [ ] [ ] [ ] [S] [ ] [ ] [ ]
	// [ ] [ ] [ ] [ ] [ ] [S] [P] [S] [ ] [ ]
	// [ ] [B] [ ] [ ] [ ] [ ] [S] [ ] [ ] [ ]
	public Game Game() {
		Game game = new Game();
		Board[][] board = new Board[10][10];
		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 10; col++) {
				board[row][col] = new Board();
			}
		}
		board[0][0].Switch('B');
		board[0][1].Switch('B');
		board[0][2].Switch('B');
		board[1][0].Switch('B');
		board[1][2].Switch('B');
		board[1][3].Switch('B');
		board[2][0].Switch('B');
		board[2][1].Switch('B');
		board[2][2].Switch('B');
		board[3][1].Switch('B');
		board[9][1].Switch('B');

		board[1][1].Switch('W');

		board[4][1].Switch('G');

		board[5][1].Switch('P');
		board[8][6].Switch('P');

		board[5][0].Switch('S');
		board[5][2].Switch('S');
		board[6][1].Switch('S');
		board[8][5].Switch('S');
		board[8][7].Switch('S');
		board[9][6].Switch('S');
		board[7][6].Switch('S');

		board[1][4].Switch('O');
		board[0][0].getVisible();

		game.SetDefaultBoard(board);
		Hunter hunter = new Hunter(0, 0, 10);
		game.SetDefaultHunter(hunter);
		return game;
	}

	// test hunter walk into the Wumpus
	@Test
	public void testGame1() {
		Game theGame = Game();
		assertEquals(10, theGame.size());
		assertEquals(0, theGame.getHunter().getHunterRow());
		assertEquals(0, theGame.getHunter().getHunterColumn());
		theGame.getHunter().HunterMove(Direction.WEST);
		theGame.HunterMoved(theGame.getHunter().getHunterRow(), theGame.getHunter().getHunterColumn());
		theGame.getHunter().HunterMove(Direction.WEST);
		theGame.HunterMoved(theGame.getHunter().getHunterRow(), theGame.getHunter().getHunterColumn());
		theGame.getHunter().HunterMove(Direction.WEST);
		theGame.HunterMoved(theGame.getHunter().getHunterRow(), theGame.getHunter().getHunterColumn());
		assertEquals(' ', theGame.getOrigin());
		assertFalse(theGame.ArrowShooted());
		assertFalse(theGame.GameOver());
	}

	// test hunter walk into the SlimePit
	@Test
	public void testGame2() {
		Game theGame = Game();
		theGame.getHunter().HunterMove(Direction.WEST);
		theGame.HunterMoved(theGame.getHunter().getHunterRow(), theGame.getHunter().getHunterColumn());
		theGame.getHunter().HunterMove(Direction.WEST);
		assertFalse(theGame.GameOver());
		theGame.HunterMoved(theGame.getHunter().getHunterRow(), theGame.getHunter().getHunterColumn());
		theGame.getHunter().HunterMove(Direction.SOUTH);
		theGame.HunterMoved(theGame.getHunter().getHunterRow(), theGame.getHunter().getHunterColumn());
		theGame.getHunter().HunterMove(Direction.SOUTH);
		theGame.HunterMoved(theGame.getHunter().getHunterRow(), theGame.getHunter().getHunterColumn());
		theGame.getHunter().HunterMove(Direction.SOUTH);
		theGame.HunterMoved(theGame.getHunter().getHunterRow(), theGame.getHunter().getHunterColumn());
		theGame.getHunter().HunterMove(Direction.NORTH);
		theGame.HunterMoved(theGame.getHunter().getHunterRow(), theGame.getHunter().getHunterColumn());
		theGame.getHunter().HunterMove(Direction.WEST);
		theGame.HunterMoved(theGame.getHunter().getHunterRow(), theGame.getHunter().getHunterColumn());
		assertEquals(' ', theGame.getOrigin());
		assertFalse(theGame.ArrowShooted());
		assertFalse(theGame.GameOver());
	}

	// test hunter fail to shoot the Wumpus in different direction
	@Test
	public void testGame3() {
		Game theGame = Game();
		theGame.getHunter().ArrowMove(Direction.SOUTH);
		assertTrue(theGame.ArrowShooted());
		assertFalse(theGame.GotWumpus());
		assertTrue(theGame.GameOver());
		
		Game theGame2 = Game();
		theGame2.getHunter().ArrowMove(Direction.WEST);
		assertTrue(theGame2.ArrowShooted());
		assertFalse(theGame2.GotWumpus());
		assertTrue(theGame2.GameOver());
		
		Game theGame3 = Game();
		theGame3.getHunter().ArrowMove(Direction.NORTH);
		assertTrue(theGame3.ArrowShooted());
		assertFalse(theGame3.GotWumpus());
		assertTrue(theGame3.GameOver());
		
		Game theGame4 = Game();
		theGame4.getHunter().ArrowMove(Direction.EAST);
		assertTrue(theGame4.ArrowShooted());
		assertFalse(theGame4.GotWumpus());
		assertTrue(theGame4.GameOver());
	}

	// test hunter hit the Wumpus successfully
	@Test
	public void testGame4() {
		Game theGame = Game();
		theGame.getHunter().HunterMove(Direction.EAST);
		theGame.HunterMoved(theGame.getHunter().getHunterRow(), theGame.getHunter().getHunterColumn());
		theGame.getHunter().HunterMove(Direction.EAST);
		theGame.HunterMoved(theGame.getHunter().getHunterRow(), theGame.getHunter().getHunterColumn());
		theGame.getHunter().HunterMove(Direction.EAST);
		theGame.HunterMoved(theGame.getHunter().getHunterRow(), theGame.getHunter().getHunterColumn());
		theGame.getHunter().HunterMove(Direction.EAST);
		theGame.HunterMoved(theGame.getHunter().getHunterRow(), theGame.getHunter().getHunterColumn());
		theGame.getHunter().HunterMove(Direction.EAST);
		assertTrue(theGame.ArrowShooted());
		assertFalse(theGame.GotWumpus());
		assertTrue(theGame.GameOver());
	}
}
