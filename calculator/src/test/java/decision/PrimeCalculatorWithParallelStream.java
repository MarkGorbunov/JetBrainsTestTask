package decision;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PrimeCalculatorWithParallelStream {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        OffsetDateTime now = OffsetDateTime.now();
        List<Integer> primes = getPrimes(1000000);
        System.out.println();
        OffsetDateTime finish = OffsetDateTime.now();
        System.out.println(Duration.between(finish, now).toMillis());
        System.out.println("<<<" + primes.size());
        System.out.println(primes.stream().sorted().collect(Collectors.toList()).equals(primes));
    }

    private static List<Integer> getPrimes(int maxPrime) throws ExecutionException, InterruptedException {
        ForkJoinPool customThreadPool = new ForkJoinPool(7);
        List<Integer> primeNumbers = customThreadPool.submit(() -> IntStream.range(2, maxPrime).parallel().
                filter(candidate -> !isPrime(candidate)).boxed().collect(Collectors.toList())).get();
        customThreadPool.shutdownNow();
        return primeNumbers;
    }

    private static boolean isPrime(Integer candidate) {
        boolean flag = false;
        for (int i = 2; i <= candidate / 2; ++i) {
            if (candidate % i == 0) {
                flag = true;
                break;
            }
        }
        return flag;
    }
}