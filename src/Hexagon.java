public class Hexagon {

    // instance variables
    private final int x;
    private final int y;
    private boolean hasAtom;

    private int numRays;
    private boolean hasInfluence;
    private Board.Direction influencedFrom;


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
        this.hasInfluence = false;
        this.numRays = 0;
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

    public void setNumRays(int i){
        this.numRays = i;
    }

    public boolean hasInfluence(){
        return hasInfluence;
    }

    public void placeInfluence(Board.Direction direction){
        //System.out.println("placimng");
        hasInfluence = true;
        influencedFrom = direction;
    }
    public void removeInfluence() {
        hasInfluence = false;
        influencedFrom = null;
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

    public Board.Direction influencedFrom() {
        return influencedFrom;
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
