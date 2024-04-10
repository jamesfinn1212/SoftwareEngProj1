import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

// class for a button that is in the shape of a hexagon
public class HexagonButton extends JButton {

    int n=6;
    int x[]= new int[n];
    int y[]= new int[n];
    double angle = 2*Math.PI/n;

    // construct
    public HexagonButton(String label) {
        super(label);
        Dimension size = getPreferredSize();
        size.width = size.height = Math.max(size.width, size.height);
        setPreferredSize(size);
        setContentAreaFilled(false);
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(getBackground());
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        GUI.drawHexagon(g, 10, 110, 40);

        g2d.dispose();
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());

    }


}
