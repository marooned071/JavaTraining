package me.training.whiteboard;

import com.google.common.collect.Sets;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Set;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(AverageExecutionTimeLoggerExtension.class)
interface TimingPowerSetTest extends Function<Set<String>, Set<Set<String>>> {

    @RepeatedTest(10)
    default void testBenchmarkPowerSets() {
        Set<String> set = Set.of("qwertyuiopasdfg".split(""));
        apply(set);
    }
}

interface PowerSetTest extends Function<Set<String>, Set<Set<String>>> {

    @Test
    default void testEmptySet() {
        Set<String> emptySet = Set.of();
        assertEquals(Set.of(Set.of()), apply(emptySet));
    }

    @Test
    default void testSingleElementsSet() {
        Set<String> singleElementSet = Set.of("a");
        assertEquals(Set.of(Set.of(), Set.of("a")), apply(singleElementSet));
    }

    @Test
    default void testTwoElementsSet() {
        Set<String> twoElementSet = Set.of("a", "b");
        assertEquals(Set.of(Set.of(),
                Set.of("a"), Set.of("b"),
                Set.of("a", "b")), apply(twoElementSet));
    }

    @Test
    default void testTreeElementsSet() {
        Set<String> threeElementsSet = Set.of("a", "b", "c");
        Set<Set<String>> powerSet = apply(threeElementsSet);
        assertEquals(Set.of(Set.of(),
                Set.of("a"), Set.of("b"), Set.of("c"),
                Set.of("a", "b"), Set.of("a", "c"), Set.of("b", "c"),
                Set.of("a", "b", "c")), powerSet);

    }
}

class RecursiveImpl1PowerSetTest implements PowerSetTest, TimingPowerSetTest {
    @Override
    public Set<Set<String>> apply(Set<String> initialSet) {
        return PowerSetRecursiveImplementations.powerSet1(initialSet);
    }
}

class RecursiveImpl2PowerSetTest implements PowerSetTest, TimingPowerSetTest {
    @Override
    public Set<Set<String>> apply(Set<String> initialSet) {
        return PowerSetRecursiveImplementations.powerSet2(initialSet);
    }
}

class GuavaPowerSetTest implements PowerSetTest, TimingPowerSetTest {
    @Override
    public Set<Set<String>> apply(Set<String> initialSet) {
        return Sets.powerSet(initialSet);
    }
}

class IterativePowerSetImplementationTest implements PowerSetTest, TimingPowerSetTest {
    @Override
    public Set<Set<String>> apply(Set<String> initialSet) {
        return PowerSetIterativeImplementation.powerSet3(initialSet);
    }
}

class RecursiveStackOverflowImplPowerSetTest implements PowerSetTest, TimingPowerSetTest {
    @Override
    public Set<Set<String>> apply(Set<String> initialSet) {
        return PowerSetRecursiveImplementations.powerSetStackOverFlowImpl(initialSet);
    }
}
@ExtendWith(AverageExecutionTimeLoggerExtension.class)
class GuavaPowerSetTest2 {
    @RepeatedTest(10)
    void testGuavaImpl() {
        Set<String> set = Set.of("qwertyuiopasdfghjklzxcvbnm".split(""));
        Set<Set<String>> result = Sets.powerSet(set);
        assertEquals((int) Math.pow(2,set.size()), result.size());
    }
}