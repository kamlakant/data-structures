package misc;

public class Fibonacci {

    /**
     * f(n) is called many times for same number. O(2^n)
     */
    private long fibonnaci(int number) {
        if (number <= 0) {
            return 0;
        } else if (number == 1) {
            return 1;
        } else {
            return fibonnaci(number - 1) + fibonnaci(number - 2);
        }
    }

    private long[] memo = new long[1000];

    /**
     * f(n) is called only once for same number. That number is stored for future reference. O(n)
     */
    private long memoizedFibonnaci(int number) {
        if (number <= 0) {
            return 0;
        } else if (number == 1) {
            return 1;
        } else {
            if (memo[number] == 0) {
                memo[number] = memoizedFibonnaci(number - 1) + memoizedFibonnaci(number - 2);
            }
            return memo[number];
        }
    }

    public static void main(String[] args) {
        Fibonacci fib = new Fibonacci();

        // Takes very less time
        System.out.println(fib.memoizedFibonnaci(50));

        // Takes a lot of time
        System.out.println(fib.fibonnaci(50));
    }

}
