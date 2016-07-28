package session;

import static org.mockito.Mockito.*;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Ignore;
import org.junit.Test;

import entity.Film;

/**
 * Test doubles are used to isolate the SUT from its collaborators (DOCs)
 * which in this example is a the SerializerImpl class
 *
 */
//@Ignore
public class InMemoryFilmDAOMockTest {
	
	// arrange
	private Serializer doc = mock(Serializer.class);//test double
	private ConcurrentHashMap<Long, Film> map = new ConcurrentHashMap<>();		
	private FilmDAO sut = new InMemoryFilmDAO(map, doc);

	/**
	 * A spy is used to verify if the SUT calls specific methods of the
	 * collaborator (indirect outputs)
	 */
	@Test
	public void insertShouldCallSerializeMethodOfSerializer() {
		//arrange
		Film film = mock(Film.class); //dummy
		// act
		sut.insert(film); 
		// assert
		verify(doc).serialize(map);	//doc is a spy (verifies indirect outputs)	
	}
	
	/**
	 * A stub is used for passing values to the SUT (indirect inputs)
	 */
	@Test
	public void selectAllShouldCallDeserializeMethodOfSerializer() {
		//arrange
		when(doc.deserialize()).thenReturn(map);
		// act
		Collection<Film> films = sut.selectAll();
		// assert
		Map<Long, Film> map = verify(doc).deserialize(); //doc is a stub (verifies indirect inputs)
	}
}
