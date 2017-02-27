package tests;
/*
 * File: HunterTheWummpustest.java
 * Author: Songzhe Zhu
 * Purpose: This is the Junit Test for the game.
 */
import static org.junit.Assert.*;

import org.junit.Test;

import model.Board;
import model.Direction;
import model.Game;
import model.Hunter;

public class HunterTheWummpusTest {
	
	@Test
	public void testHunterMove() {
		Hunter hunter = new Hunter(2, 2, 10);
		hunter.HunterMove(Direction.NORTH);
		assertEquals(1, hunter.getHunterRow());
		assertEquals(2, hunter.getHunterColumn());
		assertEquals(1, hunter.getArrowRow());
		assertEquals(2, hunter.getArrowColumn());
		hunter.HunterMove(Direction.SOUTH);
		assertEquals(2, hunter.getHunterRow());
		assertEquals(2, hunter.getHunterColumn());
		assertEquals(2, hunter.getArrowRow());
		assertEquals(2, hunter.getArrowColumn());
		hunter.HunterMove(Direction.WEST);
		assertEquals(2, hunter.getHunterRow());
		assertEquals(1, hunter.getHunterColumn());
		assertEquals(2, hunter.getArrowRow());
		assertEquals(1, hunter.getArrowColumn());
		hunter.HunterMove(Direction.EAST);
		assertEquals(2, hunter.getHunterRow());
		assertEquals(2, hunter.getHunterColumn());
		assertEquals(2, hunter.getArrowRow());
		assertEquals(2, hunter.getArrowColumn());
	}

	@Test
	public void testEdge() {
		Hunter hunter = new Hunter(0, 0, 10);
		hunter.HunterMove(Direction.NORTH);
		assertEquals(9, hunter.getHunterRow());
		assertEquals(0, hunter.getHunterColumn());
		assertEquals(9, hunter.getArrowRow());
		assertEquals(0, hunter.getArrowColumn());
		hunter.HunterMove(Direction.SOUTH);
		hunter.HunterMove(Direction.WEST);
		assertEquals(0, hunter.getHunterRow());
		assertEquals(9, hunter.getHunterColumn());
		assertEquals(0, hunter.getArrowRow());
		assertEquals(9, hunter.getArrowColumn());
		hunter = new Hunter(9, 9, 10);
		hunter.HunterMove(Direction.EAST);
		assertEquals(9, hunter.getHunterRow());
		assertEquals(0, hunter.getHunterColumn());
		assertEquals(9, hunter.getArrowRow());
		assertEquals(0, hunter.getArrowColumn());
	}

	@Test
	public void testArrow() {
		Hunter hunter = new Hunter(1, 1, 10);
		hunter.ArrowMove(Direction.NORTH);
		assertEquals(1, hunter.getHunterRow());
		assertEquals(1, hunter.getHunterColumn());
		assertEquals(0, hunter.getArrowRow());
		assertEquals(1, hunter.getArrowColumn());
		hunter.ArrowMove(Direction.SOUTH);
		assertEquals(1, hunter.getHunterRow());
		assertEquals(1, hunter.getHunterColumn());
		assertEquals(2, hunter.getArrowRow());
		assertEquals(1, hunter.getArrowColumn());
		hunter.ArrowMove(Direction.WEST);
		assertEquals(1, hunter.getHunterRow());
		assertEquals(1, hunter.getHunterColumn());
		assertEquals(1, hunter.getArrowRow());
		assertEquals(0, hunter.getArrowColumn());
		hunter.ArrowMove(Direction.EAST);
		assertEquals(1, hunter.getHunterRow());
		assertEquals(1, hunter.getHunterColumn());
		assertEquals(1, hunter.getArrowRow());
		assertEquals(2, hunter.getArrowColumn());
	}

	@Test
	public void testArrowEdge() {
		Hunter hunter = new Hunter(0, 0, 10);
		hunter.ArrowMove(Direction.NORTH);
		assertEquals(0, hunter.getHunterRow());
		assertEquals(0, hunter.getHunterColumn());
		assertEquals(9, hunter.getArrowRow());
		assertEquals(0, hunter.getArrowColumn());
		hunter.ArrowMove(Direction.WEST);
		assertEquals(0, hunter.getHunterRow());
		assertEquals(0, hunter.getHunterColumn());
		assertEquals(0, hunter.getArrowRow());
		assertEquals(9, hunter.getArrowColumn());
		hunter = new Hunter(9, 9, 10);
		hunter.ArrowMove(Direction.EAST);
		assertEquals(9, hunter.getHunterRow());
		assertEquals(9, hunter.getHunterColumn());
		assertEquals(9, hunter.getArrowRow());
		assertEquals(0, hunter.getArrowColumn());
		hunter.ArrowMove(Direction.SOUTH);
		assertEquals(9, hunter.getHunterRow());
		assertEquals(9, hunter.getHunterColumn());
		assertEquals(0, hunter.getArrowRow());
		assertEquals(9, hunter.getArrowColumn());
	}
	
	@Test
	public void testText() {
		Board character = new Board();
		assertFalse(character.getVisible());
		assertEquals(' ',character.getChar());
		character.Change();
		character.Switch('O');
		assertTrue(character.getVisible());
		assertEquals('O',character.getChar());
		character.Change();
		character.Switch('O');
		assertTrue(character.getVisible());
		assertEquals('O',character.getChar());
	}

}
