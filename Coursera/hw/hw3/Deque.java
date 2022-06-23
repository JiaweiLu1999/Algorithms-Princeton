/* *****************************************************************************
 *  Name: Jiawei Lu
 *  Date: 2022-06-22
 *  Description: Deque
 **************************************************************************** */

import java.util.Arrays;
import java.util.Iterator;

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

    public String toString() {
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
        if (head - 1 < 0) {
            resize(2 * capacity);
        }
        q[--head] = item;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (tail >= capacity) {
            resize(2 * capacity);
        }
        q[tail++] = item;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        return null;
    }

    // remove and return the item from the back
    public Item removeLast() {
        return null;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return null;
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> q = new Deque<>();
        q.addFirst(1);
        q.addLast(2);
        q.addLast(3);
        q.addFirst(4);
        q.addFirst(0);
        q.addFirst(0);
        q.addFirst(0);
        q.addFirst(0);
        System.out.println(q);

    }

}
