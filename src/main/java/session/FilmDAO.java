package session;

import java.util.Collection;

import jaxrsClient.RestClient;
import entity.Film;

public interface FilmDAO {	
	public enum Type {
		INMEMORY, JPA, REST
	}

	boolean delete(Long filmId);

	Long insert(Film film);

	Collection<Film> selectAll();

	Film selectById(Long id);

	Collection<Film> selectByTitle(String search);

	boolean update(Film film);

/*	static FilmDAO getFilmDAO(Type type){
	    switch (type) {
		case INMEMORY:
			return new InMemoryFilmDAO();
		case JPA:
			return new JpaFilmDAO();
		case REST:
			return new RestClient();
		default:
			return null;
	    }
	}*/

}