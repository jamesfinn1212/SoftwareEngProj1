// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        Board board = new Board();


        System.out.println(board.getHexagonFromNumber(27));

        Game game = new Game();

        game.startGame();

        System.out.println(board.getHexagonFromNumber(31));

    }
}