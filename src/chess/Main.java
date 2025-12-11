package chess;

import chess.gameState.GameState;

import java.util.Scanner;

class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean whiteMove = true;
        while (true) {
            game(whiteMove);
            whiteMove = !whiteMove;
            scanner.next();
        }
    }

    private static void game(boolean whiteMove) {
        // Drawing board and helping poss
        System.out.println(GameState.getBoard());
        /*
        *Game working rule:
        * Validate all {
        * (show board,
        * show who move,
        * choose figure ,
        * show possible moves,
        * do anybody chosen move)
        * }
        */





    }
}
