import java.util.ArrayList;

public class Board {
    private static final int xOrigin = 59;
    private static final int yOrigin = 28;

    private final ArrayList<Hexagon> listBoard = new ArrayList<>();
    private ArrayList<Ray> rays;

    //make private and getters and setter
    public int numAtomsPlaced = 0;
    public int numRaysPlaced = 0;

    public int numGuessAtomsPlaced = 0;

    private final String[] stringBoard;

    private final int stringBoardLength;
    public Board() {
        this.stringBoard = new String[]{
                "                                  * *  54 53  * *  52 51  * *  50 49  * *  48 47  * *",
                "                            1   *     *     *     *     *     *     *     *     *     *   46",
                "                              *         * *         * *         * *         * *         *",
                "                             *           *           *           *           *           *",
                "                         2   *           *           *           *           *           *   45",
                "                             *           *           *           *           *           *",
                "                            * *    1    * *    2    * *    3    * *    4    * *    5    * *",
                "                      3   *     *     *     *     *     *     *     *     *     *     *     *   44",
                "                        *         * *         * *         * *         * *         * *         *",
                "                       *           *           *           *           *           *           *",
                "                   4   *           *           *           *           *           *           *   43",
                "                       *           *           *           *           *           *           *",
                "                      * *    6    * *    7    * *    8    * *    9    * *    10   * *    11   * *",
                "                5   *     *     *     *     *     *     *     *     *     *     *     *     *     *   42",
                "                  *         * *         * *         * *         * *         * *         * *         *",
                "                 *           *           *           *           *           *           *           *",
                "             6   *           *           *           *           *           *           *           *   41",
                "                 *           *           *           *           *           *           *           *",
                "                * *    12   * *    13   * *    14   * *    15   * *    16   * *    17   * *    18   * *",
                "          7   *     *     *     *     *     *     *     *     *     *     *     *     *     *     *     *   40",
                "            *         * *         * *         * *         * *         * *         * *         * *         *",
                "           *           *           *           *           *           *           *           *           *",
                "       8   *           *           *           *           *           *           *           *           *   39",
                "           *           *           *           *           *           *           *           *           *",
                "          * *   19    * *    20   * *    21   * *    22   * *    23   * *    24   * *    25   * *    26   * *",
                "    9   *     *     *     *     *     *     *     *     *     *     *     *     *     *     *     *     *     *   38",
                "      *         * *         * *         * *         * *         * *         * *         * *         * *         *",
                "     *           *           *           *           *           *           *           *           *           *",
                "10   *           *           *           *           *           *           *           *           *           *   37",
                "     *           *           *           *           *           *           *           *           *           *",
                "      *    27   * *    28   * *    29   * *    30   * *    31   * *    32   * *    33   * *    34   * *    35   *",
                "   11   *     *     *     *     *     *     *     *     *     *     *     *     *     *     *     *     *     *   36",
                "          * *         * *         * *         * *         * *         * *         * *         * *         * *",
                "           *           *           *           *           *           *           *           *           *",
                "      12   *           *           *           *           *           *           *           *           *   35",
                "           *           *           *           *           *           *           *           *           *",
                "            *   36    * *    37   * *    38   * *    39   * *    40   * *    41   * *    42   * *    43   *",
                "         13   *     *     *     *     *     *     *     *     *     *     *     *     *     *     *     *   34",
                "                * *         * *         * *         * *         * *         * *         * *         * *",
                "                 *           *           *           *           *           *           *           *",
                "            14   *           *           *           *           *           *           *           *   33",
                "                 *           *           *           *           *           *           *           *",
                "                  *   44    * *    45   * *    46   * *    47   * *    48   * *    49   * *    50   *",
                "               15   *     *     *     *     *     *     *     *     *     *     *     *     *     *   32",
                "                      * *         * *         * *         * *         * *         * *         * *",
                "                       *           *           *           *           *           *           *",
                "                  16   *           *           *           *           *           *           *   31",
                "                       *           *           *           *           *           *           *",
                "                        *    51   * *    52   * *    53   * *    54   * *    55   * *    56   *",
                "                     17   *     *     *     *     *     *     *     *     *     *     *     *   30",
                "                            * *         * *         * *         * *         * *         * *",
                "                             *           *           *           *           *           *",
                "                        18   *           *           *           *           *           *   29",
                "                             *           *           *           *           *           *",
                "                              *    57   * *    58   * *    59   * *    60   * *    61   *",
                "                           19   *     *     *     *     *     *     *     *     *     *   28",
                "                                  * *  20 21  * *  22 23  * *  24 25  * *  26 27  * *",


        };
        this.stringBoardLength = stringBoard.length;
        this.rays = new ArrayList<>();

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


    //returns string representation of the board
    public String[] getStringBoard(){
        return this.stringBoard;
    }
    //returns entire list on hexagons on board
    public ArrayList<Hexagon> getListBoard() {
        return listBoard;
    }
//returns all the rays
    public ArrayList<Ray> getRays() {
        return rays;
    }




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

    // function to get hexagon from number
//    public Hexagon getHexagonFromNumber(int hexagonNum) {
//
//        // iterate over board
//        for(Hexagon hexagon : listBoard) {
//
//            // if the hexagon is of that number
//            if(hexagon.getHexagonNum() == hexagonNum) {
//                return hexagon;
//            }
//
//        }
//
//        return null;
//    }

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
    public void addChar(int x, int y, char c) {

        // Polar co-ordinates conversion
        // Each center of hexagon is 12 horizontal spaces away from each other.
        // Changing y will need adjustments for the x co-ordinate also due to hexagons travelling diagonally.
        int xPolar = (12*x + xOrigin + (-6*y));
        int yPolar = yOrigin + (-6*y);


        // Using StringBuilder, set character at (x,y) to c
        StringBuilder newRow = new StringBuilder(stringBoard[yPolar]);
        newRow.setCharAt(xPolar, c);

        // Add new row into the board, replacing the old one
        stringBoard[yPolar] = newRow.toString();


    }

    //adds atoms to baord
    public void addAtom(int x, int y){
        addChar(x, y, '*');
        getListHexagon(x, y).setContainsAtom(true);
        addCircleOfInfluence(x, y);
        edgeCaseNeighbouringHasAtom(x, y);
        numAtomsPlaced++;

    }
    //removes an atoms
    public void removeAtom(int x, int y) {
        addChar(x, y, ' ');
        getListHexagon(x, y).setContainsAtom(false);
        removeCircleOfInfluence(x, y);
        numAtomsPlaced--;
    }

    //adds ray to board
    public void addRay(Ray ray) {
        rays.add(ray);
    }



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
    //
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

    //for the edge case when way is entred and hexagon beside it on the edge contains an atom
    public void edgeCaseNeighbouringHasAtom(int x, int y ){
        ArrayList<Hexagon> neighbouringHexes = neighbouringHexagons(x, y);
        if(getListHexagon(x, y).isSide()){
            for(Hexagon hexagon : neighbouringHexes){
                if(hexagon.isSide()){
                    hexagon.setHasNeighbourAtom(true);
                }
            }
        }

    }




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

        for(Hexagon hexagon: listBoard) {

            // if the hexagon has an atom on it, draw the atom
            if(hexagon.hasInfluence() && !hexagon.hasAtom()) {
                addChar(hexagon.getX(), hexagon.getY(), '#');
            }

        }

    }

    public void removeCircleOfInfluence(int x, int y) {
        if(getListHexagon(x+1, y) != null){
            getListHexagon(x+1, y).removeInfluence();
            getListHexagon(x+1, y).removeHasNeighbourAtom();
        }if(getListHexagon(x, y-1) != null){
            getListHexagon(x, y-1).removeInfluence();
            getListHexagon(x, y-1).removeHasNeighbourAtom();
        }if(getListHexagon(x-1, y-1) != null){
            getListHexagon(x-1, y-1).removeInfluence();
            getListHexagon(x-1, y-1).removeHasNeighbourAtom();
        }if(getListHexagon(x-1, y) != null){
            getListHexagon(x-1, y).removeInfluence();
            getListHexagon(x-1, y).removeHasNeighbourAtom();
        }if(getListHexagon(x, y+1) != null) {
            getListHexagon(x, y+1).removeInfluence();
            getListHexagon(x, y+1).removeHasNeighbourAtom();
        }if(getListHexagon(x+1, y+1) != null){
            getListHexagon(x+1, y+1).removeInfluence();
            getListHexagon(x+1, y+1).removeHasNeighbourAtom();
        }


    }

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