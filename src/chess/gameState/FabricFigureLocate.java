package chess.gameState;

import chess.gameState.gameObject.Bishop;
import chess.gameState.gameObject.Figure;
import chess.gameState.gameObject.Horse;
import chess.gameState.gameObject.King;
import chess.gameState.gameObject.Pawn;
import chess.gameState.gameObject.Queen;
import chess.gameState.gameObject.Rook;

public class FabricFigureLocate {
    public FabricFigureLocate() {

    }

    public void defaultGame(Figure[][] board) {
        defaultSet(board);
    }

    private void defaultSet(Figure[][] board) {
        int side = 0;
        boolean white = true;
        for (int k = 0; k < GameState.BOARD_MAX_ROWS; k++) {
            board[1][k] = new Pawn(white);
            board[6][k] = new Pawn(false);
        }
        for (int i = 0; i < 2; i++) {
            board[side][0] = new Rook(white);
            board[side][1] = new Horse(white);
            board[side][2] = new Bishop(white);

            board[side][3] = new Queen(white);
            board[side][4] = new King(white);

            board[side][5] = new Bishop(white);
            board[side][6] = new Horse(white);
            board[side][7] = new Rook(white);

            side = 7;
            white = !white;
        }
    }

    public void testingGame(Figure[][] board) {
        testGame(board);
    }

    private void testGame(Figure[][] board) {
        board[0][0] = new King(true);
        board[7][7] = new Bishop(false);
        board[1][4] = new Bishop(false);
        board[3][2] = new Queen(false);

    }

}
