package me.training.whiteboard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PowerSetIterativeImplementation {

    public static <T> Set<Set<T>> powerSet3(Set<T> initialSet) {
        if(initialSet.isEmpty()){
            return Set.of(Set.of());
        }
        int powerSetSize = (int) Math.pow(2, initialSet.size());
        List<T> setAsList = new ArrayList<>(initialSet);
        Set<Set<T>> resultSet = new HashSet<>();
        for (int i = 0; i < powerSetSize; i++) {
            Set<T> set = new HashSet<>();
            for (int j = 0; j < setAsList.size(); j++) {
                int bit = (i >> j) & 1;
                if (bit == 1) {
                    set.add(setAsList.get(j));
                }
            }
            resultSet.add(set);
        }
        return resultSet;
    }
}
