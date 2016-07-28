package session;


import entity.Film;
import qualifiers.Jpa;
import qualifiers.Jta;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collection;

@Stateless
@Jta
public class JtaFilmDAO implements FilmDAO {

    @PersistenceContext(unitName = "pu2")
    private EntityManager em;
    @Resource
    private EJBContext context;

    @Override
    public Film selectById(Long id) {
        Film film = em.find(Film.class, id);
        return film;
    }

    /**
     * A JPQL Select Statement BNF select_statement ::= select_clause
     * from_clause [where_clause] [groupby_clause] [having_clause]
     * [orderby_clause] */

    @Override
    public Collection<Film> selectAll() {
        String jpql = "select f from Film f order by f.title";
        TypedQuery<Film> query = em.createQuery(jpql, Film.class);
        Collection<Film> films = query.getResultList();
        return films;
    }

    @Override
    public Collection<Film> selectByTitle(String search) {
        String jpql = "select f from Film f where lower(f.title) like :searchText order by f.title";
        TypedQuery<Film> query = em.createQuery(jpql, Film.class);
        query.setParameter("searchText", "%" + search.toLowerCase() + "%");
        Collection<Film> films = query.getResultList();
        return films;
    }

/**
     * Objects, in relation to a session can be transient, persistent, detached
     * or removed. The save method persists a transient instance; an object that
     * the database has no knowledge of. Changes to a persistent object are
     * written to the database when the transaction commits. */
    @Override
    public Long insert(Film film) {
        Long id = 0L;
        em.persist(film);// persists a transient instance
        id = film.getId();
        return id;
    }

    /**
     * The merge method changes an entity's state from detached or transient to
     * persistent. This will update a row with a matching primary key, or insert
     * a row if there's no match */
    @Override
    public boolean update(Film film) {
        em.merge(film);// a detached entity is changed to persistent
        return true;
    }

    /**
     * The delete method takes a persistent argument or a transient object with
     * an id matching an id in the database. The row in the database is deleted
     * when the transaction commits*/
     @Override
    public boolean delete(Long filmId) {
        try {
            Film film =  em.find(Film.class, filmId);
            if (film == null)
                return false; // will execute finally next
            em.remove(film);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            context.setRollbackOnly();
            return false;
        }
    }

}
