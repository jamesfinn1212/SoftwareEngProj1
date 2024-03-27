import java.util.ArrayList;

public class Hexagon {

    // instance variables
    private final int x;
    private final int y;
    private final int hexagonNum;
    private boolean hasAtom;

    private boolean hasNeighbourAtom;

    private int numRays;
    private boolean hasInfluence;
    private ArrayList<Board.Direction> directionsOfInfluence;


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
        this.hasNeighbourAtom = false;
        this.hasInfluence = false;
        this.numRays = 0;
        this.directionsOfInfluence = new ArrayList<>();
        this.hexagonNum = getHexagonNumFromCord(x, y);
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


    public int getNumRays(){
        return numRays;
    }

    public int getHexagonNum() { return hexagonNum; }

    public void setNumRays(int i){
        this.numRays = i;
    }

    public boolean hasInfluence(){
        return hasInfluence;
    }

    public void placeInfluence(Board.Direction direction){
        //System.out.println("placimng");
        hasInfluence = true;
        directionsOfInfluence.add(direction);
    }
    public void removeInfluence() {
        hasInfluence = false;

    }

    public boolean isSide() {

        //sides are: y = -4, x = -4, y = 4, x = 4
        // other sides: (3, -1), (2, -2), (1, -3)
        //            : (-1, 3), (-2, 2), (-3, 1)

        if((x == 3 && y == -1) || (x == 2 && y == -2) || (x == 1 && y == -3) || (x == -1 && y == 3) || (x == -2 && y == 2) || (x == -3 && y == 1)) {
            return true;
        }
        return (x == 4 || x == -4 || y == 4 || y == -4);
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


    public ArrayList<Board.Direction> getDirectionsOfInfluence() {
        return directionsOfInfluence;
    }

    public void setDirectionsOfInfluencedInfluence(ArrayList<Board.Direction> directionsInfluecedFrom) {
        this.directionsOfInfluence = directionsInfluecedFrom;
    }

    public boolean isHasNeighbourAtom() {
        return hasNeighbourAtom;
    }

    public void setHasNeighbourAtom() {
        this.hasNeighbourAtom = true;
    }

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