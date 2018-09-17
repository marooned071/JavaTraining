package me.training.tree;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NodeEntry<T> {
    private Integer id;
    private T value;

    private NodeEntry(Integer id, T value) {
        this.id = id;
        this.value = value;
    }

    public static <T> NodeEntry<T> of(Integer id, T value) {
        return new NodeEntry<>(id, value);
    }

    public Integer getId() {
        return id;
    }

    public T getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NodeEntry<?> nodeEntry = (NodeEntry<?>) o;
        return Objects.equals(id, nodeEntry.id) &&
                Objects.equals(value, nodeEntry.value);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, value);
    }

    @Override
    public String toString() {
        return "[" + id + " = " + value + "]";
    }
}

class TreeTest {

    private static final Node<NodeEntry<String>> SAMPLE_TREE =
            Node.withBoth(NodeEntry.of(0, "A"),
                    Node.withBoth(NodeEntry.of(1, "B"),
                            Node.withBoth(NodeEntry.of(2, "C"),
                                    Node.withZero(NodeEntry.of(3, "D")),
                                    Node.withZero(NodeEntry.of(4, "E"))
                            ),
                            Node.withRight(NodeEntry.of(5, "F"),
                                    Node.withRight(NodeEntry.of(6, "G"),
                                            Node.withZero(NodeEntry.of(12, "L")))
                            )

                    ),
                    Node.withRight(NodeEntry.of(7, "C"),
                            Node.withBoth(NodeEntry.of(8, "G"),
                                    Node.withZero(NodeEntry.of(9, "J")),
                                    Node.withZero(NodeEntry.of(11, "L"))
                            )

                    )
            );

    private static final Node<NodeEntry<String>> FULL_LEFT_TREE =
            Node.withLeft(NodeEntry.of(0, "A"),
                    Node.withLeft(NodeEntry.of(1, "A"),
                            Node.withLeft(NodeEntry.of(2, "A"),
                                    Node.withLeft(NodeEntry.of(3, "A"),
                                            Node.withLeft(NodeEntry.of(4, "A"),
                                                    Node.withZero(NodeEntry.of(5, "A")))))));


    private static final Node<Integer> ONE_NODE_TREE = Node.withZero(0);

    private static final Node<NodeEntry> BALANCED_TREE = Node.withBoth(NodeEntry.of(0, "A"),
            Node.withBoth(NodeEntry.of(1, "A"),
                    Node.withBoth(NodeEntry.of(3, "A"),
                            Node.withBoth(NodeEntry.of(7, "A"),
                                    Node.withZero(NodeEntry.of(15, "A")),
                                    Node.withZero(NodeEntry.of(16, "A"))),
                            Node.withBoth(NodeEntry.of(8, "A"),
                                    Node.withZero(NodeEntry.of(17, "A")),
                                    Node.withZero(NodeEntry.of(18, "A")))),
                    Node.withBoth(NodeEntry.of(4, "A"),
                            Node.withBoth(NodeEntry.of(9, "A"),
                                    Node.withZero(NodeEntry.of(19, "A")),
                                    Node.withZero(NodeEntry.of(20, "A"))),
                            Node.withBoth(NodeEntry.of(10, "A"),
                                    Node.withZero(NodeEntry.of(21, "A")),
                                    Node.withZero(NodeEntry.of(22, "A"))))),
            Node.withBoth(NodeEntry.of(2, "A"),
                    Node.withBoth(NodeEntry.of(5, "A"),
                            Node.withBoth(NodeEntry.of(11, "A"),
                                    Node.withZero(NodeEntry.of(23, "A")),
                                    Node.withZero(NodeEntry.of(24, "A"))),
                            Node.withBoth(NodeEntry.of(12, "A"),
                                    Node.withZero(NodeEntry.of(25, "A")),
                                    Node.withZero(NodeEntry.of(26, "A")))),
                    Node.withBoth(NodeEntry.of(6, "A"),
                            Node.withBoth(NodeEntry.of(13, "A"),
                                    Node.withZero(NodeEntry.of(27, "A")),
                                    Node.withZero(NodeEntry.of(28, "A"))),
                            Node.withBoth(NodeEntry.of(14, "A"),
                                    Node.withZero(NodeEntry.of(26, "A")),
                                    Node.withZero(NodeEntry.of(30, "A"))))));


