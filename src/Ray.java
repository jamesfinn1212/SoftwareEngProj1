import java.util.ArrayList;
import java.util.LinkedList;

// This class represents the rays on the board.
public class Ray {

    // instance variables
    private final LinkedList<Hexagon> path = new LinkedList<>();

    // constructor
    public Ray(Board board, Hexagon startHexagon, Board.Direction direction) {

        // add first hexagon into linked list
        path.add(startHexagon);
        //add 1 to num of arrays
        startHexagon.setNumRays(startHexagon.getNumRays()+1);

        // check if hexagon is on the side
        if(!startHexagon.isSide()) {
            throw new IllegalArgumentException("Must choose a hexagon on the side of the board for the ray to travel from");
        }

        createRay(board, startHexagon, direction);




    }

    public void createRay(Board board, Hexagon startHexagon, Board.Direction direction){

        // add next hexagon into linked list based on direction
        // while next hexagon is not null
        while(board.getNextHexagon(path.getLast(), direction) != null) {
            //if the start hexagon has an atom
            if(startHexagon.hasAtom()){
                //never gets to first hexagon as it contains atom
                path.remove(startHexagon);
                startHexagon.setNumRays(startHexagon.getNumRays()-1);
                System.out.println("Absorbed");
                break;
            }//if the start atom touches a hexagon that has an atom
            else if(startHexagon.isHasNeighbourAtom()){
                System.out.println("Reflected");
                break;
            }//if the hexagon has an atom
            // if the next hexagon does not have influence, add it to the path
            else if (!board.getNextHexagon(path.getLast(), direction).hasInfluence()) {
                path.add(board.getNextHexagon(path.getLast(), direction));
                // add 1 to num of arrays
                path.getLast().setNumRays(path.getLast().getNumRays()+1);
            }
            else { // if the next hexagon has influence, we want to add that hexagon into the path but our direction will have to change (or get absorbed)

                // add hexagon into path
                path.add(board.getNextHexagon(path.getLast(), direction));
                path.getLast().setNumRays(path.getLast().getNumRays()+1);
                // determine new direction
                Board.Direction newDirection = calculateNewDirection(path.getLast(), direction);

                //if direction and new direction are the same it is a direct collision
                if(newDirection == direction){
                    System.out.println("Absorbed");
                    break;
                }

                direction = newDirection; // set direction to new direction so the while loop contines
            }
        }
    }


    // method that determines turn direction of the ray and returns it
    private Board.Direction calculateNewDirection(Hexagon currentHexagon, Board.Direction directionOfRay) {
        //randomly set by teh end shouldn't have any effect of outcome
        Board.Direction newDirection = directionOfRay;
        //code used for debugging
//        System.out.println("Direction of ray " + directionOfRay);
//        System.out.println(directionOfRay.getValue());
//        for(Board.Direction direction : currentHexagon.getDirectionsOfInfluence()){
//            System.out.println("Dir from " + direction + " " + direction.getValue());
//        }

        //System.out.println("ans: " + (directionOfRay.getValue() -2)%6);

        // cases for ray direction

        // if only influenced from one bordering atom
        if (currentHexagon.getDirectionsOfInfluence().size() == 1) {
            newDirection = influencedFrom1Atom(currentHexagon, directionOfRay);

        }else if (currentHexagon.getDirectionsOfInfluence().size() == 2) {
            newDirection = influencedFrom2Atom(currentHexagon, directionOfRay);

        }else if (currentHexagon.getDirectionsOfInfluence().size() == 3) {
            newDirection = influencedFrom3Atom(currentHexagon, directionOfRay);

        }
        // rest of if statements for rest of ray directions...

        return newDirection;
    }

    @Override
    public String toString() {
        return path.toString();
    }


    private Board.Direction influencedFrom1Atom(Hexagon currentHexagon, Board.Direction directionOfRay){
        //if the difference between the ray and the direction of circle of influence is 3 than ray has to be absorbed
        if(positiveModulo_6(directionOfRay.getValue(),  -currentHexagon.getDirectionsOfInfluence().get(0).getValue()) == 3){

            return directionOfRay; // return same direction as where ray is going
        }//if there is a 120 degree difference between ray and circle of influence it will be deflected 120
        else if(positiveModulo_6(directionOfRay.getValue(), + 2) == currentHexagon.getDirectionsOfInfluence().get(0).getValue() ||
                (positiveModulo_6(directionOfRay.getValue(), -2) == currentHexagon.getDirectionsOfInfluence().get(0).getValue())){


            //if it is 2 bigger clockwise ray changes direction one clockwise and the opposite for counter-clockwise
            if((directionOfRay.getValue() +2)%6 == currentHexagon.getDirectionsOfInfluence().get(0).getValue()){
                return Board.Direction.fromValue(positiveModulo_6(directionOfRay.getValue(), 1));
            }else{
                return Board.Direction.fromValue(positiveModulo_6(directionOfRay.getValue(), -1));
            }


        }
        return directionOfRay;
    }
//because the only way a ray can reach a hexagon that has t20 this patter where there is a
//circle of influence in the opposite direction and another going in a direction one away from the one
//going the opposite direction as in if a ray is traveling west, the circles of influence must be going east,
// and northeast or southeast
    private Board.Direction influencedFrom2Atom(Hexagon currentHexagon, Board.Direction directionOfRay){
        ArrayList<Board.Direction> listOfDirection = new ArrayList<>();
        if(positiveModulo_6(directionOfRay.getValue(),  -currentHexagon.getDirectionsOfInfluence().get(0).getValue()) == 3){
            return currentHexagon.getDirectionsOfInfluence().get(1);
        }else if(positiveModulo_6(directionOfRay.getValue(),  -currentHexagon.getDirectionsOfInfluence().get(1).getValue()) == 3){
            return currentHexagon.getDirectionsOfInfluence().get(0);
        }
        else{
            return Board.Direction.fromValue(positiveModulo_6(directionOfRay.getValue(), 3));
        }


    }
    private Board.Direction influencedFrom3Atom(Hexagon currentHexagon, Board.Direction directionOfRay){
        return Board.Direction.fromValue(positiveModulo_6(directionOfRay.getValue(), 3));
    }

    public int positiveModulo_6(int i, int j) {
        //ensure modulo is always positive
        int n = (i+j)%6;

        if(n>= 0){
            return n;
        }
        return n + 6;
    }

}
