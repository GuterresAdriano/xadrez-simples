package curso.udemy.xadrez.exceptions;

public class ChessException extends BorderException {

	private static final long serialVersionUID = 1L;	

	public ChessException(String message) {		
		super(message); 
	}
	
	public ChessException(String message, Throwable cause) {
		super(message, cause);
	}
}
