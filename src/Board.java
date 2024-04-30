import java.util.ArrayList;
/**
 * The Board class represents the game board containing hexagonal cells.
 * It manages the placement of atoms, rays, and guess atoms on the board,
 * as well as the circle of influence around placed atoms.
 */
public class Board {
    // Constants for the origin coordinates of the board
    private static final int xOrigin = 59;
    private static final int yOrigin = 28;

    // List to hold all hexagonal cells on the board
    private final ArrayList<Hexagon> listBoard = new ArrayList<>();

    // List to hold all rays on the board
    private ArrayList<Ray> rays;

    // Number of atoms and rays placed on the board
    public int numAtomsPlaced = 0;
    public int numRaysPlaced = 0;

    // Number of guessed atoms placed on the board
    public int numGuessAtomsPlaced = 0;



    /**
     * Constructs a new Board object with predefined string representation.
     */
    public Board() {

        this.rays = new ArrayList<>();
        initialiseBoard();

    }

    /**
     * Initializes the board by populating the list of hexagons based on predefined coordinates.
     */
    protected void initialiseBoard(){
        int key = 0; // this key ensures we don't fill in hexagons that do not exist

        // fill out board
        for (int x = -4; x <= 4; x++) {

            // for x is negative
            if (x < 0) {

                // add hexagon onto the board
                for (int y = -4; y <= key; y++) {
                    listBoard.add(new Hexagon(x, y));
                }
                key++;
            }

            // for x is positive key ends at 4.
            if (x >= 0) {

                // add hexagon onto the board
                for (int y = 4; y >= (-key); y--) { // -key as positive x co-ordinate flips the y (i.e. every positive x has y 4 -> 0, same as every negative x having y -4 -> 0)
                    listBoard.add(new Hexagon(x, y));
                }
                key--;
            }

        }
    }

    public enum Direction { // made public for use in other classes
        NORTHWEST(0),
        WEST(1),
        SOUTHWEST(2),
        SOUTHEAST(3),
        EAST(4),
        NORTHEAST(5);

        // method to determine whether 2 directions are "opposite" of each other

        private final int value;

        Direction(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        // Static method to get Direction based on the value
        public static Direction fromValue(int value) {
            for (Direction direction : values()) {
                if (direction.getValue() == value) {
                    return direction;
                }
            }
            throw new IllegalArgumentException("Invalid Direction value: " + value);
        }

        // returns the opposite direction
        public Direction getOpposite() {
            int thisDirectionValue = this.getValue();

            // opposite directions are always 3 away from each other.
            // use %6 so we stay within 0-5 bounds
            thisDirectionValue = (thisDirectionValue + 3)%6;

            return fromValue(thisDirectionValue);
        }
    }



    //returns entire list on hexagons on board
    public ArrayList<Hexagon> getListBoard() {
        return listBoard;
    }
    /**
     * Returns the list of all hexagonal cells on the board.
     *
     * @return The ArrayList containing all hexagonal cells.
     */
    //returns all the rays
    public ArrayList<Ray> getRays() {
        return rays;
    }




    /**
     * Retrieves the hexagonal cell at the specified coordinates from the list of hexagons.
     *
     * @param x The x-coordinate of the hexagon.
     * @param y The y-coordinate of the hexagon.
     * @return The Hexagon object at the specified coordinates, or null if not found.
     */
//find the hexagon from teh list of hexagons retruns null if not found
    public Hexagon getListHexagon(int x, int y) {

        // iterate over board
        for(Hexagon hexagon : listBoard) {

            // if the hexagon has the x and y we are looking for, return it
            if(hexagon.getX() == x && hexagon.getY() == y) {
                return hexagon;
            }
        }

        return null;
    }

    /**
     * Adds or removes a guessed atom at the specified coordinates on the board.
     *
     * @param x The x-coordinate of the cell.
     * @param y The y-coordinate of the cell.
     */
//add a guessed atom to board
    public void addGuessAtom(int x, int y){
        if(!getListHexagon(x, y).hasGuessAtom()) {
            getListHexagon(x, y).setContainsGuessAtom(true);
            numGuessAtomsPlaced++;
        }else{
            getListHexagon(x, y).setContainsGuessAtom(false);
            numGuessAtomsPlaced--;
        }
    }


    // Method to add a character into the center of hexagon using the (x, y) co-ordinates.


    /**
     * Adds an atom at the specified coordinates on the board.
     *
     * @param x The x-coordinate of the cell.
     * @param y The y-coordinate of the cell.
     */
    //adds atoms to baord
    public void addAtom(int x, int y){
        getListHexagon(x, y).setContainsAtom(true);
        addCircleOfInfluence(x, y);
        numAtomsPlaced++;

    }
    /**
     * Removes an atom at the specified coordinates on the board.
     *
     * @param x The x-coordinate of the cell.
     * @param y The y-coordinate of the cell.
     */
    //removes an atoms
    public void removeAtom(int x, int y) {
        getListHexagon(x, y).setContainsAtom(false);
        removeCircleOfInfluence(x, y);
        numAtomsPlaced--;
    }

    /**
     * Adds a ray to the board.
     *
     * @param ray The ray object to be added.
     */
    //adds ray to board
    public void addRay(Ray ray) {
        rays.add(ray);
    }



