import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class testBoard {
    //test for string and arraylist;
    @Test
    public void testPlaceAtom(){
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

        //test for middle
        //test for side
        //test for beside
        //test for outside
        //test for same
        //
    }



    @Test
    public void testAddCircleOfInfluence(){

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
    }




}