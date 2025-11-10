package gameState;

import java.util.Arrays;
import java.util.Scanner;

public class GameState {

    static Scanner chooseMove = new Scanner(System.in);




    public static String[][] gameBoard = new String[][]{
            {"bR","bB","bH","bQ","bK","bH","bB","bR"},
            {"bP","bP","bP","bP","bP","bP","bP","bP"},
            {"  ","  ","  ","  ","  ","  ","  ","  "},
            {"  ","  ","  ","  ","  ","  ","  ","  "},
            {"  ","  ","  ","  ","  ","  ","  ","  "},
            {"  ","  ","  ","  ","  ","  ","  ","  "},
            {"wP","wP","wP","wP","wP","wP","wP","wP"},
            {"wR","wB","wH","wQ","wK","wH","wB","wR"},
    };

    public static String getHorizontLine(){

        String horizontLine = "  ";
        for(int i = 0; i<8 ; i++){
            char letter =  (char)('a' + i );
            horizontLine +=  "   " + letter;
        }
        return horizontLine;
    };

    //visual show where figures
    public static String getBoard() {
        String board = "";
        for (int i = 0; i < GameState.gameBoard.length; i++) {
            board += (i+1) + ". " + Arrays.toString(gameBoard[i]) + '\n';
        }
        return board;
    };

    public static int[] getFigure(String xy){
        int x = Character.getNumericValue(xy.charAt(0));
        int y = Character.getNumericValue(xy.charAt(1));

        if(GameState.gameBoard[x][y] != "  "){
            int[] poss = new int[2];
            poss[0] = x;
            poss[1]= y;
            return poss;
        }else if (GameState.gameBoard[x][y].startsWith("b")){
            System.out.println("You are white ,can't move by black");
            return GameState.getFigure(chooseMove.nextLine());
        }
        else {
            System.out.println("You choose a void");
            return GameState.getFigure(chooseMove.nextLine());
        }

    }

}
