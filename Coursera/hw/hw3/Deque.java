/* *****************************************************************************
 *  Name: Jiawei Lu
 *  Date: 2022-06-22
 *  Description: Deque
 **************************************************************************** */

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    // members
    private Item[] q;
    private int head;
    private int tail;
    private int capacity;

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        int size = size();

        int newHead = (capacity - size) / 2;

        for (int i = 0; i < size; i++) {
            copy[newHead + i] = q[head + i];
        }
        q = copy;
        head = newHead;
        tail = head + size;
        this.capacity = capacity;
    }

    // construct an empty deque
    public Deque() {
        q = (Item[]) new Object[1];
        head = 0;
        tail = 0;
        capacity = 1;
    }

    private String print() {
        return "Deque{" +
                "q=" + Arrays.toString(q) +
                '}';
    }

    // is the deque empty?
    public boolean isEmpty() {
        return head == tail;
    }

    // return the number of items on the deque
    public int size() {
        return tail - head;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Can not add a null pointer...");
        }
        if (head - 1 < 0) {
            resize(2 * capacity);
        }
        q[--head] = item;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Can not add a null pointer...");
        }
        if (tail >= capacity) {
            resize(2 * capacity);
        }
        q[tail++] = item;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (size() == 0) {
            throw new NoSuchElementException("Can not get an item from an empty queue...");
        }
        Item item = q[head];
        q[head++] = null;
        if (size() > 0 && size() == capacity / 4) {
            resize(capacity / 2);
        }
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (size() == 0) {
            throw new NoSuchElementException("Can not get an item from an empty queue...");
        }
        Item item = q[tail - 1];
        q[--tail] = null;
        if (size() > 0 && size() == capacity / 4) {
            resize(capacity / 2);
        }
        return item;
    }

    // iteratorHelper
    private class iteratorHelper implements Iterator<Item> {
        private int i = head;

        public boolean hasNext() {
            return i < head + size();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more items in the queue...");
            }
            return q[i++];
        }

        public void remove() {
            throw new UnsupportedOperationException("Unsupported operation...");
        }
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new iteratorHelper();
    }

    // unit testing (required)
    public static void main(String[] args) {

        Deque<Integer> q = new Deque<>();
        System.out.println(q.print());
        System.out.println("Queue is empty:" + q.isEmpty());

        q.addFirst(1);
        System.out.println(q.print());
        System.out.println("Queue is empty:" + q.isEmpty());
        System.out.println("Size of queue:" + q.size());

        q.addLast(2);
        q.addLast(3);
        q.addFirst(4);
        q.addFirst(0);
        q.addFirst(0);
        q.addFirst(0);
        q.addFirst(0);
        try {
            q.addFirst(null);
        } catch (IllegalArgumentException e) {
            System.out.println(e.toString());
        }
        q.removeFirst();
        q.removeFirst();
        q.removeLast();
        q.removeFirst();
        q.removeLast();
        // q.removeLast();
        // q.removeLast();
        // q.removeLast();
        // q.removeLast();
        System.out.println(q.print());
        Iterator<Integer> iter = q.iterator();

        try {
            iter.remove();
        } catch (UnsupportedOperationException e) {
            System.out.println("Remove not supported...");
        }

        System.out.println(iter.hasNext());
        System.out.println(iter.next());
        System.out.println(iter.next());
        System.out.println(iter.next());

        System.out.println(q.print());

    }

}
