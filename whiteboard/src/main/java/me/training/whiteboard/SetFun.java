//package me.training.whiteboard;
//
//import java.util.ArrayDeque;
//import java.util.HashSet;
//import java.util.Queue;
//import java.util.Set;
//
//public class SetFun {
//
////    Find number of Sets Of Numbers That Add Up N
//    public static int numberOfSets(Set<Integer> set, int n){
//        if (set.isEmpty()) {
//            return 1;
//        }
//        Queue<Integer> restQueue = new ArrayDeque<>(set);
//        int element = restQueue.poll();
//        return powerSet2(Set.of(), restQueue, element, n);
//    }
//
//
//    private static <E> Set<Set<E>> powerSet2(Set<Integer> prevSet, Queue<Integer> restQueue, int element, int n) {
//        if (restQueue.isEmpty()) {
//
//            int sum1 = prevSet.stream().mapToInt(i->i).sum();
//            Set<E> withElement = new HashSet<>(prevSet);
//            withElement.add(element);
//            Set<Set<E>> toRet = new HashSet<>();
//            toRet.add(prevSet);
//            toRet.add(withElement);
//            return toRet;
//        } else {
//            Set<E> withoutElementSet = Set.copyOf(prevSet);
//            Set<E> withElementSet = new HashSet<>(prevSet);
//            withElementSet.add(element);
//            Queue<E> newRestQueue = new ArrayDeque<>(restQueue);
//            E newElement = newRestQueue.poll();
//            Set<Set<E>> withElementResult = powerSet2(withoutElementSet, newRestQueue, newElement);
//            Set<Set<E>> withoutElementResult = powerSet2(withElementSet, newRestQueue, newElement);
//            withElementResult.addAll(withoutElementResult);
//            return withElementResult;
//        }
//    }
//}
