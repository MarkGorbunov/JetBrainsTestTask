package decision;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PrimeCalculatorRefactoredInitial {
    public static void main(String[] args) throws InterruptedException {
        OffsetDateTime now = OffsetDateTime.now();
        ConcurrentLinkedQueue<Integer> primes = getPrimes(1000000);
        System.out.println();
        OffsetDateTime finish = OffsetDateTime.now();
        System.out.println(Duration.between(finish, now).toMillis());
        System.out.println("<<<" + primes.size());
        System.out.println(primes.stream().sorted().collect(Collectors.toList()).equals(primes));
    }

    private static ConcurrentLinkedQueue<Integer> getPrimes(int maxPrime) throws InterruptedException {
        ConcurrentLinkedQueue<Integer> primeNumbers = new ConcurrentLinkedQueue<>();
        CountDownLatch latch = new CountDownLatch(maxPrime - 1);
        ExecutorService executors = Executors.newFixedThreadPool(7);
        for (int i = 0; i < maxPrime; i++) {
            int finalI = i;
            executors.submit(() -> {
                if (!isPrime(finalI)) {
                    primeNumbers.add(finalI);
                }
                latch.countDown();
            });
        }
        latch.await();
        executors.shutdownNow();
        return primeNumbers;
    }

    private static boolean isPrime(Integer candidate) {
        boolean flag = false;
        for (int i = 2; i <= candidate / 2; ++i) {
            // condition for nonprime number
            if (candidate % i == 0) {
                flag = true;
                break;
            }
        }
        return flag;
    }
}