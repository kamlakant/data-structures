package misc;

import java.math.BigInteger;

public class Factorial {

    private BigInteger factorial(int number) {
        if (number == 1) {
            return BigInteger.ONE;
        } else {
            return BigInteger.valueOf(number).multiply(factorial(number - 1));
        }
    }

    public static void main(String[] args) {
        Factorial fact = new Factorial();
        System.out.println(fact.factorial(50));
    }

}