    /**
     * Finds the direction of the specified index in the hexagonal grid.
     *
     * @param i The index in the hexagonal grid.
     * @return The Direction enum representing the direction.
     */
    public Direction findDirection(int i){

        if(i < 10 && i%2 == 1){
            return Direction.SOUTHEAST;
        }else if(i < 20 && i%2 == 0){
            return Direction.EAST;
        }else if(i < 29 && i%2 == 1){
            return Direction.NORTHEAST;
        }else if(i < 37 && i%2 == 0){
            return Direction.NORTHWEST;
        }else if(i < 46 && i%2 ==1){
            return Direction.WEST;
        }else if(i < 55 && i%2 == 0){
            return Direction.SOUTHWEST;
        }else if(i < 55 && i%2 == 1){
            return Direction.SOUTHEAST;
        }

        return null;
    }

    /**
     * Finds the starting hexagon for the specified index in the hexagonal grid.
     *
     * @param i The index in the hexagonal grid.
     * @return The Hexagon object representing the starting hexagon.
     */
    public Hexagon findStartHexagon(int i){
        if(i < 11){
            if(i % 2 == 1){
                i++;
            }
            i /=2;
            return  getListHexagon((1-i), (5-i));
        }else if(i < 20){
            if(i % 2 == 1){
                i--;
            }
            i /=2;
            return getListHexagon(-4, (5-i));
        }else if(i < 29){
            if(i % 2 == 1){
                i++;
            }
            i -= 20;
            i /=2;
            return getListHexagon(i-4, -4);
        }else if(i < 37) {
            i-=28;
            i/=2;

            return getListHexagon(i, i-4);
        }else if(i < 47){
            i-=37;
            i/=2;
            return getListHexagon(4, i);
        }
        else if(i <= 54){
            i-=47;
            i++;
            i/=2;
            return getListHexagon(4-i, 4);
        }

        return null;
    }

    /**
     * Finds the neighbouring hexagons of the specified cell coordinates.
     *
     * @param x The x-coordinate of the cell.
     * @param y The y-coordinate of the cell.
     * @return The ArrayList containing neighbouring Hexagon objects.
     */
    public ArrayList<Hexagon> neighbouringHexagons(int x, int y){


        ArrayList<Hexagon> neighbouringHexes = new ArrayList<>();
        if(getListHexagon(x+1, y) != null){
            neighbouringHexes.add(getListHexagon(x+1, y));
        }if(getListHexagon(x, y-1) != null){
            neighbouringHexes.add(getListHexagon(x, y-1));
        }if(getListHexagon(x-1, y-1) != null){
            neighbouringHexes.add(getListHexagon(x-1, y-1));
        }if(getListHexagon(x-1, y) != null){
            neighbouringHexes.add(getListHexagon(x-1, y));
        }if(getListHexagon(x, y+1) != null) {
            neighbouringHexes.add(getListHexagon(x, y+1));
        }if(getListHexagon(x+1, y+1) != null){
            neighbouringHexes.add(getListHexagon(x+1, y+1));
        }

        return neighbouringHexes;
    }




    /**
     * Adds the circle of influence around the specified atom coordinates on the board.
     *
     * @param x The x-coordinate of the atom.
     * @param y The y-coordinate of the atom.
     */

    public void addCircleOfInfluence(int x, int y) {
//checks all surrounding hexagons if they exits and if sow and influence marker
        if(getListHexagon(x+1, y) != null){
            getListHexagon(x+1, y).placeInfluence(Direction.EAST);
        }if(getListHexagon(x, y-1) != null){
            getListHexagon(x, y-1).placeInfluence(Direction.SOUTHEAST);
        }if(getListHexagon(x-1, y-1) != null){
            getListHexagon(x-1, y-1).placeInfluence(Direction.SOUTHWEST);
        }if(getListHexagon(x-1, y) != null){
            getListHexagon(x-1, y).placeInfluence(Direction.WEST);
        }if(getListHexagon(x, y+1) != null) {
            getListHexagon(x, y+1).placeInfluence(Direction.NORTHWEST);
        }if(getListHexagon(x+1, y+1) != null){
            getListHexagon(x+1, y+1).placeInfluence(Direction.NORTHEAST);
        }



    }

    /**
     * Removes the circle of influence around the specified atom coordinates on the board.
     *
     * @param x The x-coordinate of the atom.
     * @param y The y-coordinate of the atom.
     */
    public void removeCircleOfInfluence(int x, int y) {
        ArrayList<Hexagon> neighbouringHexes = neighbouringHexagons(x, y);
        Hexagon centreHex = getListHexagon(x, y);
        for(Hexagon hexagon : neighbouringHexes){
            hexagon.removeInfluence();
        }

    }

    /**
     * Retrieves the next hexagon in the specified direction from the given hexagon.
     *
     * @param hexagon   The current hexagon.
     * @param direction The direction in which to find the next hexagon.
     * @return The Hexagon object representing the next hexagon in the specified direction.
     */
    // Method to return the next hexagon on the board in a given direction
    public Hexagon getNextHexagon(Hexagon hexagon, Direction direction) {

        if(hexagon == null) {
            return null;
        }

        switch(direction) {
            case EAST: // x: +1, y: +0
                return getListHexagon(hexagon.getX() + 1, hexagon.getY());
            case WEST: // x: -1, y: +0
                return getListHexagon(hexagon.getX() - 1, hexagon.getY());
            case NORTHEAST: // x: +1, y: +1
                return getListHexagon(hexagon.getX() + 1, hexagon.getY() + 1);
            case NORTHWEST: // x: +0, y: +1
                return getListHexagon(hexagon.getX(), hexagon.getY() + 1);
            case SOUTHEAST: // x: +0, y: -1
                return getListHexagon(hexagon.getX(), hexagon.getY() - 1);
            case SOUTHWEST: // x: -1, y: -1
                return getListHexagon(hexagon.getX() - 1, hexagon.getY() - 1);
        }

        return null;
    }






}
















