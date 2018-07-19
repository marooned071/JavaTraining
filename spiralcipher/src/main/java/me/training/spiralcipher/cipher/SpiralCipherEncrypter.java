package me.training.spiralcipher.cipher;


import me.training.spiralcipher.impl.CyclicDirectionSupplier;
import me.training.spiralcipher.impl.Direction;
import me.training.spiralcipher.impl.SpiralCipherTable;
import me.training.spiralcipher.impl.SpiralCipherTableResult;

public class SpiralCipherEncrypter {
    private SpiralCipherTable spiralCipherTable;
    private CyclicDirectionSupplier cyclicDirectionSupplier;
    private int startColumn;
    private int startRow;

    private SpiralCipherEncrypter(SpiralCipherTable spiralCipherTable, CyclicDirectionSupplier cyclicDirectionSupplier, int startColumn, int startRow) {
        this.spiralCipherTable = spiralCipherTable;
        this.cyclicDirectionSupplier = cyclicDirectionSupplier;
        this.startColumn = startColumn;
        this.startRow = startRow;
    }

    public static SpiralCipherEncrypter of(String plainText, int columns, int rows, RotationDirection rotationDirection) {
        String toEncrypt = plainText.replaceAll("[ \\-'.?/!]", "").toUpperCase();

        return new SpiralCipherEncrypter(SpiralCipherTable.of(toEncrypt, columns, rows), new CyclicDirectionSupplier(rotationDirection.getDirectionOrder()), columns - 1, 0);
    }

    public String encrypt() {
        StringBuilder encryptedStringBuilder = new StringBuilder();
        SpiralCipherTableResult result = spiralCipherTable.moveInLineAndErase(cyclicDirectionSupplier.get(), startRow, startColumn);
        encryptedStringBuilder.append(result.getText());

        do {
            Direction nextDirection = cyclicDirectionSupplier.get();
            int nextRowStartPoint = result.getEndRow() + nextDirection.getRowChange();
            int nextColStartPoint = result.getEndColumn() + nextDirection.getColumnChange();
            result = spiralCipherTable.moveInLineAndErase(nextDirection, nextRowStartPoint, nextColStartPoint);
            encryptedStringBuilder.append(result.getText());
        } while (!result.getText().isEmpty());

        return encryptedStringBuilder.toString();
    }
}
