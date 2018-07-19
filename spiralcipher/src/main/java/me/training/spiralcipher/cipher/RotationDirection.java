package me.training.spiralcipher.cipher;

import me.training.spiralcipher.impl.Direction;

public enum RotationDirection {
    CLOCK_WISE(Direction.DOWN, Direction.LEFT, Direction.UP, Direction.RIGHT),
    COUNTER_CLOCK_WISE(Direction.LEFT, Direction.DOWN, Direction.RIGHT, Direction.UP);

    private Direction[] directionOrder;

    RotationDirection(Direction... directionOrder) {
        this.directionOrder = directionOrder;
    }

    public Direction[] getDirectionOrder() {
        return directionOrder;
    }
}