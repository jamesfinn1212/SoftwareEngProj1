import java.util.ArrayList;

// Board is represented as an ArrayList of Hexagons.
// Can find specific hexagons on the board with "Get Hexagon" method
// Can place atoms on the board
public class Board {

    // instance variables

    // represent board as an arraylist of hexagons
    private final ArrayList<Hexagon> board = new ArrayList<>();


    // constructor (fills out board with hexagons)
    public Board() {


        int key = 0; // this key ensures we don't fill in hexagons that do not exist

        // fill out board
        for(int x = -4; x <= 4; x++) {

            // for x is negative
            if(x < 0) {

                // add hexagon onto the board
                for(int y = -4; y <= key; y++) {
                    board.add(new Hexagon(x, y));
                }
                key++;
            }

            // for x is positive key ends at 4.
            if(x >= 0) {

                // add hexagon onto the board
                for(int y = 4; y >= (-key); y--) { // -key as positive x co-ordinate flips the y (i.e. every positive x has y 4 -> 0, same as every negative x having y -4 -> 0)
                    board.add(new Hexagon(x, y));
                }
                key--;
            }

        }

    }

    // getter method
    public ArrayList<Hexagon> getBoard() {
        return board;
    }

    // method to get specific hexagon from arrayList
    public Hexagon getHexagon(int x, int y) {

        // iterate over board
        for(Hexagon hexagon : board) {

            // if the hexagon has the x and y we are looking for, return it
            if(hexagon.getX() == x && hexagon.getY() == y) {
                return hexagon;
            }
        }

        return board.get(0); // if hexagon not found, return the first hexagon on the board (this is to satisfy the IDE, there is definitely a better way of doing this lol)
    }

    // method to add Atom into a specific hexagon on the board
    public void placeAtom(int x, int y) {
        getHexagon(x, y).placeAtom(); // finds hexagon at x and y and uses "placeAtom()" from Hexagon class
    }

    // method to print the current board (uses Draw class).
    // This should probably be changed to a toString() method in the future
    // Might need to make the Draw class return a string rather than performing the actual printing in that case
    public void printBoard() {

        // when we print the board, we want to call the Draw class and draw the board based on hexagons in the arraylist

        // iterate over board
        for(Hexagon hexagon: board) {

            // if the hexagon has an atom on it, draw the atom
            if(hexagon.hasAtom()) {
                Draw.addCharIntoHexagon(hexagon.getX(), hexagon.getY(), 'A');
            }

        }

        Draw.drawBoard();

    }

}
