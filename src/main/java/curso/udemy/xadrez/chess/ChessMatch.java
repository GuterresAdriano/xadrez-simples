package curso.udemy.xadrez.chess;

import java.util.ArrayList;
import java.util.List;

import curso.udemy.xadrez.bordergame.Board;
import curso.udemy.xadrez.bordergame.Piece;
import curso.udemy.xadrez.bordergame.Position;
import curso.udemy.xadrez.chess.pieces.Bishop;
import curso.udemy.xadrez.chess.pieces.Horse;
import curso.udemy.xadrez.chess.pieces.King;
import curso.udemy.xadrez.chess.pieces.Pawn;
import curso.udemy.xadrez.chess.pieces.Queen;
import curso.udemy.xadrez.chess.pieces.Rook;
import curso.udemy.xadrez.exceptions.BorderException;
import curso.udemy.xadrez.exceptions.ChessException;

public class ChessMatch {

	private Board board;
	private int turn;
	private Color currentPlayer;
	
	private List<ChessPiece> piecesOnTheBoard = new ArrayList<ChessPiece>();
	private List<Piece> capturedPieces = new ArrayList<Piece>();
	
	public ChessMatch() {
		this.board = new Board();
		this.turn = 1;
		this.currentPlayer = Color.WHITE;
		
		initializeGame();
	}

	public int getTurn() {
		return turn;
	}	

	public Color getCurrentPlayer() {
		return currentPlayer;
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
	
	public boolean[][] possibleMoves(ChessPosition source){		
		Position position = source.toPosition();		
		validateSourcePosition(position);		
		return board.piece(position).possibleMoves();		
	}	

	public ChessPiece performChessMoves(ChessPosition originPosition, ChessPosition targetPosition) {
		Position source = originPosition.toPosition();
		Position target = targetPosition.toPosition();

		validateSourcePosition(source);
		validateTargetPosition(source, target);
		nextTurn();	
		
		return (ChessPiece)  makeMove(source, target);
	}
	
	public void nextTurn() {
		turn++;
		currentPlayer = currentPlayer == Color.WHITE ? Color.BLACK: Color.WHITE; 
	}


	private void validateTargetPosition(Position source, Position target) {
		if(!board.piece(source).possibleMove(target)) {
			throw new ChessException("The chosen piece can't move to target posyion");
		}
		
	}

	private Piece makeMove(Position source, Position target) {
		Piece p = board.removePiec(source);
		Piece capturedPiece = board.removePiec(target);
		board.placePiece(p, target);
		
		if(capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);			
		}
		return capturedPiece;
	}

	private void validateSourcePosition(Position source) {
		if(!board.thereIsAPiece(source)) {
			throw new BorderException("There is no piece on source position.");
		}
			
		
		if (currentPlayer != ((ChessPiece) board.piece(source)).getColor()) {
			throw new ChessException("The chosen piece is not yours");
		}
		
		if(!board.piece(source).isThereAnyPossibleMove()) {
			throw new ChessException("There is no possible moves for the chosen piece.");
		}
	}

	private void placeNewPiece(char colunm, int row, ChessPiece piece ) {
		board.placePiece(piece, new ChessPosition(colunm, row).toPosition());
		piecesOnTheBoard.add(piece);
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
		
		placeNewPiece('h', 7,new Pawn(this.board,Color.WHITE));
		placeNewPiece('g', 7,new Pawn(this.board,Color.WHITE));
		placeNewPiece('f', 7,new Pawn(this.board,Color.WHITE));
		placeNewPiece('e', 7,new Pawn(this.board,Color.WHITE));
		placeNewPiece('d', 7,new Pawn(this.board,Color.WHITE));
		placeNewPiece('c', 7,new Pawn(this.board,Color.WHITE));		
		placeNewPiece('b', 7,new Pawn(this.board,Color.WHITE));
		placeNewPiece('a', 7,new Pawn(this.board,Color.WHITE)); 

		placeNewPiece('h', 1,new Rook  (this.board,Color.BLACK));
		placeNewPiece('g', 1,new Horse (this.board,Color.BLACK));
		placeNewPiece('f', 1,new Bishop(this.board,Color.BLACK));
		placeNewPiece('e', 1,new King  (this.board,Color.BLACK));
		placeNewPiece('d', 1,new Queen (this.board,Color.BLACK));
		placeNewPiece('c', 1,new Bishop(this.board,Color.BLACK));		
		placeNewPiece('b', 1,new Horse (this.board,Color.BLACK));
		placeNewPiece('a', 1,new Rook  (this.board,Color.BLACK));

//		placeNewPiece('h', 2,new Pawn (this.board,Color.BLACK));
//		placeNewPiece('g', 2,new Pawn(this.board,Color.BLACK));
//		placeNewPiece('f', 2,new Pawn(this.board,Color.BLACK));
//		placeNewPiece('e', 2,new Pawn(this.board,Color.BLACK));
//		placeNewPiece('d', 2,new Pawn(this.board,Color.BLACK));
//		placeNewPiece('c', 2,new Pawn(this.board,Color.BLACK));		
//		placeNewPiece('b', 2,new Pawn(this.board,Color.BLACK));
//		placeNewPiece('a', 2,new Pawn(this.board,Color.BLACK));  
	}

}
