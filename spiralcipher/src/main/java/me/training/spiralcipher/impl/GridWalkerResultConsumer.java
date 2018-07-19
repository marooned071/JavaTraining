package me.training.spiralcipher.impl;

import java.util.function.Consumer;

public interface GridWalkerResultConsumer extends Consumer<Point> {

    String result();
}
