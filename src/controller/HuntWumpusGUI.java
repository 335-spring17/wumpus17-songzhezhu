package controller;
/*
 * File: HuntWumpusGameGUI.java
 * Author: Songzhe Zhu
 * Purpose: This is the GUI for HuntTheWumpus Game
 */
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import model.Direction;
import model.Game;
import model.Hunter;
import view.GraphicView;
import view.TextView;

@SuppressWarnings("serial")
public class HuntWumpusGUI extends JFrame {

	// main
	public static void main(String[] args) {
		HuntWumpusGUI game = new HuntWumpusGUI();
		game.setVisible(true);
	}

	private Game theGame;
	private Hunter hunter;
	public static final int width = 450;
	public static final int height = 350;

	// create the move buttons and shot buttons
	private JPanel controlP;
	private JPanel moveP;
	private JPanel shotP;
	private JLabel move = new JLabel("  Move Button");
	private JLabel shot = new JLabel("  Shot Button");
	private JLabel GameStart = new JLabel("Hunting Start", SwingConstants.CENTER);
	private JButton hunterUp = new JButton("\u2191");
	private JButton hunterDown = new JButton("\u2193");
	private JButton hunterLeft = new JButton("\u2190");
	private JButton hunterRight = new JButton("\u2192");
	private JButton arrowUp = new JButton("\u2191");
	private JButton arrowDown = new JButton("\u2193");
	private JButton arrowLeft = new JButton("\u2190");
	private JButton arrowRight = new JButton("\u2192");

	// Constructor
	public HuntWumpusGUI() {
		initializeGameForTheFirstTime();
		SetupLayout();
		addObservers();
	}

	private JPanel TextView;
	private JPanel graphicView;
	private JTabbedPane view;

	// set up the layout and listeners
	private void SetupLayout() {
		// TODO Auto-generated method stub
		TextView = new TextView(theGame);
		graphicView = new GraphicView(theGame);
		controlP = new JPanel();
		view = new JTabbedPane();
		moveP = new JPanel();
		shotP = new JPanel();
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(width + 250, height + 250);
		this.setLocation(100, 100);
		this.setTitle("Hunt the Wumpus");

		view.setSize(height, height);
		view.setLocation(260, 50);
		view.add(graphicView, "GraphicView");
		view.add(TextView, "TextView");

		controlP.setSize((width - 50) / 2, height + 100);
		controlP.setLocation(30, 30);
		controlP.setLayout(null);

		moveP.setSize((width - 50) / 2, (height - 50) / 3);
		moveP.setLocation(0, 50);

		shotP.setSize((width - 50) / 2, (height - 50) / 3);
		shotP.setLocation(0, 200);

		ButtonListener button = new ButtonListener();

		hunterUp.addActionListener(button);
		hunterDown.addActionListener(button);
		hunterLeft.addActionListener(button);
		hunterRight.addActionListener(button);

		arrowUp.addActionListener(button);
		arrowDown.addActionListener(button);
		arrowLeft.addActionListener(button);
		arrowRight.addActionListener(button);

		moveP.setLayout(new BorderLayout());
		moveP.add(hunterUp, BorderLayout.NORTH);
		moveP.add(hunterDown, BorderLayout.SOUTH);
		moveP.add(hunterLeft, BorderLayout.WEST);
		moveP.add(hunterRight, BorderLayout.EAST);
		moveP.add(move, BorderLayout.CENTER);

		shotP.setLayout(new BorderLayout());
		shotP.add(arrowUp, BorderLayout.NORTH);
		shotP.add(arrowDown, BorderLayout.SOUTH);
		shotP.add(arrowLeft, BorderLayout.WEST);
		shotP.add(arrowRight, BorderLayout.EAST);
		shotP.add(shot, BorderLayout.CENTER);

		GameStart.setSize((width - 50) / 2, 200);
		GameStart.setLocation(0, 320);

		controlP.add(moveP);
		controlP.add(shotP);
		controlP.add(GameStart);

		this.add(controlP);
		this.add(view);
	}

	// add observers that user can switch views
	private void addObservers() {
		// TODO Auto-generated method stub
		theGame.addObserver((Observer) TextView);
		theGame.addObserver((Observer) graphicView);
	}

	// set the game start at the beginning
	private void initializeGameForTheFirstTime() {
		// TODO Auto-generated method stub
		theGame = new Game();
		hunter = theGame.getHunter();
	}

	// This is the button listener to control the button actions
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent behave) {
			// TODO Auto-generated method stub
			JButton button = (JButton) behave.getSource();
			if (button == hunterUp)
				hunter.HunterMove(Direction.NORTH);
			if (button == hunterDown)
				hunter.HunterMove(Direction.SOUTH);
			if (button == hunterLeft)
				hunter.HunterMove(Direction.WEST);
			if (button == hunterRight)
				hunter.HunterMove(Direction.EAST);
			// take care of the arrow movement
			if (button == arrowUp)
				hunter.ArrowMove(Direction.NORTH);
			if (button == arrowDown)
				hunter.ArrowMove(Direction.SOUTH);
			if (button == arrowLeft)
				hunter.ArrowMove(Direction.WEST);
			if (button == arrowRight)
				hunter.ArrowMove(Direction.EAST);

			// update the hint
			theGame.HunterMoved(hunter.getHunterRow(), hunter.getHunterColumn());
			if (theGame.getOrigin() == 'P') {
				GameStart.setText(
						"<html><body><p>Opps! You walked into a pit!!</p><br><p>You lost</p><body></html>");
				GameOver();
			} else if (theGame.getOrigin() == 'B') {
				GameStart.setText("BLOOD!! You feel something wrong");
			} else if (theGame.getOrigin() == 'S') {
				GameStart.setText("<html><body><p>Careful!! </p><br><p>Be aware of the ground near you</p><body></html>");
			} else if (theGame.getOrigin() == 'G') {
				GameStart.setText("<html><body><p>DANGER!!! You feel something really bad, may be wumpus and slime</p><body></html>");
			} else if (theGame.getOrigin() == 'W') {
				GameStart.setText("<html><body><p>GAME OVER!!!! You walked into a wumpus,You lost </p><body></html>");
				GameOver();
			} else {
				GameStart.setText("<html><body><p>Hunting Around. Be aware of your surrounders</p><body></html>");
			}

			// update if the arrow hits the wumpus
			if (theGame.ArrowShooted()) {
				if (theGame.GotWumpus()) {
					GameStart.setText(
							"<html><body><p>GOOD JOB!!!! You hit the wumpus</p><br><p>You win</p><body></html>");
				} else {
					GameStart.setText(
							"<html><body><p>GAME OVER!!! You missed the shot</p><br><p>You lost</p><body></html>");
				}
				GameOver();
			}
		}

		// set every buttons to enable after the game is over
		private void GameOver() {
			// TODO Auto-generated method stub
			hunterUp.setEnabled(false);
			hunterDown.setEnabled(false);
			hunterLeft.setEnabled(false);
			hunterRight.setEnabled(false);
			arrowUp.setEnabled(false);
			arrowDown.setEnabled(false);
			arrowLeft.setEnabled(false);
			arrowRight.setEnabled(false);
		}

	}
}
