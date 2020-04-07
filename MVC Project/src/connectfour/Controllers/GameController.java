package connectfour.Controllers;

import connectfour.models.GameModel;
import connectfour.models.GameModel.slot;
import connectfour.views.GeneralView;

public class GameController {

	private GameModel model;
	private GeneralView view;
	private slot who;
	private boolean gameOver;

	/*
	 * Initialize the controller. Ask for input for which View to use.
	 * 
	 */
	public GameController(GameModel model, GeneralView view) {
		this.model = model;
		this.view = view;
		who = slot.Player1;
		this.gameOver = false;
	}

	/*
	 * Starts the game by listening to the view for input and then taking that input
	 * and updating / accessing the model for needed information. Then tells the
	 * View to re-render and listens for input from the view until the game is over.
	 * 
	 * @return true or false depending on if the game is able to still be played
	 */
	public String playMove(int input) {
				
		String legal;
		
			if(!gameOver) {
				 legal = model.playMove(who, input);
			}
			else {
				return "Game Over";
			}

				if (legal.equals("Illegal Move")) {
					System.out.println("Illegal Move Please Try Again");
					return "Illegal Move";
				}

				view.reRenderBoard();

				if (!legal.equals("Tie") && !legal.equals("Game Over")) {
					

				if (who == slot.Player1) {
					System.out.println("Player 2's Turn.");
					who = slot.Player2;
				} else {
					System.out.println("Player 1's Turn.");
					who = slot.Player1;
				}
				 return "Move Played";
				}
				else {
					gameOver=true;
					return "Game Over";
				}
		} 
}