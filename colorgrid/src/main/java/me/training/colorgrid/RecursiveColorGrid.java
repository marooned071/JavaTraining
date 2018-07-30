package me.training.colorgrid;

import me.training.utils.direction.Direction;
import me.training.utils.grid.Grid2D;
import me.training.utils.grid.Point;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RecursiveColorGrid implements ColorGrid {

    private Grid2D<Integer> grid;

    public RecursiveColorGrid(Grid2D<Integer> grid) {
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
            Set<Point> visited = new HashSet<>();
            Point startingPoint = notVisitedPoints.poll();
            goRegion(startingPoint, grid.get(startingPoint), visited);
            notVisitedPoints.removeAll(visited);
            if (visited.size() > currentMax) {
                currentMax = visited.size();
                currentLargestRegionPoint = startingPoint;
            }
        }
        return Pair.of(currentLargestRegionPoint, currentMax);
    }

    private void goRegion(Point startPoint, Integer color, Set<Point> visited) {
        visited.add(startPoint);
        for (Direction direction : Direction.values()) {
            Point nextPoint = startPoint.move(direction);
            if (isValid(nextPoint, color, visited))
                goRegion(nextPoint, color, visited);
        }
    }

    private boolean isValid(Point point, Integer color, Set<Point> visited){
        return (grid.isInBounds(point)) && (grid.get(point).equals(color)) && (!visited.contains(point));
    }


}
