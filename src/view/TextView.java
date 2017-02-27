package view;
/*
 * File: TextView.java
 * Author: Songzhe Zhu
 * Purpose: This is the Text View of the 
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.Board;
import model.Game;

public class TextView extends JPanel implements Observer{

	private Game theGame;
	private JLabel[][] board = null;
	
	//This is the constructor
	public TextView(Game game){
		theGame = game;
		this.setBackground(Color.CYAN);
		this.initializePanel();
	}
	
	//Set the panel
	private void initializePanel() {
		// TODO Auto-generated method stub
		int size = theGame.size();
		Board[][] gameboard = theGame.getBoard();
		this.setLayout(new GridLayout(size,size,5,5));
		board = new JLabel[size][size];
		for(int i=0; i<size; i++){
			for(int j=0; j<size; j++){
				board[i][j] = new JLabel(" ", SwingConstants.CENTER);
				board[i][j].setFont(new Font("Arial", Font.BOLD, 10));
				//Put correct characters at the location
				if(gameboard[i][j].getVisible()){
					board[i][j].setText("[" + gameboard[i][j].getChar() + "]");
				}
				else{
					board[i][j].setText("[X]");
				}
				this.add(board[i][j]);
			}
		}
	}

	//update the observer
	@Override
	public void update(Observable observe, Object obj) {
		// TODO Auto-generated method stub
		int size = theGame.size();
		Board[][] gameboard = theGame.getBoard();
		for(int i=0; i<size; i++){
			for(int j=0; j<size; j++){
				if(gameboard[i][j].getVisible())
					board[i][j].setText("[" + gameboard[i][j].getChar() + "]");
				else
					board[i][j].setText("[X]");
				this.add(board[i][j]);
			}
		}
	}

}
