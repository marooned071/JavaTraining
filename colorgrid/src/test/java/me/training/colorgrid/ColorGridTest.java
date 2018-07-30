package me.training.colorgrid;

import me.training.utils.grid.Grid2D;
import me.training.utils.grid.Point;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ColorGridTest {

/*
The problem is to find the largest region with same color (integer value).
 */

    @Test
    void testRecursive() {

        Grid2D<Integer> grid = new Grid2D<>(4, 3, 0,
                new Integer[]{
                        1, 1, 2, 3,
                        1, 2, 3, 2,
                        3, 2, 2, 2
                });
        ColorGrid colorGrid = new RecursiveColorGrid(grid);
        Pair<Point, Integer> result = colorGrid.getLargestRegion();
        assertTrue(
                Set.of(Point.of(2, 0), Point.of(1, 1), Point.of(1, 2), Point.of(2, 2), Point.of(3, 2), Point.of(4, 2))
                        .contains(result.getLeft()));
    }

    @Test
    void testIterative() {

        Grid2D<Integer> grid = new Grid2D<>(4, 3, 0,
                new Integer[]{
                        1, 1, 2, 3,
                        1, 2, 3, 2,
                        3, 2, 2, 2
                });
        ColorGrid colorGrid = new IterativeColorGrid(grid);
        Pair<Point, Integer> result = colorGrid.getLargestRegion();
        assertTrue(
                Set.of(Point.of(2, 0), Point.of(1, 1), Point.of(1, 2), Point.of(2, 2), Point.of(3, 2), Point.of(4, 2))
                        .contains(result.getLeft()));
    }

    @Test
    void withRandomTestRecursive() {
        Grid2D<Integer> grid = new Grid2D<>(100, 100, () -> new Random().nextInt(3));
        ColorGrid colorGrid = new RecursiveColorGrid(grid);
        Pair<Point, Integer> result = colorGrid.getLargestRegion();
        System.out.println(result);
    }

    @Test
    void withRandomTestIterative(){
        Grid2D<Integer> grid = new Grid2D<>(100, 100, () -> new Random().nextInt(3));
        ColorGrid colorGrid = new IterativeColorGrid(grid);
        Pair<Point, Integer> result = colorGrid.getLargestRegion();
        System.out.println(result);
    }
}