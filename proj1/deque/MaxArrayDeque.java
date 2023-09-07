package deque;

import java.util.Comparator;
import java.util.Iterator;

public class MaxArrayDeque<T> extends ArrayDeque {
    private Comparator<T> cmp;

    public MaxArrayDeque(Comparator<T> c) {
        cmp = c;
    }

    public T max() {
        if (isEmpty()) {
            return null;
        }
        T maxElement = get(0);
        for (int i = 1; i < size(); i++) {
            T current = get(i);
            if (cmp.compare(current, maxElement) > 0) {
                maxElement = current;
            }
        }
        return maxElement;
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        T maxElement = get(0);
        for (int i = 1; i < size(); i++) {
            T current = get(i);
            if (c.compare(current, maxElement) > 0) {
                maxElement = current;
            }
        }
        return maxElement;
    }

}