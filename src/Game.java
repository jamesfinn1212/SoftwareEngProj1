import java.util.IllegalFormatException;
import java.util.Scanner;

public class Game {
    boolean isOver = false;

    public void startGame(){
        Board board1 = new Board();
        // draws empty board
        Draw.drawBoard(board1);


        System.out.println("Please enter location for 6 atoms");
        //enter the location of 6 atoms
        for(int i = 0; i<6; i++) {
            int j;
            int y;
            int x;
            Scanner input = new Scanner(System.in);


            //check if valid co-ordinate
            while(true){
                try{
                    System.out.println("Please enter number for atom: " + (i+1));
                    j = input.nextInt();
                    validateHexagonInput(j);
                    x = board1.getHexagonFromNumber(j).getX();
                    y = board1.getHexagonFromNumber(j).getY();
                    validateAtom(x, y, board1);
                    break;
                }catch (IllegalArgumentException ex){
                    System.out.println(ex.getMessage());
                }

            }



            // need to check also if same location chosen twice
            //adds atom
            board1.addAtom(x, y);
            Draw.drawBoard(board1);
        }

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

            while(true){
                try {
                    validateRay(x);
                    break;
                }catch (IllegalArgumentException ex){
                    System.out.println(ex.getMessage());
                }
            }
            Ray ray = new Ray(board1, board1.findStartHexagon(x), board1.findDirection(x));

            board1.addRayLine();
            Draw.drawBoard(board1);
            System.out.println(ray.toString());





        }

    }

    public void validateHexagonInput(int i){
        if(i < 1 || i > 61){
            throw new IllegalArgumentException("Invalid atom input");
        }
    }

    public void validateAtom(int x, int y, Board board) throws IllegalArgumentException {
        if (x > 4 || x < -4) {
            throw new IllegalArgumentException("Invalid atom input");
        }
        // y co-ordinate input validation
        if(x > 0) {
            if (y > 4 || y < -4 + x) {
                throw new IllegalArgumentException("Invalid atom input");
            }
        }else{
            if (y > 4 + x || y < -4) {
                throw new IllegalArgumentException("Invalid atom input");
            }
        }
        if(board.getListHexagon(x, y).hasAtom()){
            throw new IllegalArgumentException("Hexagon already contains atom");
        }

    }



    public void validateRay(int i) throws  IllegalArgumentException{
        if(i < 1 || i > 54){
            throw new IllegalArgumentException("Number must be between 1 and 54");
        }
    }



}