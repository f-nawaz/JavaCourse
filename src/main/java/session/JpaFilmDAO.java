package session;


import entity.Film;
import qualifiers.Jpa;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.Collection;

@Stateless
@Jpa
public class JpaFilmDAO implements FilmDAO {

    @Override
    public Film selectById(Long id) {
        // get returns null if id not in database
        EntityManager em = EntityManagerUtil.getEntityManager();
        Film film = em.find(Film.class, id);
        em.close();
        return film;
    }

    /**
     * A JPQL Select Statement BNF select_statement ::= select_clause
     * from_clause [where_clause] [groupby_clause] [having_clause]
     * [orderby_clause]
     */
    @Override
    public Collection<Film> selectAll() {
        EntityManager em = EntityManagerUtil.getEntityManager();
        String jpql = "select f from Film f order by f.title";
        TypedQuery<Film> query = em.createQuery(jpql, Film.class);
        Collection<Film> films = query.getResultList();
        em.close();
        return films;
    }

    @Override
    public Collection<Film> selectByTitle(String search) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        String jpql = "select f from Film f where lower(f.title) like :searchText order by f.title";
        TypedQuery<Film> query = em.createQuery(jpql, Film.class);
        query.setParameter("searchText", "%" + search.toLowerCase() + "%");
        Collection<Film> films = query.getResultList();
        em.close();
        return films;
    }

    /**
     * Objects, in relation to a session can be transient, persistent, detached
     * or removed. The save method persists a transient instance; an object that
     * the database has no knowledge of. Changes to a persistent object are
     * written to the database when the transaction commits.
     */
    @Override
    public Long insert(Film film) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        Long id = 0L;
        em.getTransaction().begin();
        em.persist(film);// persists a transient instance
        id = film.getId();
        em.getTransaction().commit();// updates the database from the persistence context
        em.close();
        return id;
    }

    /**
     * The merge method changes an entity's state from detached or transient to
     * persistent. This will update a row with a matching primary key, or insert
     * a row if there's no match
     */
    @Override
    public boolean update(Film film) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        em.getTransaction().begin();//IllegalStateException - if invoked on a JTA entity manager
        em.merge(film);// a detached entity is changed to persistent
        em.getTransaction().commit();
        em.close();
        return true;
    }

    /**
     * The delete method takes a persistent argument or a transient object with
     * an id matching an id in the database. The row in the database is deleted
     * when the transaction commits
     */
    @Override
    public boolean delete(Long filmId) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Film film =  em.find(Film.class, filmId);
            if (film == null)
                return false; // will execute finally next
            em.remove(film);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

/*    @Resource private EJBContext context;
    @PersistenceContext(unitName = "pu2") private EntityManager em;
    public boolean delete2(Long filmId) {
        try {
            Film film =  em.find(Film.class, filmId);
            if (film == null)
                return false; // will execute finally next
            em.remove(film);
            return true;
        } catch (Exception e) {
            context.setRollbackOnly();
            return false;
        }
    }*/

}
