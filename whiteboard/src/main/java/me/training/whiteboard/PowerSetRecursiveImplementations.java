package me.training.whiteboard;

import java.util.*;

public class PowerSetRecursiveImplementations {
    public static <E> Set<Set<E>> powerSet1(Set<E> entrySet) {
        if (entrySet.isEmpty()) {
            return Set.of(Set.of());
        } else {
            Set<E> withoutElement = new HashSet<>(entrySet);
            E element = pollFirstElement(withoutElement);
            Set<Set<E>> resultWithoutElement = powerSet1(withoutElement);
            Set<Set<E>> resultWithElement = new HashSet<>();
            resultWithoutElement.forEach(resultSet -> {
                        Set<E> set = new HashSet<>(resultSet);
                        set.add(element);
                        resultWithElement.add(set);
                    }
            );
            resultWithElement.addAll(resultWithoutElement);
            return resultWithElement;
        }
    }

    private static <E> E pollFirstElement(Set<E> set) {
        Iterator<E> initialSetIterator = set.iterator();
        E element = initialSetIterator.next();
        initialSetIterator.remove();
        return element;
    }


    public static <E> Set<Set<E>> powerSet2(Set<E> entrySet) {
        if (entrySet.isEmpty()) {
            return Set.of(Set.of());
        }
        Queue<E> restQueue = new ArrayDeque<>(entrySet);
        E element = restQueue.poll();
        return powerSet2(Set.of(), restQueue, element);
    }

    private static <E> Set<Set<E>> powerSet2(Set<E> prevSet, Queue<E> restQueue, E element) {
        if (restQueue.isEmpty()) {
            Set<E> withElement = new HashSet<>(prevSet);
            withElement.add(element);
            Set<Set<E>> toRet = new HashSet<>();
            toRet.add(prevSet);
            toRet.add(withElement);
            return toRet;
        } else {
            Set<E> withoutElementSet = Set.copyOf(prevSet);
            Set<E> withElementSet = new HashSet<>(prevSet);
            withElementSet.add(element);
            Queue<E> newRestQueue = new ArrayDeque<>(restQueue);
            E newElement = newRestQueue.poll();
            Set<Set<E>> withElementResult = powerSet2(withoutElementSet, newRestQueue, newElement);
            Set<Set<E>> withoutElementResult = powerSet2(withElementSet, newRestQueue, newElement);
            withElementResult.addAll(withoutElementResult);
            return withElementResult;
        }
    }

    public static <E> Set<Set<E>> powerSetStackOverFlowImpl(Set<E> originalSet) {
        Set<Set<E>> sets = new HashSet<>();
        if (originalSet.isEmpty()) {
            sets.add(new HashSet<>());
            return sets;
        }
        List<E> list = new ArrayList<>(originalSet);
        E head = list.get(0);
        Set<E> rest = new HashSet<E>(list.subList(1, list.size()));
        for (Set<E> set : powerSetStackOverFlowImpl(rest)) {
            Set<E> newSet = new HashSet<E>();
            newSet.add(head);
            newSet.addAll(set);
            sets.add(newSet);
            sets.add(set);
        }
        return sets;
    }
}
