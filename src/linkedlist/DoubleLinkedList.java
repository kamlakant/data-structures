package linkedlist;

public class DoubleLinkedList {

    private Node head;
    private Node tail;

    class Node {
        private int data;
        private Node previous;
        private Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    public void insertFront(int data) {
        Node newNode = new Node(data);

        if (head == tail && head == null) {
            head = newNode;
            tail = newNode;
            return;
        }

        // Point new node's next to HEAD
        newNode.next = head;

        // Point current HEAD's previous to new node
        head.previous = newNode;

        // Set new node as HEAD
        head = newNode;
    }

    public int deleteFront() {
        // List is empty
        if (head == null) {
            return -1;
        }
        // List has only one element
        if (head == tail) {
            int data = head.data;
            head = null;
            tail = null;
            return data;
        }

        int data = head.data;

        // Set HEAD's next node's previous to null
        head.next.previous = null;

        // Set HEAD's next node as HEAD
        head = head.next;
        return data;
    }

    public void insertLast(int data) {
        Node newNode = new Node(data);

        // List is empty
        if (head == tail && head == null) {
            head = newNode;
            tail = newNode;
            return;
        }

        // Set TAIL's next to new node
        tail.next = newNode;

        // Set new nodes's previous to TAIL
        newNode.previous = tail;

        // Set TAIL to new node
        tail = newNode;
    }

    public int deleteLast() {

        // List is empty
        if (tail == null) {
            return -1;
        }

        // List has only one element
        if (head == tail) {
            int data = tail.data;
            head = null;
            tail = null;
            return data;
        }

        int data = tail.data;

        // Set TAIL's previous node's next to null
        tail.previous.next = null;

        // Set TAIL's previous node as TAIL
        tail = tail.previous;

        return data;
    }

    public void delete(int key) {
        Node currentHead = head;
        Node currentTail = tail;

        // If element is at HEAD
        if (currentHead != null && currentHead.data == key) {
            deleteFront();
            return;
        }

        // If element is at TAIL
        if (currentTail != null && currentTail.data == key) {
            deleteLast();
            return;
        }

        while (currentHead != null && currentTail != null) {

            // HEAD and TAIL met or crossed, element not found
            if (currentHead == currentTail || currentHead.previous == currentTail) {
                System.out.println("Not found: " + key);
                return;
            }
            if (currentHead.data == key) {
                System.out.println("HEAD reached " + key);

                // Point matched node's previous node to matched node's next node
                currentHead.previous.next = currentHead.next;

                // Point matched node's next node to matched node's previous node
                currentHead.next.previous = currentHead.previous;
                return;
            }
            if (currentTail.data == key) {
                System.out.println("TAIL reached " + key);
                currentTail.previous.next = currentTail.next;
                currentTail.next.previous = currentTail.previous;
                return;
            }
            currentHead = currentHead.next;
            currentTail = currentTail.previous;
        }
    }

    public void traverseFront() {
        Node current = head;

        while (current != null) {
            System.out.print(current.data + " ");
            if (current.next == null) {
                break;
            }
            current = current.next;
        }
        System.out.println();
    }

    public void traverseLast() {
        Node current = tail;

        while (current != null) {
            System.out.print(current.data + " ");
            if (current.previous == null) {
                break;
            }
            current = current.previous;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        DoubleLinkedList list = new DoubleLinkedList();

        list.insertFront(1);
        list.insertFront(2);
        list.insertFront(3);

        list.traverseFront();
        list.traverseLast();

        System.out.println(list.deleteFront());
        System.out.println(list.deleteFront());
        System.out.println(list.deleteFront());

        list.insertLast(4);
        list.insertLast(5);
        list.insertLast(6);

        list.traverseFront();
        list.traverseLast();

        System.out.println(list.deleteLast());
        System.out.println(list.deleteLast());
        System.out.println(list.deleteLast());

        list.insertFront(1);
        list.insertFront(2);
        list.insertFront(3);
        list.insertFront(4);
        list.insertFront(5);
        list.insertFront(6);

        list.traverseFront();
        list.traverseLast();

        list.delete(2);
        list.delete(5);
        list.delete(6);
        list.delete(1);
        list.delete(7);

        list.traverseFront();
        list.traverseLast();
    }

}
