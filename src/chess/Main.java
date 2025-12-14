package chess;

import chess.gameState.GameState;
import chess.gameState.gameObject.Figure;

import javax.swing.plaf.FileChooserUI;
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
        boolean playerMove = true;
        boolean playerChoseMove = true;
        while (playerMove) {
            // show board
            System.out.println(gameState.getBoard());
            playerChoosingPos = true;

            // choose figure
            while (playerChoosingPos) {
                playerChoseMove = true;
                System.out.println("Please write first: a-h , second: 1-8 for choosing poss and movement");
                String figureCoordStr = scanner.next(); // b7
                System.out.println(figureCoordStr);
                Coordinates figureCoord = new Coordinates(figureCoordStr);
                if (figureCoord.sucses) {
                    if (gameState.getPossibleMoves(figureCoord) != null) {
                        System.out.println(whiteMove + " " + gameState.board[figureCoord.x][figureCoord.y].white);
                        if ( gameState.board[figureCoord.x][figureCoord.y].white == whiteMove ) {
                            playerPosChoosed = true;
                        }else if (!whiteMove){
                            System.out.println("This isn't your color , you're white");
                        }else{
                            System.out.println("This isn't your color , you're black");
                        }

                    }else {
                        System.out.println("It is empty square");
                    }
                }

                if (playerPosChoosed) {
                    while (playerChoseMove) {
                        List<Coordinates> figurePossibleMoves = gameState.getPossibleMoves(figureCoord);
                        System.out.println((gameState.getBoard(figurePossibleMoves)));
                        System.out.println("Choose move again a-h , 1-8 ( new emoji its possible moves)");
                        String figureMove = scanner.next();

                        Coordinates moveFigureCord = new Coordinates(figureMove);

                        Coordinates movement = gameState.getPossibleMove(figurePossibleMoves, moveFigureCord);

                        Figure figure = gameState.board[figureCoord.x][figureCoord.y];

                        figure.makeMove(figureCoord, movement , gameState.board);
                        System.out.println("Move has made");
                        playerChoseMove = false;
                        playerPosChoosed = false;
                        playerChoosingPos = false;


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
}
