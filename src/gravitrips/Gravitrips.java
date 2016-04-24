package gravitrips;

public class Gravitrips {

	public static void main(String[] args) {
		
		int rows = 6;
		int columns = 7;
		Player player1 = new HumanPlayer();
		Player player2 = new ComputerPlayer();
		
		Game game = new Game(rows, columns, player1, player2);
		game.run();
	
	}

}
