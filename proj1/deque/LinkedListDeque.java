package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T> {
    private Node<T> sentinel;
    private int size;

    private class Node<T> {
        T val;
        Node next;
        Node prev;

        Node(T val, Node prev, Node next) {
            this.val = val;
            this.next = next;
            this.prev = prev;
        }
    }

    public LinkedListDeque() {
        sentinel = new Node<>(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        Node<T> newnode = new Node<>(item, sentinel, sentinel.next);
        sentinel.next.prev = newnode;
        sentinel.next = newnode;
        size++;
    }

    public void addLast(T item) {
        Node<T> newnode = new Node<>(item, sentinel.prev, sentinel);
        sentinel.prev.next = newnode;
        sentinel.prev = newnode;
        size++;
    }


    public int size() {
        return size;
    }

    public void printDeque() {
        Node<T> current = sentinel.next;
        while (current != sentinel) {
            System.out.print(current.val + " ");
            current = current.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (isEmpty())
            return null;
        else {
            Node<T> first = sentinel.next;
            first.next.prev = sentinel;
            sentinel.next = first.next;
            size--;
            return first.val;
        }
    }

    public T removeLast() {
        if (isEmpty())
            return null;
        else {
            Node<T> last = sentinel.prev;
            last.prev.next = sentinel;
            sentinel.prev = last.prev;
            size--;
            return last.val;
        }
    }

    public T get(int index) {
        if (index < 0 || index >= size)
            return null;
        Node<T> cur = sentinel.next;
        while (index != 0) {
            cur = cur.next;
            index--;
        }
        return cur.val;
    }


    public T getRecursive(int index) {
        return (T) getRecursiveHelper(sentinel.next, index);
    }

    private T getRecursiveHelper(Node<T> current, int index) {
        if (index == 0) {
            return current.val;
        }
        return (T) getRecursiveHelper(current.next, index - 1);
    }

    public Iterator<T> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<T> {
        private Node<T> current = sentinel.next;

        public boolean hasNext() {
            return current != sentinel;
        }

        public T next() {
            T item = current.val;
            current = current.next;
            return item;
        }
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkedListDeque<?> that = (LinkedListDeque<?>) o;

        if (size != that.size) return false;

        Node<T> currentThis = sentinel.next;
        Node<?> currentThat = that.sentinel.next;
        while (currentThis != sentinel) {
            if (!currentThis.val.equals(currentThat.val)) {
                return false;
            }
            currentThis = currentThis.next;
            currentThat = currentThat.next;
        }
        return true;
    }

}