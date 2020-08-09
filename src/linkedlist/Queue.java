package linkedlist;

/**
 * ACCESS/SEARCH O(n)
 * 
 * INSERT/DELETE O(1)
 */

public class Queue {

    // Remove from head
    private Node head;
    // Add to tail
    private Node tail;

    private static class Node {
        private int data;
        private Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    public void add(int data) {
        Node newNode = new Node(data);
        if (tail != null) {
            tail.next = newNode;
        }
        tail = newNode;

        // Special case of 1st add
        if (head == null) {
            head = tail;
        }
    }

    public int remove() {
        if (head == null) {
            return -1;
        }
        int data = head.data;
        head = head.next;

        // Special case of last removal
        if (head == null) {
            tail = null;
        }
        return data;
    }

    public int peek() {
        if (head == null) {
            return -1;
        }
        return head.data;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public static void main(String[] args) {
        Queue q = new Queue();
        q.add(10);
        q.add(20);
        System.out.println(q.peek());
        System.out.println(q.remove());
        System.out.println(q.isEmpty());
        System.out.println(q.remove());
        System.out.println(q.isEmpty());
        System.out.println(q.remove());
        System.out.println(q.isEmpty());
    }

}
