import javax.swing.*;
import java.awt.*;

public class Game {
    public enum Action {
        PLACE_ATOM, PLACE_RAY, Guess_Atom;
    }
    private Player player1;
    private Player player2;
    private Action currentAction;
    private boolean isOver;
    boolean drawGuessAtoms = false;
    private Board board;

    public Game() {
        this.currentAction = Action.PLACE_ATOM;
        board = new Board();
        this.isOver = false;
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



        // Create JFrame for player 1
        JFrame framePlayer1 = new JFrame("Player 1's Turn");
        framePlayer1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*
        framePlayer1.add(guiPlayer1);
        framePlayer1.setSize(1500, 750);
        framePlayer1.setLocationRelativeTo(null);
        framePlayer1.setVisible(true);

         */


        // home screen panel
        JPanel homescreen = guiPlayer1.setHomeScreen();

        cardPanel.add(homescreen, "homeScreen");
        cardPanel.add(guiPlayer1, "guiPlayer1");

        framePlayer1.add(cardPanel);
        framePlayer1.setSize(1500, 750);
        framePlayer1.setLocationRelativeTo(null);
        framePlayer1.setVisible(true);




        // Start player 1's turn
        player1.setScore(startRound(guiPlayer1));

        // Close JFrame for player 1 after their turn
        framePlayer1.dispose();

        // Create GUI for player 2
        GUI guiPlayer2 = new GUI(this, cardLayout, cardPanel);

        // Create JFrame for player 2
        JFrame framePlayer2 = new JFrame("Player 2's Turn");
        framePlayer2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        framePlayer2.add(guiPlayer2);
        framePlayer2.setSize(1500, 750);
        framePlayer2.setLocationRelativeTo(null);
        framePlayer2.setVisible(true);

        // Start player 2's turn
        player2.setScore(startRound(guiPlayer2));

        // Close JFrame for player 2 after their turn
        framePlayer2.dispose();
    }



    public int startRound(GUI gui){


        // draws empty board
        Draw.drawBoard(board);






            // place atoms
            while (board.numAtomsPlaced < 6) {
                setCurrentAction(Action.PLACE_ATOM);
               // System.out.println(board.numAtomsPlaced);
                //  Draw.drawBoard(board1);
            }


            // hide atoms
            board.hideAtoms();
            board.isHexCoordVisible = false;
            board.isArrowsVisible =true;
            board.isHexSideNumVisible = true;
            setCurrentAction(Action.PLACE_RAY);
            while (!isOver) {

                 System.out.println(board.getRays().size());
            }

        setCurrentAction(Action.Guess_Atom);
            while(board.numGuessAtomsPlaced < 6) {
                drawGuessAtoms = true;
                setCurrentAction(Action.Guess_Atom);
              //  System.out.println(board.numGuessAtomsPlaced);
            }
            drawGuessAtoms = false;

            // show atoms
            board.showAtoms();

            return  calculateScore();

    }

    private int calculateScore(){
        int score = 0;

        score += board.numRaysPlaced;

        for(Hexagon hexagon : board.getListBoard()){
            if(hexagon.hasAtom() && !hexagon.isHasGuessAtom()){
                score += 5;
            }
        }
        return score;
    }

    public Board getBoard(){
        return this.board;
    }

    public void setIsOver(boolean over) {
        isOver = over;
    }

    public void setCurrentAction(Action action) {
        currentAction = action;
    }

    public Action getCurrentAction(){
        return this.currentAction;
    }

}