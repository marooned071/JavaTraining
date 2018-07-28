package me.training.routecipher.cipher;


import me.training.utils.direction.Direction;

import static me.training.utils.direction.Direction.DOWN;
import static me.training.utils.direction.Direction.LEFT;
import static me.training.utils.direction.Direction.RIGHT;
import static me.training.utils.direction.Direction.UP;

public final class RouteCycle {
    public static final Direction[] CLOCK_WISE = new Direction[]{DOWN, LEFT, UP, RIGHT};
    public static final Direction[] COUNTER_CLOCK_WISE = new Direction[]{LEFT, DOWN, RIGHT, UP};

    private RouteCycle() {
    }

}