package me.training.whiteboard;

import com.google.common.collect.Sets;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ExtendWith(AverageExecutionTimeLoggerExtension.class)
interface TimingPowerSetTest extends Function<Set<String>, Set<Set<String>>> {

    @RepeatedTest(10)
    default void testBenchmarkPowerSets() {
        Set<String> set = Set.of("qwertyuiopasdfg".split(""));
        Set<Set<String>> result = apply(set);
        Set<Set<String>> allSets = new HashSet<>();
        result.forEach(allSets::add); //iterate over all produced sets to measure lazy Guava implementation also
    }
}


class RecursiveImpl1PowerSetTimingTest implements TimingPowerSetTest {
    @Override
    public Set<Set<String>> apply(Set<String> initialSet) {
        return PowerSetRecursiveImplementations.powerSet1(initialSet);
    }
}

class RecursiveImpl2PowerSetTimingTest implements TimingPowerSetTest {
    @Override
    public Set<Set<String>> apply(Set<String> initialSet) {
        return PowerSetRecursiveImplementations.powerSet2(initialSet);
    }
}

class GuavaPowerSetTimingTest implements TimingPowerSetTest {
    @Override
    public Set<Set<String>> apply(Set<String> initialSet) {
        return Sets.powerSet(initialSet);
    }
}

class IterativePowerSetImplementationTimingTest implements TimingPowerSetTest {
    @Override
    public Set<Set<String>> apply(Set<String> initialSet) {
        return PowerSetIterativeImplementation.powerSet3(initialSet);
    }
}

class RecursiveStackOverflowImplPowerSetTimingTest implements TimingPowerSetTest {
    @Override
    public Set<Set<String>> apply(Set<String> initialSet) {
        return PowerSetRecursiveImplementations.powerSetStackOverFlowImpl(initialSet);
    }
}

class StreamImplTimingTest implements TimingPowerSetTest {
    @Override
    public Set<Set<String>> apply(Set<String> initialSet) {
        Stream<Set<String>> powerSetStream = PowerSetStream.of(initialSet);
        return powerSetStream.collect(Collectors.toSet());
    }
}

