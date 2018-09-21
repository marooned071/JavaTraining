package me.training.whiteboard;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PowerSetIterativeImplementationTest {

    @Test
    void testCopyElementsFromSetByBitmask1() {
        int bitmask = 1; // 0000-0001
        List<String> list = List.of("a", "b", "c", "d", "e", "f", "g", "h");
        assertEquals(Set.of("a"), PowerSetIterativeImplementation.copyElementsFromListByBitMask(list, bitmask));
    }

    @Test
    void testCopyElementsFromSetByBitmask255() {
        int bitmask = 255; // 1111-1111
        List<String> list = List.of("a", "b", "c", "d", "e", "f", "g", "h");
        assertEquals(Set.of("a", "b", "c", "d", "e", "f", "g", "h"), PowerSetIterativeImplementation.copyElementsFromListByBitMask(list, bitmask));
    }

    @Test
    void testCopyElementsFromSetByBitmask111() {
        int bitmask = 111; // 0110-1111
        List<String> list = List.of("a", "b", "c", "d", "e", "f", "g", "h");
        assertEquals(Set.of("a", "b", "c", "d", "f", "g"), PowerSetIterativeImplementation.copyElementsFromListByBitMask(list, bitmask));
    }
}