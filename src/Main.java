import javax.swing.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        Board board = new Board();


        Game game = new Game();
       game.startGame();

//         Draws GUI
//        SwingUtilities.invokeLater(() -> {
//            JFrame frame = new JFrame("Blackbox GUI");
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//            GUI gui = new GUI(board);
//            frame.add(gui);
//
//            frame.setSize(1500, 750);
//            frame.setLocationRelativeTo(null);
//            frame.setVisible(true);
//        });


    }
}