import java.util.ArrayList;

/**
 * The Hexagon class represents a hexagonal cell on the game board.
 */
public class Hexagon {

    // instance variables
    private final int x; // x-coordinate of the hexagon
    private final int y; // y-coordinate of the hexagon
    private final int hexagonNum; // unique identifier for the hexagon
    private boolean containsAtom; // indicates if the hexagon contains an atom
    private boolean containsGuessAtom; // indicates if the hexagon contains a guessed atom
    private boolean hasInfluence; // indicates if the hexagon has influence from neighboring atoms
    private ArrayList<Board.Direction> directionsOfInfluence; // list of directions of influence from neighboring atoms


    // constructor
    /**
     * Constructs a new Hexagon object with the specified coordinates.
     *
     * @param x The x-coordinate of the hexagon.
     * @param y The y-coordinate of the hexagon.
     * @throws IllegalArgumentException if the x or y coordinate is out of range.
     */
    public Hexagon(int x, int y) {
        // x co-ordinate input validation
        if (x > 4 || x < -4) {
            throw new IllegalArgumentException("x co-ordinate must be between -4 and 4");
        }
        // y co-ordinate input validation
        if (y > 4 || y < -4) {
            throw new IllegalArgumentException("y co-ordinate must be between -4 and 4");
        }

        // declare variables
        this.x = x;
        this.y = y;
        this.containsAtom = false;
        this.hasInfluence = false;
        this.directionsOfInfluence = new ArrayList<>();
        this.hexagonNum = getHexagonNumFromCord(x, y);
        this.containsGuessAtom = false;
    }

    // getter methods
    //gets x value
    /**
     * return x value
     * @return an int .
     */
    public int getX() {
        return x;
    }

    /**
     * return y value
     * @return an int .
     */
    public int getY() {
        return y;
    }

    public boolean hasInfluence(){
        return hasInfluence;
    }

    //
    /**
     * @param direction
     * add influence direction to arraylist
     */
    public void placeInfluence(Board.Direction direction){
        hasInfluence = true;
        directionsOfInfluence.add(direction);
    }
    public void removeInfluence() {

        hasInfluence = false;

    }

    /**
     * Determines if the hexagon is located on the side of the board.
     *
     * @return true if the hexagon is on the side, false otherwise.
     */
    public boolean isSide() {

        //sides are: y = -4, x = -4, y = 4, x = 4
        // other sides: (3, -1), (2, -2), (1, -3)
        //            : (-1, 3), (-2, 2), (-3, 1)

        if((x == 3 && y == -1) || (x == 2 && y == -2) || (x == 1 && y == -3) || (x == -1 && y == 3) || (x == -2 && y == 2) || (x == -3 && y == 1)) {
            return true;
        }
        return (x == 4 || x == -4 || y == 4 || y == -4);
    }


    /**
     * Determines if the hexagon coantains atom.
     *
     * @return true if the hexagon is on the side, false otherwise.
     */
    public void setContainsAtom(boolean bool) {
        containsAtom = bool;
    }

    // remove atom
    public boolean hasAtom() {
        return containsAtom;
    }

    /**
     * Returns a string representation of the hexagon, showing its coordinates.
     *
     * @return A string representation of the hexagon.
     */
    // toString method override returns co-ordinate of Hexagon
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    //return directionOfInfluence
    /**
     * Returns list of directions of where hexagon has been influenced from
     *
     * @return list if directions.
     */
    public ArrayList<Board.Direction> getDirectionsOfInfluence() {
        return directionsOfInfluence;
    }



    /**
     * Returns a boolean if hexagon contains atom.
     *
     * @return a boolean.
     */
    public boolean hasGuessAtom() {
        return containsGuessAtom;
    }
    //set hexagon to contain a guess atom
    public void setContainsGuessAtom(boolean bool){
        this.containsGuessAtom = bool;
    }



    /**
     * Returns the unique identifier (hexagon number) for the hexagon based on its coordinates.
     *
     * @param x The x-coordinate of the hexagon.
     * @param y The y-coordinate of the hexagon.
     * @return The unique identifier (hexagon number) for the hexagon.
     */
    // method that calculates hexagon number from a given co-ordinate
    public int getHexagonNumFromCord(int x, int y) {

        int offset = 0;


        if(y == 4) {
            offset = -1;
        }
        else if(y == 3) {
            offset = -7;
        }
        else if(y == 2) {
            offset = -14;
        }
        else if(y == 1) {
            offset = -22;
        }
        else if(y == 0) {
            offset = -31;
        }
        else if(y == -1) {
            offset = -40;
        }
        else if(y == -2) {
            offset = -48;
        }
        else if(y == -3) {
            offset = -55;
        }
        else if(y == -4) {
            offset = -61;
        }


        return x - offset;
    }
}

