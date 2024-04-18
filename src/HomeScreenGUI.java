import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeScreenGUI {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    public HomeScreenGUI(CardLayout cardLayout, JPanel cardPanel){
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
    }

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
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
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

        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setAlignmentY(Component.CENTER_ALIGNMENT);

        homePanel.add(startButton);

        homePanel.add(Box.createVerticalGlue());

        return homePanel;
    }

}
