import java.util.ArrayList;
import java.util.Arrays;

public class GameState {
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

    //visual show where figures
    public static String getBoard() {
        String board = "";
        for (int i = 0; i < GameState.gameBoard.length; i++) {
            board += Arrays.toString(gameBoard[i]) + '\n';
        }
        return board;
    };

}
