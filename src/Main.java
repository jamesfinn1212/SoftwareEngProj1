// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Board board = new Board();

        // EXAMPLE CASE

        // before atom placed on board, ray should go all the way through from -2,2 in the east direction
        Ray ray1 = new Ray(board, board.getListHexagon(-2, 2), Board.Direction.EAST);

        // place atom direction in front of ray
        board.addAtom(2, 2);

        // this ray should get absorbed by the atom at (2,2), therefore should stop right behind
        Ray ray2 = new Ray(board, board.getListHexagon(-2, 2), Board.Direction.EAST);

        // This ray is coming at (2,2) from different direction, should still get absorbed
        Ray ray3 = new Ray(board, board.getListHexagon(-4, -4), Board.Direction.NORTHEAST);

        // print both rays for comparison (printing the rays will print out the path they took)
        System.out.println("Ray 1(not absorbed as it was placed before atom) : " + ray1);
        System.out.println("Ray 2(absorbed at (2,2) coming from EAST): " + ray2);
        System.out.println("Ray 3(absorbed at (2,2) coming from NORTHEAST): " + ray3);


    }
}