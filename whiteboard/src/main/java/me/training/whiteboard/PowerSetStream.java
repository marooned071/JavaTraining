package me.training.whiteboard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static com.google.common.collect.Streams.stream;

public class PowerSetStream {
    public static <E> Stream<Set<E>> of(Set<E> initialSet) {
        return stream(new PowerSetIterator<>(initialSet));
    }
}


class PowerSetIterator<E> implements Iterator<Set<E>> {

    private final int powerSetSize;
    private final List<E> initialSetAsList;
    private int bitmask;

    public PowerSetIterator(Set<E> initialSet) {
        this.initialSetAsList = new ArrayList<>(initialSet);
        this.powerSetSize = (int) Math.pow(2, initialSet.size());
        this.bitmask = 0;
    }

    @Override
    public boolean hasNext() {
        return bitmask < powerSetSize;
    }

    @Override
    public Set<E> next() {
        Set<E> result = copyElementsFromListByBitMask(initialSetAsList, bitmask);
        bitmask++;
        return result;
    }

    private Set<E> copyElementsFromListByBitMask(List<E> setAsList, int bitmask) {
        Set<E> result = new HashSet<>(setAsList.size());
        int res = bitmask;
        if ((bitmask & 1) == 1) {
            result.add(setAsList.get(0));
        }
        for (int i = 1; i <= setAsList.size(); i++) {
            res = res >> 1;
            if ((res & 1) == 1) {
                result.add(setAsList.get(i));
            }
        }
        return result;
    }


}
