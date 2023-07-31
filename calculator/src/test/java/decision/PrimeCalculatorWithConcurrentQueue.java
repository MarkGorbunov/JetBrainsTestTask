package decision;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PrimeCalculatorWithConcurrentQueue {
    public static void main(String[] args) throws InterruptedException {
        OffsetDateTime now = OffsetDateTime.now();
        Collection<Integer> primes = getPrimes(1000000);
        System.out.println();
        OffsetDateTime finish = OffsetDateTime.now();
        System.out.println(Duration.between(finish, now).toMillis());
//        for (Integer prime : primes) {
//            System.out.println(prime);
//        }
        System.out.println("<<<" + primes.size());
        System.out.println(primes.stream().sorted().collect(Collectors.toList()).equals(primes));
    }

    private static Collection<Integer> getPrimes(int maxPrime) throws InterruptedException {
        final ConcurrentLinkedQueue<Integer> numbers = new ConcurrentLinkedQueue<>(IntStream.rangeClosed(2, maxPrime).
                boxed().collect(Collectors.toList()));

        ConcurrentLinkedQueue<Integer> primeNumbers = new ConcurrentLinkedQueue<>();
        CountDownLatch latch = new CountDownLatch(maxPrime - 1);
        ExecutorService executors = Executors.newFixedThreadPool(7);
        for (Integer candidate : numbers) {
            executors.submit(() -> {
                if (!isPrime(candidate)) {
                    primeNumbers.add(candidate);
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