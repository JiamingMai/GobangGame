package jm.app;

import java.util.Scanner;

public class GobangGame {
    private Chessboard chessboard;
    private final int WIN_COUNT = 5;
    private Chessman chessman;

    public GobangGame(Chessboard chessboard){
        this.chessboard = chessboard;
    }

    public boolean isValid(int x, int y){
        String[][] boardArray = chessboard.getBoard();
        int boardSize = chessboard.getBoardSize();
        if(x < 0 || x > boardSize || y < 0 || y > boardSize){
            System.out.println("The input coordinate is out of range. Please enter again.");
            return false;
        }
        if(!boardArray[x][y].equals("十")){
            System.out.println("The position has been occupied. Please enter again.");
            return false;
        }
        return true;
    }

    public void start(){
        chessboard.printBoard();
        Scanner in = new Scanner(System.in);
        String inputStr;
        chessman = Chessman.BLACK;
        while ((inputStr = in.nextLine()) != null){
            String[] coordinate = inputStr.split(" ");
            try{
                int x = Integer.valueOf(coordinate[0]);
                int y = Integer.valueOf(coordinate[1]);
                if(isValid(x, y) == false){
                    continue;
                }
                chessboard.setBoard(x, y, chessman.getChessman());
                if(isWon(x, y, chessman.getChessman())){
                    chessboard.printBoard();
                    System.out.println("You won. Do you want to play again? (y/n)");
                    String input = in.nextLine();
                    if(input.startsWith("y") || input.startsWith("Y")){
                        chessboard.clearBoard();
                        chessboard.printBoard();
                        continue;
                    }else{
                        break;
                    }
                }
                switchChessman();
                int[] cmpCoordinate = computerDo();
                if(isWon(cmpCoordinate[0], cmpCoordinate[1], chessman.getChessman())){
                    chessboard.printBoard();
                    System.out.println("You lost. Do you want to play again? (y/n)");
                    String input = in.nextLine();
                    if(input.startsWith("y") || input.startsWith("Y")){
                        chessboard.clearBoard();
                        switchChessman();
                        chessboard.printBoard();
                        continue;
                    }else{
                        break;
                    }
                }
                switchChessman();
                chessboard.printBoard();
            }catch(NumberFormatException e){
                e.printStackTrace();
                System.out.println("Please enter the coordinate with correct format.");
                continue;
            }
        }
    }

    public void switchChessman(){
        switch (chessman){
            case BLACK:
                chessman = Chessman.WHITE;
                break;
            case WHITE:
                chessman = Chessman.BLACK;
                break;
        }
    }

    public int[] computerDo(){
        int x = -1;
        int y = -1;
        do{
            x = (int) (Math.random()*chessboard.getBoardSize());
            y = (int) (Math.random()*chessboard.getBoardSize());
        }while(!isValid(x, y));
        chessboard.setBoard(x, y, chessman.getChessman());
        return new int[]{x, y};
    }

    public boolean isWon(int x, int y, String chessmanStr){
        int maxCount = 1;
        String[][] boardArray = chessboard.getBoard();
        // Check out the eight directions
        // The 1st direction
        int tempCount = 1;
        for(int s = x - 1, k = 0; s >= 0 && k < 4; s--, k++){
            if(boardArray[s][y].equals(chessmanStr)){
                tempCount++;
            }else{
                break;
            }
        }
        if(tempCount > maxCount){
            maxCount = tempCount;
        }

        // The 2nd direction
        tempCount = 1;
        for(int s = x + 1, k = 0; s <= chessboard.getBoardSize() - 1 && k < 4; s++, k++){
            if(boardArray[s][y].equals(chessmanStr)){
                tempCount++;
            }else{
                break;
            }
        }
        if(tempCount > maxCount){
            maxCount = tempCount;
        }

        // The 3rd direction
        tempCount = 1;
        for(int t = y - 1, k = 0; t >= 0 && k < 4; t--, k++){
            if(boardArray[x][t].equals(chessmanStr)){
                tempCount++;
            }else{
                break;
            }
        }
        if(tempCount > maxCount){
            maxCount = tempCount;
        }

        // The 4th direction
        tempCount = 1;
        for(int t = y + 1, k = 0; t <= chessboard.getBoardSize() - 1 && k < 4; t++, k++){
            if(boardArray[x][t].equals(chessmanStr)){
                tempCount++;
            }else{
                break;
            }
        }
        if(tempCount > maxCount){
            maxCount = tempCount;
        }

        // The 5th direction
        tempCount = 1;
        for(int s = x + 1, t = y + 1, k = 0; s <= chessboard.getBoardSize() - 1 && t <= chessboard.getBoardSize() - 1 && k < 4; s++, t++, k++){
            if(boardArray[s][t].equals(chessmanStr)){
                tempCount++;
            }else{
                break;
            }
        }
        if(tempCount > maxCount){
            maxCount = tempCount;
        }

        // The 6th direction
        tempCount = 1;
        for(int s = x - 1, t = y - 1, k = 0; s >= 0 && t >= 0 && k < 4; s--, t--, k++){
            if(boardArray[s][t].equals(chessmanStr)){
                tempCount++;
            }else{
                break;
            }
        }
        if(tempCount > maxCount){
            maxCount = tempCount;
        }

        // The 7th direction
        tempCount = 1;
        for(int s = x + 1, t = y - 1, k = 0; s <= chessboard.getBoardSize() - 1 && t >= 0 && k < 4; s++, t--, k++){
            if(boardArray[s][t].equals(chessmanStr)){
                tempCount++;
            }else{
                break;
            }
        }
        if(tempCount > maxCount){
            maxCount = tempCount;
        }

        // The 8th direction
        tempCount = 1;
        for(int s = x - 1, t = y + 1, k = 0; s >= 0 && t <= chessboard.getBoardSize() - 1 && k < 4; s--, t++, k++){
            if(boardArray[s][t].equals(chessmanStr)){
                tempCount++;
            }else{
                break;
            }
        }
        if(tempCount > maxCount){
            maxCount = tempCount;
        }

        if(maxCount >= WIN_COUNT){
            return true;
        }else{
            return false;
        }
    }
}
