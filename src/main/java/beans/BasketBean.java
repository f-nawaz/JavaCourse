package beans;

import java.io.Serializable;
import java.util.Collection;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBAccessException;
import javax.ejb.Init;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import session.JpaOrderModel;
import session.OrderModel;
import entity.Film;

@Named
@javax.enterprise.context.SessionScoped
public class BasketBean implements Serializable {
	//private JpaOrderModel dao = new JpaOrderModel();
	@Inject private OrderModel dao;
	private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	@PostConstruct
	private void init() {
		logger.info("OrderModel implementing class: "+dao.getClass().getName());		
	}

	public String addToOrder(Long filmId) {
		try {
			boolean added = dao.addToOrder(filmId);
			logger.info("filmId " + filmId);
		} catch (EJBAccessException e) {
			return "login.xhtml";
		}
		return "basket.xhtml?faces-redirect=true";
	}

	public Collection<Film> getFilmsInOrder() {
		return dao.getFilmsInOrder();
	}

	public String purchase() {
		dao.persistOrder();
		return "confirm.xhtml";
	}

	public String logout() {
		((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).invalidate();
		logger.info("logged out");
		return "filmlist.xhtml?faces-redirect=true";
	}
}
