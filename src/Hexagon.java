public class Hexagon {

    // instance variables
    private final int x;
    private final int y;
    private boolean hasAtom;


    // constructor
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
        this.hasAtom = false;
    }

    // getter methods
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public boolean hasAtom() {
        return hasAtom;
    }

    // setter methods
    // x and y should be immutable. i.e. we should never be able to change a hexagon's position
    public void placeAtom() {
        hasAtom = true;
    }

    // remove atom
    public void removeAtom() {
        hasAtom = false;
    }

    // toString method override returns co-ordinate of Hexagon
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }



}
