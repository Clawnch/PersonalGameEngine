package EulerClasses;

import java.util.ArrayList;
import java.util.List;

public class Problems6To10 {

    public static long sumSquareDifference(int limit) {
        long sumOfSquares = 0;
        long squareOfSums = 0;
        for (int i = 1; i <= limit; i++) {
            sumOfSquares += Math.pow(i, 2);
        }

        int sum = 0;
        for (int i = 1; i <= limit; i++) {
            sum += i;
        }
        squareOfSums += Math.pow(sum, 2);

        return squareOfSums - sumOfSquares;

    }

    public static long prime100001() {
        List<Long> primes = new ArrayList<>();
        primes.add(0L);
        Long current = 1L;
        while (primes.size() < 10002) {
            if (isPrime(current)) {
                primes.add(current);
                System.out.println(primes.size());
            }
            current+=2;
        }
        return primes.get(primes.size()-1);
    }

    private static boolean isPrime(Long number) {
        for (long i = 2; i < number / 2; i++) {
            if (number % i == 0) return false;
        }
        return true;
    }

    public static long largestProductInSeries(String series) {
        long result = 0;
        for (int i = 0; i < series.length() - 13; i++) {
            long sequenceResult = productOfSequence(series.substring(i, i + 13));
            if (sequenceResult > result) result = sequenceResult;
        }
        return result;
    }

    private static long productOfSequence(String sequence) {
        int[] numbers = new int[sequence.length()];
        for (int i = 0; i < sequence.length(); i++) {
            numbers[i] = Integer.parseInt(sequence.charAt(i) + "");
        }
        long result = 1;
        for (Integer i : numbers) {
            result *= i;
        }
        return result;
    }
}
