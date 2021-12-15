package ru.nsu.malov;

import java.util.ArrayList;
import java.util.List;

public class Node<T> {
    private List<Node<T>> children;
    private T data;
    private Node<T> parent;

    Node(T data, Node<T> parent){
        this.data = data;
        this.parent = parent;
        children = new ArrayList<>();
    }
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<Node<T>> getChildren() {
        return children;
    }

    public void setChildren(List<Node<T>> children) {
        this.children = children;
    }

    public void addChild(Node<T> child){
        children.add(child);
    }

    @Override
    public String toString() {
        return "Node {" +
                "children=" + children +
                ", data=" + data +
                '}';
    }

    public void removeChild (Node<T> elem){
        children.remove(elem);
    }
}
