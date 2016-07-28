
package session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Singleton class that provides access to a SessionFactory
 *
 */
public class EntityManagerUtil {
	//calls private constructor when class is first loaded
	private final static EntityManagerUtil instance = new EntityManagerUtil();
	//initialised by constructor
	private final EntityManagerFactory factory;

	//private constructor sets EntityManagerFactory
	private EntityManagerUtil() {
		factory = Persistence.createEntityManagerFactory("pu1");
	}

	//returns an EntityManager
	public static EntityManager getEntityManager() {
		return instance.factory.createEntityManager();
	}
}
