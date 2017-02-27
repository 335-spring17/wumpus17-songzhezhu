package model;
/*
 * File: Hunter.java
 * Author: Songzhe Zhu
 * Purpose: This is the model to create a hunter who can move around 
 * 			and also carry an arrow to shot wumpus.
 */
import model.Direction;
public class Hunter {
	private int row, column, arrowRow, arrowColumn;
	private int size;

	// This is the constructor and set everything start
	public Hunter(int StartRow, int StartCol, int gamesize) {
		size = gamesize;
		row = StartRow;
		column = StartCol;
		arrowRow = row;
		arrowColumn = column;
	}

	// Get the hunter's current location's row
	public int getHunterRow() {
		return row;
	}

	// Get the hunter's current location's column
	public int getHunterColumn() {
		return column;
	}

	// Get the arrow's current location's row
	public int getArrowRow() {
		return arrowRow;
	}

	// Get the arrow's current location's column
	public int getArrowColumn() {
		return arrowColumn;
	}

	// Control the move of the hunter
	public void HunterMove(Direction Direc) {
		// set the move base on the game board
		if (Direc == Direction.WEST)
			column--;
		if (Direc == Direction.EAST)
			column++;
		if (Direc == Direction.NORTH)
			row--;
		if (Direc == Direction.SOUTH)
			row++;
		//keep the hunter in board
		if (row < 0)
			row = size - 1;
		if (row > size - 1)
			row = 0;
		if (column < 0)
			column = size - 1;
		if (column > size - 1)
			column = 0;
		// arrow will be with the hunter before he shot it out
		arrowRow = row;
		arrowColumn = column;
	}

	// Control the arrow's shooting direction
	public void ArrowMove(Direction Direc) {
		// Set the direction for arrow base on the game board
		if (Direc == Direction.WEST) {
			arrowColumn = column - 1;
			arrowRow = row;
		}
		if (Direc == Direction.EAST) {
			arrowColumn = column + 1;
			arrowRow = row;
		}
		if (Direc == Direction.NORTH) {
			arrowColumn = column;
			arrowRow = row - 1;
		}
		if (Direc == Direction.SOUTH) {
			arrowColumn = column;
			arrowRow = row + 1;
		}
		//keep the arrow in board
		if (arrowRow < 0)
			arrowRow = size - 1;
		if (arrowRow > size - 1)
			arrowRow = 0;
		if (arrowColumn < 0)
			arrowColumn = size - 1;
		if (arrowColumn > size - 1)
			arrowColumn = 0;
	}
}
