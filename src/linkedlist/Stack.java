package linkedlist;

/**
 * ACCESS/SEARCH O(n)
 * 
 * INSERT/DELETE O(1)
 */
public class Stack {

    private Node head;

    private static class Node {
        private int data;
        private Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    public void push(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }

    public int pop() throws Exception {
        if (head != null) {
            int data = head.data;
            head = head.next;
            return data;
        }
        throw new Exception("STACK EMPTY");
    }

    public int peek() throws Exception {
        if (head != null) {
            return head.data;
        }
        throw new Exception("STACK EMPTY");
    }

    public boolean isEmpty() {
        return head == null;
    }

    public static void main(String[] args) {
        try {
            Stack stack = new Stack();
            stack.push(10);
            System.out.println(stack.peek());
            System.out.println(stack.pop());
            System.out.println(stack.isEmpty());
            stack.push(20);
            System.out.println(stack.isEmpty());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
