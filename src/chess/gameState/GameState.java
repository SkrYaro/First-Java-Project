package chess.gameState;

import chess.Coordinates;
import chess.gameState.gameObject.Bishop;
import chess.gameState.gameObject.Figure;
import chess.gameState.gameObject.Horse;
import chess.gameState.gameObject.Pawn;
import chess.gameState.gameObject.Queen;
import chess.gameState.gameObject.Rook;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameState {

    public static final int BOARD_MIN_ROWS = 0;
    public static final int BOARD_MIN_COLS = 0;
    public static final int BOARD_MAX_ROWS = 8;
    public static final int BOARD_MAX_COLS = 8;

    public Figure[][] board;

//    private boolean check = false;

    public boolean check;

    public GameState() {
        board = BoardFactory.testBoard(BOARD_MAX_ROWS, BOARD_MAX_COLS);
    }

    public String getHorizonLine() {
        StringBuilder horizonLine = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            char letter = (char) ('a' + i);
            horizonLine.append("  | ").append(letter);
        }
        return horizonLine.toString();
    }

    // visual show where figures
    public String getBoard() {
        StringBuilder consoleBoard = new StringBuilder();
        String upLine = "―――――――――――――――――――――――――\n";
        String sideLine = "|";
        consoleBoard.append(getHorizonLine()).append('\n');
        consoleBoard.append(upLine);
        for (int i = 0; i < board.length; i++) {
            consoleBoard.append(i + 1).append(sideLine);
            for (int j = 0; j < board[i].length; j++) {
                Figure figure = board[i][j];
                if (figure == null) {
                    consoleBoard.append("    ,");
                } else {
                    consoleBoard.append(' ').append(figure.getConsoleString()).append(" ,");
                }
            }
            consoleBoard.append(sideLine);
            consoleBoard.append('\n');
        }
        consoleBoard.append(upLine);
        return consoleBoard.toString();
    }

    public String getBoard(List<Coordinates> possibleMoves) {

        boolean possibleBoolMove = false;

        StringBuilder consoleBoard = new StringBuilder();
        String upLine = "―".repeat(32) + "\n";
        String sideLine = "|";
        consoleBoard.append(getHorizonLine()).append('\n');
        consoleBoard.append(upLine);
        for (int i = 0; i < board.length; i++) {
            consoleBoard.append(i + 1).append(sideLine);
            for (int j = 0; j < board[i].length; j++) {
                Figure figure = board[i][j];
                for (Coordinates possibleMove : possibleMoves) {
                    if (possibleMove.x == j && possibleMove.y == i) {

                        if (board[i][j] == null) {
                            consoleBoard.append("⬜  ,");
                        } else {
                            consoleBoard.append("⬜").append(figure.getConsoleString()).append(',');
                        }
                        possibleBoolMove = true;

                    }
                }

                if (!possibleBoolMove) {
                    if (figure == null) {
                        consoleBoard.append("    ,");
                    } else {
                        consoleBoard.append(' ').append(figure.getConsoleString()).append(" ,");
                    }
                } else {
                    possibleBoolMove = false;
                }
            }
            consoleBoard.append(sideLine);
            consoleBoard.append('\n');
        }

        consoleBoard.append(upLine);
        return consoleBoard.toString();
    }

    public List<Coordinates> getPossibleMoves(Coordinates pos) {
        Figure figure = board[pos.y][pos.x];
        if (figure != null) {
            if (check) {
                if (figure.type == 'K') {
                    return validateKingsMoves(figure.getPossibleMoves(pos, board), figure.white);
                } else {
                    return checkValidateMoves(figure.getPossibleMoves(pos, board), figure.white, figure, pos);
                }
            } else {
                if (figure.type == 'K') {
                    return validateKingsMoves(figure.getPossibleMoves(pos, board), figure.white);
                } else {
                    return checkValidateMoves(figure.getPossibleMoves(pos, board), figure.white, figure, pos);
                }
            }
        } else {
            List<Coordinates> possibleMoves = new ArrayList<>();
            System.out.println("its a null");
            return possibleMoves;
        }

    }

    public Coordinates getPossibleMove(List<Coordinates> possibleMoves, Coordinates cord) {
        for (Coordinates move : possibleMoves) {
//            System.out.println(move.x + move.y);
            if (move.x == cord.x && move.y == cord.y) {

                return move;
            }

        }
        return null;
    }

    private Coordinates getKingPos(Figure[][] board, boolean white) {
        for (int i = 0; i < BOARD_MAX_COLS; i++) {
            for (int j = 0; j < BOARD_MAX_ROWS; j++) {
                if (board[i][j] != null) {
                    if (board[i][j].type == 'K' && board[i][j].white == white) {
                        return new Coordinates(j, i);
                    }
                }
            }
        }
        throw new IllegalStateException("King must be on a board");
    }

    private List<Coordinates> getEnemyAttacks(Figure[][] board, boolean white) {
        Set<Coordinates> enemyAttacksList = new HashSet<>();
        for (int i = 0; i < BOARD_MAX_COLS; i++) {
            for (int j = 0; j < BOARD_MAX_ROWS; j++) {
                if (board[i][j] != null) {
                    if (board[i][j].white != white) {
                        enemyAttacksList.addAll(board[i][j].getForCheckMoves(new Coordinates(j, i), board));
                    }
                }
            }
        }
        return new ArrayList<>(enemyAttacksList);
    }

    public boolean isCheck(Figure[][] board, boolean white) {

        Coordinates kingPos = getKingPos(board, white);
        for (int i = 0; i < BOARD_MAX_ROWS; i++) {
            for (int j = 0; j < BOARD_MAX_COLS; j++) {
                if (board[i][j] != null) {
                    if (board[i][j].white != white) {
                        if (board[i][j].getPossibleMoves(new Coordinates(j, i), board).stream().anyMatch(kingPos::equals)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }


    public List<Coordinates> checkValidateMoves(List<Coordinates> possibleMoves, boolean white, Figure figure, Coordinates pos) {
        List<Coordinates> successMoves = new ArrayList<>();
        boolean savePawnFirstMove = false;
        if (figure.type == 'P') {
            Pawn pawn = (Pawn) figure;
            if (pawn.firstMove) {
                savePawnFirstMove = true;
            }
        }

        for (Coordinates possibleMove : possibleMoves) {

            if (board[possibleMove.y][possibleMove.x] != null) {
                if (board[possibleMove.y][possibleMove.x].type == 'K') {
                    continue;
                }
                Figure savedPos = board[possibleMove.y][possibleMove.x];
                figure.makeMove(pos, possibleMove, board);
                if (!isCheck(board, white)) {
                    successMoves.add(possibleMove);
                }
                figure.makeMove(possibleMove, pos, board);
                board[possibleMove.y][possibleMove.x] = savedPos;
            } else {
                figure.makeMove(pos, possibleMove, board);
                if (!isCheck(board, white)) {
                    successMoves.add(possibleMove);
                }
                figure.makeMove(possibleMove, pos, board);
            }
        }
        if (savePawnFirstMove) {
            Pawn pawn = (Pawn) figure;
            pawn.firstMove = true;
        }
        return successMoves;
    }

    public List<Coordinates> validateKingsMoves(List<Coordinates> possibleMoves, boolean white) {
        List<Coordinates> enemyAttacksList = getEnemyAttacks(board, white);
        List<Coordinates> wrongMoves = new ArrayList<>();

        for (Coordinates possibleMove : possibleMoves) {
            for (Coordinates coordinates : enemyAttacksList) {
                if (possibleMove.x == coordinates.x && possibleMove.y == coordinates.y) {
                    wrongMoves.add(possibleMove);
                }
            }
        }
        for (Coordinates wrongMove : wrongMoves) {
            possibleMoves.remove(wrongMove);
        }
        return possibleMoves;


    }

    public boolean isGameFinished(boolean white) {
        for (int i = 0; i < BOARD_MAX_ROWS; i++) {
            for (int j = 0; j < BOARD_MAX_COLS; j++) {
                Figure figure = board[i][j];
                if (figure != null) {
                    if (figure.white == white) {
                        if (figure.type == 'K') {
                            if (!validateKingsMoves(figure.getPossibleMoves(new Coordinates(j, i), board), white).isEmpty()) {
                                return false;
                            }
                        } else {
                            if (!checkValidateMoves((figure.getPossibleMoves(new Coordinates(j, i), board)),
                                    white, figure, new Coordinates(j, i)).isEmpty()) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    public void gameFinisher(boolean white) {
        System.out.println("Game finished");
        if (isCheck(board, white)) {
            if (!white) {
                System.out.println("Wins white");
            } else {
                System.out.println("Wins black");
            }
        } else {
            System.out.println("It's stalemate!!!");
            if (!white) {
                System.out.println("Black don't have moves");
            } else {
                System.out.println("White don't have moves");
            }
        }
    }

    public boolean isEnd(boolean white) {
        int counter;
        if (white) {
            counter = 7;
        } else {
            counter = 0;
        }
        for (int j = 0; j < BOARD_MAX_COLS; j++) {
            if (board[counter][j] != null) {
                if (board[counter][j].type == 'P') {
                    return true;
                }
            }
        }
        return false;
    }

    public Coordinates findEnd(boolean white) {
        int counter;
        if (white) {
            counter = 7;
        } else {
            counter = 0;
        }
        for (int j = 0; j < BOARD_MAX_COLS; j++) {
            if (board[counter][j] != null) {
                if (board[counter][j].type == 'P') {
                    return new Coordinates(j, counter);
                }
            }
        }
        return null;
    }

    public void changeFigure(boolean white, char chosenFigure) {
//        Figure figure = board[findEnd(white).y][findEnd(white).x];
        if ( chosenFigure == 'Q' || chosenFigure == 'q') {
            board[findEnd(white).y][findEnd(white).x] = new Queen(white);
        } else if (chosenFigure == 'H' || chosenFigure == 'h') {
            board[findEnd(white).y][findEnd(white).x] = new Horse(white);
        } else if (chosenFigure == 'R' || chosenFigure == 'r') {
            board[findEnd(white).y][findEnd(white).x] = new Rook(white);
        } else if (chosenFigure == 'B' || chosenFigure == 'b') {
            board[findEnd(white).y][findEnd(white).x] = new Bishop(white);
        }
    }
}
