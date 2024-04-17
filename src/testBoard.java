import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
public class testBoard {
    //test for string and arraylist;
    @Test
    public void testAddAtom(){
        Board board1 = new Board();
        //test origin
        board1.addAtom(0, 0);
        assertTrue(board1.getListHexagon(0, 0).hasAtom());

        // zero positive number
        board1.addAtom(0, 1);
        assertTrue(board1.getListHexagon(0, 1).hasAtom());

        // positive number zero
        board1.addAtom(1, 0);
        assertTrue(board1.getListHexagon(0, 1).hasAtom());
        //positive numbers
        board1.addAtom(4, 4);
        assertTrue(board1.getListHexagon(4, 4).hasAtom());
        //sidepieces

        //negative and zero
        board1.addAtom(-1, 0);
        assertTrue(board1.getListHexagon(-1, 0).hasAtom());
        //zero and negative number
        board1.addAtom(0,-1);
        assertTrue(board1.getListHexagon(0, -1).hasAtom());
        //negative and negative
        board1.addAtom(-4,-4);
        assertTrue(board1.getListHexagon(-4, -4).hasAtom());

        Board board2 = new Board();


        board2.addAtom(0, 1);
        board2.addAtom(0, 0);
        board2.addAtom(3, 2);
        board2.addAtom(-1, 3);
        // check if atoms that don't contain an atom have an atom
        assertFalse(board2.getListHexagon(0, 2).hasAtom());
        assertFalse(board2.getListHexagon(0, 4).hasAtom());
        assertFalse(board2.getListHexagon(-3, -4).hasAtom());
        assertFalse(board2.getListHexagon(0, -4).hasAtom());
        assertFalse(board2.getListHexagon(3, 1).hasAtom());
        assertFalse(board2.getListHexagon(4, 1).hasAtom());




    }



    @Test
    public void testAddCircleOfInfluence(){
//adding atoms to board and checking if surrounding atoms have influence
        Board board1 = new Board();
        board1.addAtom(0,0);
        assertTrue(board1.getNextHexagon(board1.getListHexagon(0, 0),
                Board.Direction.NORTHEAST).hasInfluence());
        assertTrue(board1.getNextHexagon(board1.getListHexagon(0, 0),
                Board.Direction.NORTHWEST).hasInfluence());
        assertTrue(board1.getNextHexagon(board1.getListHexagon(0, 0),
                Board.Direction.WEST).hasInfluence());
        assertTrue(board1.getNextHexagon(board1.getListHexagon(0, 0),
                Board.Direction.SOUTHWEST).hasInfluence());
        assertTrue(board1.getNextHexagon(board1.getListHexagon(0, 0),
                Board.Direction.SOUTHEAST).hasInfluence());
        assertTrue(board1.getNextHexagon(board1.getListHexagon(0, 0),
                Board.Direction.EAST).hasInfluence());

        board1.addAtom(0, 1);

        assertTrue(board1.getNextHexagon(board1.getListHexagon(0, 1),
                Board.Direction.NORTHEAST).hasInfluence());
        assertTrue(board1.getNextHexagon(board1.getListHexagon(0, 1),
                Board.Direction.NORTHWEST).hasInfluence());
        assertTrue(board1.getNextHexagon(board1.getListHexagon(0, 1),
                Board.Direction.WEST).hasInfluence());
        assertTrue(board1.getNextHexagon(board1.getListHexagon(0, 1),
                Board.Direction.SOUTHWEST).hasInfluence());
        assertTrue(board1.getNextHexagon(board1.getListHexagon(0, 1),
                Board.Direction.SOUTHEAST).hasInfluence());
        assertTrue(board1.getNextHexagon(board1.getListHexagon(0, 1),
                Board.Direction.EAST).hasInfluence());

        Board board2 = new Board();
        board2.addAtom(-2,-4);

        assertTrue(board1.getNextHexagon(board2.getListHexagon(0, 1),
                Board.Direction.NORTHWEST).hasInfluence());
        assertTrue(board1.getNextHexagon(board2.getListHexagon(0, 1),
                Board.Direction.NORTHEAST).hasInfluence());
        assertTrue(board1.getNextHexagon(board2.getListHexagon(0, 1),
                Board.Direction.EAST).hasInfluence());


        Board board3 = new Board();
        board3.addAtom(4, 4);
        assertTrue(board1.getNextHexagon(board2.getListHexagon(0, 1),
                Board.Direction.WEST).hasInfluence());
        assertTrue(board1.getNextHexagon(board2.getListHexagon(0, 1),
                Board.Direction.SOUTHEAST).hasInfluence());
        assertTrue(board1.getNextHexagon(board2.getListHexagon(0, 1),
                Board.Direction.SOUTHWEST).hasInfluence());

        Board board4 = new Board();
        board4.addAtom(0,0);

        assertFalse(board4.getListHexagon(-1, 1).hasInfluence());
        assertFalse(board4.getListHexagon(0, 2).hasInfluence());
        assertFalse(board4.getListHexagon(1, 2).hasInfluence());
        assertFalse(board4.getListHexagon(2, 2).hasInfluence());
        assertFalse(board4.getListHexagon(2, 1).hasInfluence());
        assertFalse(board4.getListHexagon(2, 0).hasInfluence());
        assertFalse(board4.getListHexagon(1, -1).hasInfluence());
        assertFalse(board4.getListHexagon(0, -2).hasInfluence());
        assertFalse(board4.getListHexagon(-1, -2).hasInfluence());
        assertFalse(board4.getListHexagon(-2, -2).hasInfluence());
        assertFalse(board4.getListHexagon(-2, -1).hasInfluence());
        assertFalse(board4.getListHexagon(-2, 0).hasInfluence());



    }

