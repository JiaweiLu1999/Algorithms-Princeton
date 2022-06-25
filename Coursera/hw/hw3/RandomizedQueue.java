/* *****************************************************************************
 *  Name: Jiawei Lu
 *  Date: 2022-060-23
 *  Description: RandomizedQueue
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] q;
    private int head;
    private int tail;
    private int size;
    private int capacity;

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0, j = 0; i < size; j++) {
            if (q[head + j] != null) {
                copy[i] = q[head + j];
                i++;
            }
        }
        q = copy;
        head = 0;
        tail = size;
        this.capacity = capacity;
    }

    private void print() {
        Iterator<Item> iter =  iterator();
        System.out.print("q = [ ");
        while (iter.hasNext()) {
            System.out.print(iter.next().toString() + " ");
        }
        System.out.println("]");
    }

    private void printAll() {
        StringBuilder buf = new StringBuilder("q = [ ");
        for (int i = 0; i < capacity; i++) {
            if (q[i] == null) {
                buf.append("null ");
            } else {
                buf.append(q[i].toString() + " ");
            }
        }
        buf.append("]");
        String s = buf.toString();
        System.out.println(s);
    }


    // construct an empty randomized queue
    public RandomizedQueue() {
        q = (Item[]) new Object[1];
        head = 0;
        tail = 0;
        size = 0;
        capacity = 1;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Can not add a null pointer...");
        }

        if (tail == capacity) {
            resize(2 * capacity);
        }

        q[tail++] = item;
        size++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (size == 0) {
            throw new NoSuchElementException("Can not get an item from an empty queue...");
        }

        int idx;
        do {
            idx = StdRandom.uniform(tail - head);
        } while (q[head + idx] == null);

        Item item = q[head + idx];
        q[head + idx] = null;
        size--;

        if (size <= capacity / 4) {
            resize(capacity / 2);
        }

        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (size == 0) {
            throw new NoSuchElementException("Can not get an item from an empty queue...");
        }

        int idx;
        do {
            idx = StdRandom.uniform(tail - head);
        } while (q[head + idx] == null);

        return q[head + idx];
    }

    private class IteratorHelper implements Iterator<Item> {
        private int i;
        private Item[] qCopy;
        private int iterSize;
        public IteratorHelper() {
            i = 0;
            iterSize = size;
            qCopy = (Item[]) new Object[iterSize];
            for (int m = 0, n = 0; m < capacity; m++) {
                if (q[m] != null) {
                    qCopy[n++] = q[m];
                }
            }
        }

        public boolean hasNext() {
            return i < iterSize;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more items in the queue...");
            }

            return qCopy[i++];
        }

        public void remove() {
            throw new UnsupportedOperationException("Unsupported operation...");
        }
    }
    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new IteratorHelper();
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> q = new RandomizedQueue<>();
        q.printAll();
        System.out.println("q isEmpty: " + q.isEmpty());
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        System.out.println();
        q.printAll();
        System.out.println("Get a sample " + q.sample());

        q.enqueue(4);
        q.enqueue(3);
        System.out.println();
        q.printAll();
        System.out.println("Size:" + q.size());
        System.out.println("Get a sample " + q.sample());
        System.out.println("Get a sample " + q.sample());
        System.out.println("Get a sample " + q.sample());
        System.out.println();

        q.printAll();
        System.out.println("Deque an item " + q.dequeue());
        q.printAll();
        System.out.println("Size:" + q.size());
        System.out.println("Deque an item " + q.dequeue());
        q.printAll();

        System.out.println();
        q.printAll();
        Iterator<Integer> iter = q.iterator();
        while (iter.hasNext()) {
            System.out.println("Next item: " + iter.next());
        }

        System.out.println("Deque an item " + q.dequeue());
        q.printAll();

        System.out.println();
        q.printAll();
        iter = q.iterator();
        while (iter.hasNext()) {
            System.out.println("Next item: " + iter.next());
        }

        System.out.println("Deque an item " + q.dequeue());
        q.printAll();
        System.out.println("Deque an item " + q.dequeue());
        q.printAll();
        System.out.println("Size:" + q.size());
        System.out.println("q isEmpty: " + q.isEmpty());
        System.out.println();

        q.enqueue(5);
        q.printAll();
        System.out.println(q.head);
        System.out.println(q.tail);
    }
}
