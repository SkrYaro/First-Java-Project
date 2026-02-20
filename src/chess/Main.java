package chess;

import chess.gameState.GameState;
import chess.gameState.gameObject.Figure;

import java.util.List;
import java.util.Scanner;

class Main {

    private static final Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        boolean whiteMove = true;
        GameState gameState = new GameState();
        game(gameState, whiteMove);
    }

//    #TODO Make a game menu

    private static void game(GameState gameState, boolean whiteMove) {
        boolean run = true;
        while (run) {
            boolean playerChoosingPos = true;
            boolean playerPosChoosed = false;
            boolean playerChoseMove = true;
            boolean cancel;

            // show board
            System.out.println(gameState.getBoard());
            while (playerChoosingPos) {
                cancel = false;

                System.out.println("Please write first: a-h , second: 1-8 for choosing poss and movement");
                String figureCoordStr = scanner.next();
                Coordinates figureCoord = new Coordinates(figureCoordStr);
                if (figureCoord.success) {
                    if (gameState.getPossibleMoves(figureCoord) != null) {
                        Figure figure = gameState.board[figureCoord.y][figureCoord.x];
                        if (figure != null) {
                            if (figure.white == whiteMove) {
                                playerPosChoosed = true;
                            } else if (!whiteMove) {
                                System.out.println("This isn't your color, you're white (now turn black)");
                                System.out.println(gameState.getBoard());
                            } else {
                                System.out.println("This isn't your color, you're black (now turn white)");
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
                                System.out.println("don't have possible move try again");
                                continue;
                            }

                            Figure figure = gameState.board[figureCoord.y][figureCoord.x];

                            figure.makeMove(figureCoord, movement, gameState.board);
                            System.out.println("Move has been made");
                            playerChoseMove = false;
                            playerPosChoosed = false;
                            playerChoosingPos = false;
                        }

                        if (gameState.isEnd(whiteMove)) {
                            System.out.println("You have pawn , whats come to the end board and you can chose figure");
                            System.out.println("There are : queen , horse , bishop , rook ");
                            System.out.println("You can write any case , but important first letter ( 'q' 'h' 'b' 'r') ");
                            String choose = scanner.next();

                            char a = choose.charAt(0);

                            gameState.changeFigure(whiteMove, a);
                        }

                        if (gameState.isCheck(gameState.board, !whiteMove)) {
                            System.out.println("Is check!");
                        }
                        if (gameState.isGameFinished(!whiteMove)) {

                            gameState.gameFinisher(!whiteMove);
                            run = false;
                            gameState.getBoard();
                            break;
                        }

                    }
                }
                if (!run) {
                    break;
                }
            }
            whiteMove = !whiteMove;
        }
    }
}
