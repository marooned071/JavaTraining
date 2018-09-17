package me.training.whiteboard;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

class PowerSet {


    public static <T> Set<Set<T>> powerSet(Set<T> initialSet) {
        if (initialSet.isEmpty()) {
            return Set.of(Set.of());
        } else {
            T element = findElementInSet(initialSet);
            Set<T> withoutElement = removeElementFromSet(initialSet, element);
            Set<Set<T>> result = powerSet(withoutElement);
            Set<Set<T>> withElement = addElementToEverySet(element, result);
            return sumSets(result, withElement);
        }
    }

    private static <T> Set<T> sumSets(Set<T> setOne, Set<T> setTwo) {
        HashSet<T> sumSet = new HashSet<>();
        sumSet.addAll(setOne);
        sumSet.addAll(setTwo);
        return sumSet;
    }

    private static <T> Set<T> removeElementFromSet(Set<T> set, T element) {
        return set.stream().filter(e -> !e.equals(element)).collect(Collectors.toSet());
    }

    private static <T> T findElementInSet(Set<T> set) {
        return set.stream().findAny().orElseThrow();
    }

    private static <T> Set<Set<T>> addElementToEverySet(T element, Set<Set<T>> setOfSets) {
        return setOfSets.stream().map(s -> {
            Set<T> newSet = new HashSet<>(s);
            newSet.add(element);
            return newSet;
        }).collect(Collectors.toSet());
    }
}
