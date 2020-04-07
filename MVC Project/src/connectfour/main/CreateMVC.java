package connectfour.main;

import java.util.Scanner;

import connectfour.Controllers.GameController;
import connectfour.models.GameModel;
import connectfour.views.GameViewGraphical;
import connectfour.views.GameViewTextual;
import connectfour.views.GeneralView;

public class CreateMVC {

	private int startOption;
	private GameController controller;
	private GameModel model;
	private GeneralView view;

	public CreateMVC() {

		model = new GameModel();

		do {
			try {
				System.out.println("Please Press 1 for Graphical View or 2 for Textual View.");
				Scanner input = new Scanner(System.in);

				startOption = input.nextInt();
			} catch (Exception e) {
				System.out.print(e.getMessage());
			}

		} while (startOption != 1 && startOption != 2);

		if (startOption == 1) {
			view = new GameViewGraphical(model);
			controller = new GameController(model, view);
			view.linkController(controller);
		} else {
			view = new GameViewTextual(model);
			controller = new GameController(model, view);
			view.linkController(controller);
		}
	}
}