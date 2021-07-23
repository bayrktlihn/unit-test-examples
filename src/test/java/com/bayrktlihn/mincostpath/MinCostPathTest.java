package com.bayrktlihn.mincostpath;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MinCostPathTest {

    private MinCostPath minCostPath;

    @BeforeEach
    void setUp() {
        minCostPath = new MinCostPath();
    }

    private Cell cell(int row, int column) {
        return new Cell(row, column);
    }

    @Test
    @DisplayName("Throw IllegalArgumentException when the start or target cell is out of matrix bound")
    void throwsIllegalArgumentExceptionWhenTheCellIsOutOfMatrixBound() {

        final int[][] matrix = {
                {4, 5, 6},
                {7, 8, 1},
        };

        assertAll("Start cell must be in matrix",
                () -> assertThrows(IllegalArgumentException.class,
                        () -> minCostPath.find(matrix, new Cell(2, 1), new Cell(0, 2))
                ),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> minCostPath.find(matrix, new Cell(-1, 1), new Cell(0, 2))
                )
        );

        assertAll("Target cell must be in matrix",
                () -> assertThrows(IllegalArgumentException.class,
                        () -> minCostPath.find(matrix, new Cell(0, 0), new Cell(2, 1))
                ),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> minCostPath.find(matrix, new Cell(0, 0), new Cell(-1, 2))
                )
        );


    }

    @Test
    @DisplayName("Return the cost of start cell when the start cell equals to target cell")
    void returnTheCostStartCellWhenTheStartCellIsEqualToTargetCell() {
        final int[][] matrix = {
                {3, 5, 7, 9}
        };
        assertEquals(3, minCostPath.find(matrix, cell(0, 0), cell(0, 0)));
        assertEquals(7, minCostPath.find(matrix, cell(0, 2), cell(0, 2)));

    }

    @Test
    @DisplayName("Find min cost path for one row matrix")
    void findMinCostPathForOneRowMatrix() {
        final int[][] matrix = {
                {3, 5, 7, 9}
        };

        assertEquals(8, minCostPath.find(matrix, cell(0, 0), cell(0, 1)));
        assertEquals(15, minCostPath.find(matrix, cell(0, 0), cell(0, 2)));
        assertEquals(16, minCostPath.find(matrix, cell(0, 2), cell(0, 3)));

    }

    @Test
    @DisplayName("Find min cost path for multi row matrix")
    void findMinCostPathForMultiRowMatrix() {
        final int[][] matrix = {
                {1, 2, 3, 4},
                {1, 3, 1, 2},
                {1, 2, 4, 5}
        };

        assertEquals(4, minCostPath.find(matrix, cell(0, 0), cell(1, 2)));
        assertEquals(3, minCostPath.find(matrix, cell(0, 0), cell(2, 0)));
        assertEquals(9, minCostPath.find(matrix, cell(0, 0), cell(2, 3)));


    }

}
