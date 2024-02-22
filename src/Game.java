import java.util.IllegalFormatException;
import java.util.Scanner;

public class Game {
    boolean isOver = false;

    public void startGame(){
        Board board1 = new Board();
        // draws empty board
        Draw.drawBoard(board1);
        //entering atoms

        System.out.println("Please enter location(x, y) for 6 atoms");
        for(int i = 0; i<6; i++) {
            System.out.println("Please enter x input for atom: " + (i+1));
            Scanner input = new Scanner(System.in);
            int x = input.nextInt();
            System.out.println("Please enter y input for atom: " + (i+1));
            input = new Scanner(System.in);
            int y = input.nextInt();
            //check if valid
            validateAtom(x, y, board1);
            // need to check also if same location chosen twice
            //adds atom
            board1.addAtom(x, y);
            Draw.drawBoard(board1);
        }

        System.out.println("Atoms and circles of influences will now be hidden");
        board1.hideAtoms();
        Draw.drawBoard(board1);
        System.out.println("Please enter hexagon co-ordinates and direction of ray");
        while(!isOver){
            System.out.println("Please enter input number for ray");
            Scanner input = new Scanner(System.in);
            int x = input.nextInt();
            Board.Direction d = board1.findDirection(x);




        }

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

        if(board.getListHexagon(x, y).hasInfluence()) {
            throw new IllegalArgumentException("Invalid y co-ordinate");
        }

    }

    // needs testing

    public void validateRay(int i){
        if(i < 1 || i > 54){
            throw new IllegalArgumentException("Number must be between 1 and 54");
        }
    }



}