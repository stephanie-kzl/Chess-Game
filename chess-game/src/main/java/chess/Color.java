package chess;

public enum Color {
    LIGHT, DARK;
    public Color opponent() {
        //returns the opposite color of the player
        return this == LIGHT ? DARK : LIGHT;
    }

}
