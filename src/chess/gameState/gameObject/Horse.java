package chess.gameState.gameObject;

import chess.Coordinates;

import java.util.ArrayList;
import java.util.List;
import java.util.SequencedCollection;

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

        possibleMoves.add(new Coordinates(pos.x + 1, pos.y - 2));

        possibleMoves.add(new Coordinates(pos.x - 1, pos.y - 2));

        possibleMoves.add(new Coordinates(pos.x + 1, pos.y + 2));

        possibleMoves.add(new Coordinates(pos.x - 1, pos.y + 2));

        possibleMoves.add(new Coordinates(pos.x - 2, pos.y + 1));

        possibleMoves.add(new Coordinates(pos.x - 2, pos.y - 1));

        possibleMoves.add(new Coordinates(pos.x + 2, pos.y + 1));

        possibleMoves.add(new Coordinates(pos.x + 2, pos.y - 1));

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
