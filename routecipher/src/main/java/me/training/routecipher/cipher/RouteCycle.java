package me.training.routecipher.cipher;

public enum RouteCycle {
    CLOCK_WISE(Direction.DOWN, Direction.LEFT, Direction.UP, Direction.RIGHT),
    COUNTER_CLOCK_WISE(Direction.LEFT, Direction.DOWN, Direction.RIGHT, Direction.UP);

    private Direction[] directionOrder;

    RouteCycle(Direction... directionOrder) {
        this.directionOrder = directionOrder;
    }

    public Direction[] getDirectionOrder() {
        return directionOrder;
    }
}