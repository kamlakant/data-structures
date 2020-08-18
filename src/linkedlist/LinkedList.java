package linkedlist;

import java.util.HashSet;
import java.util.Set;

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
        head = mergeSort(head);
    }

    /**
     * Find middle node in list and divide into left and right lists. Repeat until left and right lists have single nodes.
     * 
     * Merge the already sorted left and right lists.
     */
    private Node mergeSort(Node node) {
        // If head is null or list has 1 element
        if (node == null || node.next == null) {
            return node;
        }

        Node middle = findMiddleNode(node);

        // Next node of middle is HEAD of right list
        Node middleNext = middle.next;

        // Break the list to create left list
        middle.next = null;

        Node left = mergeSort(node);
        Node right = mergeSort(middleNext);

        Node sorted = merge(left, right);
        return sorted;

    }

    private Node findMiddleNode(Node node) {
        if (node == null) {
            return node;
        }

        // Slow moves by 1, Fast moves by 2
        Node slow = node;
        Node fast = node;

        // When fast reaches end, slow reaches middle
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    private Node merge(Node left, Node right) {
        Node merged = null;

        if (left == null) {
            return right;
        }

        if (right == null) {
            return left;
        }

        //
        if (left.data < right.data) {
            merged = left;
            merged.next = merge(left.next, right);
        } else {
            merged = right;
            merged.next = merge(left, right.next);
        }

        return merged;
    }

    /**
     * Create a hash table of each node's data. If a node already exists in hash table, remove it from list
     */
    public void removeDuplicates() {
        Set<Integer> uniqueNodes = new HashSet<>();

        Node currentNode = head;
        Node previousNode = null;

        while (currentNode != null) {
            if (uniqueNodes.add(currentNode.data)) {
                // New node
                previousNode = currentNode;
            } else {
                // Duplicate node. Bypass duplicate by pointing its previous's next pointer to its next node
                previousNode.next = currentNode.next;
            }
            currentNode = currentNode.next;
        }
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
        while (current != null) {
            System.out.print(current.data + " ");
            if (current.next == null) {
                break;
            }
            current = current.next;
        }
        System.out.println();
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

        list.addBack(30);
        list.addFront(20);
        list.insertAtIndex(3, 50);
        list.print();

        list.removeDuplicates();

        list.sort();

        list.print();
    }
}
