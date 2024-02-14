public class Draw {

    // x and y co-ordinates for the center of the board map. Represent (0,0).
    // To be used as offsets when converting to Hexagonal Polar Co-ordinates
    private static final int xOrigin = 54;
    private static final int yOrigin = 28;

    // String array storing the board



    // Method to draw out the current board
    public static void drawBoard(Board b){
        for(String line : b.getStringBoard()) {
            System.out.println(line);
        }
    }











}
