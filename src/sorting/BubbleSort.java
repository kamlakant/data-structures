package sorting;

/**
 * O(n^2) Compare each element with its right neighbor.
 * 
 * By the end of 1st pass, largest element is settled in last place.
 */
public class BubbleSort {

    private void sort(int[] numbers) {
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = 0; j < numbers.length - i - 1; j++) {
                if (numbers[j] > numbers[j + 1]) {
                    swap(numbers, j, j + 1);
                }
            }
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private void print(int[] numbers) {
        for (int i : numbers) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] numbers = { 5, 1, 3, 4, 2 };
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.print(numbers);
        bubbleSort.sort(numbers);
        bubbleSort.print(numbers);
    }

}
