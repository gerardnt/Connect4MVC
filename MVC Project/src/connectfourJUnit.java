import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import connectfour.models.GameModel;
import connectfour.models.GameModel.slot;;

class connectfourJUnit {

	// Where column numbering starts from 1 and ends on 7
	@Test
	public void InvalidMovesTest() {
		GameModel m = new GameModel();
		// check out of bounds columns
		assertFalse(m.legalPlay(-1));
		assertFalse(m.legalPlay(8));
		// check in bounds column
		assertTrue(m.legalPlay(1));

		// check for full column
		m.playMove(slot.Player1, 1);
		m.playMove(slot.Player2, 1);
		m.playMove(slot.Player1, 1);
		m.playMove(slot.Player2, 1);
		m.playMove(slot.Player1, 1);
		m.playMove(slot.Player2, 1);
		assertEquals("Illegal Move", m.playMove(slot.Player1, 1));
	}

	@Test
	void Player1WinTest() {
		GameModel m = new GameModel();
		// column win
		m.playMove(slot.Player1, 1);
		m.playMove(slot.Player1, 1);
		m.playMove(slot.Player1, 1);
		m.playMove(slot.Player1, 1);
		assertTrue(m.hasWon(slot.Player1));

		// diagonal win
		m = new GameModel();
		m.playMove(slot.Player1, 1);
		m.playMove(slot.Player2, 2);
		m.playMove(slot.Player1, 2);
		m.playMove(slot.Player2, 3);
		m.playMove(slot.Player1, 4);
		m.playMove(slot.Player2, 3);
		m.playMove(slot.Player1, 3);
		m.playMove(slot.Player2, 4);
		m.playMove(slot.Player1, 1);
		m.playMove(slot.Player2, 4);
		m.playMove(slot.Player1, 4);
		assertTrue(m.hasWon(slot.Player1));
	}

	@Test
	void Player2WinTest() {
		GameModel m = new GameModel();
		// row win
		m.playMove(slot.Player2, 1);
		m.playMove(slot.Player2, 2);
		m.playMove(slot.Player2, 3);
		m.playMove(slot.Player2, 4);
		assertTrue(m.hasWon(slot.Player2));
	}

	@Test
	void PlayerTieTest() {
		GameModel m = new GameModel();
		m.playMove(slot.Player1, 1);
		m.playMove(slot.Player2, 2);
		m.playMove(slot.Player1, 3);
		m.playMove(slot.Player2, 4);
		m.playMove(slot.Player1, 5);
		m.playMove(slot.Player2, 6);
		m.playMove(slot.Player1, 7);

		m.playMove(slot.Player2, 1);
		m.playMove(slot.Player1, 2);
		m.playMove(slot.Player2, 3);
		m.playMove(slot.Player1, 4);
		m.playMove(slot.Player2, 5);
		m.playMove(slot.Player1, 6);
		m.playMove(slot.Player2, 7);

		m.playMove(slot.Player1, 1);
		m.playMove(slot.Player2, 2);
		m.playMove(slot.Player1, 3);
		m.playMove(slot.Player2, 4);
		m.playMove(slot.Player1, 1);
		m.playMove(slot.Player2, 2);
		m.playMove(slot.Player1, 3);
		m.playMove(slot.Player2, 4);

		m.playMove(slot.Player1, 5);
		m.playMove(slot.Player2, 6);
		m.playMove(slot.Player1, 7);
		m.playMove(slot.Player2, 6);
		m.playMove(slot.Player1, 5);
		m.playMove(slot.Player2, 1);
		m.playMove(slot.Player1, 7);
		m.playMove(slot.Player2, 3);

		m.playMove(slot.Player1, 2);
		m.playMove(slot.Player2, 5);
		m.playMove(slot.Player1, 4);
		m.playMove(slot.Player2, 7);
		m.playMove(slot.Player1, 6);
		m.playMove(slot.Player2, 1);
		m.playMove(slot.Player1, 2);
		m.playMove(slot.Player2, 3);
		m.playMove(slot.Player1, 4);
		m.playMove(slot.Player2, 5);
		m.playMove(slot.Player1, 6);
		
		assertEquals("Tie", m.playMove(slot.Player2, 7));
		assertTrue(m.gameIsOver());
	}

}
