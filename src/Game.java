import javax.swing.*;
import java.awt.*;

public class Game {
    public enum Action {
        PLACE_ATOM, PLACE_RAY, GUESS_ATOM, SHOW_BOARD;
    }
    private final Player player1;
    private final Player player2;
    private Action currentAction;
    private boolean isOver;
    private boolean isRoundOver;

    private final Board board;

    public Game() {
        this.currentAction = Action.PLACE_ATOM;
        board = new Board();
        this.isOver = false;
        this.isRoundOver = false;
        this.player1 = new Player();
        this.player2 = new Player();
        player2.setSetter(false);
    }
    public void startGame() {
        // card panel that swaps to rest of game after start button is clicked
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

        // home screen panel
        JPanel homescreen = guiPlayer1.setHomeScreen();

        cardPanel.add(homescreen, "homeScreen");
        cardPanel.add(guiPlayer1, "guiPlayer1");
        cardPanel.add(guiPlayer2, "guiPlayer2");

        framePlayer1.add(cardPanel);
        framePlayer1.setSize(1500, 750);
        framePlayer1.setLocationRelativeTo(null);
        framePlayer1.setVisible(true);

        // Start player 1's turn
        player1.setScore(startRound(guiPlayer1));


        // Now, let's open GUI for player 2 after player 1's turn
        // First, dispose the JFrame for player 1
        framePlayer1.dispose();
        reset();

        // Create JFrame for player 2
        JFrame framePlayer2 = new JFrame("Player 2's Turn");
        framePlayer2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set up frame for player 2
        framePlayer2.add(cardPanel);
        framePlayer2.setSize(1500, 750);
        framePlayer2.setLocationRelativeTo(null);
        framePlayer2.setVisible(true);


        // Start player 2's turn
        player2.setScore(startRound(guiPlayer2));

        JPanel scorecardPanel = GUI.generateScorecard();
        cardPanel.add(scorecardPanel, "scorecard");
        cardLayout.show(cardPanel, "scorecard");
    }


    public int startRound(GUI gui){


        // draws empty board
        Draw.drawBoard(board);






        // place atoms
        while (board.numAtomsPlaced < 6) {
            setCurrentAction(Action.PLACE_ATOM);
            // System.out.println(board.numAtomsPlaced);

        }



        while (!isOver) {
            setCurrentAction(Action.PLACE_RAY);
           // System.out.println(board.getRays().size());
        }


        while(board.numGuessAtomsPlaced < 6) {

            setCurrentAction(Action.GUESS_ATOM);
             //System.out.println("Num atoms palced " + board.numGuessAtomsPlaced);
        }


        // show atoms
        while(!isRoundOver){
            setCurrentAction(Action.SHOW_BOARD);
        }


        return  calculateScore();

    }

    private int calculateScore(){
        int score = 0;

        score += board.numRaysPlaced;

        for(Hexagon hexagon : board.getListBoard()){
            if(hexagon.hasAtom() && !hexagon.hasAtom()){
                score += 5;
            }
        }
        return score;
    }
    private void reset(){
        System.out.println("reseting");
        this.isOver = false;
        currentAction = Action.GUESS_ATOM;
        board.numGuessAtomsPlaced = 0;
        board.numAtomsPlaced = 0;
        board.getRays().clear();
        for(Hexagon hexagon : board.getListBoard()){
            hexagon.setHasNeighbourAtom(false);
            hexagon.setContainsAtom(false);
            hexagon.setContainsGuessAtom(false);

        }
    }


    public Board getBoard(){
        return this.board;
    }

    public void setIsOver(boolean over) {
        isOver = over;
    }

    public void setIsRoundOver(boolean bool){
        isRoundOver = bool;
    }


    public void setCurrentAction(Action action) {
        currentAction = action;
    }

    public Action getCurrentAction(){
        return this.currentAction;
    }

    public Player getPlayer1(){
        return this.player1;
    }

    public Player getPlayer2(){
        return this.player2;
    }

}