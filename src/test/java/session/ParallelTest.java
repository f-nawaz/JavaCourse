package session;
import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.code.tempusfugit.concurrency.ConcurrentTestRunner;

import entity.Film;

@Ignore
@RunWith(ConcurrentTestRunner.class)
public class ParallelTest {
	private static InMemoryFilmDAO sut = new InMemoryFilmDAO(); //static field is shared by the threads
	private Film film = new Film();
	private int n = 100;

	@Test
	public void shouldRunInParallel1() throws InterruptedException {
		for (int i = 0; i < n; i++) {
			//act
			Long id1 = sut.insert(film);		
		}
		Thread.sleep(5000);//pause to allow threads to finish
		assertEquals(n*2, sut.selectAll().size());	
	}

	@Test
	public void shouldRunInParallel2() {
		for (int i = 0; i < n; i++) {
			//act
			Long id1 = sut.insert(film);
		}		
	}
	

}
