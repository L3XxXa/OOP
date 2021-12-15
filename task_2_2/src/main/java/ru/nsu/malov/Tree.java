package ru.nsu.malov;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Tree<T> implements Iterable<T>{
    private Node<T> root;
    private List<Node<T>> children;
    private final NewIterator<T> iterator;

    public Tree(){
        iterator = this.iterator();
    }

    @Override
    public NewIterator<T> iterator() {
        return new NewIterator<>();
    }

    public List<Node<T>> getChildren() {
        return children;
    }

    public void remove(T elem){
        if (root == null){
            throw new NoSuchElementException();
        }
        for (Node<T> node:root.getChildren()){
            if (node.getData().equals(elem)){
                root.removeChild(node);
                return;
            }
        }
        throw new NoSuchElementException();
    }

    public int add(T elem, T parent, Node<T>currRoot){
        Node<T> node;
        if (root.getData().equals(parent)){
            node = new Node<>(elem, root);
            root.addChild(node);
            return 1;
        }
        for (Node<T> nodes:root.getChildren()){
            if (add(elem, parent, nodes) == 1){
                return 1;
            }
        }
        return 0;
    }
}


