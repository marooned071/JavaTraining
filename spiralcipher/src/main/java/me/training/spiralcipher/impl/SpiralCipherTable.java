package me.training.spiralcipher.impl;

public class SpiralCipherTable {
    private Character[][] tab;

    private SpiralCipherTable(Character[][] tab) {
        this.tab = tab;
    }

    public static SpiralCipherTable of(String plainText, int columns, int rows) {
        Character[][] tab = new Character[rows][columns];
        int charIndex = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (charIndex >= plainText.length()) {
                    tab[i][j] = 'X';
                } else {
                    tab[i][j] = plainText.charAt(charIndex);
                    charIndex++;
                }
            }
        }
        return new SpiralCipherTable(tab);
    }

    public SpiralCipherTableResult moveInLineAndErase(Direction direction, int startRow, int startColumn) {
        StringBuilder line = new StringBuilder();

        int nextRow = startRow;
        int nextColumn = startColumn;
        int endRow = startRow;
        int endColumn = startColumn;
        while (isInArrayBounds(tab, nextRow, nextColumn) && tab[nextRow][nextColumn] != null) {
            endColumn = nextColumn;
            endRow = nextRow;
            Character character = tab[nextRow][nextColumn];
            line.append(character);
            tab[nextRow][nextColumn] = null;
            nextRow += direction.getRowChange();
            nextColumn += direction.getColumnChange();
        }
        return new SpiralCipherTableResult(line.toString(), endRow, endColumn);
    }

    private boolean isInArrayBounds(Character[][] tab, int r, int c) {
        return r < tab.length && c < tab[0].length && r >= 0 && c >= 0;
    }
}