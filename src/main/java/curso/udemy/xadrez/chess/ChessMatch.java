package curso.udemy.xadrez.chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import curso.udemy.xadrez.bordergame.Board;
import curso.udemy.xadrez.bordergame.Piece;
import curso.udemy.xadrez.bordergame.Position;
import curso.udemy.xadrez.chess.pieces.*;
import curso.udemy.xadrez.exceptions.BorderException;
import curso.udemy.xadrez.exceptions.ChessException;

public class ChessMatch {

	private Board board;	
	private boolean check;
	private boolean checkMate;
	private Color currentPlayer;	
	private int turn;
	private ChessPiece enPassantVulnerable;

	private List<Piece> capturedPieces   = new ArrayList<>();
	private List<Piece> piecesOnTheBoard = new ArrayList<>();

	public ChessMatch() {
		this.board = new Board();
		this.turn = 1;
		this.currentPlayer = Color.WHITE;
		initializeGame();
	}

	public boolean isCheck() {
		return check;
	}

	public Color getCurrentPlayer() {
		return currentPlayer;
	}

	public ChessPiece[][] getPieces(){
		ChessPiece[][] mat = new ChessPiece[this.board.ROWS][this.board.COLUNMS];

		for (int i = 0; i <this.board.ROWS ; i++) {
			for (int j = 0; j < this.board.COLUNMS; j++) {
				mat[i][j] = (ChessPiece) this.board.piece(i,j);
			}			
		}
		return mat;		
	}

	public boolean isCheckMate() {
		return this.checkMate;
	}

	public int getTurn() {
		return turn;
	}	

	public boolean[][] possibleMoves(ChessPosition sourcePosition){		
		Position position = sourcePosition.toPosition();		
		validateSourcePosition(position);		
		return board.piece(position).possibleMoves();		
	}	

	public ChessPiece performChessMoves(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();

		validateSourcePosition(source);
		validateTargetPosition(source, target);

		Piece piece = makeMove(source, target);

		ChessPiece movedPiece = (ChessPiece) board.piece(target);

		if(testCheck(currentPlayer)){
			undoMove(source, target, piece);			
			throw new ChessException("You can't put yourself in check.");
		}

		check = testCheck(opponent(currentPlayer)) ? true: false;

		if(testCheckMate(opponent(currentPlayer))) {
			checkMate = true;			
		}else {
			nextTurn();	
		}

		// Special move en passant
		if(movedPiece instanceof Pawn && (target.getRow() == source.getRow()-2 || target.getRow() == source.getRow()+2)) {
			enPassantVulnerable = movedPiece;
		}else {
			enPassantVulnerable = null;
		}

		return (ChessPiece)  piece;
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
		ChessPiece p = (ChessPiece) board.removePiece(source);
		p.increaseMoveCount();
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(p, target);

		if(capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);			
		}

		//Castling Kingside 		
		if(p instanceof King && target.getColumn() == source.getColumn() +2) {
			Position sourceRook = new Position(source.getRow(), source.getColumn()+3);
			Position targetRook = new Position(source.getRow(), source.getColumn()+1);			
			ChessPiece rook = (ChessPiece)board.removePiece(sourceRook);
			board.placePiece(rook, targetRook);
			rook.increaseMoveCount();			
		}

		//Castling Queenside 		
		if(p instanceof King && target.getColumn() == source.getColumn() -2) {
			Position sourceRook = new Position(source.getRow(), source.getColumn()-4);
			Position targetRook = new Position(source.getRow(), source.getColumn()-1);			
			ChessPiece rook = (ChessPiece)board.removePiece(sourceRook);
			board.placePiece(rook, targetRook);
			rook.increaseMoveCount();			
		}

		//En passant
		if(p instanceof Pawn) {
			if(source.getColumn() != target.getColumn() && capturedPiece == null ) {
				Position pawnPosition;
				if(p.getColor() == Color.WHITE) {
					pawnPosition = new Position(target.getRow() +1, target.getColumn());
				}else {
					pawnPosition = new Position(target.getRow() -1, target.getColumn());
				}
				capturedPiece = board.removePiece(pawnPosition);
				capturedPieces.add(capturedPiece);
			}			
		}


