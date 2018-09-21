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
        Set<Set<E>> resultSet = new HashSet<>();
        for (int i = 0; i < powerSetSize; i++) {
            Set<E> set = new HashSet<>();
            for (int j = 0; j < setAsList.size(); j++) {
                if (((i >> j) & 1) == 1) { //check if j-th bit in i is set
                    set.add(setAsList.get(j));
                }
            }
            resultSet.add(set);
        }
        return resultSet;
    }


}
