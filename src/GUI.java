import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.*;
import java.util.List;

public class GUI extends JPanel {

    private static final int HEX_RADIUS = 30;
    private static final int HEX_DISTANCE = 2;
    private static final int CENTER_PIXEL_X = 600;
    private static final int CENTER_PIXEL_Y = 300;
    private static final int HEX_SIDE_LENGTH = 30;

    private static final int HEX_HOVER_INCREMENT = 2;

    private ArrayList<Hexagon> clickedHexagons = new ArrayList<>();

    public enum Action {
        PLACE_ATOM, PLACE_RAY;
    }

    // splitting hexagon into 6 sections
    private enum Hexagon_Section {
        TOP_LEFT, TOP_RIGHT, RIGHT, BOTTOM_RIGHT, BOTTOM_LEFT, LEFT;
    }

    public Action currentAction = Action.PLACE_ATOM;
    private Board board;
    private Hexagon hoveredHexagon;

    public GUI(Board board) {
        setBackground(Color.BLACK);
        this.board = board;

        // checks if mouse is clicked
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                // Handle mouse clicks to identify the clicked hexagon
                Hexagon clickedHexagon = getFromPixelPosition(e.getX(), e.getY());
                Hexagon_Section sectionClicked = getSectionClicked(e.getX(), e.getY(), clickedHexagon);
                Board.Direction directionOfRay = getDirectionFromSection(sectionClicked);

                if (clickedHexagon != null) {

                    if(currentAction == Action.PLACE_ATOM) {


                        // if the hexagon has no atom, place that hexagon in the array, otherwise
                        if(!clickedHexagon.hasAtom()) {
                            board.addAtom(clickedHexagon.getX(), clickedHexagon.getY());
                            Draw.drawBoard(board);

                        }
                        else {
                            board.removeAtom(clickedHexagon.getX(), clickedHexagon.getY());
                        }




                    }

                    if(currentAction == Action.PLACE_RAY) {

                        // only shoot ray if hexagon is a side hexagon
                        if(clickedHexagon.isSide()) {

                            // code to place ray.... should check which side of the hexagon was clicked and shoot ray from that direction
                            Ray newRay = new Ray(board, clickedHexagon, directionOfRay);

                        }

                    }

                    repaint();  // Repaint the panel
                }
            }
        });

        // checks for mouse movement
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                hoveredHexagon = getFromPixelPosition(e.getX(), e.getY());
                repaint();
            }
        });

    }

    private Hexagon getFromPixelPosition(int x, int y) {


        for (Hexagon hexagon : board.getListBoard()) {

            int xValue = CENTER_PIXEL_X + (60*hexagon.getX() - 30*hexagon.getY());
            int yValue = CENTER_PIXEL_Y - (52*hexagon.getY());

            if ( xValue <= x && yValue <= y && x <= xValue + HEX_RADIUS *2 - 5 && y <= yValue + HEX_RADIUS *2 -5 ) {
                return hexagon;
            }
        }
        return null;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // draw hexagons from the board


        for(Hexagon hexagon: board.getListBoard()) {
            int xValueHex = CENTER_PIXEL_X + (60*hexagon.getX() - 30*hexagon.getY());
            int yValueHex = CENTER_PIXEL_Y - (52*hexagon.getY());
            int xValueText = xValueHex;
            int yValueText = yValueHex;
            int sideLength = HEX_SIDE_LENGTH;

            // if hexagon is being hovered over
            if (hexagon == hoveredHexagon) {
                sideLength += HEX_HOVER_INCREMENT;

                xValueHex -= HEX_HOVER_INCREMENT;
                yValueHex -= HEX_HOVER_INCREMENT;
            }

            drawHexagon(g, xValueHex, yValueHex, sideLength);
            drawText(g, xValueText, yValueText, String.valueOf(hexagon.getHexagonNumFromCord(hexagon.getX(), hexagon.getY())));
        }

        // draw circles of influence and atoms 2nd so they're not getting overlapped by existing hexagons
        for(Hexagon hexagon: board.getListBoard()) {
            int xValue = CENTER_PIXEL_X + (60*hexagon.getX() - 30*hexagon.getY());
            int yValue = CENTER_PIXEL_Y - (52*hexagon.getY());

            // if the hexagon has an atom in it and it is not hidden, draw the atom
            if(hexagon.hasAtom() && !hexagon.isHidden()) {
                drawCircle(g, xValue + 10, yValue + 10, HEX_RADIUS - 10);
                drawCircleOfInfluence(g, xValue - 10, yValue - 10, HEX_RADIUS + 10);
            }
        }

        // draw rays
        for(Ray ray: board.getRays()) {
            drawRay(g, ray);
        }
    }

    private void drawHexagon(Graphics g, int x, int y, int sideLength) {

        int centerX = x + sideLength;
        int centerY = y + sideLength;

        int[] xPoints = new int[6];
        int[] yPoints = new int[6];

        for (int a = 0; a < 6; a++) {
            double angle = 2 * Math.PI / 6 * a + Math.PI / 2;
            xPoints[a] = (int) (centerX + sideLength * Math.cos(angle));
            yPoints[a] = (int) (centerY + sideLength * Math.sin(angle));
        }

        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(3));
        g2d.setColor(Color.YELLOW);
        g2d.fillPolygon(xPoints, yPoints, 6);
    }

    private void drawCircle(Graphics g, int x, int y, int radius) {
        Graphics2D g2d = (Graphics2D) g;


        g2d.setColor(Color.RED);
        g2d.fillOval(x, y, radius * 2, radius * 2);
    }
    private void drawCircleOfInfluence(Graphics g, int x, int y, int radius) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.RED);
        g2d.drawOval(x - radius/2, y - radius/2, radius * 3, radius * 3);
    }

    private void drawText(Graphics g, int x, int y, String text) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setFont(new Font("Arial", Font.BOLD, 12));
        g2d.setColor(Color.BLACK);

        FontMetrics metrics = g2d.getFontMetrics();
        int textWidth = metrics.stringWidth(text);
        int textHeight = metrics.getHeight();

        int centerX = x + HEX_RADIUS - textWidth / 2;
        int centerY = y + HEX_RADIUS + textHeight / 4;

        g2d.drawString(text, centerX, centerY);
    }

    private void drawRay(Graphics g, Ray ray)  {
        Graphics2D g2d = (Graphics2D) g;

        LinkedList<Hexagon> path = ray.getPath();
        g2d.setColor(Color.LIGHT_GRAY);
        int size = path.size();
        int i = 0;

        while(size > 0) {
            if(i + 1 < path.size()) {
                Hexagon startHexagon = path.get(i);
                Hexagon endHexagon = path.get(i + 1);

                int x1Value = CENTER_PIXEL_X + (60 * startHexagon.getX() - 30 * startHexagon.getY());
                int y1Value = CENTER_PIXEL_Y - (52 * startHexagon.getY());
                int x2Value = CENTER_PIXEL_X + (60 * endHexagon.getX() - 30 * endHexagon.getY());
                int y2Value = CENTER_PIXEL_Y - (52 * endHexagon.getY());

                g2d.drawLine(x1Value + 30, y1Value + 30, x2Value + 30, y2Value + 30);
            }
            i++;
            size--;


        }


    }

    // method that returns section of the hexagon clicked
    private Hexagon_Section getSectionClicked(int clickedX, int clickedY, Hexagon clickedHexagon) {
        Hexagon_Section section = null;

        // get xValue and yValue of the center of clicked hexagon
        int xCenter = CENTER_PIXEL_X + (60*clickedHexagon.getX() - 30*clickedHexagon.getY()) + HEX_SIDE_LENGTH;
        int yCenter = CENTER_PIXEL_Y - (52*clickedHexagon.getY()) + HEX_SIDE_LENGTH;

        int x_length = clickedX - xCenter;
        int y_length = clickedY - yCenter;

        double angle = Math.atan2(y_length, x_length);

        // Adjusting the range to [0, 2π)
        if (angle < 0) {
            angle += 2 * Math.PI;
        }

        // change angle to degrees for easier comparison
        double angleDegrees = Math.toDegrees(angle);

        // determine which section was clicked based on angle within the hexagon
        if(angleDegrees >= 30 && angleDegrees <= 90) {
            return Hexagon_Section.BOTTOM_RIGHT;
        }
        if(angleDegrees >= 90 && angleDegrees <= 150) {
            return Hexagon_Section.BOTTOM_LEFT;
        }
        if(angleDegrees >= 150 && angleDegrees <= 210) {
            return Hexagon_Section.LEFT;
        }
        if(angleDegrees >= 210 && angleDegrees <= 270) {
            return Hexagon_Section.TOP_LEFT;
        }
        if(angleDegrees >= 270 && angleDegrees <= 330) {
            return Hexagon_Section.TOP_RIGHT;
        }

        // last range is for RIGHT if degrees is 330 -> 360 or 0 -> 30.
        if((angleDegrees >= 330 && angleDegrees <= 360) || (angleDegrees >= 0 && angleDegrees <= 30)) {
            return Hexagon_Section.RIGHT;
        }



        return section;
    }

    // returns ray direction based on section of the hexagon clicked
    private Board.Direction getDirectionFromSection(Hexagon_Section section) {

        switch(section) {
            case RIGHT:
                return Board.Direction.WEST;
            case LEFT:
                return Board.Direction.EAST;
            case TOP_LEFT:
                return Board.Direction.SOUTHEAST;
            case TOP_RIGHT:
                return Board.Direction.SOUTHWEST;
            case BOTTOM_LEFT:
                return Board.Direction.NORTHEAST;
            case BOTTOM_RIGHT:
                return Board.Direction.NORTHWEST;
            default:
                return null;
        }

    }

    // method to set action
    public void setAction(Action action) {
        currentAction = action;
    }
}

