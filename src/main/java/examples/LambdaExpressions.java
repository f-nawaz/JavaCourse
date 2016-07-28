package examples;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class X implements Runnable {
	@Override
	public void run() {

	}
}

public class LambdaExpressions {

	public static void main(String[] args) {
		

		
		
		// runnableExample();
		// stream1();//count()
		// collector1();//Collectors.toList()
		// collector2();//supplier, accumulator, combiner
		// collector3();//method references
		System.out.println(primeCount(1000));
	}

	private static void runnableExample() {
		Runnable r1 = new X();

		Runnable r2 = new Runnable() {
			@Override
			public void run() {
				// executed in separate thread
			}
		};

		Runnable r3 = () -> {
			// executed in separate thread
		};

	}

	private static void stream1() {
		Set<String> zoneIds = ZoneId.getAvailableZoneIds();

		Predicate<String> predicate = s -> s.contains("Europe");

		Function<String, String> function = s -> s.substring(7);

		long count = zoneIds.stream(). // create a stream
				filter(predicate). // intermediate operation
				map(function). // intermediate operation
				count(); // terminal operation

		System.out.println(count);
	}

	private static void collector1() {
		Set<String> zoneIds = ZoneId.getAvailableZoneIds();
		List<String> zones = zoneIds.stream().filter(s -> s.contains("Europe")). // predicate
				map(x -> x.substring(7)). // projection
				collect(Collectors.toList()); // terminal operation

		zones.forEach(s -> System.out.println(s));
	}

	private static void collector2() {
		Set<String> zoneIds = ZoneId.getAvailableZoneIds();

		// supplier - function that creates a new result container
		Supplier<ArrayList<String>> supplier = () -> new ArrayList<String>();

		// accumulator - function for incorporating an additional element into a
		// result
		BiConsumer<ArrayList<String>, String> accumulator = (x, y) -> {
			x.add(y);
		};

		// combiner - function for combining two values, compatible with the
		// accumulator
		BiConsumer<ArrayList<String>, ArrayList<String>> combiner = (x, y) -> {
			x.addAll(y);
		};

		List<String> zones = zoneIds.stream(). // create a stream
				filter(s -> s.contains("Europe")). // intermediate operation
				map(x -> x.substring(7)). // intermediate operation
				collect(supplier, accumulator, combiner); // terminal operation

		zones.forEach(s -> System.out.println(s));
		zones.forEach(System.out::println);
	}

	private static void collector3() {
		Set<String> zoneIds = ZoneId.getAvailableZoneIds();

		List<String> zones = zoneIds.stream().filter(s -> s.contains("Europe")).map(x -> x.substring(7))
				.collect(ArrayList::new, // supplier
						ArrayList::add, // accumulator
						ArrayList::addAll // combiner
				);

		zones.forEach(System.out::println);

	}

	public static long primeCount(int limit) {
		Instant start = Instant.now();
	
		long count = IntStream.range(2, limit).parallel().filter(p -> !IntStream.range(2, p).anyMatch(n -> p % n == 0))
				.count();
		
		Instant end = Instant.now();
		//a duration is the amount of time between two instants
		System.out.printf("%d milliseconds ", Duration.between(start, end).toMillis());		
	
		return count;
	}

}
