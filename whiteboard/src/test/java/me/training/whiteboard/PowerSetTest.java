package me.training.whiteboard;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PowerSetTest {


    @Test
    void testEmptySet() {
        Set<Object> emptySet = Set.of();
        assertEquals(Set.of(Set.of()), PowerSet.powerSet(emptySet));
    }

    @Test
    void testSingleElementSet() {
        Set<String> singleElementSet = Set.of("a");
        assertEquals(Set.of(Set.of(), Set.of("a")), PowerSet.powerSet(singleElementSet));
    }

    @Test
    void testTwoElementSet() {
        Set<String> twoElementSet = Set.of("a", "b");
        assertEquals(Set.of(Set.of(),
                Set.of("a"), Set.of("b"),
                Set.of("a", "b")), PowerSet.powerSet(twoElementSet));
    }

    @Test
    void powerSetTest() {
        Set<String> threeElementsSet = Set.of("a", "b", "c");
        Set<Set<String>> powerSet = PowerSet.powerSet(threeElementsSet);
        assertEquals(Set.of(Set.of(),
                Set.of("a"), Set.of("b"), Set.of("c"),
                Set.of("a", "b"), Set.of("a", "c"), Set.of("b", "c"),
                Set.of("a", "b", "c")), powerSet);

    }
}