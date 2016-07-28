package collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;

import entity.Film;
import entity.Genre;

@Ignore
public class CollectionTest {

	Film film1 = new Film("The Godfather", 2, LocalDate.of(1972, 4, 17), Genre.CRIME);
	Film film2 = new Film("The Godfather", 2, LocalDate.of(1972, 4, 17), Genre.CRIME);

	/**
	 * • A List is an ordered collection. The user has precise control over
	 * where in the list each element is inserted and elements can be accessed
	 * by their integer index. Unlike sets, lists allow duplicate elements.
	 */
	@Test
	public void listCanStoreDuplicates() {
		// arrange
		List<Film> set = new ArrayList<>();
		// act
		boolean film1Added = set.add(film1);
		boolean film2Added = set.add(film2);
		// assert
		assertTrue(film1Added);
		assertTrue(film2Added);
		assertEquals(2, set.size());
	}

	/**
	 * A Set is an unordered collection that contains no duplicate elements. A
	 * SortedSet orders its contents.
	 */
	@Test
	public void setContainsUniqueObjects() {
		// arrange
		Set<Film> set = new HashSet<>();
		// act
		boolean film1Added = set.add(film1);
		boolean film2Added = set.add(film2);
		// assert
		assertTrue(film1Added);
		assertFalse(film2Added);
		assertEquals(1, set.size());
	}

	/**
	 * A Map associates unique keys with values. It provides three collection
	 * views, which allow a map's contents to be viewed as a set of keys,
	 * collection of values, or set of key-value mappings. TreeMap is an ordered
	 * implementation of Map, while HashMap is unordered.
	 */
	@Test
	public void mapContainsUniqueKeys() {
		// arrange
		Map<Long, Film> map = new HashMap<>();
		// act
		Film previousValue1 = map.put(1L, film1);// put returns the previous
													// value associated with a
													// key

		Film previousValue2 = map.put(1L, film2);
		// assert
		assertNull(previousValue1);
		assertEquals(film1, previousValue2);
		assertEquals(1, map.size());
	}

	@Test
	public void addUpdateAndRemoveFromMap() {
		// arrange
		Map<Long, Film> map = new HashMap<>();
		// act
		Film previousValue1 = map.put(1L, film1); //add key and value to a map
		Film previousValue2 = map.replace(1L, film2); //null if key isn't in map
		Film removedFilm = map.remove(1L); //remove value with specified key
		// assert
		assertNull(previousValue1);
		assertEquals(film1, previousValue2);
		assertEquals(film1, removedFilm);
		assertTrue(map.isEmpty());
	}
}
