package ru.nsu.malov;

import java.util.ArrayList;
import java.util.List;

public class Node<T> {
    private List<Node<T>> children;
    private T data;
    private Node<T> parent;

    Node(T data){
        this.data = data;
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
        child.parent = this;
    }

    @Override
    public String toString() {
        return "Node {" +
                "children=" + children +
                ", data=" + data +
                '}';
    }

    public void deleteNode(){
        for (Node<T> node: children) {
            parent.addChild(node);
        }
    }
}
