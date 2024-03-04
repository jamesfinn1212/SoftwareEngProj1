import java.util.IllegalFormatException;
import java.util.Scanner;

public class Game {
    boolean isOver = false;

    public void startGame(){
        Board board1 = new Board();
        // draws empty board
        System.out.println(board1);


        System.out.println("Please enter location number for 6 atoms");
        //enter the location of 6 atoms
        for(int i = 0; i<6; i++) {
            System.out.println("Please enter number for atom: " + (i+1));
            Scanner input = new Scanner(System.in);
            int hexagonNumber = input.nextInt();
            //check if valid hexagon
            validateAtom(hexagonNumber, board1);
            // need to check also if same location chosen twice
            //adds atom
            board1.addAtom(hexagonNumber);
            System.out.println(board1);
        }

        // atoms are hidden
        System.out.println("Atoms and circles of influences will now be hidden");
        board1.hideAtoms();
        //draws empty board again
        System.out.println(board1);
        System.out.println("Please enter hexagon co-ordinates and direction of ray");
        // keeps on looping until player decides it is over
        while(!isOver){
            System.out.println("Please enter input number for ray");
            Scanner input = new Scanner(System.in);
            int x = input.nextInt();

            Ray ray = new Ray(board1, board1.findStartHexagon(x), board1.findDirection(x));

            board1.addRayLine();
            System.out.println(board1);
            System.out.println(ray);





        }

    }

    public void validateAtom(int hexagonNumm, Board board) throws IllegalArgumentException {
        if (hexagonNumm < 0 || hexagonNumm > 61) {
            throw new IllegalArgumentException("hexagon number must be between 0 and 61");
        }

    }

    // needs testing

    public void validateRay(int i){
        if(i < 1 || i > 54){
            throw new IllegalArgumentException("Number must be between 1 and 54");
        }
    }



}