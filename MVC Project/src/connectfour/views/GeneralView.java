package connectfour.views;

import connectfour.Controllers.GameController;

public interface GeneralView {
	
	/*
	 * Re-renders the textual board with the update information from the model
	 */
	public void reRenderBoard();
	
	/*
	 * Links the controller to the view so the view is able to send the controller 
	 * inputs
	 * 
	 * @param the controller that is to be linked
	 * 
	 */
	public void linkController(GameController c);
}