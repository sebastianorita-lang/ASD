package it.unicam.cs.asdl2526.esamereversebst;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReverseBSTPublicTest {

    @Test
    void emptyTree_basics() {
        ReverseBinarySearchTree<Integer> t = new ReverseBinarySearchTree<>();
        assertTrue(t.isEmpty());
        assertEquals(0, t.size());
        assertEquals(-1, t.getHeight());
        assertNull(t.getMin());
        assertNull(t.getMax());
        assertEquals(Collections.<Integer>emptyList(), t.getOrderedLabels());
    }

    @Test
    void clear_makesTreeEmptyAgain() {
        ReverseBinarySearchTree<Integer> t = sampleTree();
        assertFalse(t.isEmpty());
        t.clear();
        assertTrue(t.isEmpty());
        assertEquals(0, t.size());
        assertEquals(-1, t.getHeight());
        assertNull(t.getMin());
        assertNull(t.getMax());
        assertEquals(Collections.<Integer>emptyList(), t.getOrderedLabels());
    }

    @Test
    void add_null_throws() {
        ReverseBinarySearchTree<Integer> t = new ReverseBinarySearchTree<>();
        assertThrows(NullPointerException.class, () -> t.add(null));
    }

    @Test
    void add_and_size_and_noDuplicates() {
        ReverseBinarySearchTree<Integer> t = new ReverseBinarySearchTree<>();
        assertTrue(t.add(5));
        assertTrue(t.add(2));
        assertTrue(t.add(9));
        assertEquals(3, t.size());

        // duplicato
        assertFalse(t.add(2));
        assertEquals(3, t.size(), "Inserire un duplicato non deve cambiare size");
    }

    @Test
    void contains_basic_and_null_throws() {
        ReverseBinarySearchTree<Integer> t = sampleTree();
        assertTrue(t.contains(7));
        assertTrue(t.contains(1));
        assertFalse(t.contains(42));
        assertThrows(NullPointerException.class, () -> t.contains(null));
    }

    @Test
    void orderedLabels_areInNaturalAscendingOrder() {
        ReverseBinarySearchTree<Integer> t = sampleTree();
        assertEquals(list(1, 2, 3, 5, 6, 7, 8, 9), t.getOrderedLabels());
    }

    @Test
    void minAndMax_basic() {
        ReverseBinarySearchTree<Integer> t = sampleTree();
        assertEquals(Integer.valueOf(1), t.getMin());
        assertEquals(Integer.valueOf(9), t.getMax());
    }

    @Test
    void height_onKnownTree() {
        ReverseBinarySearchTree<Integer> t = sampleTree();
        assertEquals(3, t.getHeight());
    }

    @Test
    void successorPredecessor_exceptionsOnly() {
        ReverseBinarySearchTree<Integer> empty = new ReverseBinarySearchTree<>();

        assertThrows(NullPointerException.class, () -> empty.getSuccessor(null));
        assertThrows(NullPointerException.class, () -> empty.getPredecessor(null));

        // su albero vuoto -> IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> empty.getSuccessor(1));
        assertThrows(IllegalArgumentException.class, () -> empty.getPredecessor(1));

        ReverseBinarySearchTree<Integer> t = sampleTree();
        // label non presente -> IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> t.getSuccessor(42));
        assertThrows(IllegalArgumentException.class, () -> t.getPredecessor(42));
    }

    private static ReverseBinarySearchTree<Integer> sampleTree() {
        ReverseBinarySearchTree<Integer> t = new ReverseBinarySearchTree<>();
        int[] vals = {5, 2, 8, 1, 3, 7, 9, 6};
        for (int v : vals)
            t.add(v);
        return t;
    }

    private static <T> List<T> list(T... items) {
        return Arrays.asList(items);
    }
}