package me.training.colorgrid;

import me.training.utils.direction.Direction;
import me.training.utils.grid.Grid2D;
import me.training.utils.grid.Point;
import org.apache.commons.lang3.tuple.Pair;

import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IterativeColorGrid implements ColorGrid {

    private Grid2D<Integer> grid;

    public IterativeColorGrid(Grid2D<Integer> grid) {
        this.grid = grid;
    }

    @Override
    public Pair<Point, Integer> getLargestRegion() {
        Queue<Point> notVisitedPoints = IntStream.range(0, grid.getRowsCount())
                .mapToObj(row -> IntStream.range(0, grid.getColumnsCount()).mapToObj(column -> new Point(column, row)))
                .flatMap(f -> f).collect(Collectors.toCollection(LinkedList::new));


        int currentMax = 0;
        Point currentLargestRegionPoint = null;

        while (!notVisitedPoints.isEmpty()) {
            Point regionEntryPoint = notVisitedPoints.poll();
            Queue<Point> toVisitInRegion = new LinkedList<>();
            toVisitInRegion.add(regionEntryPoint);
            Integer regionColor = grid.get(regionEntryPoint);
            int regionSize = 0;

            while (!toVisitInRegion.isEmpty()) {
                Point currentPoint = toVisitInRegion.poll();
                notVisitedPoints.remove(currentPoint);
                regionSize++;
                for (Direction direction : Direction.values()) {
                    Point nextPoint = currentPoint.move(direction);
                    if (isInRegion(nextPoint, regionColor) && notVisitedPoints.contains(nextPoint))
                        toVisitInRegion.add(nextPoint);
                }
            }


            if (regionSize > currentMax) {
                currentMax = regionSize;
                currentLargestRegionPoint = regionEntryPoint;
            }
        }

        return Pair.of(currentLargestRegionPoint, currentMax);
    }

    private boolean isInRegion(Point point, Integer color) {
        return grid.isInBounds(point) && grid.get(point).equals(color);
    }
}
