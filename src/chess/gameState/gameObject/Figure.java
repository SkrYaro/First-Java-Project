package chess.gameState.gameObject;

import chess.Coordinates;

import java.util.List;

public abstract class Figure {

    public boolean white;
    public char type;

    public Figure(boolean white, char type) {
        this.white = white;
        this.type = type;
    }

    public String getConsoleString() {
        if (white) {
            return "w" + type;
        } else {
            return "b" + type;
        }
    }

    abstract public List<Coordinates> getPossibleMoves(Coordinates pos, Figure[][] board);

    public List<Coordinates> getForCheckMoves(Coordinates pos , Figure[][] board){
        return getPossibleMoves(pos, board);
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
