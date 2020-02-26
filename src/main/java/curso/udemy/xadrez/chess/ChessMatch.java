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
	
	private void placeNewPiece(char colunm, int row, ChessPiece piece ) {
		board.placePiece(piece, new ChessPosition(colunm, row).toPosition());
	}

	private void initializeGame() {

		placeNewPiece('h', 8,new Rook  (this.board,Color.WHITE));
		placeNewPiece('g', 8,new Horse (this.board,Color.WHITE));
		placeNewPiece('f', 8,new Bishop(this.board,Color.WHITE));
		placeNewPiece('e', 8,new King  (this.board,Color.WHITE));
		placeNewPiece('d', 8,new Queen (this.board,Color.WHITE));
		placeNewPiece('c', 8,new Bishop(this.board,Color.WHITE));		
		placeNewPiece('b', 8,new Horse (this.board,Color.WHITE));
		placeNewPiece('a', 8,new Rook  (this.board,Color.WHITE));

		placeNewPiece('h', 7,new Rook  (this.board,Color.WHITE));
		placeNewPiece('g', 7,new Horse (this.board,Color.WHITE));
		placeNewPiece('f', 7,new Bishop(this.board,Color.WHITE));
		placeNewPiece('e', 7,new King  (this.board,Color.WHITE));
		placeNewPiece('d', 7,new Queen (this.board,Color.WHITE));
		placeNewPiece('c', 7,new Bishop(this.board,Color.WHITE));		
		placeNewPiece('b', 7,new Horse (this.board,Color.WHITE));
		placeNewPiece('a', 7,new Rook  (this.board,Color.WHITE)); 

		placeNewPiece('h', 1,new Rook  (this.board,Color.BLACK));
		placeNewPiece('g', 1,new Horse (this.board,Color.BLACK));
		placeNewPiece('f', 1,new Bishop(this.board,Color.BLACK));
		placeNewPiece('e', 1,new King  (this.board,Color.BLACK));
		placeNewPiece('d', 1,new Queen (this.board,Color.BLACK));
		placeNewPiece('c', 1,new Bishop(this.board,Color.BLACK));		
		placeNewPiece('b', 1,new Horse (this.board,Color.BLACK));
		placeNewPiece('a', 1,new Rook  (this.board,Color.BLACK));

		placeNewPiece('h', 2,new Rook  (this.board,Color.BLACK));
		placeNewPiece('g', 2,new Horse (this.board,Color.BLACK));
		placeNewPiece('f', 2,new Bishop(this.board,Color.BLACK));
		placeNewPiece('e', 2,new King  (this.board,Color.BLACK));
		placeNewPiece('d', 2,new Queen (this.board,Color.BLACK));
		placeNewPiece('c', 2,new Bishop(this.board,Color.BLACK));		
		placeNewPiece('b', 2,new Horse (this.board,Color.BLACK));
		placeNewPiece('a', 2,new Rook  (this.board,Color.BLACK));  

	}

}
