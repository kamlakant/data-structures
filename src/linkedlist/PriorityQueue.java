package linkedlist;

import tree.MinBinaryHeap;

public class PriorityQueue {

    private MinBinaryHeap heap;

    public PriorityQueue() {
        heap = new MinBinaryHeap(10);
    }

    public void enqueue(int priority) {
        heap.insert(priority);
    }

    public int dequeue() {
        return heap.extractMin();
    }

    public static void main(String[] args) {
        PriorityQueue queue = new PriorityQueue();

        queue.enqueue(6);
        queue.enqueue(1);
        queue.enqueue(3);
        queue.enqueue(27);
        queue.enqueue(7);

        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());

    }

}
