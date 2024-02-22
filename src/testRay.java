
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class testRay {
    @Test
    void testToString() {
        Board board = new Board();

        // before atom placed on board, ray should go all the way through from -2,2 in the east direction
        Ray ray1 = new Ray(board, board.getListHexagon(-2, 2), Board.Direction.EAST);
        String ray1ExpectedPath = "[(-2, 2), (-1, 2), (0, 2), (1, 2), (2, 2), (3, 2), (4, 2)]";
        assertEquals(ray1ExpectedPath, ray1.toString());

    }

    @Test
    void testAbsorption() {

        Board board = new Board();
        board.addAtom(2, 2); // atom added at (2,2)

        // this ray should get absorbed by the atom at (2,2), therefore should stop right behind
        Ray ray2 = new Ray(board, board.getListHexagon(-2, 2), Board.Direction.EAST);
        String ray2ExpectedPath = "[(-2, 2), (-1, 2), (0, 2), (1, 2)]";
        assertEquals(ray2ExpectedPath, ray2.toString());

        // This ray is coming at (2,2) from different direction, should still get absorbed
        Ray ray3 = new Ray(board, board.getListHexagon(-4, -4), Board.Direction.NORTHEAST);
        String ray3ExpectedPath = "[(-4, -4), (-3, -3), (-2, -2), (-1, -1), (0, 0), (1, 1)]";
        assertEquals(ray3ExpectedPath, ray3.toString());

    }

    @Test
    void testExceptions() {
        Board board = new Board();

        // test for exception if a ray not on side is chosen
        boolean thrown = false;
        try {
            Ray ray4 = new Ray(board, board.getListHexagon(2, 2), Board.Direction.SOUTHEAST);
        } catch (IllegalArgumentException ex) { thrown = true; }
        assertTrue(thrown);

        // another example
        thrown = false;
        try {
            Ray ray5 = new Ray(board, board.getListHexagon(-1, -3), Board.Direction.SOUTHEAST);
        } catch (IllegalArgumentException ex) { thrown = true; }
        assertTrue(thrown);

        // test for exception if a null ray is chosen
        thrown = false;
        try {
            Ray ray6 = new Ray(board, board.getListHexagon(-50, -21), Board.Direction.SOUTHEAST);
        } catch (NullPointerException ex) { thrown = true; }
        assertTrue(thrown);

    }

}

