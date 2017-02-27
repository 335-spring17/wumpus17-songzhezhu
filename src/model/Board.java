package model;
/*
 * File: GameBoard.java
 * Author: Songzhe Zhu
 * Purpose: This is the control of the game board
 */
public class Board {
	private boolean visible;
	private char tmp;
	
	//This is the constructor for GameBoard
	public Board(){
		visible = false;
		tmp = ' ';
	}
	
	//switch the character
	public void Switch(char newTmp){
		tmp = newTmp;
	}
	
	//change the visible
	public void Change(){
		visible = true;
	}
	
	//get the character
	public char getChar(){
		return tmp;
	}
	
	//get the visible
	public boolean getVisible(){
		return visible;
	}
}
