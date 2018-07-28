package me.training.routecipher.grid;

import me.training.utils.grid.Point;

import java.util.function.Consumer;

public interface GridWalkerResultConsumer extends Consumer<Point> {

    String result();
}
