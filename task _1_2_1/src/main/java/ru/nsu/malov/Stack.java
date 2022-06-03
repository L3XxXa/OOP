package ru.nsu.malov;

import java.util.Arrays;
import java.util.Iterator;

public class Stack<T> implements Iterable<T> {
    private int size = 0;
    private int stackCapacity = 10;
    private T[] stackArr;
    final private int NEW_LENGTH = 2;

    private void newSize(int newSize) {
        while (stackArr.length < newSize) {
            stackArr = Arrays.copyOf(stackArr, stackArr.length * NEW_LENGTH);
        }
    }

    @SuppressWarnings("uncheked")
    public Stack() {
        stackArr = (T[]) new Object[stackCapacity];
    }

    public int count() {
        return size;
    }

    public void push(T x) {
        newSize(size + 1);
        stackArr[size] = x;
        size++;
    }

    public void pushStack(Stack<T> stack) {
        newSize(size + stack.size);
        System.arraycopy(stack.stackArr, 0, stackArr, size, stack.size);
        size += stack.size;
    }

    public T pop() {
        if (size <= 0) {
            throw new IndexOutOfBoundsException();
        }
        size--;
        return stackArr[size];
    }

    public Stack<T> popStack(int popSize) {
        if (popSize > size || size < 0) {
            throw new IndexOutOfBoundsException();
        }
        newSize(popSize);
        Stack<T> stack2 = new Stack<>();
        System.arraycopy(stackArr, size-popSize, stack2.stackArr, 0, popSize);
        size = size - popSize;
        stack2.size = popSize;
        return stack2;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int idx = 0;

            @Override
            public boolean hasNext() {
                return !(idx == size);
            }

            @Override
            public T next() throws IndexOutOfBoundsException {
                return stackArr[idx++];
            }
        };
    }
}
