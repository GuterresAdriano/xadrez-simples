

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
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
		
		List<ChessPiece> list = new ArrayList<ChessPiece>();

		while (true) {
			try {
				UI.clearScreen();
				UI.printMatch(chessMatch, list);

				System.out.println();
				System.out.print("Source: ");
				ChessPosition source = UI.readChessPosition(scanner);
				
				boolean[][] possibleMoves = chessMatch.possibleMoves(source);
				
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces(), possibleMoves);

				System.out.println();
				System.out.print("Target: ");
				ChessPosition target = UI.readChessPosition(scanner);

				ChessPiece capturedPiece = chessMatch.performChessMoves(source, target);
				if(capturedPiece != null) {
					list.add(capturedPiece);
				}

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
