package curso.udemy.xadrez.dummies;

import curso.udemy.xadrez.bordergame.Position;

public class PositionDummy extends Position {
	
	public int row;
	public int column;

	public PositionDummy(int row, int column) {
		super(row, column);
		this.row = row;
		this.column = column;
	}

}
