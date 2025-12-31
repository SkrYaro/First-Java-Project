package chess.gameState.gameObject;

import chess.Coordinates;
import chess.gameState.GameState;

import java.util.ArrayList;
import java.util.List;

public class Horse extends Figure {


    public Horse(boolean white) {
        super(white, 'H');
    }

    @Override
    public List<Coordinates> getPossibleMoves(Coordinates pos, Figure[][] board) {
                /*
                *
                *        | a  | b  | c  | d  | e  | f  | g  | h
                ―       ――――――――――――――――――――――――
                1       | wR , wH , wB , wQ , wK , wB , wH , wR ,|
                2       |    ,    , XX ,    , XX ,    ,    ,    ,|
                3       |    , XX ,    ,    ,    , XX ,    ,    ,|
                4       |    ,    ,    , bH ,    ,    ,    ,    ,|
                5       |    , XX ,    ,    ,    , XX ,    ,    ,|
                6       |    ,    , XX ,    , XX ,    ,    ,    ,|
                7       | bP , bP , bP , bP , bP , bP , bP , bP ,|
                8       | bR ,  , bB , bQ , bK , bB , bH , bR ,|
                *
                * */
        List<Coordinates> possibleMoves = new ArrayList<>();

        Figure figure = board[pos.y][pos.x];

        List<Coordinates> vectors = List.of(
                new Coordinates(2, 1),
                new Coordinates(1, 2),
                new Coordinates(-2, -1),
                new Coordinates(-1, -2),
                new Coordinates(2, -1),
                new Coordinates(-1, 2),
                new Coordinates(-2, 1),
                new Coordinates(1, -2)
        );

        for (Coordinates vector: vectors) {
            int moveX = pos.x + vector.x; // x = [0; 7]
            int moveY = pos.y + vector.y; // y = [0; 7]
            if (moveX < GameState.BOARD_MIN_COLS - 1 || moveX > GameState.BOARD_MAX_COLS - 1) { // && = AND, || = OR
                continue;
            }
            // if on board
            // if figure && figure.white != white
            possibleMoves.add(new Coordinates(moveX, moveY));
        }

        if (pos.y >= 3) {

            if (pos.x <= 6) {

                if (board[pos.y - 2][pos.x + 1] == null) {
                    possibleMoves.add(new Coordinates(pos.x + 1, pos.y - 2));
                } else {
                    if (figure.white != board[pos.y - 2][pos.x + 1].white) {
                        possibleMoves.add(new Coordinates(pos.x + 1, pos.y - 2));
                    }
                }
            }
            if (pos.x >= 1) {
                if (board[pos.y - 2][pos.x - 1] == null) {
                    possibleMoves.add(new Coordinates(pos.x - 1, pos.y - 2));
                } else {
                    if (figure.white != board[pos.y - 2][pos.x - 1].white) {
                        possibleMoves.add(new Coordinates(pos.x - 1, pos.y - 2));
                    }
                }
            }
        }
        if (pos.y <= 6) {

            if (pos.x <= 6) {
                if (board[pos.y + 2][pos.x + 1] == null) {
                    possibleMoves.add(new Coordinates(pos.x + 1, pos.y + 2));
                } else {
                    if (figure.white != board[pos.y + 2][pos.x + 1].white) {
                        possibleMoves.add(new Coordinates(pos.x + 1, pos.y + 2));
                    }
                }
            }

            if (pos.x >= 1) {
                if (board[pos.y + 2][pos.x - 1] == null) {
                    possibleMoves.add(new Coordinates(pos.x - 1, pos.y + 2));
                } else {
                    if (figure.white != board[pos.y + 2][pos.x - 1].white) {
                        possibleMoves.add(new Coordinates(pos.x - 1, pos.y + 2));
                    }
                }
            }
        }

        if (pos.x >= 2) {

            if (pos.y <= 6) {
                if (board[pos.y + 1][pos.x - 2] == null) {
                    possibleMoves.add(new Coordinates(pos.x - 2, pos.y + 1));
                } else {
                    if (figure.white != board[pos.y + 1][pos.x - 2].white) {
                        possibleMoves.add(new Coordinates(pos.x - 2, pos.y + 1));
                    }
                }
            }

            if (pos.y >= 3) {


                if (board[pos.y - 1][pos.x - 2] == null) {
                    possibleMoves.add(new Coordinates(pos.x - 2, pos.y - 1));
                } else {
                    if (figure.white != board[pos.y - 1][pos.x - 2].white) {
                        possibleMoves.add(new Coordinates(pos.x - 2, pos.y - 1));
                    }
                }

            }
        }
        if (pos.x <= 6) {
            if (pos.y <= 6) {
                if (board[pos.y + 1][pos.x + 2] == null) {
                    possibleMoves.add(new Coordinates(pos.x + 2, pos.y + 1));
                } else {
                    if (figure.white != board[pos.y + 1][pos.x + 2].white) {
                        possibleMoves.add(new Coordinates(pos.x + 2, pos.y + 1));
                    }
                }
            }
            if (pos.y >= 3) {
                if (board[pos.y - 1][pos.x + 2] == null) {
                    possibleMoves.add(new Coordinates(pos.x + 2, pos.y - 1));
                } else {
                    if (figure.white != board[pos.y - 1][pos.x + 2].white) {
                        possibleMoves.add(new Coordinates(pos.x + 2, pos.y - 1));
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


    }

}
