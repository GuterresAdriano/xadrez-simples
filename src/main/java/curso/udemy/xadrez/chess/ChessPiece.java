package curso.udemy.xadrez.chess;

import curso.udemy.xadrez.bordergame.Board;
import curso.udemy.xadrez.bordergame.Piece;
import curso.udemy.xadrez.bordergame.Position;

public abstract class ChessPiece extends Piece{
	
	private Color color; 
	
	private int moveCount;

	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;		
	}

	public Color getColor() {
		return color;
	}
	
	public void increaseMoveCount() {
		setMoveCount(getMoveCount() + 1);
	}
	public void decreaseMoveCount() {
		setMoveCount(getMoveCount() - 1);
	}
	
	protected boolean isThereOpponentPiece(Position position) {
		ChessPiece piece = (ChessPiece) getBoard().piece(position);
		return piece != null && piece.getColor() != color;
	}
	
	public ChessPosition getChessPosition() {
		return ChessPosition.fromPosition(position);
	}

	public int getMoveCount() {
		return moveCount;
	}
	public void setMoveCount(int moveCount) {
		this.moveCount = moveCount;
	}

}
