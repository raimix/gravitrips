package gravitrips;

import java.util.Scanner;

public class HumanPlayer extends Player {
	
	public Scanner scan = new Scanner(System.in);
	
	public int getMove(Board board) {
		
		board.display();
		int move = 0;
		
		int i = 0;
		do {
			if (i > 0){
				System.out.print("Invalid move. ");
			}
			System.out.println("your " + getName() + " move (1-" + board.getColumns() + "): ");
			move = scan.nextInt();
			if (move == 99){return move;}
			
			i++;
			
		} while ( move < 1 || move > board.getColumns() || !board.isItValidMove(move-1));
		
		return --move;
	}
	
	public int getValidMove2(Board board){
		int move;
		move = scan.nextInt()-1;
		return move;
	}

}
