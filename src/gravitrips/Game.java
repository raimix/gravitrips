package gravitrips;

public class Game {
	
	private Board board;
	private Player[] playersList;
	private static final int CONN_N = 4;

	public Game(int rows, int columns, Player player1, Player player2){
		this.board = new Board(rows, columns);
		player1.setToken(Token.X);
		player2.setToken(Token.O);
		playersList = new Player[]{ player1, player2 };
	}
	
	public void run(){

		board.init();
		
		int currPlayer = -1;
		//Player currentPlayer = new Player();
		Player player;
		do {
			
			if (currPlayer == 0){
				currPlayer = 1;
			} else {
				currPlayer = 0;
			}

			player = playersList[currPlayer];
			
			if (!makePlayerMove(player)){break;}
			
			player.won = playerWins(player);
	
		} while (!player.won && board.emptyCellsLeft());

		board.display();
		if (player.won){
			System.out.println("player " + player.getToken().getChar() + " win the game");
		} else {
			System.out.println("game is over");
		}
	}
	
	public boolean makePlayerMove(Player player){
		
		int column = player.getMove(board);
		if (column == 99){return false;}
		board.placeMove(column, player);
		
		return true;
	}
	
	public boolean playerWins(Player player){
		
		int lastRow = board.getRows() - 1;
		int lastCol = board.getColumns() - 1;
		
		Token token = player.getToken();
		boolean connFound = false;
		
		for (int R = 0; R <= lastRow; R++){
			for (int C = 0; C <= lastCol; C++){
				connFound = findConn(token, R, C, CONN_N);
				if (connFound){return connFound;}
			}
		}
		return false;
	}
	
	public boolean findConn(Token token, int row, int col, int connCount){
		int conn = 0;
		//Token token = board.getToken(x, y);
		
		int lastCol = board.getColumns() - 1;
		int lastRow = board.getRows() - 1;
		//int lastCol0 = lastCol - (connCount - 1);
		//int lastRow0 = lastRow - (connCount - 1);
		
		int colTo = col + connCount - 1;
		if (colTo > lastCol) colTo = col;
		int rowTo = row + connCount - 1;
		if (rowTo > lastRow){rowTo = row;}
		
		// horizontal
		for (int C = col; C <= colTo; C++){
			conn = (board.getToken(row, C) == token) ? ++conn : 0;
			if (conn == connCount){return true;}
		}
		
		// vertical
		conn = 0;
		for (int R = row; R <= rowTo; R++){
			conn = (board.getToken(R, col) == token) ? ++conn : 0;
			if (conn == connCount){return true;}
		}
		
		// up-right
		conn = 0;
		for (int C = col, R = row; C <= colTo && R <= rowTo; C++, R++){
			conn = (board.getToken(R, C) == token) ? ++conn : 0;
			if (conn == connCount){return true;}
		}
		
		// up-left
		colTo = col - connCount - 1;
		if (colTo < 0){colTo = col;}
		conn = 0;
		for (int C = col, R = row; C >= 0 && R <= rowTo; C--, R++){
			conn = (board.getToken(R, C) == token) ? ++conn : 0;
			if (conn == connCount){return true;}
		}
		
		return false;
	}
	
}
