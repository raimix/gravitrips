package gravitrips;

import java.util.Random;

public class ComputerPlayer extends Player {

	Random rand = new Random();
	
	public int getMove(Board board) {
		int move = 0;
		do {
			move = rand.nextInt(board.getColumns());
		} while (!board.isItValidMove(move));
		
		return move;
	}
}
