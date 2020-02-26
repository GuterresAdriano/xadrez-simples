package curso.udemy.xadrez.chess;

import curso.udemy.xadrez.bordergame.Board;
import curso.udemy.xadrez.bordergame.Position;
import curso.udemy.xadrez.chess.pieces.*;

public class ChessMatch {

	private Board board;

	public ChessMatch() {
		this.board = new Board();
		initializeGame();
	}

	public ChessPiece[][] getPiecces(){
		ChessPiece[][] chessPieces = 
				new ChessPiece[this.board.ROWS][this.board.COLUNMS];

		for (int i = 0; i <this.board.ROWS ; i++) {
			for (int j = 0; j < this.board.COLUNMS; j++) {
				chessPieces[i][j] = (ChessPiece) this.board.piece(i,j);
			}			
		}
		return chessPieces;		
	}

	private void initializeGame() {

		this.board.placePiece(new Rook  (this.board,Color.WHITE),new Position(0,0));
		this.board.placePiece(new Horse (this.board,Color.WHITE),new Position(0,1));
		this.board.placePiece(new Bishop(this.board,Color.WHITE),new Position(0,2));
		this.board.placePiece(new King  (this.board,Color.WHITE),new Position(0,3));
		this.board.placePiece(new Queen (this.board,Color.WHITE),new Position(0,4));
		this.board.placePiece(new Bishop(this.board,Color.WHITE),new Position(0,5));		
		this.board.placePiece(new Horse (this.board,Color.WHITE),new Position(0,6));
		this.board.placePiece(new Rook  (this.board,Color.WHITE),new Position(0,7));

		this.board.placePiece(new Pawn(this.board,Color.WHITE),new Position(1,0));
		this.board.placePiece(new Pawn(this.board,Color.WHITE),new Position(1,1));
		this.board.placePiece(new Pawn(this.board,Color.WHITE),new Position(1,2));
		this.board.placePiece(new Pawn(this.board,Color.WHITE),new Position(1,3));
		this.board.placePiece(new Pawn(this.board,Color.WHITE),new Position(1,4));
		this.board.placePiece(new Pawn(this.board,Color.WHITE),new Position(1,5));		
		this.board.placePiece(new Pawn(this.board,Color.WHITE),new Position(1,6));
		this.board.placePiece(new Pawn(this.board,Color.WHITE),new Position(1,7));

		this.board.placePiece(new Rook  (this.board,Color.BLACK),new Position(7,0));
		this.board.placePiece(new Horse (this.board,Color.BLACK),new Position(7,1));
		this.board.placePiece(new Bishop(this.board,Color.BLACK),new Position(7,2));
		this.board.placePiece(new King  (this.board,Color.BLACK),new Position(7,3));
		this.board.placePiece(new Queen (this.board,Color.BLACK),new Position(7,4));
		this.board.placePiece(new Bishop(this.board,Color.BLACK),new Position(7,5));		
		this.board.placePiece(new Horse (this.board,Color.BLACK),new Position(7,6));
		this.board.placePiece(new Rook  (this.board,Color.BLACK),new Position(7,7));

		this.board.placePiece(new Pawn(this.board,Color.BLACK),new Position(6,0));
		this.board.placePiece(new Pawn(this.board,Color.BLACK),new Position(6,1));
		this.board.placePiece(new Pawn(this.board,Color.BLACK),new Position(6,2));
		this.board.placePiece(new Pawn(this.board,Color.BLACK),new Position(6,3));
		this.board.placePiece(new Pawn(this.board,Color.BLACK),new Position(6,4));
		this.board.placePiece(new Pawn(this.board,Color.BLACK),new Position(6,5));		
		this.board.placePiece(new Pawn(this.board,Color.BLACK),new Position(6,6));
		this.board.placePiece(new Pawn(this.board,Color.BLACK),new Position(6,7));

	}

}
