package chess;

import chess.gameState.GameState;

public class Coordinates {

    public boolean success;
    public int x;
    public int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates(String figureCoordStr) {
        if (figureCoordStr.length() != 2) {
            this.success = false;
            System.out.println("You make an mistake");
        } else {
            x = figureCoordStr.charAt(0);
            y = figureCoordStr.charAt(1);
            if ((x >= 'a' && x <= ('a' + (GameState.BOARD_MAX_COLS - 1) )) && (y - 49 <= GameState.BOARD_MAX_ROWS)) {
                this.success = true;
                x = x - 'a';
                y = y - 49  ;
//                System.out.println(this.x + "   " +this.y);
            }
            else{
                System.out.println(x +"   " + y );
                System.out.println("You missed in square");
                this.success = false;
            }
        }
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Coordinates other) {
            return x == other.x && y == other.y;
        }
        return false;
    }
}
