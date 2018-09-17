package me.training.tree;

import java.util.Optional;

public class Node<T> {
    private Node<T> left;
    private Node<T> right;
    private T value;

    private Node(T value) {
        this.value = value;
    }

    private Node(Node<T> left, Node<T> right, T value) {
        this.left = left;
        this.right = right;
        this.value = value;
    }

    public static <T> Node<T> withBoth(T value, Node<T> left, Node<T> right) {
        return new Node<>(left, right, value);
    }

    public static <T> Node<T> withZero(T value) {
        return new Node<>(value);
    }


    public static <T> Node<T> withLeft(T value, Node<T> left) {
        return withBoth(value, left, null);
    }

    public static <T> Node<T> withRight(T value, Node<T> right) {
        return withBoth(value, null, right);
    }


    public Optional<Node<T>> left() {
        return Optional.ofNullable(left);
    }

    public Optional<Node<T>> right() {
        return Optional.ofNullable(right);
    }

    public T value() {
        return value;
    }
}
