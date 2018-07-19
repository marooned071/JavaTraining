package me.training.spiralcipher.impl;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SpiralCipherEncrypterTableTest {
    //A B C D E
    //F G H I J
    //K L M N O
    private SpiralCipherTable spiralCipherTable = SpiralCipherTable.of("ABCDEFGHIJKLMNO", 5, 3);


    @Test
    public void moveInLineAndEraseGoDownEnd() {
        assertEquals("EJO", spiralCipherTable.moveInLineAndErase(Direction.DOWN, 0, 4).getText());
    }

    @Test
    public void moveInLineAndEraseGoDownMiddle() {
        assertEquals("CHM", spiralCipherTable.moveInLineAndErase(Direction.DOWN, 0, 2).getText());
    }

    @Test
    public void moveInLineAndEraseGoDownStart() {
        assertEquals("AFK", spiralCipherTable.moveInLineAndErase(Direction.DOWN, 0, 0).getText());
    }

    @Test
    public void moveInLineAndEraseGoUpEnd() {
        assertEquals("OJE", spiralCipherTable.moveInLineAndErase(Direction.UP, 2, 4).getText());
    }

    @Test
    public void moveInLineAndEraseGoUpMiddle() {
        assertEquals("MHC", spiralCipherTable.moveInLineAndErase(Direction.UP, 2, 2).getText());
    }

    @Test
    public void moveInLineAndEraseGoUpStart() {
        assertEquals("KFA", spiralCipherTable.moveInLineAndErase(Direction.UP, 2, 0).getText());
    }


    @Test
    public void moveInLineAndEraseGoRightEnd() {
        assertEquals("KLMNO", spiralCipherTable.moveInLineAndErase(Direction.RIGHT, 2, 0).getText());
    }

    @Test
    public void moveInLineAndEraseGoRightMiddle() {
        assertEquals("FGHIJ", spiralCipherTable.moveInLineAndErase(Direction.RIGHT, 1, 0).getText());
    }

    @Test
    public void moveInLineAndEraseGoRightStart() {
        assertEquals("ABCDE", spiralCipherTable.moveInLineAndErase(Direction.RIGHT, 0, 0).getText());
    }

    @Test
    public void moveInLineAndEraseGoLeftEnd() {
        assertEquals("ONMLK", spiralCipherTable.moveInLineAndErase(Direction.LEFT, 2, 4).getText());
    }

    @Test
    public void moveInLineAndEraseGoLeftMiddle() {
        assertEquals("JIHGF", spiralCipherTable.moveInLineAndErase(Direction.LEFT, 1, 4).getText());
    }

    @Test
    public void moveInLineAndEraseGoLeftStart() {
        assertEquals("EDCBA", spiralCipherTable.moveInLineAndErase(Direction.LEFT, 0, 4).getText());
    }

    @Test
    public void moveInLineAndEraseMiddle() {
        assertEquals("HGF", spiralCipherTable.moveInLineAndErase(Direction.LEFT, 1, 2).getText());
    }

    @Test
    public void moveInLineAndEraseWithNulls() {
        assertEquals("DIN", spiralCipherTable.moveInLineAndErase(Direction.DOWN, 0, 3).getText());
        assertEquals("EJO", spiralCipherTable.moveInLineAndErase(Direction.DOWN, 0, 4).getText());

        assertEquals("FGH", spiralCipherTable.moveInLineAndErase(Direction.RIGHT, 1, 0).getText());
    }
}