package curso.udemy.xadrez.testCases;

import static org.junit.Assert.*;

import org.junit.Test;

import curso.udemy.xadrez.bordergame.Position;

public class PositionTest {

	@Test
	public void toStringTestCase() {		
		Position position = new Position(10, 20);		
		assertEquals("10,20", position.toString());
	}

}