    @Test
    void testHeight() {
        assertEquals(5, Tree.height(SAMPLE_TREE));
        assertEquals(6, Tree.height(FULL_LEFT_TREE));
        assertEquals(1, Tree.height(ONE_NODE_TREE));
        assertEquals(5, Tree.height(BALANCED_TREE));
    }

    @Test
    void testDeepSearch() {
        Optional<Node<NodeEntry<String>>> result = Tree.deepSearch(SAMPLE_TREE, l -> l.getValue().equals("L"));
        assertTrue(result.isPresent());
        assertEquals(12, result.get().value().getId().intValue());
    }

    @Test
    void testDeepWalk() {

        List<NodeEntry<String>> nodeList = new ArrayList<>();
        Consumer<Node<NodeEntry<String>>> nodeConsumer = (node -> nodeList.add(node.value()));
        Tree.deepWalk(SAMPLE_TREE, nodeConsumer);
        assertEquals(List.of(
                NodeEntry.of(0, "A"),
                NodeEntry.of(1, "B"),
                NodeEntry.of(2, "C"),
                NodeEntry.of(3, "D"),
                NodeEntry.of(4, "E"),
                NodeEntry.of(5, "F"),
                NodeEntry.of(6, "G"),
                NodeEntry.of(12, "L"),
                NodeEntry.of(7, "C"),
                NodeEntry.of(8, "G"),
                NodeEntry.of(9, "J"),
                NodeEntry.of(11, "L")), nodeList);

    }

    @Test
    void tetBreadthWalk() {
        List<NodeEntry<String>> nodeList = new ArrayList<>();
        Consumer<Node<NodeEntry<String>>> nodeConsumer = (node -> nodeList.add(node.value()));
        Tree.breadthWalk(SAMPLE_TREE, nodeConsumer);
        assertEquals(List.of(
                NodeEntry.of(0, "A"),
                NodeEntry.of(1, "B"),
                NodeEntry.of(7, "C"),
                NodeEntry.of(2, "C"),
                NodeEntry.of(5, "F"),
                NodeEntry.of(8, "G"),
                NodeEntry.of(3, "D"),
                NodeEntry.of(4, "E"),
                NodeEntry.of(6, "G"),
                NodeEntry.of(9, "J"),
                NodeEntry.of(11, "L"),
                NodeEntry.of(12, "L")), nodeList);
    }

    @Test
    void testBreadthSearch() {
        Optional<Node<NodeEntry<String>>> result = Tree.breadthSearch(SAMPLE_TREE, l -> l.getValue().equals("L"));
        assertTrue(result.isPresent());
        assertEquals(11, result.get().value().getId().intValue());
    }

    @Test
    void testSize() {
        int size = Tree.size(SAMPLE_TREE);
        assertEquals(12, size);
    }

    @Test
    void universalValueTreeTest() {
        Node<Integer> tree5 = Node.withBoth(0,
                Node.withZero(1),
                Node.withBoth(0,
                        Node.withBoth(1, Node.withZero(1), Node.withZero(1)),
                        Node.withZero(0)
                ));

        assertEquals(5, Tree.universalValueTrees(tree5));


        Node<Integer> tree7 = Node.withBoth(1,
                Node.withLeft(0,
                        Node.withLeft(0,
                                Node.withZero(0))),
                Node.withBoth(0,
                        Node.withLeft(1,
                                Node.withZero(0)),
                        Node.withBoth(1,
                                Node.withLeft(1,
                                        Node.withZero(1)),
                                Node.withZero(0))));

        assertEquals(7, Tree.universalValueTrees(tree7));

        Node<Integer> three1 = Node.withZero(1);
        assertEquals(1, Tree.universalValueTrees(three1));

        Node<Integer> treeAllTheSame = Node.withBoth(0,
                Node.withBoth(0,
                        Node.withZero(0),
                        Node.withZero(0)),
                Node.withBoth(0,
                        Node.withZero(0),
                        Node.withZero(0)));

        assertEquals(7, Tree.universalValueTrees(treeAllTheSame));
    }


}