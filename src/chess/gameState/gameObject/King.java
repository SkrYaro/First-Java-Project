package chess.gameState.gameObject;

import chess.Coordinates;

import java.util.ArrayList;
import java.util.List;

public class King extends Figure {


    public King(boolean white) {
        super(white, 'K');
    }

    @Override
    public List<Coordinates> getPossibleMoves(Coordinates pos, Figure[][] board) {
        List<Coordinates> possibleMoves = new ArrayList<>();
        Figure figure = board[pos.y][pos.x];
        if (pos.y >= 1) {

            if (board[pos.y - 1][pos.x] == null) {
                possibleMoves.add(new Coordinates(pos.x, pos.y - 1));
            } else {
                if (figure.white != board[pos.y - 1][pos.x].white) {
                    possibleMoves.add(new Coordinates(pos.x, pos.y - 1));
                }
            }

            if (pos.x <= 7) {
                if (board[pos.y - 1][pos.x + 1] == null) {
                    possibleMoves.add(new Coordinates(pos.x + 1, pos.y - 1));
                } else {
                    if (figure.white != board[pos.y - 1][pos.x + 1].white) {
                        possibleMoves.add(new Coordinates(pos.x + 1, pos.y - 1));
                    }
                }
            }
            if (pos.x >= 1) {
                if (board[pos.y - 1][pos.x - 1] == null) {
                    possibleMoves.add(new Coordinates(pos.x - 1, pos.y - 1));
                } else {
                    if (figure.white != board[pos.y - 1][pos.x - 1].white) {
                        possibleMoves.add(new Coordinates(pos.x - 1, pos.y - 1));
                    }
                }
            }
        }
        if (pos.x <= 7 ) {
            if (board[pos.y][pos.x + 1] == null) {
                possibleMoves.add(new Coordinates(pos.x + 1, pos.y));
            } else {
                if (figure.white != board[pos.y][pos.x + 1].white) {
                    possibleMoves.add(new Coordinates(pos.x + 1, pos.y));
                }
            }
        }
        if (pos.x >= 1) {
            if (board[pos.y][pos.x - 1] == null) {
                possibleMoves.add(new Coordinates(pos.x - 1, pos.y));
            } else {
                if (figure.white != board[pos.y][pos.x -1].white) {
                    possibleMoves.add(new Coordinates(pos.x - 1, pos.y));
                }
            }
        }
        if (pos.y <= 7) {
            if (board[pos.y + 1][pos.x] == null) {
                possibleMoves.add(new Coordinates(pos.x, pos.y + 1));
            } else {
                if (figure.white != board[pos.y + 1][pos.x].white) {
                    possibleMoves.add(new Coordinates(pos.x, pos.y + 1));
                }
            }
            if (pos.x >= 1) {
                if (board[pos.y + 1][pos.x - 1] == null) {
                    possibleMoves.add(new Coordinates(pos.x - 1, pos.y + 1));
                } else {
                    if (figure.white != board[pos.y + 1][pos.x - 1].white) {
                        possibleMoves.add(new Coordinates(pos.x - 1, pos.y + 1));
                    }
                }
            }
            if (pos.x <= 7) {
                if (board[pos.y + 1][pos.x + 1] == null) {
                    possibleMoves.add(new Coordinates(pos.x + 1, pos.y + 1));
                } else {
                    if (figure.white != board[pos.y + 1][pos.x + 1].white) {
                        possibleMoves.add(new Coordinates(pos.x + 1, pos.y + 1));
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
