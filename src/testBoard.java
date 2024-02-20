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
        //sidepiece
        board1.addAtom(4, -3);
        assertTrue(board1.getListHexagon(4, -3).hasAtom());
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


    //should be moved to separate game test
    @Test
    public void testInvalidInputs(){
        Game game1 = new Game();

        try{
            game1.validateAtom(5, 0);
        }catch(IllegalArgumentException ex){
            assertTrue(ex.getMessage() != null);
        }
        try{
            game1.validateAtom(-5, 0);
        }catch(IllegalArgumentException ex){
            assertTrue(ex.getMessage() != null);
        }
        try{
            game1.validateAtom(6, 4);
        }catch(IllegalArgumentException ex){
            assertTrue(ex.getMessage() != null);
        }
        try{
            game1.validateAtom(-5, -5);
        }catch(IllegalArgumentException ex){
            assertTrue(ex.getMessage() != null);
        }
    }


}
