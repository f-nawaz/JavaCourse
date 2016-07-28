package concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.LongStream;

public class Blocking {
	public static void main(String[] args) {
		ExecutorService threadPool = Executors.newCachedThreadPool();
		Callable<Long> callable = () -> LongStream.range(2, 100000).filter(p -> !LongStream.range(2, p).anyMatch(n -> p % n == 0)).count();		
		Future<Long> future1 = threadPool.submit(callable);
		try {
			System.out.println("get is a blocking call");
			long result = future1.get();// blocks until result returned
			System.out.println(result);
		} catch (InterruptedException | ExecutionException e) {
			System.out.println(e.getMessage());
		}
		threadPool.shutdown(); // closes the thread pool
	}	
}
