package me.training.routecipher.grid;

import me.training.utils.direction.Direction;
import me.training.utils.grid.Point;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class GridWalkerTest {
    private GridWalker gridWalker = GridWalker.create(5, 3);


    @Test
    public void shouldGoRightStraight() {
        CyclicDirectionSupplier cyclicDirectionSupplier = new CyclicDirectionSupplier(Direction.RIGHT);
        PointConsumerVerifier pointConsumerVerifier = new PointConsumerVerifier(List.of(
                Point.of(0, 0),
                Point.of(1, 0),
                Point.of(2, 0),
                Point.of(3, 0),
                Point.of(4, 0)));
        gridWalker.walk(cyclicDirectionSupplier,
                pointConsumerVerifier,
                Point.of(0, 0));
        pointConsumerVerifier.verify();
    }

    @Test
    public void shouldNotVisitOnlyEntryPoint() {
        CyclicDirectionSupplier cyclicDirectionSupplier = new CyclicDirectionSupplier(Direction.LEFT);
        PointConsumerVerifier pointConsumerVerifier = new PointConsumerVerifier(List.of(Point.of(0, 0)));
        gridWalker.walk(cyclicDirectionSupplier,
                pointConsumerVerifier,
                Point.of(0, 0));
        pointConsumerVerifier.verify();
    }

    @Test
    public void shouldGoRightAndDown() {
        CyclicDirectionSupplier cyclicDirectionSupplier = new CyclicDirectionSupplier(Direction.RIGHT, Direction.DOWN);
        PointConsumerVerifier pointConsumerVerifier = new PointConsumerVerifier(List.of(
                Point.of(0, 0),
                Point.of(1, 0),
                Point.of(2, 0),
                Point.of(3, 0),
                Point.of(4, 0),
                Point.of(4, 1),
                Point.of(4, 2))
        );
        gridWalker.walk(cyclicDirectionSupplier,
                pointConsumerVerifier,
                Point.of(0, 0));
        pointConsumerVerifier.verify();
    }


    private class PointConsumerVerifier implements Consumer<Point> {

        private List<Point> pointsToVisit;
        private List<Point> visitedPoints;

        public PointConsumerVerifier(List<Point> pointsToVisit) {
            this.pointsToVisit = pointsToVisit;
            this.visitedPoints = new ArrayList<>();
        }

        @Override
        public void accept(Point point) {
            visitedPoints.add(point);
        }

        public void verify() {
            assertEquals(this.pointsToVisit, this.visitedPoints);
        }
    }
}