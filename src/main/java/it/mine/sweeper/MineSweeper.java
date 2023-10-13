package it.mine.sweeper;

import java.util.ArrayList;
import java.util.List;

public class MineSweeper {

    /**
     * Sweeps the given board at the specified cell.
     *
     * @param board The game board.
     * @param aCell The cell that was clicked.
     * @return The updated board after the sweep.
     */
    public char[][] sweep(char[][] board, Click aCell) {
        // If the clicked cell contains a mine ('M'), it's replaced with an 'X' and returned immediately.
        if (board[aCell.row()][aCell.column()] == 'M') {
            board[aCell.row()][aCell.column()] = 'X';
            return board;
        }

        // If the clicked cell is empty ('E'), start the DFS to reveal the cells.
        dfs(board, aCell);
        return board;
    }

    /**
     * Uses Depth First Search (DFS) to reveal the cells.
     * If the current cell is empty and has no adjacent mines,
     * its value is set to 'B' and the DFS continues to its adjacent cells.
     * If it has adjacent mines, it shows the count of mines adjacent to it.
     *
     * @param board The game board.
     * @param aCell The current cell.
     */
    private void dfs(char[][] board, Click aCell) {
        if (board[aCell.row()][aCell.column()] == 'E') {
            List<Click> adjCells = findAdjacentCells(board, aCell);
            int mines = countMines(board, adjCells);

            if (mines == 0) {
                board[aCell.row()][aCell.column()] = 'B';
                for (Click myCell : adjCells) {
                    dfs(board, myCell);
                }
            } else {
                board[aCell.row()][aCell.column()] = (char) (mines + '0');
            }
        }
    }

    /**
     * Counts the number of mines in the adjacent cells.
     *
     * @param board The game board.
     * @param adjCells The list of adjacent cells.
     * @return The count of mines adjacent to the given cell.
     */
    private int countMines(char[][] board, List<Click> adjCells) {
        int count = 0;
        for (Click aCell : adjCells) {
            if (board[aCell.row()][aCell.column()] == 'M') {
                count++;
            }
        }
        return count;
    }

    /**
     * Finds the adjacent cells to the given cell.
     * It checks 8 possible directions (top-left, top, top-right, right, bottom-right, bottom, bottom-left, left).
     *
     * @param board The game board.
     * @param cell The current cell.
     * @return The list of adjacent cells to the given cell.
     */
    private List<Click> findAdjacentCells(char[][] board, Click cell) {
        int[] rowOffsets = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] colOffsets = {-1, 0, 1, -1, 1, -1, 0, 1};

        List<Click> adjacentCells = new ArrayList<>();

        for (int i = 0; i < rowOffsets.length; i++) {
            int newRow = cell.row() + rowOffsets[i];
            int newColumn = cell.column() + colOffsets[i];

            if (newRow >= 0 && newColumn >= 0 && newRow < board.length && newColumn < board[0].length && (board[newRow][newColumn] == 'E' || board[newRow][newColumn] == 'M')) {
                adjacentCells.add(new Click(newRow, newColumn));
            }
        }
        return adjacentCells;
    }
}