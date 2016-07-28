package concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;

public class Asynchronous {
	public static void main(String[] args) {
		CompletableFuture.supplyAsync(() -> LongStream.range(2, 100000).filter(p -> !LongStream.range(2, p).anyMatch(n -> p % n == 0)).count()).thenAccept(s -> System.out.println(s));
		System.out.println("a callback has been set up");
		ForkJoinPool.commonPool().awaitQuiescence(15, TimeUnit.SECONDS); //prevents main method exiting
	}
}
