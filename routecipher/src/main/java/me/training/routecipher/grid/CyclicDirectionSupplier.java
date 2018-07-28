package me.training.routecipher.grid;


import me.training.utils.direction.Direction;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class CyclicDirectionSupplier implements Supplier<Direction> {
    private int currentIndex;
    private List<Direction> sequence;

    public CyclicDirectionSupplier(Direction... sequence) {
        this.sequence = Arrays.asList(sequence);
        this.currentIndex = 0;
    }

    @Override
    public Direction get() {
        Direction toRet = sequence.get(currentIndex);
        currentIndex++;
        currentIndex %= sequence.size();
        return toRet;
    }
}