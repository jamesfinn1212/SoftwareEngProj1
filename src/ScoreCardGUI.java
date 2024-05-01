import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Class for generating the scorecard panel of the game
public class ScoreCardGUI {

    private final Game game; // Reference to the Game object

    // Constructor
    public ScoreCardGUI(Game game) {
        this.game = game; // Initialize the game reference
    }

    // Method to generate the scorecard panel
    public JPanel generateScorecard() {
        JPanel scorecardPanel = new JPanel(); // Create a JPanel to hold the scorecard components
        scorecardPanel.setLayout(new BoxLayout(scorecardPanel, BoxLayout.PAGE_AXIS)); // Set layout to vertical BoxLayout
        scorecardPanel.setBackground(Color.BLACK); // Set background color

        JLabel titleLabel = new JLabel("Scorecard"); // Create a label for the title
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36)); // Set font size and style
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align horizontally
        titleLabel.setForeground(Color.YELLOW); // Set text color
        scorecardPanel.add(titleLabel); // Add the title label to the scorecard panel
        scorecardPanel.add(Box.createVerticalStrut(50)); // Add vertical spacing

        // Display scores for player 1 and player 2
        JLabel player1ScoreLabel = new JLabel("Player 1 Score: " + game.getPlayer1().getScore()); // Label for player 1 score
        JLabel player2ScoreLabel = new JLabel("Player 2 Score: " + game.getPlayer2().getScore()); // Label for player 2 score
        player1ScoreLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Set font size and style
        player2ScoreLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Set font size and style
        player1ScoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align horizontally
        player2ScoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align horizontally
        player1ScoreLabel.setForeground(Color.YELLOW); // Set text color
        player2ScoreLabel.setForeground(Color.YELLOW); // Set text color
        scorecardPanel.add(player1ScoreLabel); // Add player 1 score label to the scorecard panel
        scorecardPanel.add(player2ScoreLabel); // Add player 2 score label to the scorecard panel

        // Determine winner and display
        JLabel winnerLabel = new JLabel(); // Label to display winner or tie
        if (game.getPlayer1().getScore() > game.getPlayer2().getScore()) {
            winnerLabel.setText("Player 1 wins!"); // Player 1 wins
        } else if (game.getPlayer1().getScore() < game.getPlayer2().getScore()) {
            winnerLabel.setText("Player 2 wins!"); // Player 2 wins
        } else {
            winnerLabel.setText("It's a tie!"); // It's a tie
        }
        winnerLabel.setFont(new Font("Arial", Font.BOLD, 30)); // Set font size and style
        winnerLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align horizontally
        winnerLabel.setForeground(Color.YELLOW); // Set text color
        scorecardPanel.add(winnerLabel); // Add winner label to the scorecard panel
        scorecardPanel.add(Box.createVerticalStrut(50)); // Add vertical spacing

        // Add "End Game" button
        JButton endGameButton = new JButton("End Game"); // Create "End Game" button
        endGameButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align horizontally
        endGameButton.setFont(new Font("Arial", Font.BOLD, 24)); // Set font size and style
        // Add action listener to "End Game" button to close the application
        endGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the application
                System.exit(0);
            }
        });
        scorecardPanel.add(endGameButton); // Add "End Game" button to the scorecard panel

        return scorecardPanel; // Return the generated scorecard panel

    }
}
