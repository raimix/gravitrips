package gravitrips;

public class Board {
	
	private int rows;
	private int columns;
	private Token[][] board;
	
	public Board(int rows, int columns){
		
		this.rows = rows;
		this.columns = columns;
		
		board = new Token[rows][columns];
		initBoard(Token.EMPTY);
	}
	
	public void initBoard(Token token){
		for (int x = 0; x < getRows(); x++) {
			for (int y = 0; y < getColumns(); y++) {
				board[x][y] = token;
			}
		}
	}
	
	public void display(){
		
        System.out.println();
        
        System.out.print("     ");
        for(int i=1; i<=getColumns(); ++i){
        	System.out.printf("%-3d",i);
        }
        
        System.out.println();
        System.out.printf("     ");
        for(int i=1; i<(3*getColumns()); ++i){
        	System.out.print("-");
        }
        
        System.out.println();
        for(int i=getRows()-1; i>=0; --i){
            System.out.printf("%-2d | ", (i+1));
            for(int j=0; j<=getColumns()-1; ++j){
                System.out.printf("%-3s", board[i][j].getChar());
            }
            System.out.println();
        }
        
        System.out.println();
    }
	
	public int getRows(){
		return rows;
	}
	
	public int getColumns(){
		return columns;
	}
	
	public boolean isItValidMove(int column){
		return board[getRows()-1][column] == Token.EMPTY;
	}
	
	public boolean placeMove(int column, Player player){
		board[findRow(column)][column] = player.getToken();
		return false;
	}
	
	public int findRow(int column){
		
		int row = 0;
		
		for (int i = 0; i < getRows(); ++i) {
			if (board[i][column] == Token.EMPTY) {
				row = i;
				break;
			}
		}
		
		return row;
	}
	
	public int countEmptyCells(){
		int emptyCells = 0;
		
		for (int i = 0; i < getRows(); i++) {
			for (int j = 0; j < getColumns(); j++) {
				if (board[i][j] == Token.EMPTY){
					emptyCells++;
				}
			}
		}
		return emptyCells;
	}
	
	public boolean emptyCellsLeft(){
		for (int j = 0; j <= getColumns()-1; j++){
			if (board[getRows()-1][j] == Token.EMPTY){return true;}
		}
		return false;
	}
	
	public Token getToken(int i, int j){
		return board[i][j];
	}


}
