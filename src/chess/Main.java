package chess;

import chess.gameState.GameState;
import chess.gameState.gameObject.Figure;

import java.util.List;
import java.util.Scanner;

class Main {

    private static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        boolean whiteMove = true;
        GameState gameState = new GameState();
        while (true) {
            game(gameState, whiteMove);
            whiteMove = !whiteMove;
        }
    }

    private static void game(GameState gameState, boolean whiteMove) {
        boolean playerChoosingPos = true;
        boolean playerPosChoosed = false;
        boolean playerChoseMove;
        boolean cancel;


        // show board
        System.out.println(gameState.getBoard());

        // choose figure
        while (playerChoosingPos) {
            cancel = false;
            playerChoseMove = true;
            System.out.println("Please write first: a-h , second: 1-8 for choosing poss and movement");
            String figureCoordStr = scanner.next(); // b7
            System.out.println(figureCoordStr);
            Coordinates figureCoord = new Coordinates(figureCoordStr);
            if (figureCoord.success) {
                if (gameState.getPossibleMoves(figureCoord) != null) {
                    Figure figure = gameState.board[figureCoord.y][figureCoord.x];
//                        System.out.println(figureCoord.x + " " + figureCoord.y);
//                        System.out.println(whiteMove + " " + figure.white);
                    if (figure != null) {
                        if (figure.white == whiteMove) {
                            playerPosChoosed = true;
                        } else if (!whiteMove) {
                            System.out.println("This isn't your color, you're white");
                            System.out.println(gameState.getBoard());
                        } else {
                            System.out.println("This isn't your color, you're black");
                            System.out.println(gameState.getBoard());
                        }
                    } else {
                        System.out.println("Error, this is empty square");
                        System.out.println(gameState.getBoard());
                    }
                } else {
                    System.out.println("It is empty square");
                    System.out.println(gameState.getBoard());
                }
                figureCoord.success = false;
            }


            if (playerPosChoosed) {
                while (playerChoseMove) {
                    List<Coordinates> figurePossibleMoves = gameState.getPossibleMoves(figureCoord);
                    System.out.println(gameState.getBoard(figurePossibleMoves));
                    System.out.println("Choose move again a-h, 1-8 (new emoji its possible moves) if want cancel write \"cancel\"");
                    String figureMove = scanner.next();
                    if (figureMove.equals("cancel")) {
                        cancel = true;
                        playerPosChoosed = false;
                        playerChoseMove = false;
                        System.out.println("You cancel move");
                        System.out.println(gameState.getBoard());
                    }

                    if (!cancel) {
                        Coordinates moveFigureCord = new Coordinates(figureMove);

                        Coordinates movement = gameState.getPossibleMove(figurePossibleMoves, moveFigureCord);

                        if (movement == null) {
                            System.out.println("Not valid move, pick again");
                            continue;
                        }

                        Figure figure = gameState.board[figureCoord.y][figureCoord.x];

                        figure.makeMove(figureCoord, movement, gameState.board);
                        System.out.println("Move has been made");
                        playerChoseMove = false;
                        playerPosChoosed = false;
                        playerChoosingPos = false;
                    }


                }
            }
        }
        // validate choosing
        // choose move
        // validate move
        // move


        // Drawing board and helping poss
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
