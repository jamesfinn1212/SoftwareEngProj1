import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    private int clickedHexagonX = -1;
    private int clickedHexagonY = -1;

    private List<HexagonCoordinates> grid = new ArrayList<>();
    private Set<HexagonCoordinates> clickedAtoms = new HashSet<>();

    public GUI() {
        setBackground(Color.BLACK);
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

        initializeGrid();  // Populate the grid
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
    }

    private void initializeGrid() {
        int rows = 5;
        int cols = 5;
        int xco = 750;
        int yco = 375;
        int xmid = 750;
        int ymid = 375;

        for (int j = 0; j < cols; j++) {
            yco = (int) ymid + (j * (2 * HEX_RADIUS + HEX_DISTANCE));
            for (int i = 0; i < rows; i++) {
                xco = xmid + (i * (2 * HEX_RADIUS + HEX_DISTANCE));
                if (i + j <= 4) {
                    grid.add(new HexagonCoordinates(i+j, j*-1, xco, yco));
                }

                xco = xmid - (i * (2 * HEX_RADIUS + HEX_DISTANCE));
                grid.add(new HexagonCoordinates((i*-1)+j, j*-1, xco, yco));
            }

            yco = (int) ymid - (j * (2 * HEX_RADIUS + HEX_DISTANCE));
            for (int i = 0; i < rows; i++) {
                xco = xmid - (i * (2 * HEX_RADIUS + HEX_DISTANCE));
                grid.add(new HexagonCoordinates(i*-1, j, xco, yco));
                xco = xmid + (i * (2 * HEX_RADIUS + HEX_DISTANCE));
                if (i + j <= 4) {
                    grid.add(new HexagonCoordinates(i, j, xco, yco));
                }
            }

            xmid += HEX_RADIUS;
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Blackbox GUI");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            GUI gui = new GUI();
            frame.add(gui);

            frame.setSize(1500, 750);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

