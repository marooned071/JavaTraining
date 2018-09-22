package me.training.whiteboard;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SetFunTest {

    @Test
    void testNumOfSubsets() {
        Set<Integer> initialSubSet = Set.of(2,4,6,10);
        int n = 16;

        assertEquals(2, SetFun.numberOfSets(initialSubSet, n));
    }

    @Test
    void testNumOfSubsets2() {
        Set<Integer> initialSubSet = Set.of(2,3,6,7,8);
        int n = 13;

        assertEquals(2, SetFun.numberOfSets(initialSubSet, n));
    }

    @Test
    void testNumOfSubsets3() {
        Set<Integer> initialSubSet = Set.of(2,3,6,7,8,10,11,21,22,50,123,125,250);
        int n = 121;

        assertEquals(5, SetFun.numberOfSets(initialSubSet, n));
    }

    @Test
    void testNumOfSubsets4() {
        Set<Integer> initialSubSet = Set.of(1,2,4,6,8,10,20,30,35,45,50);
        int n = 100;

        assertEquals(19, SetFun.numberOfSets(initialSubSet, n));
    }
}