import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;



// This class represents the rays on the board.
public class Ray {

    // instance variables
    private final LinkedList<Hexagon> path = new LinkedList<>();
    private Board.Direction direction;
    private boolean absorbed = false;
    private final Hexagon startHexagon;
    private final Board.Direction startDirection; // direction the ray is going at the start

    private Color color;

    // constructor
    public Ray(Board board, Hexagon startHexagon, Board.Direction direction) {
        this.direction = direction;
        this.startDirection = direction;
        // add first hexagon into linked list
        path.add(startHexagon);
        this.startHexagon = startHexagon;
        this.color = Color.RED;

        // check if hexagon is on the side
        if (!startHexagon.isSide()) {
            throw new IllegalArgumentException("Must choose a hexagon on the side of the board for the ray to travel from");
        }

        board.addRay(this);
        createRay(board, startHexagon);
    }

    // Method to create the path of the ray
    public void createRay(Board board, Hexagon startHexagon) {
        // add next hexagon into linked list based on direction
        // while next hexagon is not null
        while (board.getNextHexagon(path.getLast(), direction) != null) {
            // if the start hexagon has an atom
            if (startHexagon.hasAtom()) {
                // never gets to first hexagon as it contains atom
                path.remove(startHexagon);
                System.out.println("Ray absorbed start hex");
                absorbed = true;
                color = Color.green;
                break;
            } else if (atomToSideStartHexagon(path.getLast(), this)) {
                System.out.println("Ray absorbed beside start hex");
                System.out.println("Atom beside ray");
                absorbed = true;
                color = Color.green;
                break;
            } else if (!path.getLast().hasInfluence()) { // if the next hexagon does not have influence, add it to the path
                path.add(board.getNextHexagon(path.getLast(), direction)); // add 1 to num of arrays
            } else { // if the next hexagon has influence, we want to add that hexagon into the path but our direction will have to change (or get absorbed)
                // determine new direction
                Board.Direction newDirection = calculateNewDirection(path.getLast(), direction);
                // if direction and new direction are the same it is a direct collision
                if (newDirection == direction) {
                    path.add(board.getNextHexagon(path.getLast(), direction));
                    System.out.println("Ray absorbed");
                    absorbed = true;
                    color = Color.green;
                    break;
                }
               
                direction = newDirection; // set direction to new direction so the while loop continues
                //edge case if delfects in and out of board from same hexagon
                if (board.getNextHexagon(path.getLast(), direction) != null) {
                    // add hexagon into path
                    path.add(board.getNextHexagon(path.getLast(), direction));
                }
            }
        }
        if(!path.isEmpty())
            direction = calculateNewDirection(path.getLast(), direction);
    }

    // method that determines turn direction of the ray and returns it
    private Board.Direction calculateNewDirection(Hexagon currentHexagon, Board.Direction directionOfRay) {
        // randomly set by the end shouldn't have any effect of outcome
        Board.Direction newDirection = directionOfRay;
        // cases for ray direction
        // if only influenced from one bordering atom
        if (currentHexagon.getDirectionsOfInfluence().size() == 1) {
            newDirection = influencedFrom1Atom(currentHexagon, directionOfRay);

        } else if (currentHexagon.getDirectionsOfInfluence().size() == 2) {
            newDirection = influencedFrom2Atom(currentHexagon, directionOfRay);
        } else if (currentHexagon.getDirectionsOfInfluence().size() == 3) {
            newDirection = influencedFrom3Atom(currentHexagon, directionOfRay);
        }
        // rest of if statements for rest of ray directions...
        return newDirection;
    }

