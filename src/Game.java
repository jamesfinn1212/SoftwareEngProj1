import java.util.Scanner;

public class Game {

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
            validateAtom(x, y);
            // need to check also if same location chosen twice
            //adds atom
            board1.addAtom(x, y);
            Draw.drawBoard(board1);
        }

        System.out.println("Atoms and circles of influences will now be hidden");
        board1.hideAtoms();
        Draw.drawBoard(board1);

    }

    public void validateAtom(int x, int y){
        if (x > 4 || x < -4) {
            throw new IllegalArgumentException("x co-ordinate must be between -4 and 4");
        }
        // y co-ordinate input validation
        if (y > 4 || y < -4) {
            throw new IllegalArgumentException("y co-ordinate must be between -4 and 4");
        }
    }


}