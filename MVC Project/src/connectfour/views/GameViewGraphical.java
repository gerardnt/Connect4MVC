package connectfour.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.JButton;

import connectfour.models.GameModel;
import connectfour.models.GameModel.slot;
import connectfour.Controllers.GameController;

public class GameViewGraphical implements GeneralView {
	private JFrame frame;
	private GameModel model;
	private GameController controller;
	private VisualBoard graphic;
	private slot[][] board;
	private JButton firstColumn, secondColumn, thirdColumn, fourthColumn, fifthColumn, sixthColumn, seventhColumn;

	/*
	 * Creates a Graphical view of the board with the specified model as input
	 * 
	 * @param model the model object with the information for the game and board
	 */
	public GameViewGraphical(GameModel model) {
		this.model = model;
		board = model.getBoard();
		frame = new JFrame("Connect 4 Graphical");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		graphic = new VisualBoard();
		frame.setContentPane(graphic);
		frame.setSize(100 * model.getColumns(), 100 * model.getRows());
		frame.setVisible(true);
	}

	/*
	 * Links the controller to the view so the view is able to send the controller 
	 * inputs
	 * 
	 * @param the controller that is to be linked
	 * 
	 */
	public void linkController(GameController c) {
		this.controller = c;
	}
	
	/*
	 * Re-renders the textual board with the update information from the model
	 */
	public void reRenderBoard() {
		frame.invalidate();
		frame.validate();
		frame.repaint();
	}

	// inner class for the actual representation of the board
	@SuppressWarnings("serial")
	private class VisualBoard extends JPanel {
		private int startX;
		private int startY;

		/*
		 * Creates a new JPanel object to use with the provided frame. Graphical
		 * representation
		 * 
		 */
		public VisualBoard() {
			startX = 0;
			startY = 0;

			// set up buttons to correspond to columns
			firstColumn = new JButton("One");
			firstColumn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					int columnNumber = 1;
					String played;

					do {
						played = controller.playMove(columnNumber);
						
						if(played.equals("Illegal move") || played.equals("Game Over"));
							break;
					} while (!played.equals("Move Played"));
				}
			});
			secondColumn = new JButton("Two");
			secondColumn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					int columnNumber = 2;
					String played;

					do {
						played = controller.playMove(columnNumber);
						
						if(played.equals("Illegal move") || played.equals("Game Over"));
							break;
					} while (!played.equals("Move Played"));
				}
			});
			thirdColumn = new JButton("Three");
			thirdColumn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					int columnNumber = 3;
					String played;

					do {
						played = controller.playMove(columnNumber);
						
						if(played.equals("Illegal move") || played.equals("Game Over"));
						break;
					} while (!played.equals("Move Played"));
				}
			});
			fourthColumn = new JButton("Four");
			fourthColumn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					int columnNumber = 4;
					String played;

					do {
						played = controller.playMove(columnNumber);
						
						if(played.equals("Illegal move") || played.equals("Game Over"));
						break;
					} while (!played.equals("Move Played"));
				}
			});
			fifthColumn = new JButton("Five");
			fifthColumn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					int columnNumber = 5;
					String played;

					do {
						played = controller.playMove(columnNumber);
						
						if(played.equals("Illegal move") || played.equals("Game Over"));
						break;
					} while (!played.equals("Move Played"));
				}
			});
			sixthColumn = new JButton("Six");
			sixthColumn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					int columnNumber = 6;
					String played;

					do {
						played = controller.playMove(columnNumber);
						
						if(played.equals("Illegal move") || played.equals("Game Over"));
						break;
					} while (!played.equals("Move Played"));
				}
			});
			seventhColumn = new JButton("Seven");
			seventhColumn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					int columnNumber = 7;
					String played;

					do {
						played = controller.playMove(columnNumber);
						
						if(played.equals("Illegal move") || played.equals("Game Over"));
						break;	
					} while (!played.equals("Move Played"));
				}
			});
			this.add(firstColumn);
			this.add(secondColumn);
			this.add(thirdColumn);
			this.add(fourthColumn);
			this.add(fifthColumn);
			this.add(sixthColumn);
			this.add(seventhColumn);
		}

		/*
		 * paints the display for the textual board of connect 4
		 * 
		 * @param g is the graphics object to be used to render the scene
		 * 
		 */
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, 100 * model.getColumns(), 100 * model.getRows());
			int currentX = startX + 85;
			int currentY = startY + 85;

			for (int row = 0; row < model.getRows() - 1; row++) {
				for (int col = 0; col < model.getColumns() - 1; col++) {

					if (board[row][col] == slot.Player1) {
						g.setColor(Color.YELLOW);
					} else if (board[row][col] == slot.Player2) {
						g.setColor(Color.RED);
					} else {
						g.setColor(Color.WHITE);
					}
					g.fillOval(currentX, currentY, 70, 70);
					currentX += 85;
				}
				currentX = startX + 85;
				currentY += 85;
			}
			currentY += 25;
			currentX += 25;

			for (int col = 0; col < model.getColumns() - 1; col++) {
				g.setColor(Color.WHITE);
				g.setFont(new Font("TimesRoman", Font.PLAIN, 38));
				g.drawString((col + 1) + " ", currentX, currentY);
				currentX += 85;
			}
		}
	}
}