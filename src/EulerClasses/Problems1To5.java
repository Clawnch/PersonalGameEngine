package EulerClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Problems1To5 {

    public static int sumBelow1000() {
        int result = 0;
        for (int i = 1; i < 1000; i++) {
            if (i % 3 == 0 || i % 5 == 0) {
                result += i;
            }
        }
        return result;
    }

    public static int evenFibonacciNumbers() {
        int result = 0;

        int num1 = 0;
        int num2 = 1;

        int num3 = 1;
        while (num3 < 4000000) {
            num3 = num2 + num1;

            num1 = num2;
            num2 = num3;
            if (num3 % 2 == 0) result += num3;
        }


        return result;
    }

    public static long largestPrimeFactor(long input) {
        List<Long> factors = new ArrayList<>();
        long current = input;

        while (current != 1) {
            for (long i = 2; i < input / 2; i++) {
                if (current % i == 0) {
                    current = current / i;
                    factors.add(i);
                    break;
                }
            }
        }

        System.out.println(factors);
        long largest = 0;
        for (Long i : factors) {
            if (i > largest) largest = i;
        }

        return largest;

    }

    public static int palindrome() {
        int topLimit = 999 * 999;

        int[] factors;
        while (true) {
            while (!isPalindrome(topLimit)) {
                topLimit--;
            }
            factors = threeDigitFactors(topLimit, 999);
            System.out.println("Top Number: " + topLimit + " ~~ Factor 1: " + factors[0] + " " + "Factor 2: " + factors[1]);

            if (isPalindrome(topLimit) && bothFactorsThreeDigits(factors)) break;
            topLimit--;
        }

        return topLimit;
    }

    private static boolean isPalindrome(int number) {
        String strInt = "" + number;
        boolean isPalindrome = true;
        for (int i = 0; i < strInt.length() / 2; i++) {
            if (strInt.charAt(i) != strInt.charAt(strInt.length() - i - 1)) return false;
        }
        return isPalindrome;
    }

    private static int[] threeDigitFactors(int number, int start) {
        int[] factors = new int[2];
        int first = start;
        while (number % first != 0) {
            first -= 1;
        }
        factors[0] = first;
        factors[1] = number / first;
        if (first < 100) return factors;
        if (!bothFactorsThreeDigits(factors)) return threeDigitFactors(number, first - 1);
        return factors;
    }

    private static boolean bothFactorsThreeDigits(int[] factors) {
        String first = "" + factors[0];
        String second = "" + factors[1];
        return first.length() == 3 && second.length() == 3;
    }

    public static long smallestMultiple1Through20() {
        long number = 20;

        while (!divisibleCheck1Through20(number)) {
            number += 20;
        }

        return number;
    }

    private static boolean divisibleCheck1Through20(long numberToCheck) {
        for (int i = 2; i < 21; i++) {
            if (numberToCheck % i != 0) return false;
        }
        return true;
    }
}
