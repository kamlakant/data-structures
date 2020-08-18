package sorting;

/**
 * 1. Build heap from array.
 * 
 * 2. Extract the root node (Swap the right-most and root nodes) and reduce heap size.
 * 
 * 3. Heapify down and repeat step 2 until heap is empty.
 * 
 * Average - O(n log n). Worst case O(n log n) better than quick sort.
 */
public class HeapSort {

    /**
     * Floyd's build heap O(n) using heapify down. Trivial heapify up takes O(n log n)
     */
    private static void buildHeap(int[] arr) {
        int size = arr.length;

        // Start from the last non-leaf node
        int startIndex = (size / 2) - 1;

        // Perform reverse level order traversal from last non-leaf node and heapify each node
        for (int i = startIndex; i >= 0; i--) {
            heapifyDown(arr, size, i);
        }
    }

    private static void heapifyDown(int[] arr, int heapSize, int nodeIndex) {
        // Set current node as largest
        int largestIndex = nodeIndex;

        // Get indices of left and right child
        int leftChildIndex = 2 * nodeIndex + 1;
        int rightChildIndex = 2 * nodeIndex + 2;

        // Determine the largest among children and current node
        if (leftChildIndex < heapSize && arr[leftChildIndex] > arr[largestIndex]) {
            largestIndex = leftChildIndex;
        }
        if (rightChildIndex < heapSize && arr[rightChildIndex] > arr[largestIndex]) {
            largestIndex = rightChildIndex;
        }

        // If current node is not largest, swap it with largest child
        if (largestIndex != nodeIndex) {
            swap(arr, nodeIndex, largestIndex);

            // Repeat the process for the largest child
            heapifyDown(arr, heapSize, largestIndex);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void sort(int[] arr) {
        buildHeap(arr);

        for (int i = arr.length - 1; i > 0; i--) {
            swap(arr, i, 0);
            heapifyDown(arr, i, 0);
        }
    }

    public static void print(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int arr[] = { 15, 6, 22, 35, 9 };
        print(arr);
        sort(arr);
        print(arr);
    }

}
