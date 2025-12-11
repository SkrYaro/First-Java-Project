package chess.gameState;

import chess.Coordinates;
import chess.gameState.gameObject.Bishop;
import chess.gameState.gameObject.Figure;
import chess.gameState.gameObject.Horse;
import chess.gameState.gameObject.King;
import chess.gameState.gameObject.Pawn;
import chess.gameState.gameObject.Queen;
import chess.gameState.gameObject.Rook;

import java.util.List;
import java.util.Scanner;

public class GameState {

    static Scanner chooseMove = new Scanner(System.in);

    /*public static String[][] gameBoard = {
            {"bR", "bB", "bH", "bQ", "bK", "bH", "bB", "bR"},
            {"bP", "bP", "bP", "bP", "bP", "bP", "bP", "bP"},
            {"  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "},
            {"  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "},
            {"  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "},
            {"  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "},
            {"wP", "wP", "wP", "wP", "wP", "wP", "wP", "wP"},
            {"wR", "wB", "wH", "wQ", "wK", "wH", "wB", "wR"},
    };*/

    public static Figure[][] board = {
            {new Rook(true, 'R'), new Horse(true, 'H'),
                    new Bishop(true, 'B'), new Queen(true, 'Q'),
                    new King(true, 'K'), new Bishop(true, 'B'),
                    new Horse(true, 'H'), new Rook(true, 'R')},
            {new Pawn(true), new Pawn(true),
                    new Pawn(true), new Pawn(true),
                    new Pawn(true), new Pawn(true),
                    new Pawn(true), new Pawn(true)},
            {new Pawn(false), null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {new Pawn(false), null, null, null, null, null, null, null},
            {new Pawn(false), new Pawn(false),
                    new Pawn(false), new Pawn(false),
                    new Pawn(false), new Pawn(false),
                    new Pawn(false), new Pawn(false)},
            {new Rook(false, 'R'), new Horse(false, 'H'),
                    new Bishop(false, 'B'), new Queen(false, 'Q'),
                    new King(false, 'K'), new Bishop(false, 'B'),
                    new Horse(false, 'H'), new Rook(false, 'R')},

    };

    public static String getHorizonLine() {
        StringBuilder horizonLine = new StringBuilder(" ");
        for (int i = 0; i < 8; i++) {
            char letter = (char) ('a' + i);
            horizonLine.append("| ").append(letter);
        }
        return horizonLine.toString();
    }

    // visual show where figures
    public static String getBoard() {
        StringBuilder consoleBoard = new StringBuilder();
        String upLine = "――――――――――――――――\n";
        String sideLine = "|";
        consoleBoard.append(getHorizonLine()).append('\n');
        consoleBoard.append(upLine);
        for (int i = 0; i < GameState.board.length; i++) {
            consoleBoard.append(i + 1).append(sideLine);
            for (int j = 0; j < GameState.board[i].length; j++) {
                Figure figure = GameState.board[i][j];
                if (figure == null) {
                    consoleBoard.append("  ,");
                } else {
                    consoleBoard.append(figure.getConsoleString()).append(',');
                }
            }
            consoleBoard.append(sideLine);
            consoleBoard.append('\n');
        }
        consoleBoard.append(upLine);
        return consoleBoard.toString();
    }

    public static List<Coordinates> getPossibleMoves(Coordinates pos) {
//        System.out.println(pos.x);

        pos.x -= 1;
        pos.y -= 1;


        Figure figure = board[pos.y][pos.x];

//        System.out.println(figure.white + " " + figure.type);

        if (figure == null) {
            List<Coordinates> possibleMoves = List.of();
            System.out.println("its a null");
            return possibleMoves;
        } else {
            List<Coordinates> possibleMoves = figure.getPossibleMoves(pos, board);

            System.out.println(figure.getPossibleMoves(pos, board));
            return possibleMoves;
        }
    }

    public static void showPossibleMoves(List<Coordinates> possibleMoves) {
        for (int i = 0; i < possibleMoves.size(); i++) {
            //            possibleList[i] += (possibleMoves.get(i).x + possibleMoves.get(i).y);
            char a = (char) ('a' + possibleMoves.get(i).x);
            System.out.println((a + "" + (possibleMoves.get(i).y + 1)));
        }

    }

    public static Coordinates getPossibleMove(List<Coordinates> possibleMoves, String cord) {


        Coordinates possibleMove = null;
        for (int i = 0; i < possibleMoves.size(); i++) {
            System.out.println(possibleMoves.get(i).toString());
            if (possibleMoves.get(i).toString() == cord) {
                possibleMove = new Coordinates((possibleMoves.get(i).x), possibleMoves.get(i).y);
                break;
            }

        }
        return possibleMove;

    }


}
