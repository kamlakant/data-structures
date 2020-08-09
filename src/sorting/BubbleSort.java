package sorting;

/**
 * O(n^2)
 *
 */
public class BubbleSort {

    private void sort(int[] numbers) {
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = 0; j < numbers.length - i - 1; j++) {
                if (numbers[j] > numbers[j + 1]) {
                    int temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                }
            }
        }
    }

    private void print(int[] numbers) {
        for (int i : numbers) {
            System.out.print(i);
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
