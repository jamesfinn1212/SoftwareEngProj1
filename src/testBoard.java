import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
public class testBoard {
    //test for string and arraylist;
    @Test
    public void testPlaceAtom(){
        Board board1 = new Board();
        //test origin
        board1.addAtom(31);
        assertTrue(board1.getListHexagon(0, 0).hasAtom());

        // zero positive number
        board1.addAtom(22);
        assertTrue(board1.getListHexagon(0, 1).hasAtom());

        // positive number zero
        board1.addAtom(32);
        assertTrue(board1.getListHexagon(0, 1).hasAtom());
        //positive numbers
        board1.addAtom(5);
        assertTrue(board1.getListHexagon(4, 4).hasAtom());
        //sidepieces

        //negative and zero
        board1.addAtom(30);
        assertTrue(board1.getListHexagon(-1, 0).hasAtom());
        //zero and negative number
        board1.addAtom(40);
        assertTrue(board1.getListHexagon(0, -1).hasAtom());
        //negative and negative
        board1.addAtom(57);
        assertTrue(board1.getListHexagon(-4, -4).hasAtom());

        Board board2 = new Board();


        board2.addAtom(22);
        board2.addAtom(31);
        board2.addAtom(17);
        board2.addAtom(6);
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
        board1.addAtom(31);
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

        board1.addAtom(22);

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
        board2.addAtom(59);

        assertTrue(board1.getNextHexagon(board2.getListHexagon(0, 1),
                Board.Direction.NORTHWEST).hasInfluence());
        assertTrue(board1.getNextHexagon(board2.getListHexagon(0, 1),
                Board.Direction.NORTHEAST).hasInfluence());
        assertTrue(board1.getNextHexagon(board2.getListHexagon(0, 1),
                Board.Direction.EAST).hasInfluence());


        Board board3 = new Board();
        board3.addAtom(5);
        assertTrue(board1.getNextHexagon(board2.getListHexagon(0, 1),
                Board.Direction.WEST).hasInfluence());
        assertTrue(board1.getNextHexagon(board2.getListHexagon(0, 1),
                Board.Direction.SOUTHEAST).hasInfluence());
        assertTrue(board1.getNextHexagon(board2.getListHexagon(0, 1),
                Board.Direction.SOUTHWEST).hasInfluence());

        Board board4 = new Board();
        board4.addAtom(31);

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

    @Test
    public void testHideAtom(){
        //draw an empty board
        Board board1 = new Board();
        //draw another board
        Board board = new Board();
        //populate board
        board.addAtom(23);
        board.addAtom(45);
        board.addAtom(5);
        //hide atoms for this board
        board.hideAtoms();




       assertTrue(Arrays.equals(board.getStringBoard(), board1.getStringBoard()));

    }



}