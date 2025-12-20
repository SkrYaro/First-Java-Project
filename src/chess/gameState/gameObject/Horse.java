package chess.gameState.gameObject;

import chess.Coordinates;

import java.util.ArrayList;
import java.util.List;
import java.util.SequencedCollection;

public class Horse extends Figure {


    public Horse(boolean white, char type) {
        super(white, 'H');
    }

    @Override
    public List<Coordinates> getPossibleMoves(Coordinates pos, Figure[][] board) {
        List<Coordinates> possibleMoves = new ArrayList<>();
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