    @Test
    public void testFindDirection(){

        //finds direction depending on number for input
        Board board = new Board();
        assertEquals(Board.Direction.SOUTHEAST, board.findDirection(7));
        assertEquals(Board.Direction.EAST, board.findDirection(2));
        assertEquals(Board.Direction.EAST, board.findDirection(16));
        assertEquals(Board.Direction.NORTHEAST, board.findDirection(15));
        assertEquals(Board.Direction.NORTHEAST, board.findDirection(21));
        assertEquals(Board.Direction.NORTHWEST, board.findDirection(22));
        assertEquals(Board.Direction.WEST, board.findDirection(29));
        assertEquals(Board.Direction.NORTHWEST, board.findDirection(36));
        assertEquals(Board.Direction.SOUTHWEST, board.findDirection(42));
        assertEquals(Board.Direction.WEST, board.findDirection(41));
        assertEquals(Board.Direction.SOUTHEAST, board.findDirection(49));
        assertEquals(Board.Direction.SOUTHWEST, board.findDirection(48));

        assertEquals(Board.Direction.SOUTHWEST, board.findDirection(46));
        assertEquals(Board.Direction.NORTHWEST, board.findDirection(28));
        assertEquals(Board.Direction.SOUTHWEST, board.findDirection(38));
        assertEquals(Board.Direction.SOUTHEAST, board.findDirection(9));
        assertEquals(Board.Direction.NORTHEAST, board.findDirection(11));
        assertEquals(Board.Direction.NORTHWEST, board.findDirection(20));

    }

    @Test
    public void testFindStartHexagon() {
        Board board = new Board();

        assertEquals(board.getListHexagon(0,4),board.findStartHexagon(2));
        assertEquals(board.getListHexagon(-3,1),board.findStartHexagon(7));
        assertEquals(board.getListHexagon(-4,0),board.findStartHexagon(11));
        assertEquals(board.getListHexagon(-4,-3),board.findStartHexagon(16));
        assertEquals(board.getListHexagon(-4,-4),board.findStartHexagon(18));
        assertEquals(board.getListHexagon(-4,-4),board.findStartHexagon(20));
        assertEquals(board.getListHexagon(-1,-4),board.findStartHexagon(26));
        assertEquals(board.getListHexagon(0,-4),board.findStartHexagon(29));
        assertEquals(board.getListHexagon(3,-1),board.findStartHexagon(35));
        assertEquals(board.getListHexagon(4,0),board.findStartHexagon(37));
        assertEquals(board.getListHexagon(4,1),board.findStartHexagon(40));
        assertEquals(board.getListHexagon(4,4),board.findStartHexagon(45));
        assertEquals(board.getListHexagon(4,4),board.findStartHexagon(47));
        assertEquals(board.getListHexagon(2,4),board.findStartHexagon(50));
        assertEquals(board.getListHexagon(1,4),board.findStartHexagon(52));
        assertEquals(board.getListHexagon(0,4),board.findStartHexagon(54));

    }





}