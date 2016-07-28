package console;

import java.util.Collection;
import java.util.Hashtable;
import java.util.logging.Level;

import entity.Film;
import session.FilmDAO;
import session.FilmDAO.Type;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu1");
		EntityManager em =  factory.createEntityManager();
		String jpql = "select f from Film f order by f.title";
		TypedQuery<Film> query = em.createQuery(jpql, Film.class);
		Collection<Film> films = query.getResultList();
		for (Film film : films) {
			System.out.println(film.getTitle());
		}
		em.close();
 
	}

}
