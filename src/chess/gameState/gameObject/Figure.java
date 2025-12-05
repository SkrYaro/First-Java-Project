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

    abstract public void makeMove(Coordinates pos, Coordinates move , Figure[][] board);
}
