package me.training.spiralcipher.impl;

public enum Direction {
    DOWN(1, 0),
    UP(-1, 0),
    RIGHT(0, 1),
    LEFT(0, -1);

    private int rowChange;
    private int columnChange;

    Direction(int rowChange, int columnChange) {
        this.rowChange = rowChange;
        this.columnChange = columnChange;
    }

    public int getRowChange() {
        return rowChange;
    }

    public int getColumnChange() {
        return columnChange;
    }
}