package view;
/*
 * File: GraphicView.java
 * Author: Songzhe Zhu
 * Purpose: This is the Graphic view of the hunt the wumpus game.
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.Board;
import model.Game;

public class GraphicView extends JPanel implements Observer {

	public static final int GRIDSIZE = 32;
	private Game theGame;
	private Image hunter, ground, blood, goop, wumpus, slime, slimepit;

	// This is the constructor
	public GraphicView(Game game) {
		this.theGame = game;
		this.initializePanel();
	}

	// This method is to set the panel which set the images
	private void initializePanel() {
		// TODO Auto-generated method stub
		try {
			hunter = ImageIO.read(new File("images/TheHunter.png"));
			ground = ImageIO.read(new File("images/Ground.png"));
			blood = ImageIO.read(new File("images/Blood.png"));
			goop = ImageIO.read(new File("images/Goop.png"));
			wumpus = ImageIO.read(new File("images/Wumpus.png"));
			slime = ImageIO.read(new File("images/Slime.png"));
			slimepit = ImageIO.read(new File("images/SlimePit.png"));
		} catch (Exception nfe) {
			System.out.println("ERROR! CAN NOT FIND THE FILE");
			return;
		}
	}

	// update the view
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		this.repaint();
	}

	// paint the graphic view and send notify to observer
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		int size = theGame.size();
		Board[][] gameBoard = theGame.getBoard();

		// draw the other things in the game
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				g2.drawImage(ground, j * GRIDSIZE, i * GRIDSIZE, 32, 32, null);
				if (gameBoard[i][j].getChar() != 'O') {
					Image im = PutImage(gameBoard[i][j].getChar());
					g2.drawImage(im, j * GRIDSIZE, i * GRIDSIZE, 32, 32, null);
				}
			}
		}

		// draw the hunter, set hunter to visible and the other places to dark
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (gameBoard[i][j].getVisible()) {
					if (gameBoard[i][j].getChar() == 'O') {
						Image img = PutImage(theGame.getOrigin());
						g2.drawImage(img, j * GRIDSIZE, i * GRIDSIZE, 32, 32, null);
						g2.drawImage(hunter, j * GRIDSIZE, i * GRIDSIZE, 32, 32, null);
					}
				} else {
					g2.setPaint(Color.BLACK);
					g2.fill(new Rectangle2D.Double(j * GRIDSIZE, i * GRIDSIZE, 32, 32));
				}
			}
		}
	}

	// Put the images in the game
	private Image PutImage(char img) {
		// TODO Auto-generated method stub
		if (img == 'W')
			return wumpus;
		else if (img == 'B')
			return blood;
		else if (img == 'S')
			return slime;
		else if (img == 'G')
			return goop;
		else if (img == 'P')
			return slimepit;
		else
			return ground;
	}
}
