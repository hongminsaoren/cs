package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private Node root;
    private int size;
    public BSTMap(){
        clear();
    }

    private class Node {
        K key;
        V value;
        Node left;
        Node right;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }
    public Node search_helper(Node n,K key){
        if(n==null){
            return null;
        }
        else {
            if(n.key.compareTo(key)>0)
                return search_helper(n.left,key);
            else if(n.key.compareTo(key)==0)
                return n;
            else return search_helper(n.right,key);
        }
    }
    @Override
    public boolean containsKey(K key) {
        return search_helper(root,key)!=null;
    }

    @Override
    public V get(K key) {
        if (search_helper(root,key)==null)
            return null;
        return search_helper(root,key).value;
    }

    @Override
    public int size() {
        return size;
    }

    private Node put(Node x, K key, V value) {
        if (x == null) {
            size++;
            return new Node(key, value);
        }

        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, value);
        } else if (cmp > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }

        return x;
    }
    @Override
    public void put(K key, V value) {
        root=put(root,key,value);
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException("keySet is not supported.");
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException("remove is not supported.");
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException("remove is not supported.");
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException("iterator is not supported.");
    }
    public void printInOrder(Node n){
        if(n==null)
            return;
        else {
            printInOrder(n.left);
            System.out.println(n.value);
            System.out.println(' ');
            printInOrder(n.right);
        }
    }
    public void printInOrder(){
        printInOrder(root);
    }
}
