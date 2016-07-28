package session;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entity.Film;
import entity.Order;

/**
 * This class uses JPA, configured in META-INF/persistence.xml
 *
 */
@Stateful
public class JpaOrderModel implements OrderModel {

	private Order order = new Order();
	private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	//@RolesAllowed("customer")
	public boolean addToOrder(Long filmId) {
		EntityManager em = EntityManagerUtil.getEntityManager();
		try {
			Film film = em.find(Film.class, filmId);
			if (film == null || order.getFilms().contains(film))
				return false;
			order.addFilm(film);
			return true;
		} finally {
			em.close();
		}
	}
	
	public Set<Film> getFilmsInOrder() {
		return order.getFilms();
	}

	public int persistOrder() {		
		EntityManager em = EntityManagerUtil.getEntityManager();
		em.getTransaction().begin();

		updateFilmStock(em);

		// makes the order object persistent, running a sql insert
		em.persist(order);
		order.getId();
		em.getTransaction().commit();
		em.close();
		return order.getId();
	}
	
	private void updateFilmStock(EntityManager em) {		
		String ql = "update Film f set f.stock = f.stock - 1 where f.stock > 0 and f.id in :filmsInOrder";
		Query query = em.createQuery(ql);
		
		//lambda expression gets filmIds in customer's order
		List<Long> filmIds = order.getFilms().stream().map(f -> f.getId()).collect(Collectors.toList());
		query.setParameter("filmsInOrder", filmIds);
		int rowsUpdated = query.executeUpdate();
							
		//removeIf removes elements of a collection that satisfy the predicate
		//using an iterator, so avoiding a ConcurrentModificationException
		order.getFilms().removeIf(f->f.getStock()==0);
	}
}
//	public void removeOrder(int id){
//		EntityManager em = EntityManagerUtil.getEntityManager();
//		em.getTransaction().begin();
//		Order order = em.find(Order.class, id);
//		em.remove(order);
//		em.getTransaction().commit();
//		em.close();
//	}




//}
