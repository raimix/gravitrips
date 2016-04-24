package gravitrips;

enum Token { 
	X('X'), O('O'), EMPTY('.');
	
	private char tChar;
	
	private Token(char tChar) { 
		this.tChar = tChar;
	}
	
	public char getChar() {
		return tChar;
	}
	
}

