package me.training.spiralcipher.impl;

import org.apache.commons.lang3.ArrayUtils;

public class EncryptGridWalkerStepConsumer implements GridWalkerResultConsumer {
    private Grid2D<Character> plainTextGrid;
    private StringBuilder resultBuilder;

    public EncryptGridWalkerStepConsumer(Grid2D<Character> plainTextGrid, StringBuilder resultBuilder) {
        this.plainTextGrid = plainTextGrid;
        this.resultBuilder = resultBuilder;
    }

    public static EncryptGridWalkerStepConsumer of(String text, int rows, int columns) {

        return new EncryptGridWalkerStepConsumer(
                new Grid2D<>(columns, rows, 'X', ArrayUtils.toObject(text.toCharArray())), new StringBuilder());
    }

    @Override
    public String result() {
        return resultBuilder.toString();
    }

    @Override
    public void accept(Point point) {
        resultBuilder.append(plainTextGrid.get(point));
    }
}
