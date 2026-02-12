package chess.gameState.gameObject;

import chess.Coordinates;
import chess.gameState.GameState;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Figure {

    public boolean firstMove = true;

    public Pawn(boolean white) {
        super(white, 'P');
    }

    @Override
    public List<Coordinates> getPossibleMoves(Coordinates pos, Figure[][] board) {
        List<Coordinates> possibleMoves = new ArrayList<>();
        int posX = pos.x;
        int posY = pos.y;
        if (!board[posY][posX].white) {
            if (posY - 1 > GameState.BOARD_MIN_COLS) {
                if (board[posY + 1][posX] == null) {
                    possibleMoves.add(new Coordinates(posX, posY + 1));
                }
            }
            if (posY - 2 > GameState.BOARD_MIN_COLS) {
                if (firstMove && board[posY + 2][posX] == null) {
                    possibleMoves.add(new Coordinates(posX, posY + 2));
                }
            }
            if (posY - 1 > GameState.BOARD_MIN_COLS && posX + 1 < GameState.BOARD_MAX_COLS ) {
                if (board[posY - 1][posX + 1] != null && board[posY - 1][posX + 1].white) {
                    possibleMoves.add(new Coordinates(posX + 1, posY - 1));
                }
            }
            if (posY - 1 > GameState.BOARD_MIN_COLS && posX - 1 > GameState.BOARD_MIN_ROWS ) {
                if (board[posY + 1][posX - 1] != null && board[posY + 1][posX - 1].white) {
                    possibleMoves.add(new Coordinates(posX - 1, posY + 1));
                }
            }
        } else {
            if (posY + 1 < GameState.BOARD_MAX_COLS) {
                if (board[posY + 1][posX] == null) {
                    possibleMoves.add(new Coordinates(posX, posY + 1));
                }
            }
            if (posY + 2 < GameState.BOARD_MAX_COLS) {
                if (firstMove && board[posY + 2][posX] == null) {
                    possibleMoves.add(new Coordinates(posX, posY + 2));
                }
            }
            if (posY + 1 < GameState.BOARD_MAX_COLS && posX + 1 < GameState.BOARD_MAX_COLS ) {
                if (board[posY - 1][posX + 1] != null && board[posY - 1][posX + 1].white) {
                    possibleMoves.add(new Coordinates(posX + 1, posY - 1));
                }
            }
            if (posY + 1 < GameState.BOARD_MAX_COLS && posX - 1 > GameState.BOARD_MIN_ROWS ) {
                if (board[posY + 1][posX - 1] != null && board[posY + 1][posX - 1].white) {
                    possibleMoves.add(new Coordinates(posX - 1, posY + 1));
                }
            }
        }
        return possibleMoves;
    }


    public List<Coordinates> getForCheckMoves(Coordinates pos, Figure[][] board) {
        List<Coordinates> possibleMoves = new ArrayList<>();
        int posX = pos.x;
        int posY = pos.y;
        if (!board[posY][posX].white) {
            if (posX + 1 < GameState.BOARD_MAX_COLS && posY - 1 > GameState.BOARD_MIN_ROWS) {
                if (board[posY - 1][posX + 1] != null && board[posY - 1][posX + 1].white) {
                    possibleMoves.add(new Coordinates(posX + 1, posY - 1));
                }
            }
            if (posX - 1 > GameState.BOARD_MIN_COLS && posY - 1 > GameState.BOARD_MIN_ROWS) {
                if (board[posY - 1][posX - 1] != null && board[posY - 1][posX - 1].white) {
                    possibleMoves.add(new Coordinates(posX - 1, posY - 1));
                }
            }
        } else {
            if (posX + 1 < GameState.BOARD_MAX_COLS && posY + 1 < GameState.BOARD_MAX_ROWS) {
                if (board[posY + 1][posX + 1] != null && !board[posY - 1][posX + 1].white) {
                    possibleMoves.add(new Coordinates(posX + 1, posY - 1));
                }
            }
            if (posX - 1 > GameState.BOARD_MIN_COLS && posY - 1 > GameState.BOARD_MIN_ROWS) {
                if (board[posY + 1][posX - 1] != null && !board[posY - 1][posX - 1].white) {
                    possibleMoves.add(new Coordinates(posX - 1, posY - 1));
                }
            }
        }
        return possibleMoves;
    }

    @Override
    public void makeMove(Coordinates pos, Coordinates move, Figure[][] board) {

//            start position
        int posX = pos.x;
        int posY = pos.y;
//            position to move
        int moveX = move.x;
        int moveY = move.y;

//            moving your figure
        board[moveY][moveX] = board[posY][posX];
        board[posY][posX] = null;
        firstMove = false;
    }

}
