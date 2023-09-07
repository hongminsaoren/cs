package deque;

import java.util.Iterator;
import java.util.Objects;

public class ArrayDeque<T> implements Deque<T> {
    private T[] items;
    private int size;
    private int front;
    private int back;

    // Constructor
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        front = 0;
        back = 0;
    }

    // Add an item to the front of the deque
    public void addFirst(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        front = (front - 1 + items.length) % items.length;
        items[front] = item;
        size++;
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[back] = item;
        back = (back + 1) % items.length;
        size++;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            int index = (front + i) % items.length;
            System.out.print(items[index] + " ");
        }
        System.out.println();
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T removedItem = items[front];
        front = (front + 1) % items.length;
        size--;
        if (size > 0 && size < items.length / 4) {
            resize(items.length / 2);
        }
        return removedItem;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        back = (back - 1 + items.length) % items.length;
        T removedItem = items[back];
        size--;
        if (size > 0 && size < items.length / 4) {
            resize(items.length / 2);
        }
        return removedItem;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        int realIndex = (front + index) % items.length;
        return items[realIndex];
    }


    private void resize(int newSize) {
        T[] newArray = (T[]) new Object[newSize];
        for (int i = 0; i < size; i++) {
            newArray[i] = items[(front + i) % items.length];
        }
        items = newArray;
        front = 0;
        back = size;
    }

    public Iterator<T> iterator() {
        return new DequeIterator();
    }

    public class DequeIterator implements Iterator<T> {
        private int idx = front;

        public boolean hasNext() {
            return idx != back;
        }

        public T next() {
            int copy = idx;
            idx = (idx + 1) % items.length;
            return items[copy];
        }
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrayDeque<?> that = (ArrayDeque<?>) o;

        if (size != that.size) return false;
        Iterator<T> thisIterator = iterator();
        Iterator<?> thatIterator = that.iterator();
        while (thisIterator.hasNext() && thatIterator.hasNext()) {
            T thisElement = thisIterator.next();
            Object thatElement = thatIterator.next();
            if (!Objects.equals(thisElement, thatElement)) {
                return false;
            }
        }

        return true;
    }


}
