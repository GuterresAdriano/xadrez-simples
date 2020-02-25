package curso.udemy.xadrez.chess.pieces;

import curso.udemy.xadrez.bordergame.Board;
import curso.udemy.xadrez.chess.ChessPiece;
import curso.udemy.xadrez.chess.Color;

public class Bishop extends ChessPiece {

	public Bishop(Board board, Color color) {
		super(board, color);
	}
	
	@Override
	public String toString() {
		return "B";
	}

}
