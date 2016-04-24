package gravitrips;

public class Board {
	
	private int rows = 6;
	private int columns = 7;
	private Token[][] board = new Token[rows][columns];
	
	public Board(int rows, int columns){
		this.rows = rows;
		this.columns = columns;
		board = new Token[rows][columns];
	}
	
	public void init(){
		for (int x = 0; x < this.getRows(); x++) {
			for (int y = 0; y < this.getColumns(); y++) {
				board[x][y] = Token.EMPTY;
			}
		}
	}
	
	public void show_4del(){
		for (int y = 0; y < this.getColumns(); y++) { 
			System.out.print(y + " ");
		}
		System.out.println();
		
		System.out.println(new String(new char[(this.getColumns()) * 2 - 1]).replace("\0", "-"));
		
		for (int x = 0; x < this.getRows(); x++) {
			for (int y = 0; y < this.getColumns(); y++) {
				System.out.print(board[x][y].getChar() + " "); 
			}
			System.out.println();
		}
	}
	
	public void display(){
		
        System.out.println();
        
        System.out.print("     ");
        for(int i=1;i<=columns;++i){
        	System.out.printf("%-3d",i);
        }
        
        System.out.println();
        System.out.printf("     ");
        for(int i=1; i<(3*columns); ++i){
        	System.out.print("-");
        }
        
        System.out.println();
        for(int i=rows-1; i>=0; --i){
            System.out.printf("%-2d | ", (i+1));
            for(int j=0;j<=columns-1;++j){
                System.out.printf("%-3s",board[i][j].getChar());
            }
            System.out.println();
        }
        
        System.out.println();
    }
	
	public int getRows(){
		return this.rows;
	}
	
	public int getColumns(){
		return this.columns;
	}
	
	public boolean makePlayerMove(Player player){
		return false;
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
		for (int i=0; i<this.rows; ++i) {
			if (board[i][column] == Token.EMPTY) {
				row = i;
				break;
			}
		}
		return row;
	}
	
	public int countEmptyCells(){
		int emptyCells = 0;
		
		for (int i = 0; i < this.getRows(); i++) {
			for (int j = 0; j < this.getColumns(); j++) {
				if (board[i][j] == Token.EMPTY){
					emptyCells++;
				}
			}
		}
		return emptyCells;
	}
	
	public boolean noEmptyCellsLeft4del(){
		return false;
	}
	
	public boolean emptyCellsLeft(){
		for (int j = 0; j <= getColumns()-1; j++){
			if (board[this.getRows()-1][j] == Token.EMPTY){return true;}
		}
		return false;
	}
	
	public Token getToken(int i, int j){
		return board[i][j];
	}


}
