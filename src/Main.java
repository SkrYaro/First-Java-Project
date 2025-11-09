import gameState.GameState;

class Main {
    public static void main(String[] args){
        GameState game = new GameState();
        boolean whiteMove = true;
        while (true) {
            if (whiteMove) {
                System.out.println(GameState.getBoard());



                System.out.printf("white move " + whiteMove);

                whiteMove = !whiteMove;
            }
            else {

                System.out.println(GameState.getBoard());
                System.out.printf("black move " + whiteMove);

                whiteMove = !whiteMove;
            }
        }
    }
}