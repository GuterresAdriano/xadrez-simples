

import java.util.InputMismatchException;
import java.util.Scanner;

import curso.udemy.xadrez.application.UI;
import curso.udemy.xadrez.chess.ChessMatch;
import curso.udemy.xadrez.chess.ChessPiece;
import curso.udemy.xadrez.chess.ChessPosition;
import curso.udemy.xadrez.exceptions.ChessException;

public class App{
	public static void main( String[] args ){

		Scanner scanner = new Scanner(System.in);

		ChessMatch chessMatch = new ChessMatch();

		while (true) {
			try {
				UI.clearScreen();
				UI.printBoard(chessMatch.getPiecces());

				System.out.println();
				System.out.print("Source: ");
				ChessPosition source = UI.readChessPosition(scanner);
				
				boolean[][] possibleMoves = chessMatch.possibleMoves(source);
				
				UI.clearScreen();
				UI.printBoard(chessMatch.getPiecces(), possibleMoves);

				System.out.println();
				System.out.print("Target: ");
				ChessPosition target = UI.readChessPosition(scanner);

				ChessPiece capturedPiece = chessMatch.performChessMoves(source, target);
				System.out.println();

			}catch (ChessException e) {
				System.out.println(e.getMessage());
				scanner.hasNextLine();
			}catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				scanner.hasNextLine();
			}

		}


	}
}
