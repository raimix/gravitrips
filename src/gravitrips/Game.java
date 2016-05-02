package gravitrips;

public class Game {
	
	private Board board;
	private Player[] playersList;
	private static final int CONNECTED = 4;

	public Game(int rows, int columns, Player player1, Player player2){
		
		this.board = new Board(rows, columns);
		
		player1.setToken(Token.X);
		player2.setToken(Token.O);
		
		playersList = new Player[]{ player1, player2 };
		
	}
	
	public void run(){

		int currPlayer = -1;
		
		Player player;
		do {
			
			if (currPlayer == 0){
				currPlayer = 1;
			} else {
				currPlayer = 0;
			}

			player = playersList[currPlayer];
			
			//if (!makePlayerMove(player)){break;}
			makePlayerMove(player);
			
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
		board.placeMove(column, player);
		
		return true;
	}
	
	public boolean playerWins(Player player){
		
		int lastRow = board.getRows() - 1;
		int lastCol = board.getColumns() - 1;
		
		Token token = player.getToken();
		boolean connFound = false;
		
		for (int row = 0; row <= lastRow; row++){
			for (int col = 0; col <= lastCol; col++){
				connFound = findConn(token, row, col, CONNECTED);
				if (connFound){return connFound;}
			}
		}
		return false;
	}
	
	public boolean findConn(Token token, int startRow, int startCol, int connCount){
		int conn = 0;
		
		int lastCol = board.getColumns() - 1;
		int lastRow = board.getRows() - 1;
		
		int colTo = startCol + connCount - 1;
		if (colTo > lastCol) colTo = startCol;
		int rowTo = startRow + connCount - 1;
		if (rowTo > lastRow){rowTo = startRow;}
		
		// horizontal
		for (int col = startCol; col <= colTo; col++){
			conn = (board.getToken(startRow, col) == token) ? ++conn : 0;
			if (conn == connCount){return true;}
		}
		
		// vertical
		conn = 0;
		for (int row = startRow; row <= rowTo; row++){
			conn = (board.getToken(row, startCol) == token) ? ++conn : 0;
			if (conn == connCount){return true;}
		}
		
		// up-right
		conn = 0;
		for (int col = startCol, row = startRow; col <= colTo && row <= rowTo; col++, row++){
			conn = (board.getToken(row, col) == token) ? ++conn : 0;
			if (conn == connCount){return true;}
		}
		
		// up-left
		colTo = startCol - connCount - 1;
		if (colTo < 0) colTo = startCol;
		conn = 0;
		for (int col = startCol, row = startRow; col >= 0 && row <= rowTo; col--, row++){
			conn = (board.getToken(row, col) == token) ? ++conn : 0;
			if (conn == connCount){return true;}
		}
		
		return false;
	}
	
}
