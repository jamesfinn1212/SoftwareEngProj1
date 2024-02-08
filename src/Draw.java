public class Draw {

    // x and y co-ordinates for the center of the board map. Represent (0,0).
    // To be used as offsets when converting to Hexagonal Polar Co-ordinates
    private static final int xOrigin = 54;
    private static final int yOrigin = 28;

    // String array storing the board
    private static String[] board = {
            "                             / \\         / \\         / \\         / \\         / \\",
            "                           /     \\     /     \\     /     \\     /     \\     /     \\",
            "                         /         \\ /         \\ /         \\ /         \\ /         \\",
            "                        |           |           |           |           |           |",
            "                        |           |           |           |           |           |",
            "                        |           |           |           |           |           |",
            "                       / \\         / \\         / \\         / \\         / \\         / \\",
            "                     /     \\     /     \\     /     \\     /     \\     /     \\     /     \\",
            "                   /         \\ /         \\ /         \\ /         \\ /         \\ /         \\",
            "                  |           |           |           |           |           |           |",
            "                  |           |           |           |           |           |           |",
            "                  |           |           |           |           |           |           |",
            "                 / \\         / \\         / \\         / \\         / \\         / \\         / \\",
            "               /     \\     /     \\     /     \\     /     \\     /     \\     /     \\     /     \\",
            "             /         \\ /         \\ /         \\ /         \\ /         \\ /         \\ /         \\",
            "            |           |           |           |           |           |           |           |",
            "            |           |           |           |           |           |           |           |",
            "            |           |           |           |           |           |           |           |",
            "           / \\         / \\         / \\         / \\         / \\         / \\         / \\         / \\",
            "         /     \\     /     \\     /     \\     /     \\     /     \\     /     \\     /     \\     /     \\",
            "       /         \\ /         \\ /         \\ /         \\ /         \\ /         \\ /         \\ /         \\",
            "      |           |           |           |           |           |           |           |           |",
            "      |           |           |           |           |           |           |           |           |",
            "      |           |           |           |           |           |           |           |           |",
            "     / \\         / \\         / \\         / \\         / \\         / \\         / \\         / \\         / \\",
            "   /     \\     /     \\     /     \\     /     \\     /     \\     /     \\     /     \\     /     \\     /     \\",
            " /         \\ /         \\ /         \\ /         \\ /         \\ /         \\ /         \\ /         \\ /         \\",
            "|           |           |           |           |           |           |           |           |           |",
            "|           |           |           |           |           |           |           |           |           |",
            "|           |           |           |           |           |           |           |           |           |",
            " \\         / \\         / \\         / \\         / \\         / \\         / \\         / \\         / \\         /",
            "   \\     /     \\     /     \\     /     \\     /     \\     /     \\     /     \\     /     \\     /     \\     /",
            "     \\ /         \\ /         \\ /         \\ /         \\ /         \\ /         \\ /         \\ /         \\ /",
            "      |           |           |           |           |           |           |           |           |",
            "      |           |           |           |           |           |           |           |           |",
            "      |           |           |           |           |           |           |           |           |",
            "       \\         / \\         / \\         / \\         / \\         / \\         / \\         / \\         /",
            "         \\     /     \\     /     \\     /     \\     /     \\     /     \\     /     \\     /     \\     /",
            "           \\ /         \\ /         \\ /         \\ /         \\ /         \\ /         \\ /         \\ /",
            "            |           |           |           |           |           |           |           |",
            "            |           |           |           |           |           |           |           |",
            "            |           |           |           |           |           |           |           |",
            "             \\         / \\         / \\         / \\         / \\         / \\         / \\         /",
            "               \\     /     \\     /     \\     /     \\     /     \\     /     \\     /     \\     /",
            "                 \\ /         \\ /         \\ /         \\ /         \\ /         \\ /         \\ /",
            "                  |           |           |           |           |           |           |",
            "                  |           |           |           |           |           |           |",
            "                  |           |           |           |           |           |           |",
            "                   \\         / \\         / \\         / \\         / \\         / \\         /",
            "                     \\     /     \\     /     \\     /     \\     /     \\     /     \\     /",
            "                       \\ /         \\ /         \\ /         \\ /         \\ /         \\ /",
            "                        |           |           |           |           |           |",
            "                        |           |           |           |           |           |",
            "                        |           |           |           |           |           |",
            "                         \\         / \\         / \\         / \\         / \\         /",
            "                           \\     /     \\     /     \\     /     \\     /     \\     /",
            "                             \\ /         \\ /         \\ /         \\ /         \\ /",


    };


    // Method to draw out the current board
    public static void drawBoard(){
        for(int i = 0; i < board.length; i++) {
            System.out.println(board[i]);
        }
    }

    // Method to add a character into the center of hexagon using the (x, y) co-ordinates.
    public static void addCharIntoHexagon(int x, int y, char c) {
        // x co-ordinate input validation
        if (x > 4 || x < -4) {
            throw new IllegalArgumentException("x co-ordinate must be between -4 and 4");
        }
        // y co-ordinate input validation
        if (y > 4 || y < -4) {
            throw new IllegalArgumentException("y co-ordinate must be between -4 and 4");
        }


        // Polar co-ordinates conversion
        // Each center of hexagon is 12 horizontal spaces away from each other.
        // Changing y will need adjustments for the x co-ordinate also due to hexagons travelling diagonally.
        int xPolar = (12*x + xOrigin + (-6*y));
        int yPolar = yOrigin + (-6*y);


        // Using StringBuilder, set character at (x,y) to c
        StringBuilder newRow = new StringBuilder(board[yPolar]);
        newRow.setCharAt(xPolar, c);

        // Add new row into the board, replacing the old one
        board[yPolar] = newRow.toString();

    }


}
