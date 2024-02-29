// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        /*
        Board board = new Board();

        Game game = new Game();
        game.startGame();
        */

        Board board = new Board();

        board.addAtom(0, 0);

        // each of these rays hit the influence and turn approprialy
        Ray ray1 = new Ray(board, board.getListHexagon(-4, -3), Board.Direction.NORTHEAST);
        Ray ray2 = new Ray(board, board.getListHexagon(3, 4), Board.Direction.SOUTHWEST);
        Ray ray3 = new Ray(board, board.getListHexagon(-3, 1), Board.Direction.EAST);

        System.out.println(ray1);
        System.out.println(ray2);
        System.out.println(ray3);
    }
}