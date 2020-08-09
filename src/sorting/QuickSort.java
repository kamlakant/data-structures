package sorting;

import java.util.Random;

/**
 * Choose a pivot P.
 * 
 * Two indices LEFT and RIGHT move towards each other until they detect an inversion (i.e. LEFT greater than P and RIGHT smaller
 * than P). Swap the inverted elements.
 * 
 * If RIGHT meets or crosses over LEFT, Pivot is in its correct place.
 * 
 * Partition the array on Pivot. Perform above operations on the sub-arrays until their size becomes 1. Array of size 1 is sorted
 * automatically.
 * 
 * Average - O(n log n)
 */
public class QuickSort {

    public int[] sort(int[] array) {

        quickSort(array, 0, array.length - 1);

        return array;
    }

    private void quickSort(int[] array, int left, int right) {
        // If left == right, array has single element already sorted
        if (left < right) {
            int partitionIndex = partition(array, left, right);
            quickSort(array, left, partitionIndex);
            quickSort(array, partitionIndex + 1, right);
        }
    }

    private int partition(int[] array, int left, int right) {
        int pivot = array[(new Random().nextInt(right - left + 1) + left)];
        // int pivot = array[(left + right) / 2];

        while (true) {
            while (array[left] < pivot) {
                left++;
            }

            while (array[right] > pivot) {
                right--;
            }

            if (left >= right) {
                return right;
            }
            swap(array, left, right);
        }
    }

    private void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }

    public void prettyPrint(int[] a) {
        for (int i : a) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        int[] arr = { 15, 3, 9, 8, 5, 2, 7, 1, 6 };
        quickSort.sort(arr);
        quickSort.prettyPrint(arr);
    }

}
