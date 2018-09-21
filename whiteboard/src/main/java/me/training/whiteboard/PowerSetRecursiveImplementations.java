package me.training.whiteboard;

import java.util.*;

public class PowerSetRecursiveImplementations {
    public static <T> Set<Set<T>> powerSet1(Set<T> entrySet) {
        if (entrySet.isEmpty()) {
            return Set.of(Set.of());
        } else {
            Set<T> withoutElement = new HashSet<>(entrySet);
            T element = pollFirstElement(withoutElement);
            Set<Set<T>> resultWithoutElement = powerSet1(withoutElement);
            Set<Set<T>> resultWithElement = new HashSet<>();
            resultWithoutElement.forEach(resultSet -> {
                        Set<T> set = new HashSet<>(resultSet);
                        set.add(element);
                        resultWithElement.add(set);
                    }
            );
            resultWithElement.addAll(resultWithoutElement);
            return resultWithElement;
        }
    }

    private static <T> T pollFirstElement(Set<T> set) {
        Iterator<T> initialSetIterator = set.iterator();
        T element = initialSetIterator.next();
        initialSetIterator.remove();
        return element;
    }


    public static <T> Set<Set<T>> powerSet2(Set<T> entrySet) {
        if (entrySet.isEmpty()) {
            return Set.of(Set.of());
        }
        Queue<T> restQueue = new ArrayDeque<>(entrySet);
        T element = restQueue.poll();
        return powerSet2(Set.of(), restQueue, element);
    }

    private static <T> Set<Set<T>> powerSet2(Set<T> prevSet, Queue<T> restQueue, T element) {
        if (restQueue.isEmpty()) {
            Set<T> withElement = new HashSet<>(prevSet);
            withElement.add(element);
            Set<Set<T>> toRet = new HashSet<>();
            toRet.add(prevSet);
            toRet.add(withElement);
            return toRet;
        } else {
            Set<T> withoutElementSet = Set.copyOf(prevSet);
            Set<T> withElementSet = new HashSet<>(prevSet);
            withElementSet.add(element);
            Queue<T> newRestQueue = new ArrayDeque<>(restQueue);
            T newElement = newRestQueue.poll();
            Set<Set<T>> withElementResult = powerSet2(withoutElementSet, newRestQueue, newElement);
            Set<Set<T>> withoutElementResult = powerSet2(withElementSet, newRestQueue, newElement);
            withElementResult.addAll(withoutElementResult);
            return withElementResult;
        }
    }

    public static <T> Set<Set<T>> powerSetStackOverFlowImpl(Set<T> originalSet) {
        Set<Set<T>> sets = new HashSet<Set<T>>();
        if (originalSet.isEmpty()) {
            sets.add(new HashSet<T>());
            return sets;
        }
        List<T> list = new ArrayList<T>(originalSet);
        T head = list.get(0);
        Set<T> rest = new HashSet<T>(list.subList(1, list.size()));
        for (Set<T> set : powerSetStackOverFlowImpl(rest)) {
            Set<T> newSet = new HashSet<T>();
            newSet.add(head);
            newSet.addAll(set);
            sets.add(newSet);
            sets.add(set);
        }
        return sets;
    }
}
