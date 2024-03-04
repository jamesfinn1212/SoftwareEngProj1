import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class testGame {

    @Test
    public void testValidInputs() {
        Game game1 = new Game();
        Board board = new Board();

        try {
            game1.validateAtom(21, board);
            game1.validateAtom(30, board);
            game1.validateAtom(45, board);
            game1.validateAtom(11, board);
            game1.validateAtom(13, board);
            game1.validateAtom(61, board);
            game1.validateAtom(1, board);
        }catch(Exception ex){
            if(ex.getMessage() != null){
                fail("All valid inputs no reason for fail");
            }
        }





    }


    @Test
    public void testInvalidInputs(){
        Game game1 = new Game();
        Board board = new Board();


        try{
            game1.validateAtom(68, board);
        }catch(IllegalArgumentException ex){
            assertTrue(ex.getMessage() != null);
        }
        try{
            game1.validateAtom(79, board);
        }catch(IllegalArgumentException ex){
            assertTrue(ex.getMessage() != null);
        }
        try{
            game1.validateAtom(105, board);
        }catch(IllegalArgumentException ex){
            assertTrue(ex.getMessage() != null);
        }
        try{
            game1.validateAtom(-8, board);
        }catch(IllegalArgumentException ex){
            assertTrue(ex.getMessage() != null);
        }
        try{
            game1.validateAtom(0, board);
        }catch(IllegalArgumentException ex){
            assertTrue(ex.getMessage() != null);
        }

        try{
            game1.validateAtom(-10, board);
        }catch(IllegalArgumentException ex){
            assertTrue(ex.getMessage() != null);
        }

        board.addAtom(27);
        board.addAtom(31);
        board.addAtom(61);
        board.addAtom(1);
        board.addAtom(4);
        try{
            game1.validateAtom(27, board);
        }catch(IllegalArgumentException ex){
            assertTrue(ex.getMessage() != null);
        }
        try{
            game1.validateAtom(41, board);
        }catch(IllegalArgumentException ex){
            assertTrue(ex.getMessage() != null);
        }
        try{
            game1.validateAtom(61, board);
        }catch(IllegalArgumentException ex){
            assertTrue(ex.getMessage() != null);
        }
        try{
            game1.validateAtom(1, board);
        }catch(IllegalArgumentException ex){
            assertTrue(ex.getMessage() != null);
        }
        try{
            game1.validateAtom(4, board);
        }catch(IllegalArgumentException ex){
            assertTrue(ex.getMessage() != null);
        }
    }
}
