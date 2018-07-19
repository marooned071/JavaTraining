package me.training.spiralcipher.impl;

public class SpiralCipherTableResult {
    private String text;
    private int endRow;
    private int endColumn;

    public SpiralCipherTableResult(String text, int endRow, int endColumn) {
        this.text = text;
        this.endRow = endRow;
        this.endColumn = endColumn;
    }

    public String getText() {
        return text;
    }

    public int getEndColumn() {
        return endColumn;
    }

    public int getEndRow() {
        return endRow;
    }
}
