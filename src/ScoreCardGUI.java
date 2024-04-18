import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScoreCardGUI {

    private final Game game;
    public ScoreCardGUI(Game game) {

        this.game = game;
    }

    public JPanel generateScorecard() {
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
            winnerLabel.setText("Player 2 wins!");
        } else if (game.getPlayer1().getScore() < game.getPlayer2().getScore()) {
            winnerLabel.setText("Player 1 wins!");
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

}
