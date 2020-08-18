package sorting;

/**
 * 1. Divide the array into two halves until each half contains only one element.
 *
 * 2. Merge the two halves. Repeat step 1.
 * 
 * Average - O(n log n). Best suited for sequential access (e.g. LinkedList)
 */
public class MergeSort {

    public static void mergeSort(int[] arr, int low, int high) {

        // If low == high, array size is 1 which is automatically sorted
        if (low < high) {
            int mid = (low + high) / 2;
            mergeSort(arr, low, mid);
            mergeSort(arr, mid + 1, high);

            merge(arr, low, mid, high);

        }
    }

    private static void merge(int[] arr, int low, int mid, int high) {

        // Get the size of two sub-lists to be merged
        int size1 = mid - low + 1;
        int size2 = high - mid;

        // Create two temp arrays
        int[] lowArray = new int[size1];
        int[] highArray = new int[size2];

        // Copy data into temp arrays
        for (int i = 0; i < size1; i++) {
            lowArray[i] = arr[low + i];
        }
        for (int i = 0; i < size2; i++) {
            highArray[i] = arr[mid + 1 + i];
        }

        int i = 0;
        int j = 0;
        int k = low;

        // Perform two way merge
        while (i < size1 && j < size2) {
            if (lowArray[i] < highArray[j]) {
                arr[k] = lowArray[i];
                i++;
            } else {
                arr[k] = highArray[j];
                j++;
            }
            k++;
        }

        while (i < size1) {
            arr[k] = lowArray[i];
            i++;
            k++;
        }

        while (j < size2) {
            arr[k] = highArray[j];
            j++;
            k++;
        }
    }

    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = { 20, 16, 17, 5, 6 };
        mergeSort(arr, 0, arr.length - 1);
        printArray(arr);
    }

}
