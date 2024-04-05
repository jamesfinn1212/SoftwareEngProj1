import javax.swing.*;
import java.util.IllegalFormatException;
import java.util.Scanner;

public class Game {
    boolean isOver = false;
    boolean drawGuessAtoms = false;
    private Board board;

    public Game() {
        board = new Board();
    }


    public void startGame(){


        // draws empty board
        Draw.drawBoard(board);


        GUI gui = new GUI(this);
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Blackbox GUI");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


            frame.add(gui);

            frame.setSize(1500, 750);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);



        });



            // place atoms
            while (board.numAtomsPlaced < 6) {
                gui.setAction(GUI.Action.PLACE_ATOM);
               // System.out.println(board.numAtomsPlaced);
                //  Draw.drawBoard(board1);
            }


            // hide atoms
            board.hideAtoms();
            board.isHexCoordVisible = false;
            board.isArrowsVisible =true;
            board.isHexSideNumVisible = true;
            gui.setAction(GUI.Action.PLACE_RAY);
            while (board.getRays().size() < 5) {

                 System.out.println(board.getRays().size());
            }


            while(board.numGuessAtomsPlaced < 6) {
                drawGuessAtoms = true;
                gui.setAction(GUI.Action.Guess_Atom);
              //  System.out.println(board.numGuessAtomsPlaced);
            }
            drawGuessAtoms = false;

            // show atoms
            board.showAtoms();








/*
        System.out.println("Please enter location(x, y) for 6 atoms");
        //enter the location of 6 atoms
//        for(int i = 0; i<6; i++) {
//            System.out.println("Please enter number for atom: " + (i+1));
//            Scanner input = new Scanner(System.in);
//            int j = input.nextInt();
//            int x = board1.getHexagonFromNumber(j).getX();
//            int y = board1.getHexagonFromNumber(j).getY();
//
//            //check if valid co-ordinate
//            validateAtom(x, y, board1);
//            // need to check also if same location chosen twice
//            //adds atom
//            board1.addAtom(x, y);
//            Draw.drawBoard(board1);
//        }

        // atoms are hidden
        System.out.println("Atoms and circles of influences will now be hidden");
        board1.hideAtoms();
        //draws empty board again
        Draw.drawBoard(board1);
        System.out.println("Please enter hexagon co-ordinates and direction of ray");
        // keeps on looping until player decides it is over
        while(!isOver){
            System.out.println("Please enter input number for ray");
            Scanner input = new Scanner(System.in);
            int x = input.nextInt();

            Ray ray = new Ray(board1, board1.findStartHexagon(x), board1.findDirection(x));

            board1.addRayLine();
            Draw.drawBoard(board1);
            System.out.println(ray.toString());

}
 */

    }
    public Board getBoard(){
        return this.board;
    }


    public void validateAtom(int x, int y, Board board) throws IllegalArgumentException {
        if (x > 4 || x < -4) {
            throw new IllegalArgumentException("x co-ordinate must be between -4 and 4");
        }
        // y co-ordinate input validation
        if(x > 0) {
            if (y > 4 || y < -4 + x) {
                throw new IllegalArgumentException("Invalid y co-ordinate");
            }
        }else{
            if (y > 4 + x || y < -4) {
                throw new IllegalArgumentException("Invalid y co-ordinate");
            }
        }


    }

    // needs testing

    public void validateRay(int i){
        if(i < 1 || i > 54){
            throw new IllegalArgumentException("Number must be between 1 and 54");
        }
    }



}