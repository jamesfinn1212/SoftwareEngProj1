import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

class HexagonCoordinates {
    int x;
    int y;
    int pixelX;
    int pixelY;

    public HexagonCoordinates(int x, int y, int pixelX, int pixelY) {
        this.x = x;
        this.y = y;
        this.pixelX = pixelX;
        this.pixelY = pixelY;
    }

    public int getPixelX() {
        return pixelX;
    }

    public int getPixelY() {
        return pixelY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Logical Coordinates: (" + x + ", " + y + "), Pixel Coordinates: (" + pixelX + ", " + pixelY + ")";
    }
}

public class GUI extends JPanel {

    private static final int HEX_RADIUS = 30;
    private static final int HEX_DISTANCE = 2;
    private static final int CENTER_PIXEL_X = 600;
    private static final int CENTER_PIXEL_Y = 300;

    private int clickedHexagonX = -1;
    private int clickedHexagonY = -1;

    private List<HexagonCoordinates> grid = new ArrayList<>();
    private Set<HexagonCoordinates> clickedAtoms = new HashSet<>();
    private Board board;

    public GUI(Board board) {
        setBackground(Color.BLACK);
        this.board = board;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Handle mouse clicks to identify the clicked hexagon
                HexagonCoordinates clickedHexagon = getFromPixelPosition(e.getX(), e.getY());
                if (clickedHexagon != null) {
                    if (clickedAtoms.size() < 6) {
                        clickedAtoms.add(clickedHexagon);
                    }
                    repaint();  // Repaint the panel to show the circle
                }
            }
        });

        //initializeGrid();  // Populate the grid
    }

    private HexagonCoordinates getFromPosition(int x, int y) {
        for (HexagonCoordinates hexCoord : grid) {
            if (hexCoord.getX() == x && hexCoord.getY() == y) {
                return hexCoord;
            }
        }
        return null;
    }

    private HexagonCoordinates getFromPixelPosition(int x, int y) {
        for (HexagonCoordinates hexCoord : grid) {
            if (hexCoord.getPixelX() <= x && x <= hexCoord.getPixelX() + HEX_RADIUS * 2 - 5 &&
                    hexCoord.getPixelY() <= y && y <= hexCoord.getPixelY() + HEX_RADIUS * 2 - 5) {
                return hexCoord;
            }
        }
        return null;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);


        /*
        for (HexagonCoordinates hexCoord : grid) {
            int x = hexCoord.getPixelX();
            int y = hexCoord.getPixelY();
            drawHexagon(g, x, y, HEX_RADIUS);
            // Display text in the center of the hexagon
            String text = hexCoord.getX() + ", " + hexCoord.getY();
            drawText(g, x, y, text);
        }

        for (HexagonCoordinates clickedHexagon : clickedAtoms) {
            int x = clickedHexagon.getPixelX();
            int y = clickedHexagon.getPixelY();
            drawCircle(g, x + 10, y + 10, HEX_RADIUS - 10);
            drawCircleOfInfluence(g, x - 10, y - 10, HEX_RADIUS + 10);
        }
        */

        // draw hexagons from the board
        for(Hexagon hexagon: board.getListBoard()) {
            int xValue = CENTER_PIXEL_X + (60*hexagon.getX() - 30*hexagon.getY());
            int yValue = CENTER_PIXEL_Y - (52*hexagon.getY());

            drawHexagon(g, xValue, yValue, 30);
            drawText(g, xValue, yValue, hexagon.toString());
        }

        // draw circles of influence and atoms 2nd so they're not getting overlapped by existing hexagons
        for(Hexagon hexagon: board.getListBoard()) {
            int xValue = CENTER_PIXEL_X + (60*hexagon.getX() - 30*hexagon.getY());
            int yValue = CENTER_PIXEL_Y - (52*hexagon.getY());

            // if the hexagon has an atom in it, draw the atom
            if(hexagon.hasAtom()) {
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

}

