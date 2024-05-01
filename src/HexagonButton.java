import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

// Class for a button that is in the shape of a hexagon
public class HexagonButton extends JButton {

    int n = 6; // Number of sides of the hexagon
    int x[] = new int[n]; // Array to hold x coordinates of vertices
    int y[] = new int[n]; // Array to hold y coordinates of vertices
    double angle = 2 * Math.PI / n; // Angle between each pair of consecutive vertices

    // Constructor
    public HexagonButton(String label) {
        super(label); // Call to parent constructor to set button label
        Dimension size = getPreferredSize(); // Get preferred size of button
        // Ensure the button is square
        size.width = size.height = Math.max(size.width, size.height);
        setPreferredSize(size); // Set preferred size
        setContentAreaFilled(false); // Make the content area of the button transparent
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Create a Graphics2D object
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(getBackground()); // Set the color to the background color of the button
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Enable anti-aliasing

        // Call method to draw a hexagon with specified parameters
        GUI.drawHexagon(g, 10, 110, 40); // Example call to a method (presumably defined elsewhere) to draw a hexagon

        g2d.dispose(); // Dispose of the Graphics2D object
        super.paintComponent(g); // Call to parent's paintComponent method
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground()); // Set the color to the foreground color of the button (for the border)
    }

}