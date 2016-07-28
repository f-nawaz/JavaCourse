package session;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import entity.Film;
import entity.Genre;
//@Ignore
public class InMemoryFilmDAOTest {

	// arrange
	private Map<Long, Film> films = new HashMap<>();
	private FilmDAO sut = new InMemoryFilmDAO(films, null);

	@Test
	public void insertShouldReturnGeneratedId() {

		Film film1 = new Film("The Pink Panther", 5, LocalDate.of(1964, 1, 20), Genre.COMEDY);

		// act
		Long id1 = sut.insert(film1);

		// assert
		assertTrue(id1 instanceof Long);
	}

	@Test
	public void updateShouldModifyFilm() {
		// arrange
		Film film1 = new Film("The Pink Panther", 5, LocalDate.of(1964, 1, 20), Genre.COMEDY);
		Long generatedId = sut.insert(film1);
		film1.setTitle("The Black Panther");

		// act
		boolean updated = sut.update(film1);
		Film retrievedFilm = sut.selectById(generatedId);

		// assert
		assertTrue(updated);
		assertEquals("The Black Panther", retrievedFilm.getTitle());
	}

	@Test
	public void deleteShouldRemoveFilm() {
		// arrange
		Film film1 = new Film("The Pink Panther", 5, LocalDate.of(1964, 1, 20), Genre.COMEDY);
		Long generatedId = sut.insert(film1);

		// act
		boolean deleted = sut.delete(generatedId);

		// assert
		assertTrue(deleted);
		assertTrue(sut.selectAll().isEmpty());
	}

	@Test
	public void selectByIdShouldReturnMatchingFilm() {
		// arrange
		Film film1 = new Film("The Pink Panther", 5, LocalDate.of(1964, 1, 20), Genre.COMEDY);
		Film film2 = new Film("The Godfather", 2, LocalDate.of(1972, 4, 17), Genre.CRIME);
		Long id1 = sut.insert(film1);
		Long id2 = sut.insert(film2);

		// act
		Film retrievedFilm = sut.selectById(id1);

		// assert
		assertTrue(retrievedFilm.equals(film1));
	}

	@Test
	public void selectAllShouldReturnCollection() {
		// arrange
		Film film1 = new Film("The Pink Panther", 5, LocalDate.of(1964, 1, 20), Genre.COMEDY);
		Film film2 = new Film("The Godfather", 2, LocalDate.of(1972, 4, 17), Genre.CRIME);
		Long id1 = sut.insert(film1);
		Long id2 = sut.insert(film2);

		// act
		Collection<Film> films = sut.selectAll();

		// assert
		assertTrue(films.size() == 2);
	}

	@Test
	public void selectByTitleShouldGetMatchingFilms() {
		// arrange
		Film film1 = new Film("The Pink Panther", 5, LocalDate.of(1964, 1, 20), Genre.COMEDY);
		Film film2 = new Film("The Godfather", 2, LocalDate.of(1972, 4, 17), Genre.CRIME);
		Film film3 = new Film("Avatar", 2, LocalDate.of(2009, 7, 2), Genre.SCIENCE_FICTION);
		Long id1 = sut.insert(film1);
		Long id2 = sut.insert(film2);
		Long id3 = sut.insert(film3);

		// act
		Collection<Film> films = sut.selectByTitle("at");

		// assert
		assertTrue(films.size() == 2);
	}

/*	*//**
	 * A spy is used to verify if the SUT calls specific methods of the
	 * collaborator (indirect outputs)
	 *//*
	@Test
	public void insertShouldCallSerializeMethodOfSerializer() {
		// arrange
		Film film = mock(Film.class);// dummy
		SerializerImpl doc = mock(SerializerImpl.class);// spy
		HashMap<Long, Film> map = new HashMap<>();
		// add overloaded constructors to InMemoryFilmDAO class
		sut = new InMemoryFilmDAO(doc,map);
		// act
		sut.insert(film);
		// assert
		verify(doc).serialize(map);
	}*/

}
