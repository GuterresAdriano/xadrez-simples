

import java.util.Scanner;

import curso.udemy.xadrez.application.UI;
import curso.udemy.xadrez.chess.ChessMatch;
import curso.udemy.xadrez.chess.ChessPiece;
import curso.udemy.xadrez.chess.ChessPosition;

public class App{
    public static void main( String[] args ){
    	
    	Scanner scanner = new Scanner(System.in);
    	
       ChessMatch chessMatch = new ChessMatch();
       
       while (true) {
    	   System.out.println();
    	   UI.printBoard(chessMatch.getPiecces());
    	   
    	   System.out.println();
    	   System.out.print("Source: ");
    	   ChessPosition source = UI.readChessPosition(scanner);
    	   
    	   System.out.println();
    	   System.out.print("Target: ");
    	   ChessPosition target = UI.readChessPosition(scanner);
		
    	   ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
    	   System.out.println();
	}
       
      
    }
}
