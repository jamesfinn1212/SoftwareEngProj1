
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

    @Test
    void testAbsorbedFromSide(){
        Board board = new Board();
        board.addAtom(-2, -4);
        board.addAtom(4, 2);
        Ray ray1 = new Ray(board, board.findStartHexagon(23), board.findDirection(23));
        String s = "[]";
        assertEquals(s, ray1.toString());

        ray1 = new Ray(board, board.findStartHexagon(24), board.findDirection(24));
        s = "[]";
        assertEquals(s, ray1.toString());

        ray1 = new Ray(board, board.findStartHexagon(25), board.findDirection(25));
        s = "[(-1, -4)]";
        assertEquals(s, ray1.toString());

        ray1 = new Ray(board, board.findStartHexagon(26), board.findDirection(26));
        s = "[(-1, -4)]";
        assertEquals(s, ray1.toString());

        ray1 = new Ray(board, board.findStartHexagon(21), board.findDirection(21));
        s = "[(-3, -4)]";
        assertEquals(s, ray1.toString());

        ray1 = new Ray(board, board.findStartHexagon(22), board.findDirection(22));
        s = "[(-3, -4)]";
        assertEquals(s, ray1.toString());



    }
    @Test
    void testInfluencedFrom1AtomAbsorbed(){

        //testing to be absorbed
        Board board = new Board();
        board.addAtom(0, 0);
        //from northwest
        Ray ray1 = new Ray(board, board.findStartHexagon(19), board.findDirection(19));
        String s = "[(-4, -4), (-3, -3), (-2, -2), (-1, -1)]";
        assertEquals(s, ray1.toString());
        //from north-east
        ray1 = new Ray(board, board.findStartHexagon(28), board.findDirection(28));
        s = "[(0, -4), (0, -3), (0, -2), (0, -1)]";
        assertEquals(s, ray1.toString());
        //from east
        ray1 = new Ray(board, board.findStartHexagon(10), board.findDirection(10));
        s = "[(-4, 0), (-3, 0), (-2, 0), (-1, 0)]";
        assertEquals(s, ray1.toString());
        //from west
        ray1 = new Ray(board, board.findStartHexagon(37), board.findDirection(37));
        s = "[(4, 0), (3, 0), (2, 0), (1, 0)]";
        assertEquals(s, ray1.toString());
        //from south-east
        ray1 = new Ray(board, board.findStartHexagon(1), board.findDirection(1));
        s = "[(0, 4), (0, 3), (0, 2), (0, 1)]";
        assertEquals(s, ray1.toString());
        //from south-west
        ray1 = new Ray(board, board.findStartHexagon(46), board.findDirection(46));
        s = "[(4, 4), (3, 3), (2, 2), (1, 1)]";
        assertEquals(s, ray1.toString());

    }

    @Test
    void testInfluencedFrom1Atom120(){

        //testing to be absorbed
        Board board = new Board();
        board.addAtom(0, 0);
        //from northwest
        Ray ray1 = new Ray(board, board.findStartHexagon(17), board.findDirection(17));
        String s = "[(-4, -3), (-3, -2), (-2, -1), (-1, 0), (-1, 1), (-1, 2), (-1, 3)]";
        assertEquals(s, ray1.toString());
        //from north-east
        ray1 = new Ray(board, board.findStartHexagon(30), board.findDirection(30));
        s = "[(1, -3), (1, -2), (1, -1), (1, 0), (2, 1), (3, 2), (4, 3)]";
        assertEquals(s, ray1.toString());
        //from east
        ray1 = new Ray(board, board.findStartHexagon(3), board.findDirection(3));
        s = "[(-1, 3), (-1, 2), (-1, 1), (-1, 0), (-2, -1), (-3, -2), (-4, -3)]";
        assertEquals(s, ray1.toString());
        //from west
        ray1 = new Ray(board, board.findStartHexagon(44), board.findDirection(44));
        s = "[(4, 3), (3, 2), (2, 1), (1, 0), (1, -1), (1, -2), (1, -3)]";
        assertEquals(s, ray1.toString());
        //from south-east
        ray1 = new Ray(board, board.findStartHexagon(53), board.findDirection(53));
        s = "[(1, 4), (1, 3), (1, 2), (1, 1), (2, 1), (3, 1), (4, 1)]";
        assertEquals(s, ray1.toString());
        //from south-west
        ray1 = new Ray(board, board.findStartHexagon(48), board.findDirection(48));
        s = "[(3, 4), (2, 3), (1, 2), (0, 1), (-1, 1), (-2, 1), (-3, 1)]";
        assertEquals(s, ray1.toString());
        ray1 = new Ray(board, board.findStartHexagon(26), board.findDirection(26));
        s = "[(-1, -4), (-1, -3), (-1, -2), (-1, -1), (-2, -1), (-3, -1), (-4, -1)]";
        assertEquals(s, ray1.toString());
        //from south-west
        ray1 = new Ray(board, board.findStartHexagon(21), board.findDirection(21));
        s = "[(-3, -4), (-2, -3), (-1, -2), (0, -1), (1, -1), (2, -1), (3, -1)]";
        assertEquals(s, ray1.toString());

    }

    @Test
    void testInfluencedFrom2Atom60(){

        //testing cases for when it is absorbed
        Board board = new Board();
        board.addAtom(0, 0);
        board.addAtom(1, 1);
        //
        Ray ray1 = new Ray(board, board.findStartHexagon(30), board.findDirection(30));
        String s = "[(1, -3), (1, -2), (1, -1), (1, 0), (2, 0), (3, 0), (4, 0)]";
        assertEquals(s, ray1.toString());

        ray1 = new Ray(board, board.findStartHexagon(37), board.findDirection(37));
        s = "[(4, 0), (3, 0), (2, 0), (1, 0), (1, -1), (1, -2), (1, -3)]";
        assertEquals(s, ray1.toString());

        ray1 = new Ray(board, board.findStartHexagon(8), board.findDirection(8));
        s = "[(-3, 1), (-2, 1), (-1, 1), (0, 1), (0, 2), (0, 3), (0, 4)]";
        assertEquals(s, ray1.toString());

        ray1 = new Ray(board, board.findStartHexagon(1), board.findDirection(1));
        s = "[(0, 4), (0, 3), (0, 2), (0, 1), (-1, 1), (-2, 1), (-3, 1)]";
        assertEquals(s, ray1.toString());


    }

    @Test
    void testInfluencedFrom2Atom180(){

        //testing cases for when it is absorbed
        Board board = new Board();
        board.addAtom(-1, 0);
        board.addAtom(1, 1);
        //
        Ray ray1 = new Ray(board, board.findStartHexagon(28), board.findDirection(28));
        String s = "[(0, -4), (0, -3), (0, -2), (0, -1), (0, 0), (0, -1), (0, -2), (0, -3), (0, -4)]";
        assertEquals(s, ray1.toString());

        ray1 = new Ray(board, board.findStartHexagon(1), board.findDirection(1));
        s = "[(0, 4), (0, 3), (0, 2), (0, 1), (0, 2), (0, 3), (0, 4)]";
        assertEquals(s, ray1.toString());


    }
    @Test
    void testInfluencedFrom3Atom180(){

        //testing cases for when it is absorbed
        Board board = new Board();
        board.addAtom(-1, 0);
        board.addAtom(1, 1);
        board.addAtom(0,1);
        //
        Ray ray1 = new Ray(board, board.findStartHexagon(28), board.findDirection(28));
        String s = "[(0, -4), (0, -3), (0, -2), (0, -1), (0, 0), (0, -1), (0, -2), (0, -3), (0, -4)]";
        assertEquals(s, ray1.toString());


        Board board1 = new Board();
        board1.addAtom(-1, 0);
        board1.addAtom(1, 1);
        board1.addAtom(0,0);
        ray1 = new Ray(board1, board1.findStartHexagon(1), board1.findDirection(1));
        s = "[(0, 4), (0, 3), (0, 2), (0, 1), (0, 2), (0, 3), (0, 4)]";
        assertEquals(s, ray1.toString());



    }

}

