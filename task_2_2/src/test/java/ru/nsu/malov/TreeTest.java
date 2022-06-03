package ru.nsu.malov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TreeTest {
    private Tree<Integer> tree;

    @BeforeEach
    private void init(){
        tree = new Tree<>();
    }

    @Test
    public void addContains(){
        Integer value = 10;
        tree.add(value);
        Assertions.assertTrue(tree.contains(10));
    }

    @Test
    public void removeContains(){
        Integer value = 10;
        tree.add(value);
        tree.remove(value);
        Assertions.assertFalse(tree.contains(10));

    }

    @Test
    public void addAllContainsAll(){
        Integer[] values = new Integer[] {10, 11, 12, 13};
        Integer[] sentinel = new Integer[] {10, 11, 12, 13};
        tree.addAll(Arrays.asList(values));
        Assertions.assertTrue(tree.containsAll(Arrays.asList(sentinel)));
    }

    @Test
    public void removeAllContainsAll(){
        Integer[] values = new Integer[] {10, 11, 12, 13};
        Integer[] sentinel = new Integer[] {10, 11, 12, 13};
        tree.addAll(Arrays.asList(values));
        tree.removeAll(Arrays.asList(values));
        Assertions.assertEquals(tree.size(), 0);
    }

    @Test
    public void size(){
        Integer value = 10;
        tree.add(value);
        Assertions.assertEquals(tree.size(), 1);
    }

    @Test
    public void isEmpty(){
        Integer value = 10;
        tree.add(value);
        tree.remove(10);
        Assertions.assertTrue(tree.isEmpty());
    }

}