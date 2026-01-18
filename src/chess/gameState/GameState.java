package chess.gameState;

import chess.Coordinates;
import chess.gameState.gameObject.Bishop;
import chess.gameState.gameObject.Figure;
import chess.gameState.gameObject.Queen;

import java.util.ArrayList;
import java.util.List;

public class GameState {

    public static final int BOARD_MIN_ROWS = 0;
    public static final int BOARD_MIN_COLS = 0;
    public static final int BOARD_MAX_ROWS = 8;
    public static final int BOARD_MAX_COLS = 8;

/*
*
* | a  | b  | c  | d  | e  | f  | g  | h
―――――――――――――――――――――――――
1| wR , wH , wB , wQ , wK , wB , wH , wR ,|
2| wP , wP , wP , wP , wP , wP , wP , wP ,|
3|    ,    ,    ,    ,    ,    ,    ,    ,|
4|    ,    ,    ,    ,    ,    ,    ,    ,|
5|    ,    ,    ,    ,    ,    ,    ,    ,|
6|    ,    ,    ,    ,    ,    ,    ,    ,|
7| bP , bP , bP , bP , bP , bP , bP , bP ,|
8| bR , bH , bB , bQ , bK , bB , bH , bR ,|
*
* */

    /*public String[][] gameBoard = {
            {"bR", "bB", "bH", "bQ", "bK", "bH", "bB", "bR"},
            {"bP", "bP", "bP", "bP", "bP", "bP", "bP", "bP"},
            {"  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "},
            {"  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "},
            {"  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "},
            {"  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "},
            {"wP", "wP", "wP", "wP", "wP", "wP", "wP", "wP"},
            {"wR", "wB", "wH", "wQ", "wK", "wH", "wB", "wR"},
    };*/


    /*public Figure[][] board = {
            {new Rook(true), new Horse(true), new Bishop(true), new Queen(true),new King(true) , new Bishop(true), new Horse(true), new Rook(true)},
            {new Pawn(true), new Pawn(true), new Pawn(true), new Pawn(true), new Pawn(true), new Pawn(true), new Pawn(true), new Pawn(true)},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null , null, null,null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {new Pawn(false), new Pawn(false), new Pawn(false), new Pawn(false), new Pawn(false), new Pawn(false), new Pawn(false), new Pawn(false)},
            {new Rook(false), new Horse(false), new Bishop(false), new Queen(false), new King(false), new Bishop(false), new Horse(false), new Rook(false)},

    };*/

    /*public Figure[][] board = {
            {new Horse(false), null, null, null, null, null, null, new Horse(true)},
            {null, null, new Pawn(true), null, new Pawn(false), null, null, null},
            {null, new Horse(true), null, null, null, new Pawn(false), null, null},
            {null, null, null, new Horse(false), null, null, null, null},
            {null, null, new Pawn(false), null, null, null, null, null},
            {null, new Pawn(false), new Pawn(false), null, null, null, null, null},
            {null, null, new Pawn(true), null, null, null, null, null},
            {new Horse(true), null, null, null, null, null, null, new Horse(true)},
    };*/

    public Figure[][] board = new Figure[BOARD_MAX_ROWS][BOARD_MAX_COLS];

    public GameState() {
        board[1][1] = new Bishop(true);
        board[7][7] = new Bishop(false);
        board[1][4] = new Bishop(false);
        board[5][5] = new Queen(true);

    }

    public String getHorizonLine() {
        StringBuilder horizonLine = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            char letter = (char) ('a' + i);
            horizonLine.append("  | ").append(letter);
        }
        return horizonLine.toString();
    }

    // visual show where figures
    public String getBoard() {
        StringBuilder consoleBoard = new StringBuilder();
        String upLine = "―――――――――――――――――――――――――\n";
        String sideLine = "|";
        consoleBoard.append(getHorizonLine()).append('\n');
        consoleBoard.append(upLine);
        for (int i = 0; i < board.length; i++) {
            consoleBoard.append(i + 1).append(sideLine);
            for (int j = 0; j < board[i].length; j++) {
                Figure figure = board[i][j];
                if (figure == null) {
                    consoleBoard.append("    ,");
                } else {
                    consoleBoard.append(' ').append(figure.getConsoleString()).append(" ,");
                }
            }
            consoleBoard.append(sideLine);
            consoleBoard.append('\n');
        }
        consoleBoard.append(upLine);
        return consoleBoard.toString();
    }

    public String getBoard(List<Coordinates> possibleMoves) {

        boolean possibleBoolMove = false;

        StringBuilder consoleBoard = new StringBuilder();
        String upLine = "――――――――――――――――――――――――――――――――\n";
        String sideLine = "|";
        consoleBoard.append(getHorizonLine()).append('\n');
        consoleBoard.append(upLine);
        for (int i = 0; i < board.length; i++) {
            consoleBoard.append(i + 1).append(sideLine);
            for (int j = 0; j < board[i].length; j++) {
                Figure figure = board[i][j];
                for (Coordinates possibleMove : possibleMoves) {
                    if (possibleMove.x == j && possibleMove.y == i) {

                        if (board[i][j] == null) {
                            consoleBoard.append("⬜  ,");
                        } else {
                            consoleBoard.append("⬜").append(figure.getConsoleString()).append(',');
                        }
                        possibleBoolMove = true;

                    }
                }

                if (!possibleBoolMove) {
                    if (figure == null) {
                        consoleBoard.append("    ,");
                    } else {
                        consoleBoard.append(' ').append(figure.getConsoleString()).append(" ,");
                    }
                } else {
                    possibleBoolMove = false;
                }
            }
            consoleBoard.append(sideLine);
            consoleBoard.append('\n');
        }

        consoleBoard.append(upLine);
        return consoleBoard.toString();
    }

    public List<Coordinates> getPossibleMoves(Coordinates pos) {
        Figure figure = board[pos.y][pos.x];

        if (figure == null) {
            List<Coordinates> possibleMoves = new ArrayList<>();
            System.out.println("its a null");
            return possibleMoves;
        } else {
            return figure.getPossibleMoves(pos, board);
        }
    }

    public void showPossibleMoves(List<Coordinates> possibleMoves) {
        for (Coordinates possibleMove : possibleMoves) {
            //            possibleList[i] += (possibleMoves.get(i).x + possibleMoves.get(i).y);
            char a = (char) ('a' + possibleMove.x);
            System.out.println((a + "" + (possibleMove.y + 1)));
        }

    }

    public Coordinates getPossibleMove(List<Coordinates> possibleMoves, Coordinates cord) {
        for (Coordinates move : possibleMoves) {
            System.out.println(move.x + move.y);
            if (move.x == cord.x && move.y == cord.y) {
                return move;
            }
            System.out.println("don't have possible move try again");
        }
        return null;
    }

//    #TODO CheckLogic
    /*
     * System:
     * 1. Validate King's moves{
     * 1.1 GetKing
     * 1.2 Check King's possible moves and find connections with enemy attacks

     * 2. Check , is King under attack ( check ){
     * 2.1 Check after enemy move , if king is in check
     * 2.2 Alarm of check}
     *
     * 3. Validate possible moves for defend king{
     * 3.1 Get all possible figures in king's side ( white or black side)
     * 3.2 From this figures start searching this moves ,whats can defend king , there are: move or attack }
     *
     * 4. Validate possible moves , whats can create a check for it king{
     * 4.1. When we validate moves , start creating theoretical moves and check them ,\
     *       \ if move create check or broking game logic , they left List<PossibleMoves }*/


}
