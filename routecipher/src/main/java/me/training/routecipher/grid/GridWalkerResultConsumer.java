package me.training.routecipher.grid;

import java.util.function.Consumer;

public interface GridWalkerResultConsumer extends Consumer<Point> {

    String result();
}
