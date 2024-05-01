import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Class for generating the home screen of the Black Box game
public class HomeScreenGUI {
    // CardLayout to manage switching between panels
    private CardLayout cardLayout;
    // JPanel that contains all the cards (panels) of the game
    private JPanel cardPanel;

    // Constructor
    public HomeScreenGUI(CardLayout cardLayout, JPanel cardPanel){
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
    }

    // Method to generate the home screen panel
    public JPanel generateHomeScreen() {
        // JPanel to hold all components of the home screen
        JPanel homePanel;
        // JLabel for the title
        JLabel titleLabel;

        // Create home panel
        homePanel = new JPanel();
        // Set layout to BoxLayout for vertical arrangement of components
        homePanel.setLayout(new BoxLayout(homePanel, BoxLayout.PAGE_AXIS));
        // Set empty border for spacing
        homePanel.setBorder(BorderFactory.createEmptyBorder(50, 20, 50, 20));
        // Set background color
        homePanel.setBackground(Color.BLACK);

        // Create title label
        titleLabel = new JLabel("Black Box game");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 50));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align horizontally
        titleLabel.setForeground(Color.YELLOW); // Set text color
        homePanel.add(titleLabel); // Add title label to home panel

        // Add vertical glue to push components to the top
        homePanel.add(Box.createVerticalGlue());

        // Create start button
        JButton startButton = new HexagonButton("Start Game"); // Custom button with hexagon shape
        startButton.setPreferredSize(new Dimension(300, 300)); // Set button size
        startButton.setFocusPainted(false); // Remove focus border

        // Add action listener to start button
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Switch to the GUI panel for player 1
                cardLayout.show(cardPanel, "guiPlayer1");
            }
        });

        startButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align horizontally
        startButton.setAlignmentY(Component.CENTER_ALIGNMENT); // Center align vertically

        homePanel.add(startButton); // Add start button to home panel

        homePanel.add(Box.createVerticalGlue()); // Add vertical glue for spacing

        return homePanel; // Return the generated home panel
    }
}