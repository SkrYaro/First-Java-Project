import gameState.GameState;
import gameState.gameObject.Pawn;

import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        GameState game = new GameState();
        boolean whiteMove = true;
        boolean run = true;

        Scanner chooseMove = new Scanner(System.in);

        while (run) {
            if (whiteMove) {

                //  Drawind board and helping poss
                try {
                    System.out.println(GameState.getHorizontLine());
                    System.out.println(GameState.getBoard());

                    System.out.println("Write horizontal line (a-h) and vertical (1-8) together");
                    System.out.println("Example : b2");
                    int[] poss = GameState.getFigure(chooseMove.nextLine());
                    System.out.println((Pawn.changePos(poss)));

                    System.out.printf("white move on" + poss);
                    whiteMove = !whiteMove;
                } catch (Exception e) {
                    System.out.println("Error uncorrect write, try again");
                }
            } else {
                try {
                    System.out.println(GameState.getHorizontLine());
                    System.out.println(GameState.getBoard());

                    System.out.println("Write X (1-8) and Y (1-8) together");
                    System.out.println("Example : 72");
                    int[] poss = GameState.getFigure(chooseMove.nextLine());
                    System.out.println((Pawn.changePos(poss)));

                    System.out.printf("white move on" + poss);
                    whiteMove = !whiteMove;
                } catch (Exception e) {
                    System.out.println("Error uncorrect write, try again");
                }
            }

        }
    }
}