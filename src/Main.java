import javax.swing.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        Board board = new Board();
        board.addAtom(-2, 1);
        board.addAtom(1, 4);
        board.addAtom(-2, -2);
        board.addAtom(-1, -2);

        // This ray hits multiple atoms, starts at (4, 3) and comes out at (4,4)
        Ray ray1 = new Ray(board, board.getListHexagon(4, 3), Board.Direction.WEST);

        // This ray just hits the one atom, starts at (0, -4) and comes out at (4, 2)
        Ray ray2 = new Ray(board, board.getListHexagon(0, -4), Board.Direction.NORTHWEST);

        Game game = new Game();
       // game.startGame();

        // Draws GUI
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Blackbox GUI");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            GUI gui = new GUI(board);
            frame.add(gui);

            frame.setSize(1500, 750);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });



    }
}