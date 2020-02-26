package curso.udemy.xadrez.chess;

import curso.udemy.xadrez.bordergame.Position;
import curso.udemy.xadrez.exceptions.ChessException;

public class ChessPosition {
	
	private int row;
	private char column; 
	
	
	
	public ChessPosition(char column,int row) { 
		if(column < 'a' || column > 'h' || row < 1 || row > 8) {
			throw new ChessException("Error invalid position: .");
		}		
		this.row = row;
		this.column = column;
	}
	
	protected Position toPosition() {
		return new Position(8-row, column - 'a');
	}
	
	protected static Position fromPosition(Position position) {
		return new Position((char)('a'-position.getColumn()), 8-position.getRow());
	}
	
	@Override
	public String toString() {
		return "" + column + row;
	}
		
	public int getRow() {
		return row;
	}
	public char getColumn() {
		return column;
	}
	
}
