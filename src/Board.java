import java.util.ArrayList;

public class Board {
    private static final int xOrigin = 59;
    private static final int yOrigin = 28;

    private final ArrayList<Hexagon> listBoard = new ArrayList<>();

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
                "                            * *         * *         * *         * *         * *         * *",
                "                      3   *     *     *     *     *     *     *     *     *     *     *     *   44",
                "                        *         * *         * *         * *         * *         * *         *",
                "                       *           *           *           *           *           *           *",
                "                   4   *           *           *           *           *           *           *   43",
                "                       *           *           *           *           *           *           *",
                "                      * *         * *         * *         * *         * *         * *         * *",
                "                5   *     *     *     *     *     *     *     *     *     *     *     *     *     *   42",
                "                  *         * *         * *         * *         * *         * *         * *         *",
                "                 *           *           *           *           *           *           *           *",
                "             6   *           *           *           *           *           *           *           *   41",
                "                 *           *           *           *           *           *           *           *",
                "                * *         * *         * *         * *         * *         * *         * *         * *",
                "          7   *     *     *     *     *     *     *     *     *     *     *     *     *     *     *     *   40",
                "            *         * *         * *         * *         * *         * *         * *         * *         *",
                "           *           *           *           *           *           *           *           *           *",
                "       8   *           *           *           *           *           *           *           *           *   39",
                "           *           *           *           *           *           *           *           *           *",
                "          * *         * *         * *         * *         * *         * *         * *         * *         * *",
                "    9   *     *     *     *     *     *     *     *     *     *     *     *     *     *     *     *     *     *   38",
                "      *         * *         * *         * *         * *         * *         * *         * *         * *         *",
                "     *           *           *           *           *           *           *           *           *           *",
                "10   *           *           *           *           *           *           *           *           *           *   37",
                "     *           *           *           *           *           *           *           *           *           *",
                "      *         * *         * *         * *         * *         * *         * *         * *         * *         *",
                "   11   *     *     *     *     *     *     *     *     *     *     *     *     *     *     *     *     *     *   36",
                "          * *         * *         * *         * *         * *         * *         * *         * *         * *",
                "           *           *           *           *           *           *           *           *           *",
                "      12   *           *           *           *           *           *           *           *           *   35",
                "           *           *           *           *           *           *           *           *           *",
                "            *         * *         * *         * *         * *         * *         * *         * *         *",
                "         13   *     *     *     *     *     *     *     *     *     *     *     *     *     *     *     *   34",
                "                * *         * *         * *         * *         * *         * *         * *         * *",
                "                 *           *           *           *           *           *           *           *",
                "            14   *           *           *           *           *           *           *           *   33",
                "                 *           *           *           *           *           *           *           *",
                "                  *         * *         * *         * *         * *         * *         * *         *",
                "               15   *     *     *     *     *     *     *     *     *     *     *     *     *     *   32",
                "                      * *         * *         * *         * *         * *         * *         * *",
                "                       *           *           *           *           *           *           *",
                "                  16   *           *           *           *           *           *           *   31",
                "                       *           *           *           *           *           *           *",
                "                        *         * *         * *         * *         * *         * *         *",
                "                     17   *     *     *     *     *     *     *     *     *     *     *     *   30",
                "                            * *         * *         * *         * *         * *         * *",
                "                             *           *           *           *           *           *",
                "                        18   *           *           *           *           *           *   29",
                "                             *           *           *           *           *           *",
                "                              *         * *         * *         * *         * *         *",
                "                           19   *     *     *     *     *     *     *     *     *     *   28",
                "                                  * *  20 21  * *  22 23  * *  24 25  * *  26 27  * *",


        };
        this.stringBoardLength = stringBoard.length;

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
        EAST,
        WEST,
        NORTHEAST,
        NORTHWEST,
        SOUTHEAST,
        SOUTHWEST;

        // method to determine whether 2 directions are "opposite" of each other
        public boolean isOpposite(Direction otherDirection) {
            switch (this) {
                case EAST:
                    return otherDirection == WEST;
                case WEST:
                    return otherDirection == EAST;
                case NORTHEAST:
                    return otherDirection == SOUTHWEST;
                case NORTHWEST:
                    return otherDirection == SOUTHEAST;
                case SOUTHWEST:
                    return otherDirection == NORTHEAST;
                case SOUTHEAST:
                    return otherDirection == NORTHWEST;
            }

            return false;
        }
    }

    public int getStringBoardLength(){
        return this.stringBoardLength;
    }

    public String[] getStringBoard(){
        return this.stringBoard;
    }
    public ArrayList<Hexagon> getListBoard() {
        return listBoard;
    }

    public Hexagon getListHexagon(int x, int y) {

        // iterate over board
        for(Hexagon hexagon : listBoard) {

            // if the hexagon has the x and y we are looking for, return it
            if(hexagon.getX() == x && hexagon.getY() == y) {
                return hexagon;
            }
        }

        return null; // if hexagon not found, return the first hexagon on the board (this is to satisfy the IDE, there is definitely a better way of doing this lol)
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

    public void addAtom(int x, int y){
        addChar(x, y, '*');
        getListHexagon(x, y).placeAtom();
        //System.out.println("add atom");
        addCircleOfInfluence(x, y);

    }

    public void addRayLine(){
        for(Hexagon hexagon : listBoard) {
            if(hexagon.getNumRays() > 0) {
                addChar(hexagon.getX(), hexagon.getY(), '+');
            }
        }
    }

    public void hideAtoms(){
        for(Hexagon hexagon : listBoard) {
            addChar(hexagon.getX(), hexagon.getY(), ' ');
        }
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



    public void addCircleOfInfluence(int x, int y) {
        //System.out.println("im here");
//checks all surrounding hexagons if they exits and if sow and influence marker
        if(getListHexagon(x+1, y) != null){
            //System.out.println("im here 20");
            getListHexagon(x+1, y).placeInfluence(Direction.EAST);
        }if(getListHexagon(x, y-1) != null){
            getListHexagon(x, y-1).placeInfluence(Direction.SOUTHEAST);
        }if(getListHexagon(x-1, y-1) != null){
            getListHexagon(x-1, y-1).placeInfluence(Direction.SOUTHWEST);
        }if(getListHexagon(x-1, y) != null){
            getListHexagon(x-1, y).placeInfluence(Direction.WEST);
        }if(getListHexagon(x, y+1) != null) {
            getListHexagon(x, y + 1).placeInfluence(Direction.NORTHWEST);
        }if(getListHexagon(x+1, y+1) != null){
            getListHexagon(x+1, y+1).placeInfluence(Direction.NORTHEAST);
        }

        for(Hexagon hexagon: listBoard) {

            // if the hexagon has an atom on it, draw the atom
            if(hexagon.hasInfluence() && !hexagon.hasAtom()) {
                //System.out.println(hexagon);
                addChar(hexagon.getX(), hexagon.getY(), '#');
            }

        }

    }

    // Method to return the next hexagon on the board in a given direction
    public Hexagon getNextHexagon(Hexagon hexagon, Direction direction) {

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