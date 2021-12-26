package ru.nsu.malov;

import java.util.*;

public class Tree<T> implements Collection<T> {
    private List<Tree<T>> children;
    private T root;
    private int size = 0;

    /**
     * Initialize children
     */
    Tree() {
        children = new ArrayList<>();
    }

    /**
     * Get size of the tree
     *
     * @return size - size of the tree
     */
    @Override
    public int size() {
        for (Tree<T> child : children) {
            size += child.size();
        }
        if (this.root != null) {
            size++;
        }
        return size;
    }

    /**
     * Check is tree empty
     *
     * @return true if it is empty
     */
    @Override
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Check is tree contains an element
     *
     * @param o - element to check
     * @return true - there is an element in the tree
     */
    @Override
    public boolean contains(Object o) {
        if (o.equals(root)) {
            return true;
        }
        for (Tree<T> node : children) {
            if (node.contains(o)) {
                return true;
            }
        }
        return false;
    }

    /**
     * New Breadth-first search iterator
     *
     * @return iterator - new iterator
     */
    @Override
    public Iterator<T> iterator() {
        return BfsIterator();
    }

    private Iterator<T> BfsIterator() {
        Iterator<T> iterator = new Iterator<>() {
            private int size = size();
            private int i = 0;
            private Queue<Tree<T>> queue = new ArrayDeque<>();
            private Set<Tree<T>> coloredVertices = new HashSet<>();

            /**
             * Check is there a next element
             * @return true if there is a next element
             * */
            @Override
            public boolean hasNext() {
                return i < size;
            }

            /**
             * Get next element
             * @return next element
             * */
            @Override
            public T next() {
                Tree<T> nextElem;
                if (!hasNext()) {
                    throw new IndexOutOfBoundsException();
                }
                if (!queue.isEmpty()) {
                    nextElem = queue.poll();
                } else {
                    nextElem = Tree.this;
                    coloredVertices.add(nextElem);
                }
                for (Tree<T> nodes : nextElem.children) {
                    if (!coloredVertices.contains(nodes)) {
                        coloredVertices.add(nodes);
                        queue.offer(nodes);
                    }
                }
                i++;
                return nextElem.root;
            }
        };
        return iterator;
    }

    /**
     * Migrate collection to the array
     *
     * @return array with elements from the tree
     */
    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size];
        int i = 0;
        for (T t : this) {
            arr[i] = t;
            i++;
        }
        return arr;
    }

    /**
     * Migrate collection to the array from signature (or reallocate if there is no enough space)
     *
     * @param t1s - array to add
     * @return array with elements from the tree
     */
    @Override
    public <T1> T1[] toArray(T1[] t1s) {
        int arrSize = size();
        int i = 0;
        if (t1s == null) {
            throw new NoSuchElementException();
        }
        if (arrSize > t1s.length) {
            t1s = (T1[]) new Object[size()];
        }
        for (T t : this) {
            t1s[i] = (T1) t;
            i++;
        }
        return t1s;
    }

    /**
     * Item to add to the tree
     *
     * @param t - item to add
     * @return true - if element was added successfully
     */
    @Override
    public boolean add(T t) {
        Tree<T> sub = new Tree<>();
        if (isEmpty()) {
            root = t;
            return true;
        }
        if (contains(t)) {
            return false;
        }
        sub.add(t);
        children.add(sub);
        return true;
    }

    /**
     * Remove element from the tree
     *
     * @param o - element to remove
     * @return true - removed successfully
     */
    @Override
    public boolean remove(Object o) {
        if (!contains(o)) {
            return false;
        }
        if (o.equals(root)) {
            clear();
            return true;
        }
        Queue<Tree<T>> queue = new ArrayDeque<>();
        Tree<T> tree;
        List<Tree<T>> child;
        T childRoot;
        while (!queue.isEmpty()) {
            tree = queue.poll();
            child = tree.children;
            for (Tree<T> nodes : child) {
                childRoot = nodes.root;
                if (o.equals(childRoot)) {
                    child.remove(o);
                    return true;
                }
                queue.offer(nodes);
            }
        }
        return false;
    }

    /**
     * Check are elements from collection in tree
     *
     * @param collection - collection to check
     * @return true - all elements are in tree
     * @return false - some elements aren't in tree
     */
    @Override
    public boolean containsAll(Collection<?> collection) {
        if (root == null) {
            return false;
        }
        if (collection == null) {
            return false;
        }
        for (Object o : collection) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Add whole collection of elements to the tree
     *
     * @param collection - collection to add
     * @return true - added successfully
     */
    @Override
    public boolean addAll(Collection<? extends T> collection) {
        if (collection == null) {
            return true;
        }
        for (T t : collection) {
            if (!add(t)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Remove from tree collection of elements
     *
     * @param collection - elements to remove
     * @return true - removed successfully
     */
    @Override
    public boolean removeAll(Collection<?> collection) {
        if (collection == null) {
            return true;
        }
        for (Object o : collection) {
            if (!remove(o)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Retain in tree collection of elements
     *
     * @param collection - elements to retain
     * @return true - tree changed after retaining
     */
    @Override
    public boolean retainAll(Collection<?> collection) {
        if (collection == null) {
            return true;
        }
        if (root == null) {
            return false;
        }
        Queue<Tree<T>> queue = new ArrayDeque<>();
        Tree<T> tree;
        int newSize = size();
        List<Tree<T>> child;
        T childRoot;
        while (!queue.isEmpty()) {
            tree = queue.poll();
            child = tree.children;
            for (Tree<T> nodes : child) {
                childRoot = nodes.root;
                if (!collection.contains(childRoot)) {
                    child.remove(nodes);
                    newSize--;
                }
                queue.offer(nodes);
            }
        }
        return size() != newSize;
    }

    /**
     * Clear tree.
     */
    @Override
    public void clear() {
        children.clear();
        root = null;
    }

    /**
     * Add element
     *
     * @param child  - child
     * @param parent = parent
     * @return true if added successfully
     */
    public Boolean addElement(T child, T parent) {
        if (contains(child) || !contains(parent)) {
            return false;
        }
        Stack<Tree<T>> stack = new Stack<>();
        Tree<T> subtree;
        stack.push(this);
        while (!stack.isEmpty()) {
            Tree<T> tree = stack.pop();
            if (parent.equals(tree.root)) {
                subtree = new Tree<>();
                tree.children.add(subtree);
                return true;
            }
            for (Tree<T> nodes : tree.children) {
                stack.push(nodes);
            }
        }
        return false;
    }

    /**
     * Get subtree
     * @param t - root of subtree
     * @return subtree
     * */
    public Tree<T> getTree(T t){
        Queue<Tree<T>> queue = new ArrayDeque<>();
        queue.offer(this);
        while (!queue.isEmpty()){
            Tree<T> tree = queue.poll();
            for (Tree<T> nodes: tree.children) {
                if (t.equals(nodes.root)){
                    return nodes;
                }
                queue.offer(nodes);
            }
        }
        throw new NoSuchElementException();
    }

}


