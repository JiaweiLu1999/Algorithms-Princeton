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

        int num = StdRandom.uniform(size);
        int idx = head;
        while (num > 0) {
            if (q[idx] != null) {
                num--;
            }
            idx++;
        }

        Item item = q[idx];
        q[idx] = null;
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

        int num = StdRandom.uniform(size);
        Item item = q[head + num];

        return item;
    }

    private class IteratorHelper implements Iterator<Item> {
        private int i;


        public IteratorHelper() {
            i = 0;
        }
        public boolean hasNext() {
            return i < size;
        }

        public Item next() {
            return null;
        }
    }
    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return null;
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> q = new RandomizedQueue<>();
        q.printAll();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.printAll();
        q.enqueue(4);
        q.enqueue(3);

        System.out.println(q.sample().toString());
        System.out.println(q.sample().toString());
        System.out.println(q.sample().toString());
        q.printAll();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.printAll();
        q.enqueue(5);
        q.printAll();
        System.out.println(q.head);
        System.out.println(q.tail);
    }
}
