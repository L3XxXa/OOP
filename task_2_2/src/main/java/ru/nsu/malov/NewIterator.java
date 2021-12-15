package ru.nsu.malov;

import java.util.Iterator;
import java.util.List;

public class NewIterator<T> implements Iterator<T> {
    private int nodes;
    private int currNode;
    private List<Node<T>> tree;
    private Tree<T> root;
    private Node<T> last;

    @Override
    public boolean hasNext() {
        return nodes == currNode;
    }

    @Override
    public T next() {
        last = tree.get(currNode);
        return tree.get(currNode++).getData();
    }

    public int getNodes() {
        return nodes;
    }

    public void setNodes(int nodes) {
        this.nodes = nodes;
    }

    public int getCurrNode() {
        return currNode;
    }

    public void setCurrNode(int currNode) {
        this.currNode = currNode;
    }
}
