package curso.udemy.xadrez.application;

import curso.udemy.xadrez.chess.ChessMatch;

/**
 * Hello world!
 *
 */
public class App{
    public static void main( String[] args ){
       ChessMatch chessMatch = new ChessMatch();
       
       UI.printBoard(chessMatch.getPiecces());
    }
}
