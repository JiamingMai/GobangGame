package jm.app;

public class Main {
    public static void main(String[] args){
        Chessboard chessboard = new Chessboard();
        GobangGame gobangGame = new GobangGame(chessboard);
        gobangGame.start();
    }
}
