package curso.udemy.xadrez.chess.pieces;

import curso.udemy.xadrez.bordergame.Board;
import curso.udemy.xadrez.bordergame.Position;
import curso.udemy.xadrez.chess.ChessMatch;
import curso.udemy.xadrez.chess.ChessPiece;
import curso.udemy.xadrez.chess.Color;

public class King extends ChessPiece {

	private ChessMatch chessMatch;

	public King(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}

	@Override
	public String toString() {
		return "K";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().ROWS][getBoard().COLUNMS];


		Position p = new Position(0,0);		

		//Above
		p.setValues(position.getRow() -1, position.getColumn());
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		//below
		p.setValues(position.getRow() +1, position.getColumn());
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}		
		//Left
		p.setValues(position.getRow(), position.getColumn()-1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		//Right
		p.setValues(position.getRow(), position.getColumn()+1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		//NW
		p.setValues(position.getRow() -1, position.getColumn()-1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		//NE
		p.setValues(position.getRow() -1, position.getColumn()+1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}		
		//SW
		p.setValues(position.getRow() +1, position.getColumn()-1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		//SE
		p.setValues(position.getRow()+1, position.getColumn()+1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		//Castling
		if(getMoveCount() == 0 && !chessMatch.isCheck()) {
			// Castling side king			
			Position posCast1 = new Position(position.getRow(), position.getColumn()+3);			
			if(isRookCastling(posCast1)) {
				Position cell1 = new Position(position.getRow(), position.getColumn()+1);		
				Position cell2 = new Position(position.getRow(), position.getColumn()+2);		
				if(getBoard().piece(cell1) ==  null && getBoard().piece(cell2) ==  null) {
					mat[position.getRow()][position.getColumn()+2] = true;				
				}
			}		

			// Castling side Queen			
			Position posCast2 = new Position(position.getRow(), position.getColumn()-4);			
			if(isRookCastling(posCast2)) {
				Position cell1 = new Position(position.getRow(), position.getColumn()-1);		
				Position cell2 = new Position(position.getRow(), position.getColumn()-2);	
				Position cell3 = new Position(position.getRow(), position.getColumn()-3);	
				if(getBoard().piece(cell1) ==  null 
						&& getBoard().piece(cell2) ==  null 
							&& getBoard().piece(cell3) ==  null) {
					mat[position.getRow()][position.getColumn() -2] = true;				
				}
			}		
		}


		return mat;
	}

	private boolean canMove(Position position) {		
		ChessPiece piece = (ChessPiece) getBoard().piece(position);		
		return piece == null || piece.getColor() != getColor();
	}

	private boolean isRookCastling(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		return p != null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0;		
	}

}
