import java.util.LinkedList;

// This class represents the rays on the board.
public class Ray {

    // instance variables
    private final LinkedList<Hexagon> path = new LinkedList<>();

    // constructor
    public Ray(Board board, Hexagon startHexagon, Board.Direction direction) {

        // add first hexagon into linked list
        path.add(startHexagon);

        // check if hexagon is on the side
        if(!startHexagon.isSide()) {
            throw new IllegalArgumentException("Must choose a hexagon on the side of the board for the ray to travel from");
        }

        // add next hexagon into linked list based on direction
        // while next hexagon is not null
        while(board.getNextHexagon(path.getLast(), direction) != null) {

            // if the next hexagon does not have influence, add it to the path
            if (!board.getNextHexagon(path.getLast(), direction).hasInfluence()) {
                path.add(board.getNextHexagon(path.getLast(), direction));
            }
            else { // if the next hexagon has influence, we want to add that hexagon into the path but our direction will have to change (or get absorbed)

                // add hexagon into path
                path.add(board.getNextHexagon(path.getLast(), direction));

                // determine new direction
                Board.Direction newDirection = calculateNewDirection(path.getLast(), direction);

                // if this new direction is the same as previous, ray is absorbed and stops going
                if (newDirection == direction) {
                    System.out.println("Ray absorbed!");
                    break;
                }

                direction = newDirection; // set direction to new direction so the while loop contines
            }
        }


    }

    // method that determines turn direction of the ray and returns it
    private Board.Direction calculateNewDirection(Hexagon currentHexagon, Board.Direction directionOfRay) {
        Board.Direction newDirection = directionOfRay;

        // cases for ray direction

        // if direction of ray is opposite of where the hexagon is influenced from (i.e. there is an atom on the other side)
        if (directionOfRay.isOpposite(currentHexagon.influencedFrom())) {
            return directionOfRay; // return same direction as where ray is going
        }
        // rest of if statements for rest of ray directions...

        return newDirection;
    }

    @Override
    public String toString() {
        return path.toString();
    }


}
