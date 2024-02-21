import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class testGame {

    @Test
    public void testInvalidInputs(){
        Game game1 = new Game();
        Board board = new Board();


        try{
            game1.validateAtom(5, 0, board);
        }catch(IllegalArgumentException ex){
            assertTrue(ex.getMessage() != null);
        }
        try{
            game1.validateAtom(-5, 0, board);
        }catch(IllegalArgumentException ex){
            assertTrue(ex.getMessage() != null);
        }
        try{
            game1.validateAtom(6, 4, board);
        }catch(IllegalArgumentException ex){
            assertTrue(ex.getMessage() != null);
        }
        try{
            game1.validateAtom(-4, 1, board);
        }catch(IllegalArgumentException ex){
            assertTrue(ex.getMessage() != null);
        }
        try{
            game1.validateAtom(-3, 3, board);
        }catch(IllegalArgumentException ex){
            assertTrue(ex.getMessage() != null);
        }

        try{
            game1.validateAtom(3, -2, board);
        }catch(IllegalArgumentException ex){
            assertTrue(ex.getMessage() != null);
        }

        board.addAtom(1, 1);
        board.addAtom(-4, 0);
        board.addAtom(0, 0);
        board.addAtom(2,3);
        board.addAtom(4, 0);
        try{
            game1.validateAtom(1, 1, board);
        }catch(IllegalArgumentException ex){
            assertTrue(ex.getMessage() != null);
        }
        try{
            game1.validateAtom(-4, 0, board);
        }catch(IllegalArgumentException ex){
            assertTrue(ex.getMessage() != null);
        }
        try{
            game1.validateAtom(0, 0, board);
        }catch(IllegalArgumentException ex){
            assertTrue(ex.getMessage() != null);
        }
        try{
            game1.validateAtom(2, 3, board);
        }catch(IllegalArgumentException ex){
            assertTrue(ex.getMessage() != null);
        }
        try{
            game1.validateAtom(4, 0, board);
        }catch(IllegalArgumentException ex){
            assertTrue(ex.getMessage() != null);
        }
    }
}
