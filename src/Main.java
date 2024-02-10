import io.Input;
import io.Output;
import java.util.List;
import model.Board;
import model.Game;
import model.Player;

import static model.Game.COLUMNS;
import static model.Game.ROWS;
import static model.Token.RED;
import static model.Token.YELLOW;

public class Main {
    public static void main(String[] args) {
        Player player1 = new Player("Player-1", RED);
        Player player2 = new Player("Player-2", YELLOW);

        Board board = new Board(ROWS, COLUMNS);
        Game game = new Game(Input.CONSOLE, Output.CONSOLE, board, List.of(player1, player2));

        game.play();
    }
}