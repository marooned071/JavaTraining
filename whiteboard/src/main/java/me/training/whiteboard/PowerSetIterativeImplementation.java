package me.training.whiteboard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PowerSetIterativeImplementation {

    public static <E> Set<Set<E>> powerSet3(Set<E> initialSet) {
        if (initialSet.isEmpty()) {
            return Set.of(Set.of());
        }
        int powerSetSize = (int) Math.pow(2, initialSet.size());
        List<E> setAsList = new ArrayList<>(initialSet);
        Set<Set<E>> resultSet = new HashSet<>(powerSetSize);
        for (int bitmask = 0; bitmask < powerSetSize; bitmask++) {
            resultSet.add(copyElementsFromListByBitMask(setAsList, bitmask));
        }
        return resultSet;
    }


    static <E> Set<E> copyElementsFromListByBitMask(List<E> setAsList, int bitmask) {
        Set<E> result = new HashSet<>(setAsList.size());
        int res = bitmask;
        if ((bitmask & 1) == 1) {
            result.add(setAsList.get(0));
        }
        for(int i=1;i<=setAsList.size(); i++){
            res = res >> 1;
            if ((res & 1) == 1) {
                result.add(setAsList.get(i));
            }
        }
        return result;
    }
}
