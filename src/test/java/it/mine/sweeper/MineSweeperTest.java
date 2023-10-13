package it.mine.sweeper;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MineSweeperTest {

    @Test
    void testClickOnMine() {
        MineSweeper sweeper = new MineSweeper();
        char[][] board = {
                {'E', 'E', 'E'},
                {'E', 'M', 'E'},
                {'E', 'E', 'E'}
        };

        char[][] result = sweeper.sweep(board, new Click(1, 1));
        assertEquals('X', result[1][1]);
    }

    @Test
    void testClickOnEmptyCell() {
        MineSweeper sweeper = new MineSweeper();
        char[][] board = {
                {'E', 'E', 'E'},
                {'E', 'E', 'E'},
                {'E', 'E', 'E'}
        };

        char[][] result = sweeper.sweep(board, new Click(1, 1));
        assertEquals('B', result[1][1]);
    }

    @Test
    void testClickOnCellNextToMine() {
        MineSweeper sweeper = new MineSweeper();
        char[][] board = {
                {'E', 'E', 'E'},
                {'E', 'M', 'E'},
                {'E', 'E', 'E'}
        };

        char[][] result = sweeper.sweep(board, new Click(1, 0));
        assertEquals('1', result[1][0]);
    }

    @Test
    void testClickOnCellBetweenMines() {
        MineSweeper sweeper = new MineSweeper();
        char[][] board = {
                {'E', 'M', 'E'},
                {'E', 'E', 'E'},
                {'E', 'M', 'E'}
        };

        char[][] result = sweeper.sweep(board, new Click(1, 1));
        assertEquals('2', result[1][1]);
    }

    @Test
    void testLargeBoard() {
        MineSweeper sweeper = new MineSweeper();
        char[][] board = {
                {'E', 'E', 'E', 'E', 'E'},
                {'E', 'M', 'E', 'M', 'E'},
                {'E', 'E', 'E', 'E', 'E'},
                {'E', 'M', 'E', 'E', 'E'},
                {'E', 'E', 'E', 'M', 'E'}
        };

        char[][] result = sweeper.sweep(board, new Click(2, 3));
        assertEquals('1', result[2][3]);
        result = sweeper.sweep(board, new Click(2, 2));
        assertEquals('3', result[2][2]);
        assertEquals('1', result[2][3]);
        result = sweeper.sweep(board, new Click(3, 3));
        assertEquals('1', result[3][3]);
    }

}