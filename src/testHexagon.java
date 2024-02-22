import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class testHexagon {

    @Test
    void testDeclare() { // testing hexagon methods

        // test declaring 2 hexagons
        Hexagon h = new Hexagon(2, 1);
        String expected = "(2, 1)";
        assertEquals(expected, h.toString());

        Hexagon h2 = new Hexagon(3, -1);
        expected = "(3, -1)";
        assertEquals(expected, h2.toString());

    }

    @Test
    void testAddingAtoms() {

        // Test adding atoms and circle of influence
        Board board1 = new Board();
        board1.addAtom(2, 3);// add atom to the board
        Hexagon hexagon1 = board1.getListHexagon(2, 3);

        // Test to see if the atom was added
        assertTrue(hexagon1.hasAtom());

        int x = hexagon1.getX();
        int y = hexagon1.getY();

        // test for circle of influence
        //checks all surrounding hexagons if they exist and if they do test if they have influence
        if (board1.getListHexagon(x + 1, y) != null) {
            assertTrue(board1.getListHexagon(x + 1, y).hasInfluence());
        }
        if (board1.getListHexagon(x, y - 1) != null) {
            assertTrue(board1.getListHexagon(x, y - 1).hasInfluence());
        }
        if (board1.getListHexagon(x - 1, y - 1) != null) {
            assertTrue(board1.getListHexagon(x - 1, y - 1).hasInfluence());
        }
        if (board1.getListHexagon(x - 1, y) != null) {
            assertTrue(board1.getListHexagon(x - 1, y).hasInfluence());
        }
        if (board1.getListHexagon(x, y + 1) != null) {
            assertTrue(board1.getListHexagon(x, y + 1).hasInfluence());
        }
        if (board1.getListHexagon(x + 1, y + 1) != null) {
            assertTrue(board1.getListHexagon(x + 1, y + 1).hasInfluence());
        }

        // Test to see if the atom was added
        assertTrue(hexagon1.hasAtom());

        // test the same for another set of co-ordinates (-3, -2)
        Board board2 = new Board();

        board2.addAtom(-3, -2);// add atom to the board
        Hexagon hexagon2 = board2.getListHexagon(-3, -2);

        // Test to see if the atom was added
        assertTrue(hexagon2.hasAtom());

        x = hexagon2.getX();
        y = hexagon2.getY();

        // test for circle of influence
        //checks all surrounding hexagons if they exist and if they do test if they have influence
        if (board2.getListHexagon(x + 1, y) != null) {
            assertTrue(board2.getListHexagon(x + 1, y).hasInfluence());
        }
        if (board2.getListHexagon(x, y - 1) != null) {
            assertTrue(board2.getListHexagon(x, y - 1).hasInfluence());
        }
        if (board2.getListHexagon(x - 1, y - 1) != null) {
            assertTrue(board2.getListHexagon(x - 1, y - 1).hasInfluence());
        }
        if (board2.getListHexagon(x - 1, y) != null) {
            assertTrue(board2.getListHexagon(x - 1, y).hasInfluence());
        }
        if (board2.getListHexagon(x, y + 1) != null) {
            assertTrue(board2.getListHexagon(x, y + 1).hasInfluence());
        }
        if (board2.getListHexagon(x + 1, y + 1) != null) {
            assertTrue(board2.getListHexagon(x + 1, y + 1).hasInfluence());
        }

    }

    @Test
    void testIsSide() {

        // Testing "isSide" method

        // Testing 4 side hexagons to see if they are on the side
        Hexagon sideHexagon1 = new Hexagon(1, 4);
        Hexagon sideHexagon2 = new Hexagon(4, 4);
        Hexagon sideHexagon3 = new Hexagon(1, -3);
        Hexagon sideHexagon4 = new Hexagon(-4, -1);
        assertTrue(sideHexagon1.isSide());
        assertTrue(sideHexagon2.isSide());
        assertTrue(sideHexagon3.isSide());
        assertTrue(sideHexagon4.isSide());

        // Testing 4 hexagons not on the side
        Hexagon middleHexagon1 = new Hexagon(1, 2);
        Hexagon middleHexagon2 = new Hexagon(0, 1);
        Hexagon middleHexagon3 = new Hexagon(-1, -2);
        Hexagon middleHexagon4 = new Hexagon(-2, 1);
        assertFalse(middleHexagon1.isSide());
        assertFalse(middleHexagon2.isSide());
        assertFalse(middleHexagon3.isSide());
        assertFalse(middleHexagon4.isSide());


    }

}
