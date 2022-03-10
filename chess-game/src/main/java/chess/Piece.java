package chess;

public abstract class Piece {
    private final Color color;
    protected String lightSymbol;
    protected String darkSymbol;
    protected int value;

    //CONSTRUCTOR
    protected Piece(Color color, String lightSymbol, String darkSymbol, int value) {
        this.color = color;
        this.lightSymbol = lightSymbol;
        this.darkSymbol = darkSymbol;
        this.value = value;
    }

    //METHODS
    public abstract boolean isMoveLegal(GameState gameState, Move move);
    public abstract void makeMove(GameState gameState, Move move);
    @Override public String toString() {
        return getColor() == Color.LIGHT ? lightSymbol : darkSymbol;
    }

    //GETTERS
    public int getValue() {return value;}
    public Color getColor() {return color;}
}
