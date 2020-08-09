package tree;

/**
 * Insert at bottom then heapify up (If parent is smaller than node, move Node up)
 * 
 * Extract min from top and then heapify down (If children are smaller than parent, move Parent down)
 */
public class MinBinaryHeap {

    private int capacity;
    private int size;
    private int[] heap;

    public MinBinaryHeap(int initialCapacity) {
        size = 0;
        capacity = initialCapacity;
        heap = new int[initialCapacity];
    }

    public void insert(int node) {
        // Ensure capacity
        ensureCapacity();

        // Insert node at bottom
        heap[size] = node;
        size++;

        heapifyUp();
    }

    private void heapifyUp() {
        int nodeIndex = size - 1;

        while (hasParent(nodeIndex) && parent(nodeIndex) > heap[nodeIndex]) {
            swap(heap, parentIndex(nodeIndex), nodeIndex);
            nodeIndex = parentIndex(nodeIndex);
        }
    }

    public int extractMin() {
        // Swap the node at bottom with root
        int min = heap[0];
        heap[0] = heap[size - 1];
        size--;

        heapifyDown();

        return min;
    }

    private void heapifyDown() {
        int parentIndex = 0;

        // Complete binary must have left children before it has right
        while (hasLeftChild(parentIndex)) {
            int smallestChildIndex = leftChildIndex(parentIndex);
            if (hasRightChild(parentIndex) && rightChild(parentIndex) < leftChild(parentIndex)) {
                smallestChildIndex = rightChildIndex(parentIndex);
            }
            if (heap[smallestChildIndex] > heap[parentIndex]) {
                break;
            } else {
                swap(heap, parentIndex, smallestChildIndex);
            }
            parentIndex = smallestChildIndex;
        }
    }

    private void ensureCapacity() {
        if (size == capacity) {
            capacity *= 2;
            int[] newItems = new int[capacity];
            for (int i = 0; i < size; i++) {
                newItems[i] = heap[i];
            }
            heap = newItems;
        }
    }

    private boolean hasLeftChild(int parentIndex) {
        return leftChildIndex(parentIndex) < size;
    }

    private int leftChildIndex(int parentIndex) {
        return (2 * parentIndex) + 1;
    }

    private int leftChild(int parentIndex) {
        return heap[leftChildIndex(parentIndex)];
    }

    private boolean hasRightChild(int parentIndex) {
        return rightChildIndex(parentIndex) < size;
    }

    private int rightChildIndex(int parentIndex) {
        return (2 * parentIndex) + 2;
    }

    private int rightChild(int parentIndex) {
        return heap[rightChildIndex(parentIndex)];
    }

    private void swap(int[] array, int indexA, int indexB) {
        array[indexA] = array[indexA] + array[indexB];
        array[indexB] = array[indexA] - array[indexB];
        array[indexA] = array[indexA] - array[indexB];
    }

    private boolean hasParent(int nodeIndex) {
        return parentIndex(nodeIndex) >= 0;
    }

    private int parentIndex(int nodeIndex) {
        return (nodeIndex - 1) / 2;
    }

    private int parent(int nodeIndex) {
        return heap[parentIndex(nodeIndex)];
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MinBinaryHeap heap = new MinBinaryHeap(10);

        heap.insert(10);
        heap.insert(6);
        heap.insert(20);
        heap.insert(5);
        heap.insert(16);
        heap.insert(17);
        heap.insert(13);
        heap.insert(2);

        heap.print();

        System.out.println(heap.extractMin());
        System.out.println(heap.extractMin());
        System.out.println(heap.extractMin());
        System.out.println(heap.extractMin());
        System.out.println(heap.extractMin());
        System.out.println(heap.extractMin());
        System.out.println(heap.extractMin());
        System.out.println(heap.extractMin());

        heap.print();
    }

}
