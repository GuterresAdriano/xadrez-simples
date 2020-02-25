package curso.udemy.xadrez.chess.pieces;

import curso.udemy.xadrez.bordergame.Board;
import curso.udemy.xadrez.chess.ChessPiece;
import curso.udemy.xadrez.chess.Color;

public class Pawn extends ChessPiece {

	public Pawn(Board board, Color color) {
		super(board, color);
	}
	
	@Override
	public String toString() {
		return "P";
	}

}
