package tree;

/**
 * Use - Routers, Priority queues, Shortest path. At every insert, heap must be a complete binary tree and maintain heap priority.
 * 
 * Heap Priority - Priority of parent node must be larger than or equal to children nodes
 * 
 * Best stored in array. Parent = (i-1)/2, Left child = (2*i)+1, Right child = (2*i)+2
 * 
 * INSERT Average O(1) Worst O(log n)
 *
 * DELETE O(log n)
 * 
 * PEEK O(1)
 */
public class MaxBinaryHeap {

    private int capacity = 2;
    private int size = 0;
    private int[] items;

    public MaxBinaryHeap() {
        items = new int[capacity];
    }

    private int leftChildIndex(int parentIndex) {
        return (2 * parentIndex) + 1;
    }

    private int rightChildIndex(int parentIndex) {
        return (2 * parentIndex) + 2;
    }

    private int parentIndex(int childIndex) {
        return (childIndex - 1) / 2;
    }

    private boolean hasLeftChild(int parentIndex) {
        return leftChildIndex(parentIndex) < size;
    }

    private boolean hasRightChild(int parentIndex) {
        return rightChildIndex(parentIndex) < size;
    }

    private boolean hasParent(int childIndex) {
        return parentIndex(childIndex) >= 0;
    }

    private int leftChild(int parentIndex) {
        return items[leftChildIndex(parentIndex)];
    }

    private int rightChild(int parentIndex) {
        return items[rightChildIndex(parentIndex)];
    }

    private int parent(int childIndex) {
        return items[parentIndex(childIndex)];
    }

    public int extractMax() {
        if (size == 0) {
            return -1;
        }
        // Grab the max
        int maxItem = items[0];

        // Set the last item as max and reduce size. Effectively removing the maxItem from array
        items[0] = items[size - 1];
        size--;

        // Heapify down
        heapifyDown();

        return maxItem;
    }

    private void heapifyDown() {
        int parentIndex = 0;

        // If parent has children. Only need to check left as if no left then no right children
        while (hasLeftChild(parentIndex)) {

            // Take the larger of the two children
            int largerChildIndex = leftChildIndex(parentIndex);
            if (hasRightChild(parentIndex) && rightChild(parentIndex) > leftChild(parentIndex)) {
                largerChildIndex = rightChildIndex(parentIndex);
            }

            // If child is larger than parent, then swap them. Otherwise, heap is sorted and exit loop
            if (items[largerChildIndex] > items[parentIndex]) {
                swap(largerChildIndex, parentIndex);
            } else {
                break;
            }

            // Set parent as the child and go down
            parentIndex = largerChildIndex;
        }
    }

    public void insert(int item) {
        // Ensure capacity
        ensureCapacity();

        // Add item to last to maintain complete binary tree
        items[size] = item;
        size++;

        // Heapify up
        heapifyUp();
    }

    private void heapifyUp() {
        // Start with the last item
        int lastIndex = size - 1;

        // Swap child with its parent until parent >= child
        // If the item has a parent and its smaller than item
        while (hasParent(lastIndex) && parent(lastIndex) < items[lastIndex]) {

            // Swap the item with its parent
            swap(parentIndex(lastIndex), lastIndex);

            // Set parent as the item and go up
            lastIndex = parentIndex(lastIndex);
        }
    }

    private void ensureCapacity() {
        if (size == capacity) {
            capacity *= 2;
            int[] newItems = new int[capacity];
            for (int i = 0; i < size; i++) {
                newItems[i] = items[i];
            }
            items = newItems;
        }
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

    private void swap(int indexOne, int indexTwo) {
        int temp = items[indexOne];
        items[indexOne] = items[indexTwo];
        items[indexTwo] = temp;
    }

    public static void main(String[] args) {
        MaxBinaryHeap binaryHeap = new MaxBinaryHeap();

        binaryHeap.insert(10);
        binaryHeap.insert(6);
        binaryHeap.insert(20);
        binaryHeap.insert(5);
        binaryHeap.insert(16);
        binaryHeap.insert(17);
        binaryHeap.insert(13);
        binaryHeap.insert(2);

        binaryHeap.print();

        System.out.println(binaryHeap.extractMax());
        System.out.println(binaryHeap.extractMax());
        System.out.println(binaryHeap.extractMax());
        System.out.println(binaryHeap.extractMax());
        System.out.println(binaryHeap.extractMax());
        System.out.println(binaryHeap.extractMax());
        System.out.println(binaryHeap.extractMax());
        System.out.println(binaryHeap.extractMax());

        binaryHeap.print();

    }

}
