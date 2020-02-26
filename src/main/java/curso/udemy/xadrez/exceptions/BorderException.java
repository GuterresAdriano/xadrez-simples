package curso.udemy.xadrez.exceptions;

public class BorderException extends RuntimeException {

	private static final long serialVersionUID = 1L;	

	public BorderException(String message) {		
		super(message); 
	}
	
	public BorderException(String message, Throwable cause) {
		super(message, cause);
	}
}
