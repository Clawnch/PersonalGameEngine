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

    public static int[] pythagoreanTriplet() {
        int[] triplet = new int[3];

        System.out.println("Start of create");
        triplet = createTriplet(5);
//        while (sumOfTriplet(triplet) < 10000) {
//            triplet = createTriplet(a);
//            a++;
//            System.out.println("A: " + triplet[0] + " B: " + triplet[1] + " C: " + triplet[2] + " ~ Sum: " + sumOfTriplet(triplet));
//        }


        return triplet;
    }

    private static int[] createTriplet(int lowestNumber) {
        double a = 0, b = 0 , c = 0;
        a = lowestNumber - 1;
        while (a + b + c != 1000) {
            a++;
            b = a + 1;
            c = Math.pow(a, 2) + Math.pow(b, 2);
            while (true) {
                System.out.println("below 1000 check");
                while (true) {
                    c = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
                    if (c % 1 == 0 || b > 1000) break;
                    b++;
                }
                System.out.println("A: " + a + " B: " + b + " C: " + c);
                if (b > 1000 || c > 1000 || a + b + c == 1000) break;
                b++;
            }
        }
        System.out.println("Sum: " + (a + b + c) + " ~~ Product: " + (long)(a * b * c));
        return new int[]{(int)a,(int)b, (int)c};
    }

    private static int sumOfTriplet(int[] triplet) {
        return triplet[0] + triplet[1] + triplet[2];
    }

    private static boolean tripletNotContainOver1000(int[] triplet) {
        for (int i : triplet) {
            if (i >= 1000) return false;
        }
        return true;
    }

    public static long sumOfPrimesBelow(int limit) {
        List<Integer> primes = new ArrayList<>();
        for (int i = 3; i < limit; i+=2) {
            if (isPrime((long)i)) primes.add(i);
        }
        long result = 2;
        for (Integer i : primes) result += i;
        return result;

    }
}
