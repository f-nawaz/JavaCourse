package session;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import qualifiers.InMemory;
import entity.Film;
@InMemory
public class InMemoryFilmDAO implements FilmDAO {

//	private Long currentId = 1L;
//	private Map<Long, Film> films = new HashMap<>();	
	private Serializer serializer = new SerializerImpl();;
	private AtomicLong currentId = new AtomicLong(1L);	
	private Map<Long, Film> films;


	public InMemoryFilmDAO() {
	}

	//for unit test
	public InMemoryFilmDAO(Map<Long, Film> films, Serializer serializer) {
		this.serializer = serializer;
		this.films = films;
	}

	//for unit test (interactions test)
//	public InMemoryFilmDAO(Serializer serializer) {
//		this.serializer=serializer;
//	}

	@Override
	public boolean delete(Long filmId) {
		boolean deleted = films.remove(filmId) == null ? false : true;
		//serializer.serialize(films);
		return deleted;
	}

	@Override
	public Long insert(Film film) {
		Long id = currentId.addAndGet(1L);
		//Long id = currentId++;
		film.setId(id);
		films.putIfAbsent(id, film);
		if(serializer != null)
			serializer.serialize(films);
		return id;
	}

	/* (non-Javadoc)
	 * @see session.FilmDAO#selectAll()
	 */
	@Override
	public Collection<Film> selectAll() {
		if(serializer != null)
			films = serializer.deserialize();
		return films.values();
	}

	/* (non-Javadoc)
	 * @see session.FilmDAO#selectById(java.lang.Long)
	 */
	@Override
	public Film selectById(Long id) {
		return films.get(id);
	}

	/* (non-Javadoc)
	 * @see session.FilmDAO#selectByTitle(java.lang.String)
	 */
	@Override
	public Collection<Film> selectByTitle(String search) {

		Collection<Film> filmCollection = films.values();
		return filmCollection.stream().filter(f -> f.getTitle().contains(search)).collect(Collectors.toList());


	}

	/* (non-Javadoc)
	 * @see session.FilmDAO#update(entity.Film)
	 */
	@Override
	public boolean update(Film film) {
		boolean updated = films.replace(film.getId(), film) == null ? false : true;
		return updated;
	}
}
