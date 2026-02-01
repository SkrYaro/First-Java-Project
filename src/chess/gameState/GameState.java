package chess.gameState;

import chess.Coordinates;
import chess.gameState.gameObject.Figure;

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
            /*
            if (getCheck(figure.white)) {
                if (figure.type == 'K') {
                    return validateKingsMoves(figure.getPossibleMoves(pos, board), figure.white);
                } else {
                    return checkValidateMoves(figure.getPossibleMoves(pos, board), figure.white, figure, pos);
                }
            }*/
            if (figure.type == 'K') {
                return validateKingsMoves(figure.getPossibleMoves(pos, board), figure.white);
            } else {
                return checkValidateMoves(figure.getPossibleMoves(pos, board),figure.white,figure,pos);
            }
        } else {
            List<Coordinates> possibleMoves = new ArrayList<>();
            System.out.println("its a null");
            return possibleMoves;
        }
    }

   /* public void showPossibleMoves(List<Coordinates> possibleMoves) {
        for (Coordinates possibleMove : possibleMoves) {
            //            possibleList[i] += (possibleMoves.get(i).x + possibleMoves.get(i).y);
            char a = (char) ('a' + possibleMove.x);
            System.out.println((a + "" + (possibleMove.y + 1)));
        }

    }*/

    public Coordinates getPossibleMove(List<Coordinates> possibleMoves, Coordinates cord) {
        for (Coordinates move : possibleMoves) {
//            System.out.println(move.x + move.y);
            if (move.x == cord.x && move.y == cord.y) {

                return move;
            }
            System.out.println("don't have possible move try again");
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
                    if (board[i][j].white != white && board[i][j].type != 'K') {
                        enemyAttacksList.addAll(board[i][j].getPossibleMoves(new Coordinates(j, i), board));
                    }
                }
            }
        }
        return new ArrayList<>(enemyAttacksList);
    }

    public List<Coordinates> checkValidateMoves(List<Coordinates> possibleMoves, boolean white, Figure figure, Coordinates pos) {
        List<Coordinates> successMoves = new ArrayList<>();
        for (Coordinates possibleMove : possibleMoves) {
            if (board[possibleMove.y][possibleMove.x] != null) {
                Figure savedPos = board[possibleMove.y][possibleMove.x];
                figure.makeMove(pos, possibleMove, board);
                if (!getCheck(white)) {
                    successMoves.add(possibleMove);
                }
                figure.makeMove(possibleMove, pos, board);
                board[possibleMove.y][possibleMove.x] = savedPos;
            } else {
                figure.makeMove(pos, possibleMove, board);
                if (!getCheck(white)) {
                    successMoves.add(possibleMove);
                }
                figure.makeMove(possibleMove, pos, board);
            }
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

    public void isCheck(boolean white) {
        if (getCheck(white)){
            System.out.println("You are under attack");
        }
    }

    private boolean getCheck(boolean white) {
        Coordinates kingPos = getKingPos(board, white);
        return getEnemyAttacks(board, white).stream()
                .anyMatch(kingPos::equals);
    }
}

