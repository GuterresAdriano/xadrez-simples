package curso.udemy.xadrez.testCases;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;

import curso.udemy.xadrez.bordergame.Board;
import curso.udemy.xadrez.dummies.PositionDummy;
import curso.udemy.xadrez.exceptions.BorderException;

public class BoardTest {

	@Test
	public void createPieceUpperLimitIntIntTestCase() {
		Board board = new Board();		
		assertNull(board.piece(7,7));		
	}
	@Test
	public void createPieceBotonLimitIntIntTestCase() {
		Board board = new Board();		
		assertNull(board.piece(0,0));		
	}	
	@Test
	public void createPieceBotonLimitIntIntExceptionTestCase() {
		Board board = new Board();		
		assertThrows(BorderException.class, () -> { board.piece(-1,-1);});
	}
	@Test
	public void createPieceUpperLimitIntIntExceptionTestCase() {
		Board board = new Board();		
		assertThrows(BorderException.class, () -> {board.piece(8,8);});
	}	
	@Test
	public void createPieceMidBorderIntIntTestCase() {
		Board board = new Board();		
		assertNull(board.piece(4,4));		
	}		
	@Test
	public void createPieceUpperLimitPositionTestCase() {
		Board board = new Board();		
		PositionDummy dummy = new PositionDummy(7, 7);
		assertNull(board.piece(dummy));		
	}
	@Test
	public void createPieceBotonLimitPositionTestCase() {
		Board board = new Board();		
		PositionDummy dummy = new PositionDummy(0, 0);
		assertNull(board.piece(dummy));		
	}	
	@Test
	public void createPieceBotonLimitPositionExceptionTestCase() {
		Board board = new Board();		
		PositionDummy dummy = new PositionDummy(-1, -1);
		assertThrows(BorderException.class, () -> { board.piece(dummy);});
	}
	@Test
	public void createPieceUpperLimitPositionExceptionTestCase() {
		Board board = new Board();		
		PositionDummy dummy = new PositionDummy(8, 8);
		assertThrows(BorderException.class, () -> { board.piece(dummy);});
	}	
	@Test
	public void createPieceMidBorderPositionTestCase() {
		Board board = new Board();		
		PositionDummy dummy = new PositionDummy(0, 0);
		assertNull(board.piece(dummy));		
	}
	@Test
	public void verifyThereIsAPieceUpperLimitPositionTestCase() {
		Board board = new Board();		
		PositionDummy dummy = new PositionDummy(7, 7);
		assertFalse(board.thereIsAPiece(dummy));		
	}
	@Test
	public void verifyThereIsAPieceBotonLimitPositionTestCase() {
		Board board = new Board();		
		PositionDummy dummy = new PositionDummy(0, 0);
		assertFalse(board.thereIsAPiece(dummy));		
	}	
	@Test
	public void verifyThereIsAPieceBotonLimitPositionExceptionTestCase() {
		Board board = new Board();		
		PositionDummy dummy = new PositionDummy(-1, -1);
		assertThrows(BorderException.class, () -> { board.thereIsAPiece(dummy);});
	}
	@Test
	public void verifyThereIsAPiecePositionExceptionTestCase() {
		Board board = new Board();		
		PositionDummy dummy = new PositionDummy(8, 8);
		assertThrows(BorderException.class, () -> { board.thereIsAPiece(dummy);});
	}	
	@Test
	public void verifyThereIsAPiecePositionTestCase() {
		Board board = new Board();		
		PositionDummy dummy = new PositionDummy(0, 0);
		assertFalse(board.thereIsAPiece(dummy));		
	}		
}