		return capturedPiece;
	}

	private void undoMove(Position source, Position target, Piece piece) {
		ChessPiece p = (ChessPiece) board.removePiece(target);		
		p.decreaseMoveCount();
		board.placePiece(p, source);

		if(piece != null) {
			board.placePiece(piece, target);
			capturedPieces.remove(piece);
			piecesOnTheBoard.add(piece);
		}	


		//Castling Kingside 		
		if(p instanceof King && target.getColumn() == source.getColumn() +2) {
			Position sourceRook = new Position(source.getRow(), source.getColumn()+3);
			Position targetRook = new Position(source.getRow(), source.getColumn()+1);			
			ChessPiece rook = (ChessPiece)board.removePiece(targetRook);
			board.placePiece(rook, sourceRook);
			rook.decreaseMoveCount();			
		}

		//Castling Queenside 		
		if(p instanceof King && target.getColumn() == source.getColumn() -2) {
			Position sourceRook = new Position(source.getRow(), source.getColumn()-4);
			Position targetRook = new Position(source.getRow(), source.getColumn()-1);			
			ChessPiece rook = (ChessPiece)board.removePiece(targetRook);
			board.placePiece(rook, sourceRook);
			rook.decreaseMoveCount();			
		}
		
		//En passant
		Piece capturedPiece = board.removePiece(target);
		if(p instanceof Pawn) {			
			if(source.getColumn() != target.getColumn() && capturedPiece == enPassantVulnerable ) {
				ChessPiece pawn = (ChessPiece) board.removePiece(target);
				
				Position pawnPosition;
				if(p.getColor() == Color.WHITE) {
					pawnPosition = new Position(3, target.getColumn());
				}else {
					pawnPosition = new Position(4, target.getColumn());
				}
				board.placePiece(pawn, pawnPosition);
				
			}			
		}
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

	private Color opponent(Color color) {
		return color == Color.WHITE ? Color.BLACK: Color.WHITE;				
	}

	private ChessPiece king(Color color) {
		List<Piece> list = piecesOnTheBoard.stream().filter(x-> ((ChessPiece)x).getColor()==color).collect(Collectors.toList());			
		for (Piece x : list) {
			if(x instanceof King) {
				return (ChessPiece) x;				
			}
		}
		throw new IllegalStateException("There is no "+color+" king on the board.");
	}

	private boolean testCheck(Color color) {
		Position kingPosition = king(color).getChessPosition().toPosition();
		List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x-> ((ChessPiece)x).getColor()== opponent(color)).collect(Collectors.toList());

		for (Piece x : opponentPieces) {
			boolean[][] mat = x.possibleMoves();
			if(mat[kingPosition.getRow()][kingPosition.getColumn()]) {
				return true;				
			}
		}
		return false;
	}

	private boolean testCheckMate(Color color) {
		if(!testCheck(color)) {
			return false;
		}

		List<Piece> listColor = piecesOnTheBoard.stream().filter(x-> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
		for (Piece x : listColor) {
			boolean[][] mat = x.possibleMoves();
			for (int i = 0; i < mat.length; i++) {
				for (int j = 0; j < mat.length; j++) {
					if(mat[i][j]) {
						Position source = ((ChessPiece)x).getChessPosition().toPosition();
						Position target = new Position(i,j);						
						Piece capturePiece = makeMove(source, target);						
						boolean  testCheck = testCheck(color);
						undoMove(source, target, capturePiece);
						if(!testCheck) {
							return false;							
						}						
					}
				}				
			}
		}

		return true;	

	}


	private void initializeGame() {		



		placeNewPiece('h', 8,new Rook  (this.board,Color.BLACK));
		placeNewPiece('g', 8,new Knight(this.board,Color.BLACK));
		placeNewPiece('f', 8,new Bishop(this.board,Color.BLACK));
		placeNewPiece('e', 8,new King  (this.board,Color.BLACK, this));
		//		placeNewPiece('d', 8,new Queen (this.board,Color.BLACK));
		placeNewPiece('c', 8,new Bishop(this.board,Color.BLACK));		
		placeNewPiece('b', 8,new Knight(this.board,Color.BLACK));
		placeNewPiece('a', 8,new Rook  (this.board,Color.BLACK));

		placeNewPiece('h', 7,new Pawn(this.board,Color.BLACK,this));
		placeNewPiece('g', 7,new Pawn(this.board,Color.BLACK,this));
		placeNewPiece('f', 7,new Pawn(this.board,Color.BLACK,this));
		placeNewPiece('e', 7,new Pawn(this.board,Color.BLACK,this));
		placeNewPiece('d', 7,new Pawn(this.board,Color.BLACK,this));
		placeNewPiece('c', 7,new Pawn(this.board,Color.BLACK,this));		
		placeNewPiece('b', 7,new Pawn(this.board,Color.BLACK,this));
		placeNewPiece('a', 7,new Pawn(this.board,Color.BLACK,this)); 

		placeNewPiece('h', 1,new Rook  (this.board,Color.WHITE));
		placeNewPiece('g', 1,new Knight(this.board,Color.WHITE));
		placeNewPiece('f', 1,new Bishop(this.board,Color.WHITE));
		placeNewPiece('e', 1,new King  (this.board,Color.WHITE, this));
		//		placeNewPiece('d', 1,new Queen (this.board,Color.WHITE));
		placeNewPiece('c', 1,new Bishop(this.board,Color.WHITE));		
		placeNewPiece('b', 1,new Knight(this.board,Color.WHITE));
		placeNewPiece('a', 1,new Rook  (this.board,Color.WHITE));

		placeNewPiece('h', 2,new Pawn(this.board,Color.WHITE,this));
		placeNewPiece('g', 2,new Pawn(this.board,Color.WHITE,this));
		placeNewPiece('f', 2,new Pawn(this.board,Color.WHITE,this));
		placeNewPiece('e', 2,new Pawn(this.board,Color.WHITE,this));
		placeNewPiece('d', 2,new Pawn(this.board,Color.WHITE,this));
		placeNewPiece('c', 2,new Pawn(this.board,Color.WHITE,this));		
		placeNewPiece('b', 2,new Pawn(this.board,Color.WHITE,this));
		placeNewPiece('a', 2,new Pawn(this.board,Color.WHITE,this));  
	}

	public ChessPiece getEnPassantVulnerable() {
		return enPassantVulnerable;
	}

}
