package examples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class DatesAndTimes {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		//Instant
		
		// An instant represents a point in time
		// the origin is set arbitrarily at 1 Jan 1970 GMT
		System.out.printf("Instant.now %d seconds since 1 Jan 1970 GMT %n",Instant.now().getEpochSecond());
		Instant start = Instant.now();
		Thread.sleep(1000);
		Instant end = Instant.now();
		//a duration is the amount of time between two instants
		System.out.printf("Duration %d milliseconds between two instants%n", Duration.between(start, end).toMillis());		
		
		new BufferedReader(new InputStreamReader(System.in)).readLine();//wait for enter key


		//LocalDate

		System.out.println("LocalDate and LocalTime have no time zone information, so don't correspond to a precise instant in time");
		LocalDate today = LocalDate.now();
		LocalDate date1 = LocalDate.of(2015, 1, 20);
		System.out.printf("LocalTime %s%n",date1);
		LocalDate date2 = date1.plusMonths(1);
		System.out.printf("LocalDate one month later %s %n",date2);
		LocalTime time1 = LocalTime.now();
		LocalTime time2 = LocalTime.of(12, 30);
		System.out.printf("LocalTime %s%n",time2);
		LocalDateTime localDateTime1 = LocalDateTime.of(2014,3,29,14,45);
		LocalDateTime localDateTime2 = localDateTime1.plusHours(24);
		System.out.printf("LocalDateTime %s%n",localDateTime2);
		
		new BufferedReader(new InputStreamReader(System.in)).readLine();//wait for enter key

		//ZonedDateTime

		LocalDateTime localDateTime = LocalDateTime.of(2014,3,29,14,45);//summer time starts midnight 29 March 2014
		ZonedDateTime zonedTime1 = ZonedDateTime.of(localDateTime, 
		                                       ZoneId.of("Europe/Berlin"));
		
		//A ZoneId sets the offset from UTC/Greenwich
		
		
		System.out.printf("ZonedDateTime %s%n",zonedTime1);
		ZonedDateTime zonedTime2 = zonedTime1.plusHours(24);
		System.out.printf("Summer time %s%n",zonedTime2);
		
		new BufferedReader(new InputStreamReader(System.in)).readLine();//wait for enter key

		//DateTimeFormatter

		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL);
		System.out.printf("Formatted: %s%n", formatter.format(zonedTime2));



	}

}
