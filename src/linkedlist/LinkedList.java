package linkedlist;

/**
 * GET/SET O(n)
 * 
 * INSERT/DELETE at front O(1)
 *
 * INSERT/DELETE at other places O(n)
 */
public class LinkedList {

    private static class Node {
        private int data;
        private Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    private Node head;
    
    public void sort() {
        // TODO
    }

    public void addFront(int data) {
        // Create new node
        Node newNode = new Node(data);

        // If 1st insertion
        if (head == null) {
            head = newNode;
            return;
        }
        // New node points to existing HEAD
        newNode.next = head;

        // New node becomes the new HEAD
        head = newNode;
    }

    public int getFirst() {
        return head.data;
    }

    public int getLast() {
        // Start with the HEAD
        Node current = head;

        // Traverse until we reach TAIL
        while (current.next != null) {
            current = current.next;
        }
        return current.data;
    }

    public void addBack(int data) {
        Node newNode = new Node(data);
        // If 1st insertion
        if (head == null) {
            head = newNode;
            return;
        }

        // Traverse until we reach TAIL
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }

        // Make new node the new TAIL
        current.next = newNode;
    }

    public int size() {
        if (head == null) {
            return 0;
        }
        int size = 1;
        Node current = head;
        while (current.next != null) {
            size++;
            current = current.next;
        }
        return size;
    }

    public void clear() {
        head = null;
    }

    public void deleteByValue(int value) {
        Node current = head;

        // If value is at HEAD, just make the next node HEAD
        if (current.data == value) {
            head = current.next;
            return;
        }
        // Traverse to the node which has the value
        while (current.next != null) {
            if (current.next.data == value) {
                current.next = current.next.next;
                return;
            }
            current = current.next;
        }
    }

    public int getAtIndex(int index) {
        Node current = head;
        // Traverse the list until index becomes 0
        while (current.next != null) {
            if (index == 0) {
                return current.data;
            }
            current = current.next;
            index--;
        }
        // If index becomes 0 at TAIL
        if (index == 0) {
            return current.data;
        }
        return -1;
    }

    public void insertAtIndex(int index, int value) {
        if (index == 0) {
            addFront(value);
            return;
        }
        Node newNode = new Node(value);
        Node current = head;
        // Traverse the list until index becomes 0
        // New node will now point to next node
        // current node will now point to new node
        while (current.next != null) {
            if (index == 1) {
                newNode.next = current.next;
                current.next = newNode;
                return;
            }
            current = current.next;
            index--;
        }
    }

    public void print() {
        Node current = head;
        while (current.next != null) {
            System.out.print(current.data + "\t");
            current = current.next;
        }
        System.out.println(current.data);
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        System.out.println("size=" + list.size());
        list.addFront(20);
        list.addFront(10);
        list.addBack(30);
        list.print();
        System.out.println("size=" + list.size());
        list.deleteByValue(20);
        list.print();
        list.addFront(20);
        System.out.println(list.getAtIndex(2));
        list.insertAtIndex(1, 40);
        list.insertAtIndex(2, 50);
        list.insertAtIndex(4, 70);
        System.out.println(list.getAtIndex(5));
        list.print();
        System.out.println("size=" + list.size());
    }
}
