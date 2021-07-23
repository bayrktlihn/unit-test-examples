package com.bayrktlihn.mincostpath;

public class Cell {
    private final int row;
    private final int column;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int row() {
        return row;
    }

    public int column() {
        return column;
    }

    @Override
    public boolean equals(Object o) {
        if (!Cell.class.isInstance(o))
            return false;
        Cell cell = (Cell) o;
        return row == cell.row && column == cell.column;
    }

}
