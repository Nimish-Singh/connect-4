package model;

import exception.InvalidMoveException;
import java.util.ArrayList;
import java.util.List;

import static model.Token.EMPTY;

public class Board {
    private final int CONNECT_N = 4;
    private final int rows;
    private final int columns;
    private final List<List<Token>> board;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.board = new ArrayList<>(rows);
        for (int index = 0; index < rows; index++) {
            this.board.add(index, new ArrayList<>(columns));
        }

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                board.get(row).add(EMPTY);
            }
        }
    }

    public int playMove(Player player, int column) throws InvalidMoveException {
        if (column < 0 || column >= columns) {
            throw new InvalidMoveException("Invalid column number");
        }
        for (int index = 0; index < rows; index++) {
            if (EMPTY.equals(board.get(index).get(column))) {
                board.get(index).set(column, player.getToken());
                return index;
            }
        }
        throw new InvalidMoveException("Column already full");
    }

    public boolean hasPlayerWon(Player player, int lastPieceRow, int lastPieceColumn) {
        return hasWonHorizontally(player, lastPieceRow) ||
                hasWonVertically(player, lastPieceRow, lastPieceColumn) ||
                hasWonDiagonally(player, lastPieceRow, lastPieceColumn) ||
                hasWonReverseDiagonally(player, lastPieceRow, lastPieceColumn);
    }

    private boolean hasWonHorizontally(Player player, int lastPieceRow) {
        int count = 0;
        for (int index = 0; index < columns; index++) {
            if (player.getToken().equals(board.get(lastPieceRow).get(index))) {
                count++;
            } else {
                count = 0;
            }
            if (count == CONNECT_N) {
                return true;
            }
        }
        return false;
    }

    private boolean hasWonVertically(Player player, int lastPieceRow, int lastPieceColumn) {
        int count = 0;
        for (int index = lastPieceRow; index >= 0; index--) {
            if (player.getToken().equals(board.get(index).get(lastPieceColumn))) {
                count++;
            } else {
                count = 0;
            }
            if (count == CONNECT_N) {
                return true;
            }
        }
        return false;
    }

    private boolean hasWonDiagonally(Player player, int lastPieceRow, int lastPieceColumn) {
        int count = 0;
        for (int rowIndex = 0; rowIndex < rows; rowIndex++) {
            int columnToCheck = lastPieceColumn + lastPieceRow - rowIndex;
            if (columnToCheck >= 0 && columnToCheck < columns && board.get(rowIndex).get(columnToCheck)
                    .equals(player.getToken())) {
                count++;
            } else {
                count = 0;
            }
            if (count == CONNECT_N) {
                return true;
            }
        }
        return false;
    }

    private boolean hasWonReverseDiagonally(Player player, int lastPieceRow, int lastPieceColumn) {
        int count = 0;
        for (int rowIndex = 0; rowIndex < rows; rowIndex++) {
            int columnToCheck = lastPieceColumn - lastPieceRow + rowIndex;
            if (columnToCheck >= 0 && columnToCheck < columns && board.get(rowIndex).get(columnToCheck)
                    .equals(player.getToken())) {
                count++;
            } else {
                count = 0;
            }
            if (count == CONNECT_N) {
                return true;
            }
        }
        return false;
    }

    public List<List<Token>> getBoardStatus() {
        List<List<Token>> boardCopy = new ArrayList<>();
        for (int index = 0; index < rows; index++) {
            boardCopy.add(index, new ArrayList<>(board.get(index)));
        }
        return boardCopy;
    }
}
