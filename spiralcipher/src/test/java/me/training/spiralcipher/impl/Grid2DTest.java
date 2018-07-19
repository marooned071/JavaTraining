package me.training.spiralcipher.impl;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Grid2DTest {

    @Test
    public void shouldFillGridWithProvidedValuesAndFillToEndWithDefault() {
        Grid2D<Character> characterGrid2D = new Grid2D<>(4, 3, 'X',
                ArrayUtils.toObject("ABCDEFGHI".toCharArray()));
        assertEquals(Character.valueOf('A'), characterGrid2D.get(0, 0));
        assertEquals(Character.valueOf('B'), characterGrid2D.get(1, 0));
        assertEquals(Character.valueOf('C'), characterGrid2D.get(2, 0));
        assertEquals(Character.valueOf('D'), characterGrid2D.get(3, 0));
        assertEquals(Character.valueOf('F'), characterGrid2D.get(1, 1));
        assertEquals(Character.valueOf('I'), characterGrid2D.get(0, 2));
        assertEquals(Character.valueOf('X'), characterGrid2D.get(1, 2));
        assertEquals(Character.valueOf('X'), characterGrid2D.get(2, 2));
    }

    @Test
    public void shouldFillWithDefaultValue() {
        Grid2D<Character> characterGrid2D = new Grid2D<>(4, 3, 'X');
        IntStream.of(0, 1, 2, 3).forEach(x -> IntStream.of(0, 1, 2)
                .forEach(y -> assertEquals(Character.valueOf('X'), characterGrid2D.get(x, y))));

    }

    @Test
    public void shouldSet() {
        Grid2D<Character> characterGrid2D = new Grid2D<>(4, 3, 'X',
                ArrayUtils.toObject("ABCDEFGHI".toCharArray()));
        assertEquals(Character.valueOf('D'), characterGrid2D.get(3, 0));

        characterGrid2D.set(3, 0, 'O');

        assertEquals(Character.valueOf('O'), characterGrid2D.get(3, 0));
    }

    @Test
    public void shouldGetFromPoint() {
        Grid2D<Character> characterGrid2D = new Grid2D<>(4, 3, 'X',
                ArrayUtils.toObject("ABCDEFGHI".toCharArray()));
        assertEquals(Character.valueOf('D'), characterGrid2D.get(new Point(3, 0)));
    }

    @Test
    public void shouldSetFromPoint() {
        Grid2D<Character> characterGrid2D = new Grid2D<>(4, 3, 'X',
                ArrayUtils.toObject("ABCDEFGHI".toCharArray()));
        assertEquals(Character.valueOf('D'), characterGrid2D.get(3, 0));

        characterGrid2D.set(new Point(3, 0), 'O');

        assertEquals(Character.valueOf('O'), characterGrid2D.get(3, 0));
    }

    @Test
    public void shouldTestIfCoordinatesAreInBounds() {
        Grid2D<Character> characterGrid2D = new Grid2D<>(4, 3, 'X',
                ArrayUtils.toObject("ABCDEFGHI".toCharArray()));

        assertTrue(characterGrid2D.isInBounds(0, 0));
        assertTrue(characterGrid2D.isInBounds(3, 2));
        assertTrue(characterGrid2D.isInBounds(1, 1));
        assertTrue(characterGrid2D.isInBounds(1, 2));

        assertFalse(characterGrid2D.isInBounds(-1, 0));
        assertFalse(characterGrid2D.isInBounds(0, -1));
        assertFalse(characterGrid2D.isInBounds(3, 3));
        assertFalse(characterGrid2D.isInBounds(4, 1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddOutOfBounds() {
        Grid2D<Character> characterGrid2D = new Grid2D<>(4, 3, 'X',
                ArrayUtils.toObject("ABCDEFGHI".toCharArray()));

        characterGrid2D.set(3, 3, 'Y');
    }
}