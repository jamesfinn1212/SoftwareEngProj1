import javax.swing.*;
import java.awt.*;

public class Game {
    // Define actions that can be taken in the game
    public enum Action {
        PLACE_ATOM, PLACE_RAY, GUESS_ATOM, SHOW_BOARD;
    }

    // Players in the game
    private final Player player1;
    private final Player player2;

    // Current action in the game
    private Action currentAction;

    // Flags indicating whether the game is over or the round is over
    private boolean isOver;
    private boolean isRoundOver;

    // Board for the game
    private final Board board;

    // Constructor for the game
    public Game() {
        // Initialize the current action to placing atoms
        this.currentAction = Action.PLACE_ATOM;

        // Initialize the board
        board = new Board();

        // Initialize flags
        this.isOver = false;
        this.isRoundOver = false;

        // Initialize players
        this.player1 = new Player();
        this.player2 = new Player();
        player2.setSetter(false); // Player 2 starts as a guesser
    }

    // Method to start the game
    public void startGame() {
        // Create a panel with CardLayout to manage different screens
        JPanel cardPanel = new JPanel();
        CardLayout cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        // Create GUI for player 1
        GUI guiPlayer1 = new GUI(this, cardLayout, cardPanel);

        // Create GUI for player 2
        GUI guiPlayer2 = new GUI(this, cardLayout, cardPanel);

        // Create JFrame for player 1
        JFrame framePlayer1 = new JFrame("Player 1's Turn");
        framePlayer1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add home screen panel
        JPanel homescreen = guiPlayer1.setHomeScreen();
        cardPanel.add(homescreen, "homeScreen");

        // Add GUI panels for player 1 and player 2
        cardPanel.add(guiPlayer1, "guiPlayer1");
        cardPanel.add(guiPlayer2, "guiPlayer2");

        // Set up frame for player 1
        framePlayer1.add(cardPanel);
        framePlayer1.setSize(1500, 750);
        framePlayer1.setLocationRelativeTo(null);
        framePlayer1.setVisible(true);

        // Start player 1's turn
        player1.setScore(startRound(guiPlayer1));

        // Close player 1's frame
        framePlayer1.dispose();
        reset(); // Reset the game state

        // Create JFrame for player 2
        JFrame framePlayer2 = new JFrame("Player 2's Turn");
        framePlayer2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set up frame for player 2
        framePlayer2.add(cardPanel);
        framePlayer2.setSize(1500, 750);
        framePlayer2.setLocationRelativeTo(null);
        framePlayer2.setVisible(true);

        // Start player 2's turn
        player1.setSetter(false); // Player 1 is no longer the setter
        player2.setScore(startRound(guiPlayer2));

        // Display the scorecard
        JPanel scorecardPanel = GUI.generateScorecard();
        cardPanel.add(scorecardPanel, "scorecard");
        cardLayout.show(cardPanel, "scorecard");
    }

    // Method to start a round
    public int startRound(GUI gui) {
        // Draw the empty board
        Draw.drawBoard(board);

        // Place atoms
        while (board.numAtomsPlaced < 6) {
            setCurrentAction(Action.PLACE_ATOM);
        }

        // Place rays
        while (!isOver) {
            setCurrentAction(Action.PLACE_RAY);
        }

        // Guess atoms
        while (board.numGuessAtomsPlaced < 6) {
            setCurrentAction(Action.GUESS_ATOM);
        }

        // Show the board
        while (!isRoundOver) {
            setCurrentAction(Action.SHOW_BOARD);
        }

        // Calculate and return the score
        return calculateScore();
    }

    // Method to calculate the score
    private int calculateScore() {
        int score = 0;

        // Score based on the number of rays
        score += board.getRays().size();

        // Score based on correct guesses of atoms
        for (Hexagon hexagon : board.getListBoard()) {
            if (hexagon.hasAtom() && !hexagon.hasGuessAtom()) {
                score += 5;
            }
        }

        return score;
    }

    // Method to reset the game state
    private void reset() {
        this.isOver = false;
        this.isRoundOver = false;
        currentAction = Action.GUESS_ATOM;
        board.numGuessAtomsPlaced = 0;
        board.numAtomsPlaced = 0;
        board.getRays().clear();

        // Reset each hexagon on the board
        for (Hexagon hexagon : board.getListBoard()) {
            hexagon.setNumNeighbourAtom(0);
            hexagon.setContainsAtom(false);
            hexagon.setContainsGuessAtom(false);
            hexagon.getDirectionsOfInfluence().clear();
        }
    }

    // Getter method for the board
    public Board getBoard() {
        return this.board;
    }

    // Setter method for the isOver flag
    public void setIsOver(boolean over) {
        isOver = over;
    }

    // Setter method for the isRoundOver flag
    public void setIsRoundOver(boolean bool) {
        isRoundOver = bool;
    }

    // Setter method for the current action
    public void setCurrentAction(Action action) {
        currentAction = action;
    }

    // Getter method for the current action
    public Action getCurrentAction() {
        return this.currentAction;
    }

    // Getter method for player 1
    public Player getPlayer1() {
        return this.player1;
    }

    // Getter method for player 2
    public Player getPlayer2() {
        return this.player2;
    }

    // Getter method for the current setter (player with the setter role)
    public Player getCurrentSetter() {
        return player1.isSetter() ? player1 : player2;
    }
}