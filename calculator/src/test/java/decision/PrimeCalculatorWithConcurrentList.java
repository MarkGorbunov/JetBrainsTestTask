package decision;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PrimeCalculatorWithConcurrentList {
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
        final CopyOnWriteArrayList<Integer> numbers = new CopyOnWriteArrayList(IntStream.rangeClosed(2, maxPrime).
                boxed().collect(Collectors.toList()));
        ForkJoinPool customThreadPool = new ForkJoinPool(7);
        List<Integer> primeNumbers = customThreadPool.submit(() -> numbers.parallelStream().filter(candidate -> !isPrime(candidate)).collect(Collectors.toList())).get();
        customThreadPool.shutdownNow();
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