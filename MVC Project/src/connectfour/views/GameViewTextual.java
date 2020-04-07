package connectfour.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

import connectfour.Controllers.GameController;
import connectfour.models.GameModel;
import connectfour.models.GameModel.slot;

public class GameViewTextual implements GeneralView {
	private JFrame frame;
	private GameModel model;
	private TextualBoard graphic;
	private slot[][] board;
	private GameController controller;

	/*
	 * Creates a textual view of the board with the specified model as input
	 * 
	 * @param model the model object with the information for the game and board
	 */
	public GameViewTextual(GameModel model) {
		this.model = model;
		board = model.getBoard();
		frame = new JFrame("Connect 4 Textual");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		graphic = new TextualBoard();
		frame.setContentPane(graphic);
		frame.setSize(100 * model.getColumns(), 100 * model.getRows());
		frame.setVisible(true);
	}

	/*
	 * Re-renders the textual board with the update information from the model
	 */
	public void reRenderBoard() {
		frame.invalidate();
		frame.validate();
		frame.repaint();
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
		String played;

		do {
			System.out.println("Type the number of the column where you want to play your move.");
			Scanner input = new Scanner(System.in);
			int column = input.nextInt();
			played = controller.playMove(column);			
		} while (!played.equals("Game Over"));
	}

	/*
	 * Receives the users input for the controller to take and update the model
	 * accordingly
	 * 
	 * @return the inputed column number from the user
	 */
	public void getInput() {
		String played;

		do {
			System.out.println("Type the number of the column where you want to play your move.");
			Scanner input = new Scanner(System.in);
			int column = input.nextInt();
			played = controller.playMove(column);
			
			if(played.equals("Illegal move") || played.equals("Game Over"));
			break;	
		} while (!played.equals("Move Played"));
	}

	// inner class for the actual representation of the board
	@SuppressWarnings("serial")
	private class TextualBoard extends JPanel {
		private int startX;
		private int startY;

		/*
		 * Creates a new JPanel object to use with the provided frame. Textual
		 * representation
		 * 
		 */
		public TextualBoard() {
			startX = 0;
			startY = 0;
		}

		/*
		 * paints the display for the textual board of connect 4
		 * 
		 * @param g is the graphics object to be used to render the scene
		 * 
		 */
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.GRAY);
			g.fillRect(0, 0, 100 * model.getColumns(), 100 * model.getRows());
			int currentX = startX + 85;
			int currentY = startY + 85;

			for (int row = 0; row < model.getRows() - 1; row++) {
				for (int col = 0; col < model.getColumns() - 1; col++) {

					if (board[row][col] == slot.Player1) {
						g.setColor(Color.YELLOW);
						g.setFont(new Font("TimesRoman", Font.BOLD, 38));
						g.drawString("Y", currentX, currentY);
					} else if (board[row][col] == slot.Player2) {
						g.setColor(Color.RED);
						g.setFont(new Font("TimesRoman", Font.BOLD, 38));
						g.drawString("R", currentX, currentY);
					} else {
						g.setColor(Color.WHITE);
						g.setFont(new Font("TimesRoman", Font.ITALIC, 38));
						g.drawString("X", currentX, currentY);
					}
					currentX += 85;
				}
				currentX = startX + 85;
				currentY += 85;
			}

			for (int col = 0; col < model.getColumns() - 1; col++) {
				g.setColor(Color.WHITE);
				g.setFont(new Font("TimesRoman", Font.PLAIN, 38));
				g.drawString((col + 1) + " ", currentX, currentY);
				currentX += 85;
			}
		}
	}
}