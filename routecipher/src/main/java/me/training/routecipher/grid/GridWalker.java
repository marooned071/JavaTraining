package me.training.routecipher.grid;

import me.training.routecipher.cipher.Direction;

import java.util.function.Consumer;

public class GridWalker {
    private Grid2D<Boolean> visited;

    public GridWalker(Grid2D<Boolean> visited) {
        this.visited = visited;
    }

    public static GridWalker create(int columns, int rows) {
        return new GridWalker(new Grid2D<>(columns, rows, false));
    }

    public void walk(CyclicDirectionSupplier cyclicDirectionSupplier, Consumer<Point> stepConsumer, Point entryPoint) {
        WalkResult walkResult = walkLine(cyclicDirectionSupplier.get(), entryPoint, stepConsumer);
        do {
            Point endPoint = walkResult.getEndPoint();
            Direction nextDirection = cyclicDirectionSupplier.get();
            Point lineStartPoint = endPoint.move(nextDirection);
            walkResult = walkLine(nextDirection, lineStartPoint, stepConsumer);
        } while (!(walkResult.getSteps() == 0));
    }


    private WalkResult walkLine(Direction direction, Point startPoint, Consumer<Point> stepConsumer) {
        Point nextPoint = startPoint.copy();
        Point endPoint = startPoint.copy();
        int stepCount = 0;
        while (visited.isInBounds(nextPoint) && !visited.get(nextPoint)) {
            endPoint = nextPoint.copy();
            stepConsumer.accept(nextPoint);
            visited.set(nextPoint, true);
            nextPoint = nextPoint.move(direction);
            stepCount++;
        }
        return new WalkResult(endPoint, stepCount);
    }

    private class WalkResult {
        private Point endPoint;
        private int steps;

        public WalkResult(Point endPoint, int steps) {
            this.endPoint = endPoint;
            this.steps = steps;
        }

        public Point getEndPoint() {
            return endPoint;
        }

        public int getSteps() {
            return steps;
        }
    }

}
