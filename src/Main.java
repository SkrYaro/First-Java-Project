import gameState.GameState;
import gameState.gameObject.Pawn;

class Main {
    public static void main(String[] args){
        GameState game = new GameState();
        boolean whiteMove = true;
        boolean run = true;
        while (run) {
            if (whiteMove) {
                System.out.println(GameState.getBoard());

                System.out.println((Pawn.changePos(new int[] {6 , 1} )));
                System.out.println((Pawn.changePos(new int[] {6 , 2} )));


                System.out.printf("white move " + whiteMove);

                whiteMove = !whiteMove;
            }
            else {

                System.out.println(GameState.getBoard());
                System.out.printf("black move " + whiteMove);

                whiteMove = !whiteMove;
            }
            run = false;
        }
    }
}