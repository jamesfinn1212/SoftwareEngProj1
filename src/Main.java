// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        // Some examples
        Board board = new Board(); // declares new board

        // places some atoms on board
        board.placeAtom(0, 0);
        board.placeAtom(-3, -1);
        board.placeAtom(2, 2);
        board.placeAtom(0, -2);

        board.printBoard(); // prints board

        // prints the array representing the board
        // (hexagon's toString method returns the co-ordinates so this just shows all the co-ordinates of all the atoms in one line)
        System.out.println(board.getBoard());
    }
}