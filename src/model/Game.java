package model;

import io.Input;
import io.Output;
import java.util.List;

import static java.lang.String.format;

public class Game {
    public static final int ROWS = 6;
    public static final int COLUMNS = 7;
    private final int TOTAL_PLAYERS = 2;
    private final String PLAYER_TURN_MESSAGE = "%s turn: ";
    private final String WIN_MESSAGE = "%s has won!";
    private final String LINE_BREAK = "\n";

    private final Input input;
    private final Output output;
    private final Board board;
    private final List<Player> players;

    public Game(Input input, Output output, Board board, List<Player> players) {
        this.input = input;
        this.output = output;
        this.board = board;
        this.players = players;
    }

    public void play() {
        int playerIndex = 0;
        int totalMoves = 0;

        do {
            try {
                Player currentPlayer = players.get(playerIndex);
                output.print(format(PLAYER_TURN_MESSAGE, currentPlayer.getName()));

                int column = input.nextMoveColumn();
                int rowNumber = board.playMove(currentPlayer, column);
                printBoard();

                totalMoves++;
                playerIndex = (playerIndex + 1) % TOTAL_PLAYERS;

                if (board.hasPlayerWon(currentPlayer, rowNumber, column)) {
                    output.print(format(WIN_MESSAGE, currentPlayer.getName()));
                    System.exit(0);
                }
            } catch (Exception exception) {
                output.print(exception.toString());
                output.print(LINE_BREAK);
            }
        } while (totalMoves <= ROWS * COLUMNS);
    }

    private void printBoard() {
        List<List<Token>> boardStatus = board.getBoardStatus();

        for (int row = ROWS - 1; row >= 0; row--) {
            for (int column = 0; column < COLUMNS; column++) {
                output.print(boardStatus.get(row).get(column).getRepresentation() + " ");
            }
            output.print(LINE_BREAK);
        }
    }
}
