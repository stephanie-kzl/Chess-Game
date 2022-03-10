package chess;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class GameState {
    private GameState prev, future;
    private Move lastMove;
    private Color playerTurn = Color.LIGHT;
    private Piece[][] squares;

    //CONSTRUCTORS
    public GameState() {
        this(8,8);
    }
    public GameState(int boardWidth, int boardHeight){
        squares = new Piece[boardWidth][boardHeight];
    }

    //METHODS
    public boolean isMoveLegal(Move move) {
        //is the move within the bounds of the board?
        for (int coordinate : new int[]{move.getX1(), move.getY1(), move.getX2(), move.getY2()}) {
            if (coordinate < 0 || coordinate >= getBoardWidth()) {
                return false;
            }
        }

        Piece piece = getPiece(move.getStart());
        //does the chosen piece belong to the current player and can it make the requested move?
        return piece != null
                && piece.getColor() == getPlayerTurn()
                && piece.isMoveLegal(this, move);
    }
    public void makeMove(Move move){
        getPiece(move.getStart()).makeMove(this, move);
        setLastMove(move);
    }
    public abstract void postMoveUINotification(UserInterface ui);
    public GameState copy() {
        GameState copy = null;
        try{
            copy = this.getClass().getDeclaredConstructor().newInstance();
            copy.prev = prev;
            copy.future = future;
            copy.lastMove = lastMove;
            copy.playerTurn = playerTurn;
            for(int x = 0; x < squares.length; x++) {
                for(int y = 0; y < squares[x].length; y++) {
                    copy.squares[x][y] = squares[x][y];
                }
            }
        }
        catch (Exception e) {
            System.out.println("ERROR: " + this.getClass() + " must have a no arg constructor for copy() to work.");
            System.exit(1);
        }
        return copy;
    }
    public ArrayList<Position> getPlayerPositons(Color playerColor) {
        ArrayList<Position> positions = new ArrayList<>();

        for(Position position : getAllPossiblePositions()) {
            Piece piece = getPiece(position);
            if(piece != null && piece.getColor() == playerColor) {
                positions.add(position);
            }
        }
        return positions;
    }
    public ArrayList<Position> getAllPossiblePositions() {
        ArrayList<Position> positions = new ArrayList<>(64);

        for(int x = 0; x < getBoardWidth(); x++) {
            for(int y = 0; y < getBoardHeight(); y++) {
                positions.add(new Position(x, y));
            }
        }
        return positions;
    }
    public boolean equals(GameState other) {
        return playerTurn == other.playerTurn
                && Arrays.deepEquals(squares, other.squares);
    }
    public boolean isTerminal() {
        boolean whiteFound = false;
        boolean blackFound = false;
        for (Position position : getAllPossiblePositions()) {
            Piece piece = getPiece(position);
            if(piece != null) {
                if(piece.getColor() == Color.LIGHT) {
                    whiteFound = true;
                } else {
                    blackFound = true;
                }
            }
        }
        return !whiteFound || !blackFound;
    }
//STOPPED YOUTUBE VIDEO AT 6:21


    //GETTERS AND SETTERS
    public GameState getPrev() {return prev;}
    public void setPrev(GameState prev) {this.prev = prev;}

    public GameState getFuture() {return future;}
    public void setFuture(GameState future) {this.future = future;}

    public Move getLastMove() {return lastMove;}
    public void setLastMove(Move lastMove) {this.lastMove = lastMove;}

    public Color getPlayerTurn() {return playerTurn;}
    public void setPlayerTurn(Color playerTurn) {this.playerTurn = playerTurn;}

    public Piece[][] getSquares() {return squares;}
    public void setSquares(Piece[][] squares) {this.squares = squares;}

    public int getBoardWidth() {return squares.length;}
    public int getBoardHeight() {return squares[0].length;}
}
