package model;

import java.util.Observable;
import java.util.Random;
/*
 * File: Game.java
 * Author: Songzhe Zhu
 * Purpose: This is mainly control the game.
 */

public class Game extends Observable {
	private Board[][] board;
	private Hunter hunter;
	private int Row, Column, size;
	private char tmp;
	private boolean gameOver;
	Random random = new Random();

	// This is the constructor
	public Game() {
		size = 10;
		tmp = ' ';
		gameOver = false;
		initializeBoard();
	}

	public void startNewGame() {
		initializeBoard();
		// The state of this model just changed so tell any observer to update
		// themselves
		notifyObservers();
	}

	// get the hunter
	public Hunter getHunter() {
		return hunter;
	}

	// get the game board
	public Board[][] getBoard() {
		return board;
	}

	// This will create the game board and set everything
	// randomly as started
	private void initializeBoard() {
		// TODO Auto-generated method stub
		int WumpusRow = random.nextInt(size);
		int WumpusCol = random.nextInt(size);
		int count = 0;
		board = new Board[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				board[i][j] = new Board();
			}
		}
		// initialize the location of wumpus
		board[WumpusRow][WumpusCol].Switch('W');
		// initialize the location of hunter
		for (int i = WumpusRow - 1; i <= WumpusRow + 1; i++) {
			for (int j = WumpusCol - 1; j <= WumpusCol + 1; j++) {
				if (!(i == WumpusRow && j == WumpusCol)) {
					board[HunterAction(i)][HunterAction(j)].Switch('B');
				}
			}
		}
		board[this.HunterAction(WumpusRow - 2)][WumpusCol].Switch('B');
		board[this.HunterAction(WumpusRow + 2)][WumpusCol].Switch('B');
		board[WumpusRow][this.HunterAction(WumpusCol + 2)].Switch('B');
		board[WumpusRow][this.HunterAction(WumpusCol + 2)].Switch('B');
		// initialize the location of pit, goop, slime
		int numofPit = random.nextInt(3) + 3;
		while (numofPit != count) {
			int PitRow = random.nextInt(size);
			int PitCol = random.nextInt(size);
			if (board[PitRow][PitCol].getChar() == ' ') {
				board[PitRow][PitCol].Switch('P');
				GoopandSlime(this.HunterAction(PitRow - 1), PitCol);
				GoopandSlime(this.HunterAction(PitRow + 1), PitCol);
				GoopandSlime(PitRow, this.HunterAction(PitCol + 1));
				GoopandSlime(PitRow, this.HunterAction(PitCol - 1));
				count++;
			}
		}
		HunterStartGame();
	}

	// place the hunter's location
	private void HunterStartGame() {
		// TODO Auto-generated method stub
		int hunterRow = random.nextInt(size);
		int hunterCol = random.nextInt(size);
		while (true) {
			if (board[hunterRow][hunterCol].getChar() == ' ') {
				board[hunterRow][hunterCol].Switch('O');
				board[hunterRow][hunterCol].Change();
				break;
			} else {
				hunterRow = random.nextInt(size);
				hunterCol = random.nextInt(size);
			}
		}
		Row = hunterRow;
		Column = hunterCol;
		hunter = new Hunter(Row, Column, size);
	}

	// Put slimes and goop characters.
	private void GoopandSlime(int row, int col) {
		// TODO Auto-generated method stub
		if (board[row][col].getChar() == ' ') {
			board[row][col].Switch('S');
		} else if (board[row][col].getChar() == 'B') {
			board[row][col].Switch('G');
		}
	}

	// make hunter move
	private int HunterAction(int num) {
		// TODO Auto-generated method stub
		if (num < 0)
			num += size;
		if (num > size - 1)
			num = num - size;
		return num;
	}

	// get the size of the game
	public int size() {
		return size;
	}

	// notify observes when hunter move
	public void HunterMoved(int row, int col) {
		board[Row][Column].Switch(tmp);
		tmp = board[row][col].getChar();
		board[row][col].Switch('O');
		board[row][col].Change();
		if (tmp == 'W' || tmp == 'P')
			GameOverorNot();
		Row = row;
		Column = col;
		this.setChanged();
		this.notifyObservers();
	}

	// check if the hunter shot the arrow
	public boolean ArrowShooted() {
		if (hunter.getArrowRow() == Row && hunter.getArrowColumn() == Column)
			return false;
		else
			return true;
	}

	// check if the arrow hit the wumpus
	public boolean GotWumpus() {
		GameOverorNot();
		this.setChanged();
		this.notifyObservers();
		if (board[hunter.getArrowRow()][hunter.getArrowColumn()].getChar() == 'W')
			return true;
		else
			return false;
	}

	// Check the gameBoard to see if the game was over or not
	public void GameOverorNot() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				board[i][j].Change();
			}
		}
		gameOver = true;
	}

	// get the origin char
	public char getOrigin() {
		return tmp;
	}

	// check if the game is over
	public boolean GameOver() {
		return gameOver;
	}

	// set a default game board to test
	public void SetDefaultBoard(Board[][] b) {
		board = b;
	}

	// set a default hunter for test
	public void SetDefaultHunter(Hunter h) {
		hunter = h;
	}
}
