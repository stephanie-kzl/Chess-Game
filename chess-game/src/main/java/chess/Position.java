package chess;

public class Position {
    private int x;
    private int y;

    //CONSTRUCTORS
    public Position(char file, int rank) {
        x = fileToInt(file);
        y = rantToInt(rank);
    }
    public Position(int arrayX, int arrayY) {
        x = arrayX;
        y = arrayY;
    }


    //METHODS
    @Override public boolean equals(Object other) {
        return other instanceof Position
                && ((Position) other).getX() == getX()
                && ((Position) other).getY() == getY();
    }

    //GETTERS AND SETTERS
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }



    //HELPER METHODS
    /*
    * *Translate the file of the algebraic chess move to its array equivalent.
    * *(ex, the 'A' in A1 becomes 0)
     */
    private static int fileToInt(char file) {
        if(!Character.isAlphabetic(file)) {
            return -1;
        }
        if(file >= 97) {
            return file-97; //A=0, B=1, etc.
        } else {
            return file-65; //a=0, b=1, etc.
        }
    }
    /*
     * *Translate the rank of the algebraic chess move to its array equivalent.
     * *(ex, the '1' in A1 becomes 0)
     * *so the 'human' position A1 becomes the computer position using an array (0,0)
     */
    private static int rantToInt(int rank){
        return rank-1;
    }

}
