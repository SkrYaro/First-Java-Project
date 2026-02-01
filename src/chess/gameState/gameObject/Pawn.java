package chess.gameState.gameObject;

import chess.Coordinates;

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
            if (board[posY - 1][posX] == null) {
                possibleMoves.add(new Coordinates(posX, posY - 1));
            }
            if (posY > 2) {
                if (firstMove && board[posY - 2][posX] == null && board[posY - 1][posX] == null) {
                    possibleMoves.add(new Coordinates(posX, posY - 2));
                }
            }
            if (posX < 7) {
                if (board[posY - 1][posX + 1] != null && board[posY - 1][posX + 1].white) {
                    possibleMoves.add(new Coordinates(posX + 1, posY - 1));
                }
            }
            if (posX > 1) {
                if (board[posY - 1][posX - 1] != null && board[posY - 1][posX - 1].white) {
                    possibleMoves.add(new Coordinates(posX - 1, posY - 1));
                }
            }
        } else {
            if (board[posY + 1][posX] == null) {
                possibleMoves.add(new Coordinates(posX, posY + 1));
            }
            if (posY < 5) {
                if (firstMove && board[posY + 2][posX] == null) {
                    possibleMoves.add(new Coordinates(posX, posY + 2));
                }
            }
            if (posX < 7) {
                if (board[posY + 1][posX + 1] != null && board[posY - 1][posX + 1].white) {
                    possibleMoves.add(new Coordinates(posX + 1, posY - 1));
                }
            }
            if (posX > 1) {
                if (board[posY + 1][posX - 1] != null && board[posY - 1][posX - 1].white) {
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

        firstMove = false;
//            moving your figure


        board[moveY][moveX] = board[posY][posX];
        board[posY][posX] = null;


    }

}
