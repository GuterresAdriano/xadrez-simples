package curso.udemy.xadrez.chess.pieces;

import curso.udemy.xadrez.bordergame.Board;
import curso.udemy.xadrez.bordergame.Position;
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

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().ROWS][getBoard().COLUNMS];

		//Color White

		Position p = new Position(0,0);				
		if(getColor() == Color.WHITE) {
			
			p.setValues(position.getRow() -1 , position.getColumn());	
			if(getBoard().positionExists(p)&&!getBoard().thereIsAPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			Position p2 = new Position(position.getRow() -2 , position.getColumn());
			p.setValues(position.getRow() -1 , position.getColumn());	
			if(getBoard().positionExists(p) 
					&& !getBoard().thereIsAPiece(p)					
					&& getMoveCount() == 0 
					&& getBoard().positionExists(p2) 
					&& !getBoard().thereIsAPiece(p2)) {
				mat[p2.getRow()][p2.getColumn()] = true;
			}

			p.setValues(position.getRow() -1 , position.getColumn()-1);	
			if(getBoard().positionExists(p)&&isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			p.setValues(position.getRow() -1 , position.getColumn()+1);	
			if(getBoard().positionExists(p)&&isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			
		}else {
			
			p.setValues(position.getRow() +1 , position.getColumn());	
			if(getBoard().positionExists(p)&&!getBoard().thereIsAPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			Position p2 = new Position(position.getRow() +2 , position.getColumn());
			p.setValues(position.getRow() +1 , position.getColumn());	
			if(getBoard().positionExists(p) 
					&& !getBoard().thereIsAPiece(p)					
					&& getMoveCount() == 0 
					&& getBoard().positionExists(p2) 
					&& !getBoard().thereIsAPiece(p2)) {
				mat[p2.getRow()][p2.getColumn()] = true;
			}

			p.setValues(position.getRow() +1 , position.getColumn()-1);	
			if(getBoard().positionExists(p)&&isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			p.setValues(position.getRow() +1 , position.getColumn()+1);	
			if(getBoard().positionExists(p)&&isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			
		}

		//Above

		//Left


		//Right		


		return mat;
	}

}
