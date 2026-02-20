package chess.gameState.gameObject;

import chess.Coordinates;
import chess.gameState.GameState;

import java.util.ArrayList;
import java.util.List;

public class King extends Figure {


    public King(boolean white) {
        super(white, 'K');
    }

    boolean castle = true;

    List<Coordinates> vectors = List.of(
            new Coordinates(1, 1),
            new Coordinates(-1, 1),
            new Coordinates(0, 1),
            new Coordinates(-1, 0),
            new Coordinates(1, 0),
            new Coordinates(-1, -1),
            new Coordinates(1, -1),
            new Coordinates(0, -1)
    );

    @Override
    public List<Coordinates> getPossibleMoves(Coordinates pos, Figure[][] board) {
        List<Coordinates> possibleMoves = new ArrayList<>();
        Figure figure = board[pos.y][pos.x];
        for (Coordinates vector : vectors) {
            int moveX = pos.x + vector.x;
            int moveY = pos.y + vector.y;

            if ((GameState.BOARD_MIN_COLS <= moveY && moveY <= GameState.BOARD_MAX_COLS - 1) && (GameState.BOARD_MIN_ROWS <= moveX && moveX <= GameState.BOARD_MAX_ROWS - 1)) {
                if (board[moveY][moveX] == null) {
                    possibleMoves.add(new Coordinates(moveX, moveY));
                } else {
                    if (figure.white != board[moveY][moveX].white) {
                        possibleMoves.add(new Coordinates(moveX, moveY));
                    }
                }
            }
        }
        return possibleMoves;
    }

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
        castle = false;
    }
}
