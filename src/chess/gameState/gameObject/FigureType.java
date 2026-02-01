package chess.gameState.gameObject;

public enum FigureType {

    PAWN('P'),
    ROOK('R'),
    KING('K');

    private final char figureCode;

    FigureType(char figureCode) {
        this.figureCode = figureCode;
    }

    public char getFigureCode() {
        return figureCode;
    }
}