    // Method to handle ray direction when influenced by one atom
    private Board.Direction influencedFrom1Atom(Hexagon currentHexagon, Board.Direction directionOfRay) {
        // if the difference between the ray and the direction of circle of influence is 3 than ray has to be absorbed
        if (positiveModulo_6(directionOfRay.getValue(), -currentHexagon.getDirectionsOfInfluence().get(0).getValue()) == 3) {
            return directionOfRay; // return same direction as where ray is going

            // if it is 2 bigger clockwise ray changes direction one clockwise and the opposite for counter-clockwise
        } else if (positiveModulo_6(directionOfRay.getValue(), +2) == currentHexagon.getDirectionsOfInfluence().get(0).getValue() ||
                (positiveModulo_6(directionOfRay.getValue(), -2) == currentHexagon.getDirectionsOfInfluence().get(0).getValue())) {
            setColor(Color.BLUE);
            if ((directionOfRay.getValue() + 2) % 6 == currentHexagon.getDirectionsOfInfluence().get(0).getValue()) {
                return Board.Direction.fromValue(positiveModulo_6(directionOfRay.getValue(), 1));
            } else {
                return Board.Direction.fromValue(positiveModulo_6(directionOfRay.getValue(), -1));
            }
        }
        return directionOfRay;
    }

    // Method to handle ray direction when influenced by two atoms
    private Board.Direction influencedFrom2Atom(Hexagon currentHexagon, Board.Direction directionOfRay) {
        ArrayList<Board.Direction> listOfDirection = new ArrayList<>();
        if (positiveModulo_6(directionOfRay.getValue(), -currentHexagon.getDirectionsOfInfluence().get(0).getValue()) == 3) {
            setColor(Color.MAGENTA);
            return currentHexagon.getDirectionsOfInfluence().get(1);
        } else if (positiveModulo_6(directionOfRay.getValue(), -currentHexagon.getDirectionsOfInfluence().get(1).getValue()) == 3) {
            setColor(Color.MAGENTA);
            return currentHexagon.getDirectionsOfInfluence().get(0);
        } else {
            System.out.println("IM here reflected");
            this.color = new Color(148, 0, 211);
            return Board.Direction.fromValue(positiveModulo_6(directionOfRay.getValue(), 3));
        }
    }

    // Method to handle ray direction when influenced by three atoms
    private Board.Direction influencedFrom3Atom(Hexagon currentHexagon, Board.Direction directionOfRay) {
        //sets ray marker shade purple
        System.out.println("IM here reflected");
        this.color = new Color(148, 0, 211);
        return Board.Direction.fromValue(positiveModulo_6(directionOfRay.getValue(), 3));
    }

    // Method to ensure modulo operation always returns a positive result
    public int positiveModulo_6(int i, int j) {
        // ensure modulo is always positive
        int n = (i + j) % 6;
        if (n >= 0) {
            return n;
        }
        return n + 6;
    }

    private boolean atomToSideStartHexagon(Hexagon h, Ray ray){
        if(h.isSide()){

            for(Board.Direction direction : h.getDirectionsOfInfluence()){
                int diff1 = positiveModulo_6(direction.getValue(), 1);
                int diff2 = positiveModulo_6(direction.getValue(), -1);

                if(diff1 == ray.direction.getValue()|| diff2 == ray.direction.getValue())
                    return true;

            }
        }
        return false;
    }

    // Getters and setters
    public LinkedList<Hexagon> getPath() {
        return path;
    }

    public Board.Direction getDirection() {
        return direction;
    }

    public Board.Direction getStartDirection() {
        return startDirection;
    }

    public boolean isAbsorbed() {
        return absorbed;
    }

    public Hexagon getStartHexagon() {
        return startHexagon;
    }

    @Override
    public String toString() {
        return path.toString();
    }

    public Color getColor() {
        return color;
    }

    private void setColor(Color newColor){

//        switch (newColor.getRGB()) {
//            case Color.BLUE -> {
//                if (color.getRGB() == Color.red.getRGB())
//                    color = newColor;
//            }
//            case Color.magenta -> {
//                if (color.getRGB() == Color.blue.getRGB() || color.getRGB() == Color.red.getRGB())
//                    color = newColor;
//            }
//        }
        int newColorRGB = newColor.getRGB();
        int colorRGB = color.getRGB();

        if (newColorRGB == Color.BLUE.getRGB()) {
            if (colorRGB == Color.RED.getRGB()) {
                color = newColor;
            }
        } else if (newColorRGB == Color.MAGENTA.getRGB()) {
            if (colorRGB == Color.BLUE.getRGB() || colorRGB == Color.RED.getRGB()) {
                color = newColor;
            }
        }
    }

}