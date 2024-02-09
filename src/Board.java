public class Board {
    private static final int xOrigin = 54;
    private static final int yOrigin = 28;
    private String[] board;
    private int length;
    public Board() {
        this.board = new String[]{
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
        this.length = board.length;;
    }

    public int getLength(){
        return this.length;
    }

    public String[] getBoard(){
        return this.board;
    }

    // Method to add a character into the center of hexagon using the (x, y) co-ordinates.

    public void addAtom(int x, int y) {
        // Polar co-ordinates conversion
        // Each center of hexagon is 12 horizontal spaces away from each other.
        // Changing y will need adjustments for the x co-ordinate also due to hexagons travelling diagonally.
        int xPolar = (12*x + xOrigin + (-6*y));
        int yPolar = yOrigin + (-6*y);


        // Using StringBuilder, set character at (x,y) to c
        StringBuilder newRow = new StringBuilder(board[yPolar]);
        newRow.setCharAt(xPolar, '*');

        // Add new row into the board, replacing the old one
        board[yPolar] = newRow.toString();
    }
}
