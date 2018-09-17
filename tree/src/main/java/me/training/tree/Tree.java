package me.training.tree;

import java.util.ArrayDeque;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Tree {

    public static int height(Node<?> node) {
        return height(Optional.ofNullable(node));
    }


    public static <T> void deepWalk(Node<T> rootNode, Consumer<Node<T>> consumer) {
        consumer.accept(rootNode);
        rootNode.left().ifPresent(node -> deepWalk(node, consumer));
        rootNode.right().ifPresent(node -> deepWalk(node, consumer));
    }

    public static <T> Optional<Node<T>> deepSearch(Node<T> rootNode, Predicate<T> valuePredicate) {
        if (valuePredicate.test(rootNode.value())) {
            return Optional.of(rootNode);
        }
        if(rootNode.left().isPresent()){
            Optional<Node<T>> result = deepSearch(rootNode.left().get(), valuePredicate);
            if(result.isPresent()){
                return result;
            }
        }

        if(rootNode.right().isPresent()){
            Optional<Node<T>> result = deepSearch(rootNode.right().get(), valuePredicate);
            if(result.isPresent()){
                return result;
            }
        }
        return Optional.empty();
    }


    public static <T> void breadthWalk(Node<T> rootNode, Consumer<Node<T>> consumer) {
        Queue<Node<T>> queue = new ArrayDeque<>();
        queue.add(rootNode);
        while (!queue.isEmpty()) {
            Node<T> node = queue.remove();
            consumer.accept(node);
            node.left().ifPresent(queue::add);
            node.right().ifPresent(queue::add);
        }
    }

    public static <T> Optional<Node<T>> breadthSearch(Node<T> rootNode, Predicate<T> valuePredicate) {
        Queue<Node<T>> queue = new ArrayDeque<>();
        queue.add(rootNode);
        while (!queue.isEmpty()) {
            Node<T> node = queue.remove();
            if (valuePredicate.test(node.value())) {
                return Optional.of(node);
            }
            node.left().ifPresent(queue::add);
            node.right().ifPresent(queue::add);
        }
        return Optional.empty();
    }


    public static <T> int size(Node<T> rootNode) {
        AtomicInteger atomicInteger = new AtomicInteger();
        breadthWalk(rootNode, (n) -> atomicInteger.incrementAndGet());
        return atomicInteger.get();
    }

    public static<T> int countUniversalValueTreesForSubTree(Node<T> node, T value){
        int result = universalValueTrees(node);
        if(value== node.value()){
            result++;
        }
        return result;
    }

    public static <T>int universalValueTrees(Node<T> rootNode){
        Optional<Node<T>> leftNode = rootNode.left();
        Optional<Node<T>> rightNode = rootNode.right();

        if(!rightNode.isPresent() && !leftNode.isPresent()){
            return 1;
        }

        if(leftNode.isPresent() && !rightNode.isPresent()){
            return countUniversalValueTreesForSubTree(leftNode.get(), rootNode.value());
        }

        if(rightNode.isPresent() && !leftNode.isPresent()){
            return countUniversalValueTreesForSubTree(rightNode.get(), rootNode.value());
        }
        
        if(rightNode.isPresent() && leftNode.isPresent()){
            int leftResult = universalValueTrees(leftNode.get());
            int rightResult = universalValueTrees(rightNode.get());
            int result = leftResult+rightResult;
            if(rootNode.value()== rightNode.get().value() && rootNode.value()== leftNode.get().value() && leftResult>0 && rightResult>0){
                result++;
            }
            return result;
        }
        return 0;
    }




    private static int height(Optional<? extends Node<?>> rootNode) {
        return rootNode.map(node -> 1 + Integer.max(height(node.left()), height(node.right()))).orElse(0);
    }
}
