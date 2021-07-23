package com.bayrktlihn.mincostpath;

public class MinCostPath {
    public int find(int[][] matrix, Cell start, Cell target) {
        int countOfRow = matrix.length;
        int countOfColumn = matrix[0].length;

        validateIfTheCellsOutOfMatrixBound(start, countOfRow, countOfColumn);

        validateIfTheCellsOutOfMatrixBound(target, countOfRow, countOfColumn);

        if (start.equals(target))
            return matrix[start.row()][start.column()];


//        Cell currentCell = start;
//        int cost = 0;
//        while (!currentCell.equals(target)) {
//            cost += matrix[currentCell.row()][currentCell.column()];
//            currentCell = new Cell(currentCell.row(), currentCell.column() + 1);
//        }
//        cost += matrix[currentCell.row()][currentCell.column()];

//        return cost;

        return this.findMinCostPath(matrix, start, target);
    }

    private int findMinCostPath(int[][] matrix, Cell start, Cell target) {
        if (start.row() > target.row() || start.column() > target.column())
            return Integer.MAX_VALUE;

        if (start.equals(target))
            return matrix[start.row()][start.column()];

        int rightPathCost = findMinCostPath(matrix, new Cell(start.row(), start.column() + 1), target);
        int downPathCost = findMinCostPath(matrix, new Cell(start.row() + 1, start.column()), target);
        int diagonalPathCost = findMinCostPath(matrix, new Cell(start.row() + 1, start.column() + 1), target);

        final int min = Math.min(rightPathCost, Math.min(downPathCost, diagonalPathCost));
        return min + matrix[start.row()][start.column()];

    }


    private void validateIfTheCellsOutOfMatrixBound(Cell start, int countOfRow, int countOfColumn) {
        if (start.row() >= countOfRow || start.row() < 0)
            throw new IllegalArgumentException();

        if (start.column() >= countOfColumn || start.column() < 0)
            throw new IllegalArgumentException();
    }
}
