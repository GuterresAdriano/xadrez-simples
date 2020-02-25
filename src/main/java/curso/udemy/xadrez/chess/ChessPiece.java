package curso.udemy.xadrez.chess;

import curso.udemy.xadrez.bordergame.Board;
import curso.udemy.xadrez.bordergame.Piece;

public class ChessPiece extends Piece{
	
	private Color color; 

	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

}
