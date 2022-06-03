package ru.nsu.malov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {
    Stack<Integer> stack;

    @BeforeEach
    private void newStack() {
        stack = new Stack<>();
    }

    @Test
    public void push_pop_size_twoElements() {
        stack.push(10);
        stack.push(-12);
        int size = stack.count();
        int x = stack.pop();
        int y = stack.pop();
        Assertions.assertEquals(2, size);
        Assertions.assertEquals(x, -12);
        Assertions.assertEquals(y, 10);
        Assertions.assertEquals(0, stack.count());
    }

    @Test
    public void pushStack_popStack_smallStackToEmptyStack() {
        Stack<Integer> stack2 = new Stack<>();
        stack2.push(34);
        stack2.push(15);
        stack.pushStack(stack2);
        Assertions.assertEquals(15, stack.pop());
        Assertions.assertEquals(34, stack.pop());
        Assertions.assertEquals(0, stack.count());
    }

    @Test
    public void pushStack_popStack_NotEmptyStack() {
        Stack<Integer> stack2 = new Stack<>();
        Iterator<Integer> iterator = stack.iterator();
        stack.push(12);
        stack.push(11);
        stack2.push(10);
        stack2.push(9);
        stack.pushStack(stack2);
        Assertions.assertEquals(4, stack.count());
        Stack<Integer> resStack = stack.popStack(4);
        Assertions.assertEquals(9, resStack.pop());
        Assertions.assertEquals(10, resStack.pop());
        Assertions.assertEquals(11, resStack.pop());
        Assertions.assertEquals(12, resStack.pop());
        Assertions.assertEquals(0, stack.count());
    }

    @Test
    public void push_pop_bigStack() {
        for (int i = 0; i < 300; i++) {
            stack.push(i);
        }
        for (int i = 299; i >= 0; i--) {
            Assertions.assertEquals(i, stack.pop());
        }
    }


    @Test
    public void pop_emptyStack() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            stack.pop();
        });
    }

    @Test
    public void popStack_emptyStack() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            stack.popStack(3);
        });
    }

    @Test
    public void iterator_testMyIterator() {
        Iterator<Integer> iterator = stack.iterator();
        int[] ans = new int[100];
        Iterator<Integer> iteratorForArr = Arrays.stream(ans).iterator();
        for (int i = 0; i < 100; i++) {
            stack.push(i);
            ans[i] = i;
        }
        while (iterator.hasNext()) {
            Assertions.assertEquals(iterator.next(), iteratorForArr.next());
        }
    }
}