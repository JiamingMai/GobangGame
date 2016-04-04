package jm.app;

public class Chessboard {
    private String[][] boardArray;
    private final int BOARD_SIZE = 15;

    public Chessboard(){
        initBoard();
    }

    public void initBoard(){
        boardArray = new String[BOARD_SIZE][BOARD_SIZE];
        for(int i = 0; i < boardArray.length; i++){
            for(int j = 0; j < boardArray[i].length; j++){
                boardArray[i][j] = "十";
            }
        }
    }

    public void clearBoard(){
        for(int i = 0; i < boardArray.length; i++){
            for(int j = 0; j < boardArray[i].length; j++){
                boardArray[i][j] = "十";
            }
        }
    }

    public void printBoard(){
        for(int i = 0; i < BOARD_SIZE; i++){
            for(int j = 0; j < BOARD_SIZE; j++){
                System.out.print(boardArray[i][j]);
            }
            System.out.println();
        }
    }

    public void setBoard(int x, int y, String chessmanStr){
        if(x < 0 || x > BOARD_SIZE - 1 || y < 0 || y > BOARD_SIZE - 1 ){
            System.out.println("Board setting error: the input coordinate is out of range.");
            return;
        }
        boardArray[x][y] = chessmanStr;
    }

    public String[][] getBoard(){
        return boardArray;
    }

    public int getBoardSize(){
        return this.BOARD_SIZE;
    }
}
