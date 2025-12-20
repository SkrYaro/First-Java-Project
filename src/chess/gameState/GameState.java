package chess.gameState;

import chess.Coordinates;
import chess.gameState.gameObject.Bishop;
import chess.gameState.gameObject.Figure;
import chess.gameState.gameObject.Horse;
import chess.gameState.gameObject.King;
import chess.gameState.gameObject.Pawn;
import chess.gameState.gameObject.Queen;
import chess.gameState.gameObject.Rook;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameState {

    Scanner chooseMove = new Scanner(System.in);

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


    public Figure[][] board = {
            {new Rook(true, 'R'), new Horse(true, 'H'), new Bishop(true, 'B'), new Queen(true, 'Q'), new King(true, 'K'), new Bishop(true, 'B'), new Horse(true, 'H'), new Rook(true, 'R')},
            {new Pawn(true), new Pawn(true), new Pawn(true), new Pawn(true), new Pawn(true), new Pawn(true), new Pawn(true), new Pawn(true)},
            {new Pawn(false), null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {new Pawn(true), null, null, null, null, null, null, null},
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

    public String getHorizonLine() {
        StringBuilder horizonLine = new StringBuilder("");
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
        StringBuilder consoleBoard = new StringBuilder();
        String upLine = "――――――――――――――――\n";
        String sideLine = "|";
        consoleBoard.append(getHorizonLine()).append('\n');
        consoleBoard.append(upLine);
        for (int i = 0; i < board.length; i++) {
            consoleBoard.append(i + 1).append(sideLine);
            for (int j = 0; j < board[i].length; j++) {
                Figure figure = board[i][j];
                for (int k = 0; k < possibleMoves.size(); k++) {
                    if (possibleMoves.get(k).x == j && possibleMoves.get(k).y == i) {
                        consoleBoard.append("⬜");
                    }
                }
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

    public List<Coordinates> getPossibleMoves(Coordinates pos) {
        Figure figure = board[pos.y][pos.x];

        if (figure == null) {
            List<Coordinates> possibleMoves = new ArrayList<>();
            System.out.println("its a null");
            return possibleMoves;
        } else {
            List<Coordinates> possibleMoves = figure.getPossibleMoves(pos, board);

//            System.out.println(figure.getPossibleMoves(pos, board));
            return possibleMoves;
        }
    }

    public void showPossibleMoves(List<Coordinates> possibleMoves) {
        for (int i = 0; i < possibleMoves.size(); i++) {
            //            possibleList[i] += (possibleMoves.get(i).x + possibleMoves.get(i).y);
            char a = (char) ('a' + possibleMoves.get(i).x);
            System.out.println((a + "" + (possibleMoves.get(i).y + 1)));
        }

    }

    public Coordinates getPossibleMove(List<Coordinates> possibleMoves, Coordinates cord) {


        Coordinates possibleMove = null;
        for (int i = 0; i < possibleMoves.size(); i++) {
            System.out.println(possibleMoves.get(i).x + possibleMoves.get(i).y);
            if (possibleMoves.get(i).x == cord.x && possibleMoves.get(i).y == cord.y) {
                possibleMove = new Coordinates(possibleMoves.get(i).x, possibleMoves.get(i).y);
                return possibleMove;
            }
            System.out.println("don't have possible move try again");
        }
        return possibleMove;

    }

}
