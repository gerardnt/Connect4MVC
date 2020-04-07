package connectfour.models;

import java.util.Arrays;

public class GameModel {

	private final int COLUMNS = 8;
	private final int ROWS = 7;
	private slot[][] board;

	/*
	 * Initializes a model for the game board that has all empty spaces and no
	 * played spots yet
	 */
	public GameModel() {
		board = new slot[COLUMNS][ROWS];
		for (slot[] row : board)
			Arrays.fill(row, slot.Empty);
	}

	/*
	 * Returns the current models board with spots that are played or empty
	 * 
	 * @return the board with the information about the game
	 * 
	 */
	public slot[][] getBoard() {
		return board;
	}

	/*
	 * Returns the set column number of the board
	 * 
	 * @return the column number
	 * 
	 */
	public int getColumns() {
		return COLUMNS;
	}

	/*
	 * Returns the set row number of the board
	 * 
	 * @return the row number
	 * 
	 */
	public int getRows() {
		return ROWS;
	}

	/*
	 * Tests whether or not a data that was inputed is valid for the current board
	 * 
	 * @param the column number inputed to play
	 * 
	 * @return boolean whether or not the column was a legal play
	 * 
	 */
	public boolean legalPlay(int column) {

		if (column < 0) {
			return false;
		} else if (column + 1 >= COLUMNS) {
			return false;
		} else if (board[0][column] != slot.Empty) {
			return false;
		} else {
			return true;
		}
	}

	/*
	 * Tells whether the current board has a winner or not
	 * 
	 * @param piece - the enum of which player to test. Player1 or Player2
	 * 
	 */
	public boolean hasWon(slot piece) {

		for (int row = 0; row < ROWS - 1; row++) {
			
			for (int col = 0; col < COLUMNS - 1; col++) {

				// check 4 in row to the left
				if (col - 3 >= 0) {
					
					if (board[row][col] == piece && board[row][col - 1] == piece && board[row][col - 2] == piece
							&& board[row][col - 3] == piece)
						return true;
				}

				// check 4 in row going down and to the right
				if (row - 3 >= 0 && col + 3 < COLUMNS - 1) {
					
					if (board[row][col] == piece && board[row - 1][col + 1] == piece && board[row - 2][col + 2] == piece
							&& board[row - 3][col + 3] == piece)
						return true;
				}

				// check 4 in row going down
				if (row - 3 >= 0) {
					
					if (board[row][col] == piece && board[row - 1][col] == piece && board[row - 2][col] == piece
							&& board[row - 3][col] == piece)
						return true;
				}
				// check 4 in row going down and to the left
				if (row - 3 >= 0 && col - 3 >= 0) {
					
					if (board[row][col] == piece && board[row - 1][col - 1] == piece && board[row - 2][col - 2] == piece
							&& board[row - 3][col - 3] == piece)
						return true;
				}
			}
		}
		// no winner
		return false;
	}

	/*
	 * Plays the move from the user and updates the board accordingly
	 * 
	 * @param who - who played the piece
	 * 
	 * @param column - the column number that was played
	 * 
	 * @return returns a message about the success of adding the piece to the board
	 * 
	 */
	public String playMove(slot who, int column) {

		column -= 1;
		
		// initially check to see if the move is illegal
		if (!legalPlay(column)) {
			return "Illegal Move";
		}

		// play the move
		for (int row = 5; row >= 0; row--) {
			if (board[row][column] == slot.Empty) {
				board[row][column] = who;
				break;
			}
		}

		// print out who won
		if (hasWon(who)) {
			System.out.println(who.name() + " has Won.");
			return "Game Over";
		}

		// print out a tie
		if (isTie()) {
			System.out.println("There is a Tie. The board is full");
			return "Tie";
		}
		return "Piece played";
	}

	// check to see if there is a tie
	private boolean isTie() {
		for (int col = 0; col < COLUMNS-1; col++) {
			
			if (board[0][col] == slot.Empty)
				return false;
		}
		return true;
	}

	// return tie or winning player when the game is over
	public boolean gameIsOver() {
		return isTie() || (hasWon(slot.Player1) || hasWon(slot.Player2));
	}

	// enum for player information
	public enum slot {
		Empty, Player1, Player2
	}
}