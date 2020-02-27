package curso.udemy.xadrez.chess.pieces;

import curso.udemy.xadrez.bordergame.Board;
import curso.udemy.xadrez.chess.ChessPiece;
import curso.udemy.xadrez.chess.Color;

public class Queen extends ChessPiece {

	public Queen(Board board, Color color) {
		super(board, color);
	}
	
	@Override
	public String toString() {
		return "Q";
	}

	
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().ROWS][getBoard().COLUNMS];
		return null;
	}
}
