package me.training.routecipher.cipher;


import static me.training.routecipher.cipher.Direction.DOWN;
import static me.training.routecipher.cipher.Direction.LEFT;
import static me.training.routecipher.cipher.Direction.RIGHT;
import static me.training.routecipher.cipher.Direction.UP;

public final class RouteCycle {
    public static final Direction[] CLOCK_WISE = new Direction[]{DOWN, LEFT, UP, RIGHT};
    public static final Direction[] COUNTER_CLOCK_WISE = new Direction[]{LEFT, DOWN, RIGHT, UP};

    private RouteCycle() {
    }

}