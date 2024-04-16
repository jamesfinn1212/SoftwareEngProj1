import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class GUI extends JPanel {

    // Constants for hexagon rendering
    private static final int HEX_RADIUS = 30;
    private static final int HEX_DISTANCE = 2;
    private static final int CENTER_PIXEL_X = 600;
    private static final int CENTER_PIXEL_Y = 300;
    private static final int HEX_SIDE_LENGTH = 30;
    private static final int HEX_HOVER_INCREMENT = 2;

    // Buttons and panels
    private final JButton endGuessButton;
    private final JButton endRoundButton;
    private JPanel endGuessButtonPane;
    private JPanel endRoundButtonPane;
    private JTextArea textArea_right;

    // Card layout for managing GUI panels
    JPanel cardPanel;
    CardLayout cardLayout;

    // Enum for hexagon sections
    private enum Hexagon_Section {
        TOP_LEFT, TOP_RIGHT, RIGHT, BOTTOM_RIGHT, BOTTOM_LEFT, LEFT;
    }

    // Game instance
    private static Game game;
    private Hexagon hoveredHexagon;

    // GUI constructor
    public GUI(Game game, CardLayout cardLayout, JPanel cardPanel) {
        setBackground(Color.BLACK);
        setLayout(new BorderLayout());
        this.game  = game;
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;

        setRightText("test");

        // Mouse listener for handling clicks
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Hexagon clickedHexagon = getFromPixelPosition(e.getX(), e.getY());
                Hexagon_Section sectionClicked = getSectionClicked(e.getX(), e.getY(), clickedHexagon);
                Board.Direction directionOfRay = getDirectionFromSection(sectionClicked);

                if (clickedHexagon != null) {
                    // Handle different game actions based on current action
                    if (game.getCurrentAction() == Game.Action.PLACE_ATOM) {
                        if (!clickedHexagon.hasAtom()) {
                            game.getBoard().addAtom(clickedHexagon.getX(), clickedHexagon.getY());
                            Draw.drawBoard(game.getBoard());
                        } else {
                            game.getBoard().removeAtom(clickedHexagon.getX(), clickedHexagon.getY());
                        }
                    }

                    if (game.getCurrentAction() == Game.Action.PLACE_RAY ) {
                        toggleEndGuessButtonVisibility(true);
                        if (clickedHexagon.isSide() && sectionOnSide(sectionClicked, clickedHexagon)) {
                            Ray newRay = new Ray(game.getBoard(), clickedHexagon, directionOfRay);
                            game.getBoard().numRaysPlaced++;
                            game.getCurrentSetter().incrementRayCounter();
                        }
                    }

                    if (game.getCurrentAction() == Game.Action.GUESS_ATOM){
                        toggleEndGuessButtonVisibility(false);
                        toggleEndRoundButtonVisibility(false);
                        game.getBoard().addGuessAtom(clickedHexagon.getX(), clickedHexagon.getY());
                    }

                    repaint();  // Repaint the panel
                }
            }
        });

        // Mouse motion listener for handling hover
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                hoveredHexagon = getFromPixelPosition(e.getX(), e.getY());
                repaint();
            }
        });

        // Create the "End Guess" button
        endGuessButton = new JButton("Click to Guess Atoms");
        JPanel endGuessButtonPanel = new JPanel();
        endGuessButtonPanel.add(endGuessButton);
        endGuessButtonPanel.setBackground(Color.BLACK);
        endGuessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.setIsOver(true); // Call the endGuess() method of the Game class when the button is clicked
            }
        });

        // Add the "End Guess" button to the GUI
        add(endGuessButtonPanel, BorderLayout.NORTH);

        // Create the "End Round" button
        endRoundButton = new JButton("End Round");
        JPanel endRoundButtonPanel = new JPanel();
        endRoundButtonPanel.setBackground(Color.BLACK);
        endRoundButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.setIsRoundOver(true); // Call the endRound() method of the Game class when the button is clicked
            }
        });

        // Add the "End Round" button to the GUI
        endRoundButtonPanel.add(endRoundButton);
        add(endRoundButtonPanel, BorderLayout.WEST);

        // Store button panels for toggling visibility
        this.endGuessButtonPane = endGuessButtonPanel;
        this.endRoundButtonPane = endRoundButtonPanel;
    }




    private static Hexagon getFromPixelPosition(int x, int y) {
        // Iterate through all hexagons on the board
        for (Hexagon hexagon : game.getBoard().getListBoard()) {
            // Calculate pixel position of the hexagon
            int xValue = CENTER_PIXEL_X + (60 * hexagon.getX() - 30 * hexagon.getY());
            int yValue = CENTER_PIXEL_Y - (52 * hexagon.getY());

            // Check if the given pixel position (x, y) is inside the bounding box of the hexagon
            if (xValue <= x && yValue <= y && x <= xValue + HEX_RADIUS * 2 - 5 && y <= yValue + HEX_RADIUS * 2 - 5) {
                return hexagon;
            }
        }
        return null; // No hexagon found at the given pixel position
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw hexagons on the board
        for (Hexagon hexagon : game.getBoard().getListBoard()) {
            int xValueHex = CENTER_PIXEL_X + (60 * hexagon.getX() - 30 * hexagon.getY());
            int yValueHex = CENTER_PIXEL_Y - (52 * hexagon.getY());
            int sideLength = HEX_SIDE_LENGTH;

            // Increase side length if hexagon is hovered over
            if (hexagon == hoveredHexagon) {
                sideLength += HEX_HOVER_INCREMENT;
                xValueHex -= HEX_HOVER_INCREMENT;
                yValueHex -= HEX_HOVER_INCREMENT;
            }

            // Draw hexagon with different colored sections
            drawHexagon(g, xValueHex, yValueHex, sideLength);

            // Draw text on hexagons based on current game action
            if (game.getCurrentAction() == Game.Action.PLACE_ATOM || game.getCurrentAction() == Game.Action.GUESS_ATOM) {
                toggleEndRoundButtonVisibility(false);

                // Adjust position if hexagon is hovered over
                if (hexagon == hoveredHexagon) {
                    xValueHex += HEX_HOVER_INCREMENT;
                    yValueHex += HEX_HOVER_INCREMENT;
                }

                // Draw text showing hexagon coordinates
                drawText(g, xValueHex, yValueHex, String.valueOf(hexagon.getHexagonNumFromCord(hexagon.getX(), hexagon.getY())));
            }

            // Draw hexagon side numbers for placing rays
            if (game.getCurrentAction() == Game.Action.PLACE_RAY) {
                drawHexSideNum(g, xValueHex, yValueHex);
            }

            // Show end round button if needed
            if (game.getCurrentAction() == Game.Action.SHOW_BOARD) {
                toggleEndGuessButtonVisibility(false);
                toggleEndRoundButtonVisibility(true);
            }
        }

        // Draw circles of influence and atoms
        for (Hexagon hexagon : game.getBoard().getListBoard()) {
            int xValue = CENTER_PIXEL_X + (60 * hexagon.getX() - 30 * hexagon.getY());
            int yValue = CENTER_PIXEL_Y - (52 * hexagon.getY());

            // Draw atom and circle of influence if hexagon contains an atom and is visible
            if ((hexagon.hasAtom() || (game.getCurrentAction() == Game.Action.GUESS_ATOM && hexagon.hasGuessAtom())) &&
                    (game.getCurrentAction() == Game.Action.PLACE_ATOM || game.getCurrentAction() == Game.Action.SHOW_BOARD ||
                            (game.getCurrentAction() == Game.Action.GUESS_ATOM && hexagon.hasGuessAtom()))) {
                drawCircle(g, xValue + 10, yValue + 10, HEX_RADIUS - 10);
                drawCircleOfInfluence(g, xValue - 10, yValue - 10, HEX_RADIUS + 10);
            }
        }

        // Draw rays and markers
        for (Ray ray : game.getBoard().getRays()) {
            if (game.getCurrentAction() == Game.Action.PLACE_RAY || game.getCurrentAction() == Game.Action.SHOW_BOARD) {
                drawRay(g, ray);
            }
            drawMarker(g, ray);
        }

        // Update right text based on game state
        updateRightTextBasedOnState(game.getCurrentAction());
    }
    public void toggleEndGuessButtonVisibility(boolean visible) {
        endGuessButtonPane.setVisible(visible);
        endGuessButton.setEnabled(visible); // Enable/disable the button based on visibility
    }

    public void toggleEndRoundButtonVisibility(boolean visible) {
        endRoundButtonPane.setVisible(visible);
        endRoundButton.setEnabled(visible); // Enable/disable the button based on visibility
    }

    public static void drawHexagon(Graphics g, int x, int y, int sideLength) {
        // Calculate the points of the hexagon
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

        // Draw arrows if needed
        for (int i = 0; i < 6; i++) {
            double xMiddle = midpoint(xPoints[i], xPoints[(i + 1) % 6]);
            double yMiddle = midpoint(yPoints[i], yPoints[(i + 1) % 6]);
            try {
                if (isArrowNeeded(x, y, i) && game.getCurrentAction() == Game.Action.PLACE_RAY) {
                    g2d.setColor(Color.RED);
                    int[] xPointsTriangle = {(int) Math.round(midpoint(centerX, xMiddle)), (int) Math.round(midpoint(xPoints[i], xMiddle)), (int) Math.round(midpoint(xMiddle, xPoints[(i + 1) % 6]))};
                    int[] yPointsTriangle = {(int) Math.round(midpoint(centerY, yMiddle)), (int) Math.round(midpoint(yPoints[i], yMiddle)), (int) Math.round(midpoint(yMiddle, yPoints[(i + 1) % 6]))};
                    g2d.fillPolygon(xPointsTriangle, yPointsTriangle, 3);
                }
            } catch (Exception ex) {
                // Handle exceptions if any
            }
        }
    }

    // Utility method to find the midpoint between two points
    private static double midpoint(double x1, double x2){
        return (x1 + x2) / 2.0;
    }

    private void drawMarker(Graphics g, Ray ray) {
        Graphics2D g2d = (Graphics2D) g;

        int sideLength = 30;
        Hexagon startHex;
        Hexagon endHex;

        Board.Direction startDirection = ray.getStartDirection();
        if (ray.getPath().isEmpty()) {
            startHex = ray.getStartHexagon();
            endHex = ray.getStartHexagon();
        } else {
            startHex = ray.getPath().getFirst();
            endHex = ray.getPath().getLast();
        }

        if (endHex == null) {
            endHex = startHex;
        }

        int xStartCentre = CENTER_PIXEL_X + (60 * startHex.getX() - 30 * startHex.getY()) + 30;
        int yStartCentre = CENTER_PIXEL_Y - (52 * startHex.getY()) + 30;
        int xEndCentre = CENTER_PIXEL_X + (60 * endHex.getX() - 30 * endHex.getY()) + 30;
        int yEndCentre = CENTER_PIXEL_Y - (52 * endHex.getY()) + 30;

        // Adjust side length and center position if hovered over
        if (hoveredHexagon == startHex) {
            sideLength += HEX_HOVER_INCREMENT;
            xStartCentre -= HEX_HOVER_INCREMENT;
            yStartCentre -= HEX_HOVER_INCREMENT;
        }
        if (hoveredHexagon == endHex) {
            sideLength += HEX_HOVER_INCREMENT;
            xEndCentre -= HEX_HOVER_INCREMENT;
            yEndCentre -= HEX_HOVER_INCREMENT;
        }

        // Calculate points for start and end hexagons
        int[] xStartPoints = new int[6];
        int[] yStartPoints = new int[6];
        int[] xEndPoints = new int[6];
        int[] yEndPoints = new int[6];
        for (int a = 0; a < 6; a++) {
            double angle = 2 * Math.PI / 6 * a + Math.PI / 2;
            xStartPoints[a] = (int) (xStartCentre + sideLength * Math.cos(angle));
            yStartPoints[a] = (int) (yStartCentre + sideLength * Math.sin(angle));
            xEndPoints[a] = (int) (xEndCentre + sideLength * Math.cos(angle));
            yEndPoints[a] = (int) (yEndCentre + sideLength * Math.sin(angle));
        }

        // Generate a unique color for the marker
        int uniqueColor = ray.hashCode(); // Using ray's hash code for uniqueness
        Color color = new Color(uniqueColor * 1079 % 333 * 1088);

        // Draw start marker
        int point1 = (5 - startDirection.getValue());
        int point2 = (point1 + 1) % 6;
        double xMidPoint = midpoint(xStartPoints[point1], xStartPoints[point2]);
        double yMidPoint = midpoint(yStartPoints[point1], yStartPoints[point2]);
        g2d.setColor(color);
        g2d.fillOval((int) xMidPoint - 6, (int) yMidPoint - 6, 13, 13);

        // Draw end marker if ray is not absorbed
        if (!ray.isAbsorbed()) {
            int endPoint1 = (5 - ray.getDirection().getOpposite().getValue());
            int endPoint2 = (endPoint1 + 1) % 6;
            xMidPoint = midpoint(xEndPoints[endPoint1], xEndPoints[endPoint2]);
            yMidPoint = midpoint(yEndPoints[endPoint1], yEndPoints[endPoint2]);
            g2d.fillOval((int) xMidPoint - 6, (int) yMidPoint - 6, 13, 13);
        }
    }





    private static boolean isArrowNeeded(int x, int y, int i){
        // Get the hexagon based on pixel position
        Hexagon hexagon = getFromPixelPosition(x + 30, y + 30);

        // Check if arrow is needed based on hexagon properties
        if(hexagon.isSide()){
            if(hexagon.getY() == 4 && (i == 2 || i == 3)){
                return true;
            }
            if(hexagon.getY() == -4 && (i == 0 || i == 5)){
                return true;
            }
            if(hexagon.getX() == -4 && (i == 1 || i == 0)){
                return true;
            }
            if(hexagon.getY() - hexagon.getX() == 4 && (i == 1 || i == 2)){
                return true;
            }
            if(hexagon.getX() == 4 && (i == 3 || i == 4)){
                return true;
            }
            if(hexagon.getY() - hexagon.getX() == -4 && (i == 4 || i == 5)){
                return true;
            }
        }
        return false;
    }

    private void drawCircle(Graphics g, int x, int y, int radius) {
        // Draw a filled circle
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        g2d.fillOval(x, y, radius * 2, radius * 2);
    }

    private void drawCircleOfInfluence(Graphics g, int x, int y, int radius) {
        // Draw a circle of influence (hollow circle)
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        g2d.drawOval(x - radius/2, y - radius/2, radius * 3, radius * 3);
    }

    private void drawText(Graphics g, int x, int y, String text) {
        // Draw text at the specified position
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

    private void drawHexSideNum(Graphics g, int x, int y){
        // Draw numbers on hexagon sides
        Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(new Font("Arial", Font.BOLD, 12));
        g2d.setColor(Color.WHITE);
        FontMetrics metrics = g2d.getFontMetrics();
        int textWidth = metrics.stringWidth("High");
        int textHeight = metrics.getHeight();
        Hexagon hexagon = getFromPixelPosition(x + 30, y + 30); // Get the hexagon
        // Draw numbers based on hexagon position and orientation
    }

    //draws ray on to GUI
    private void drawRay(Graphics g, Ray ray)  {
        Graphics2D g2d = (Graphics2D) g;

        //gets path, start direction, end direction
        LinkedList<Hexagon> path = ray.getPath();
        Board.Direction startDirection = ray.getStartDirection();
        Board.Direction endDirection = ray.getDirection();
        g2d.setColor(Color.LIGHT_GRAY);


        int size = path.size();
        int i = 0;
        int xOffset = 0;
        int yOffset = 0;

        //ensures at least on one hexagon
        while(size > 0) {

            // edge case for if there is only 1 hexagon in the path and the ray has been absorbed
            if(path.size() == 1 && ray.isAbsorbed()) {

                Hexagon startHexagon = path.getFirst();

                int xValueCenter = CENTER_PIXEL_X + (60 * startHexagon.getX() - 30 * startHexagon.getY()) + HEX_SIDE_LENGTH;
                int yValueCenter = CENTER_PIXEL_Y - (52 * startHexagon.getY()) + HEX_SIDE_LENGTH;
                xOffset = getXOffset(ray.getStartDirection());
                yOffset = getYOffset(ray.getStartDirection());



                g2d.drawLine(xValueCenter, yValueCenter, xOffset + xValueCenter, yOffset + yValueCenter);

            }
            //edge case if it enters and exits the same triangle in different places
            if(path.size() == 1 && !ray.isAbsorbed()) {

                Hexagon startHexagon = path.getFirst();

                int xValueCenter = CENTER_PIXEL_X + (60 * startHexagon.getX() - 30 * startHexagon.getY()) + HEX_SIDE_LENGTH;
                int yValueCenter = CENTER_PIXEL_Y - (52 * startHexagon.getY()) + HEX_SIDE_LENGTH;
                xOffset = getXOffset(ray.getStartDirection());
                yOffset = getYOffset(ray.getStartDirection());

                int endXOffset = getXOffset(ray.getDirection().getOpposite());
                int endYOffset = getYOffset(ray.getDirection().getOpposite());

                g2d.drawLine(xValueCenter, yValueCenter, xOffset + xValueCenter, yOffset + yValueCenter);
                g2d.drawLine(xValueCenter, yValueCenter, endXOffset + xValueCenter, endYOffset + yValueCenter);

            }

            if(i + 1 < path.size()) {
                Hexagon startHexagon = path.get(i);
                Hexagon endHexagon = path.get(i + 1);

                // edge case if end hexagon is null, set to start hexagon
                // this occurs if the ray is deflected in a direction where there is no hexagon
                if(endHexagon == null) {
                    endHexagon = path.get(i);
                }


                int x1Value = CENTER_PIXEL_X + (60 * startHexagon.getX() - 30 * startHexagon.getY()) + HEX_SIDE_LENGTH;
                int y1Value = CENTER_PIXEL_Y - (52 * startHexagon.getY()) + HEX_SIDE_LENGTH;

                // if we are drawing on the first hexagon in the path
                if(i == 0) {
                    xOffset = getXOffset(startDirection);
                    yOffset = getYOffset(startDirection);

                    // draw line from side to middle

                    g2d.drawLine(x1Value, y1Value, xOffset + x1Value, yOffset + y1Value);
                }



                int x2Value = CENTER_PIXEL_X + (60 * endHexagon.getX() - 30 * endHexagon.getY()) + HEX_SIDE_LENGTH;
                int y2Value = CENTER_PIXEL_Y - (52 * endHexagon.getY()) + HEX_SIDE_LENGTH;


                // if we are drawing on the last hexagon in the path
                if(i + 1 == path.size() - 1) {

                    // only draw further if the ray has NOT been absorbed

                    if(!ray.isAbsorbed()) {
                        xOffset = getXOffset(endDirection.getOpposite()); // get opposite direction so can use same function as for startDirection
                        yOffset = getYOffset(endDirection.getOpposite());

                        // draw line from side ot middle
                        g2d.drawLine(x2Value, y2Value, xOffset + x2Value, yOffset + y2Value);
                    }

                }

                g2d.drawLine(x1Value, y1Value, x2Value, y2Value);


            }
            i++;
            size--;


        }


    }

    // method that returns section of the hexagon clicked
    private Hexagon_Section getSectionClicked(int clickedX, int clickedY, Hexagon clickedHexagon) throws NullPointerException {
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



    // method that returns how far back on x axist you should draw ray for it to start at side
    private int getXOffset(Board.Direction direction) {
        int offset = 0;

        switch(direction)  {
            case WEST:
                offset = 24;
                break;
            case EAST:
                offset = -24;
                break;
            case NORTHWEST:
            case SOUTHWEST:
                offset = 12;
                break;
            case SOUTHEAST:
            case NORTHEAST:
                offset = -12;
                break;
        }

        return offset;
    }

    private int getYOffset(Board.Direction direction) {

        int offset = 0;

        switch(direction)  {
            case NORTHWEST:
            case NORTHEAST:
                offset = 21;
                break;
            case SOUTHEAST:
            case SOUTHWEST:
                offset = -21;
                break;

        }

        return offset;
    }

    // method that returns direction the section is in from center of the hexagon
    private Board.Direction getDirectionOfSectionFromCenter(Hexagon_Section section) {
        return getDirectionFromSection(section).getOpposite(); // direction of section from center is the opposite of the direction the ray would be going
    }

    // method that returns true if a section clicked is on the side of the board
    private boolean sectionOnSide(Hexagon_Section clickedSection, Hexagon clickedHexagon) {

        Board.Direction directionOfSectionFromCenter = getDirectionOfSectionFromCenter(clickedSection);

        if(game.getBoard().getNextHexagon(clickedHexagon, directionOfSectionFromCenter) == null) { // if there is no hexagon in this direction
            return true;
        }
        return false;
    }

    // method for the Home Screen, returns a panel of Home Screen
    public JPanel setHomeScreen() {
        JPanel homePanel;
        JLabel titleLabel;

        // Create home panel
        homePanel = new JPanel();
        homePanel.setLayout(new BoxLayout(homePanel, BoxLayout.PAGE_AXIS));
        homePanel.setBorder(BorderFactory.createEmptyBorder(50, 20, 50, 20));
        homePanel.setBackground(Color.BLACK);

        // Create title label
        titleLabel = new JLabel("Black Box game");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 50));
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        titleLabel.setForeground(Color.YELLOW);
        homePanel.add(titleLabel);

        // Add vertical glue to push components to the top
        homePanel.add(Box.createVerticalGlue());

        // create button
        JButton startButton = new HexagonButton("Start Game");
        startButton.setPreferredSize(new Dimension(300, 300));
        startButton.setFocusPainted(false);

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Switch to the GUI panel
                cardLayout.show(cardPanel, "guiPlayer1");
            }
        });

        startButton.setAlignmentX(CENTER_ALIGNMENT);
        startButton.setAlignmentY(CENTER_ALIGNMENT);

        homePanel.add(startButton);

        homePanel.add(Box.createVerticalGlue());

        return homePanel;
    }

    public static JPanel generateScorecard() {
        JPanel scorecardPanel = new JPanel();
        scorecardPanel.setLayout(new BoxLayout(scorecardPanel, BoxLayout.PAGE_AXIS));
        scorecardPanel.setBackground(Color.BLACK); // Set background color

        JLabel titleLabel = new JLabel("Scorecard");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36)); // Increase font size
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setForeground(Color.YELLOW); // Set text color
        scorecardPanel.add(titleLabel);
        scorecardPanel.add(Box.createVerticalStrut(50)); // Add some spacing

        // Display scores for player 1 and player 2
        JLabel player1ScoreLabel = new JLabel("Player 1 Score: " + game.getPlayer1().getScore());
        JLabel player2ScoreLabel = new JLabel("Player 2 Score: " + game.getPlayer2().getScore());
        player1ScoreLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Increase font size
        player2ScoreLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Increase font size
        player1ScoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        player2ScoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        player1ScoreLabel.setForeground(Color.YELLOW); // Set text color
        player2ScoreLabel.setForeground(Color.YELLOW); // Set text color
        scorecardPanel.add(player1ScoreLabel);
        scorecardPanel.add(player2ScoreLabel);

        // Determine winner
        JLabel winnerLabel = new JLabel();
        if (game.getPlayer1().getScore() > game.getPlayer2().getScore()) {
            winnerLabel.setText("Player 1 wins!");
        } else if (game.getPlayer1().getScore() < game.getPlayer2().getScore()) {
            winnerLabel.setText("Player 2 wins!");
        } else {
            winnerLabel.setText("It's a tie!");
        }
        winnerLabel.setFont(new Font("Arial", Font.BOLD, 30)); // Increase font size
        winnerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        winnerLabel.setForeground(Color.YELLOW); // Set text color
        scorecardPanel.add(winnerLabel);
        scorecardPanel.add(Box.createVerticalStrut(50)); // Add some spacing

        // Add "End Game" button
        JButton endGameButton = new JButton("End Game");
        endGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        endGameButton.setFont(new Font("Arial", Font.BOLD, 24)); // Increase font size
        endGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the application
                System.exit(0);
            }
        });
        scorecardPanel.add(endGameButton);

        return scorecardPanel;

    }

    public void setRightText(String text) {
        //Create a JPanel to hold the text area and set its background color to black
        JPanel textAreaPanel = new JPanel(new BorderLayout());
        textAreaPanel.setBackground(Color.BLACK);

        // Create the JTextArea and set its properties
        JTextArea textAreaRight = new JTextArea(30, 30);
        this.textArea_right = textAreaRight;
        textArea_right.setEditable(false); // Make sure the user can't edit it
        textArea_right.setForeground(Color.YELLOW); // Set text color to yellow
        textArea_right.setBackground(Color.BLACK); // Set background color to black

        textArea_right.append(text);

        // Add the JTextArea to the text area panel
        textAreaPanel.add(textArea_right, BorderLayout.SOUTH);

        // Add the text area panel to the right side of the GUI panel
        this.add(textAreaPanel, BorderLayout.EAST);

    }


    public void updateRightText(String text) {
        textArea_right.setText(text);
    }

    private String placeAtomInfo(int numOfAtomsLeft) {
        return "PLACING ATOMS ROUND\nThis round involves placing atoms on the board.\nClick a hexagon to place an atom.\nYou are allowed to place 6 atoms.\nNumber of atoms left to place: " + numOfAtomsLeft;
    }

    private String placeRayInfo(int raysPlaced) {
        return "PLACING RAYS ROUND\nPass over to the next player.\nPlace as many rays as you need\nto guess where atoms are.\nEach ray placed adds score to the other player\nRays placed: " + raysPlaced;
    }

    private String guessAtomInfo() {
        return "GUESSING ATOMS ROUND\nBased on your rays guess where atoms are\nEach incorrectly guessed atom\nadds score to the other player.";
    }


    private void updateRightTextBasedOnState(Game.Action currentAction) {

        switch(currentAction) {
            case PLACE_ATOM:
                SwingUtilities.invokeLater(() -> {
                    updateRightText(placeAtomInfo(6 - game.getBoard().numAtomsPlaced));
                });
                break;
            case PLACE_RAY:
                // update the GUI text
                SwingUtilities.invokeLater(() -> {
                    updateRightText(placeRayInfo(game.getCurrentSetter().getRaysPlaced()));
                });
                break;
            case GUESS_ATOM:
                SwingUtilities.invokeLater(() -> {
                    updateRightText(guessAtomInfo());
                });
                break;
        }

    }

}