import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class testGame {
    //test for board of all atoms no guesses no rays
    @Test
    void calculateScoreNORaysNoGuesses(){
        Game game = new Game();
        game.getBoard().addAtom(-3, 1);
        game.getBoard().addAtom(4, 0);
        game.getBoard().addAtom(2, 1);
        game.getBoard().addAtom(0, 0);

        assertEquals(20, game.calculateScore());

        game.getBoard().addAtom(-2, 0);
        assertEquals(25, game.calculateScore());

        game.getBoard().addAtom(4, 4);
        assertEquals(30, game.calculateScore());

    }
    @Test
    void calculateScoreNoGuesses(){
        Game game = new Game();
        game.getBoard().addAtom(-3, 1);
        game.getBoard().addAtom(4, 0);
        game.getBoard().addAtom(2, 1);
        game.getBoard().addAtom(0, 0);


        Ray ray = new Ray(game.getBoard(), game.getBoard().getListHexagon(-2, 2), Board.Direction.SOUTHEAST);
        Ray ray1 = new Ray(game.getBoard(), game.getBoard().getListHexagon(4, 2), Board.Direction.WEST);
        Ray ray2 = new Ray(game.getBoard(), game.getBoard().getListHexagon(1, 4), Board.Direction.WEST);
        assertEquals(23, game.calculateScore());

        game.getBoard().addAtom(-2, 0);
        game.getBoard().addAtom(4, 4);

        Ray ray3 = new Ray(game.getBoard(), game.getBoard().getListHexagon(-3, -4), Board.Direction.WEST);
        Ray ray4 = new Ray(game.getBoard(), game.getBoard().getListHexagon(1, -3), Board.Direction.WEST);
        Ray ray5 = new Ray(game.getBoard(), game.getBoard().getListHexagon(2, -2), Board.Direction.WEST);
        assertEquals(36, game.calculateScore());
        Ray ray6 = new Ray(game.getBoard(), game.getBoard().getListHexagon(0, 4), Board.Direction.WEST);

        assertEquals(37, game.calculateScore());


    }

    @Test
    void calculateScore(){
        Game game = new Game();
        game.getBoard().addAtom(-3, 1);
        game.getBoard().addAtom(4, 0);
        game.getBoard().addAtom(2, 1);
        game.getBoard().addAtom(0, 0);
        game.getBoard().addAtom(-2, 0);
        game.getBoard().addAtom(4, 4);


        Ray ray = new Ray(game.getBoard(), game.getBoard().getListHexagon(-2, 2), Board.Direction.SOUTHEAST);
        Ray ray1 = new Ray(game.getBoard(), game.getBoard().getListHexagon(4, 2), Board.Direction.WEST);
        Ray ray2 = new Ray(game.getBoard(), game.getBoard().getListHexagon(1, 4), Board.Direction.WEST);
        Ray ray3 = new Ray(game.getBoard(), game.getBoard().getListHexagon(-3, -4), Board.Direction.WEST);
        Ray ray4 = new Ray(game.getBoard(), game.getBoard().getListHexagon(1, -3), Board.Direction.WEST);
        Ray ray5 = new Ray(game.getBoard(), game.getBoard().getListHexagon(2, -2), Board.Direction.WEST);


        assertEquals(36, game.calculateScore());





        game.getBoard().getListHexagon(-2, 0).setContainsGuessAtom(true);

        assertEquals(31, game.calculateScore());

        game.getBoard().getListHexagon(4, 4).setContainsGuessAtom(true);
        game.getBoard().getListHexagon(-3, 1).setContainsGuessAtom(true);

        assertEquals(21, game.calculateScore());


    }



}
