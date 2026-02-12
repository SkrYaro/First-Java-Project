package chess.gameState.gameObject;

import chess.Coordinates;
import chess.gameState.GameState;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Figure {

    private final List<Coordinates> directions = List.of(
            new Coordinates(1, 0),
            new Coordinates(-1, 0),
            new Coordinates(0, -1),
            new Coordinates(0, 1),
            new Coordinates(1, 1),
            new Coordinates(-1, -1),
            new Coordinates(1, -1),
            new Coordinates(-1, 1)

    );

    public Queen(boolean white) {
        super(white, 'Q');
    }

    @Override
    public List<Coordinates> getPossibleMoves(Coordinates pos, Figure[][] board) {
        int repeat = 1;
        Figure figure = board[pos.y][pos.x];

        List<Coordinates> possibleMoves = new ArrayList<>();




        for (Coordinates direction : directions) {
            for (int i = 0; i < GameState.BOARD_MAX_COLS; i++) {
                int moveX = pos.x + direction.x * repeat;
                int moveY = pos.y + direction.y * repeat;
                if ((GameState.BOARD_MIN_COLS <= moveY && moveY <= GameState.BOARD_MAX_COLS - 1) && (GameState.BOARD_MIN_ROWS <= moveX && moveX <= GameState.BOARD_MAX_ROWS - 1)) {
                    if (board[moveY][moveX] == null) {
                        possibleMoves.add(new Coordinates(moveX, moveY));
                    } else {
                        if (figure.white != board[moveY][moveX].white) {
                            possibleMoves.add(new Coordinates(moveX, moveY));
                            break;
                        } else if (figure.white == board[moveY][moveX].white) {
                            break;
                        }
                    }
                }
                repeat++;
            }
            repeat = 1;
        }
        return possibleMoves;
    }


    @Override
    public List<Coordinates> getForCheckMoves(Coordinates pos, Figure[][] board) {
        int repeat = 1;
        Figure figure = board[pos.y][pos.x];

        List<Coordinates> possibleMoves = new ArrayList<>();

        for (Coordinates direction : directions) {
            for (int i = 0; i < GameState.BOARD_MAX_COLS; i++) {
                int moveX = pos.x + direction.x * repeat;
                int moveY = pos.y + direction.y * repeat;
                if ((GameState.BOARD_MIN_COLS <= moveY && moveY <= GameState.BOARD_MAX_COLS - 1) && (GameState.BOARD_MIN_ROWS <= moveX && moveX <= GameState.BOARD_MAX_ROWS - 1)) {
                    if (board[moveY][moveX] == null) {
                        possibleMoves.add(new Coordinates(moveX, moveY));
                    } else {
                        if (figure.white != board[moveY][moveX].white && board[moveY][moveX].type != 'K') {
                            possibleMoves.add(new Coordinates(moveX, moveY));
                            break;
                        }
                        if (figure.white == board[moveY][moveX].white) {
                            break;
                        }
                    }
                }
                repeat++;
            }
            repeat = 1;
        }
        return possibleMoves;
    }
}
