package gravitrips;

public abstract class Player {

	private Token token;
	private String name;
	public boolean won = false;
	
	public void setToken(Token token){
		this.token = token;
	}
	
	public Token getToken(){
		return this.token;
	}
	
	public char getName(){
		return this.token.getChar();
	}
	
	public abstract int getMove(Board board);

}
