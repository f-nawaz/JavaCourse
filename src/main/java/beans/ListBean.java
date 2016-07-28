package beans;

import java.io.Serializable;
import java.util.Collection;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import qualifiers.Jpa;
import qualifiers.Jta;
import session.FilmDAO;
import entity.Film;

@Named("listBean")
@javax.enterprise.context.SessionScoped
//Session scoped beans must be capable of being passivated, so implement
//Serializable
public class ListBean  implements Serializable{
	private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);	
	//private FilmDAO dao = new JpaFilmDAO();
	@Inject @Jta
	private FilmDAO dao;
	private Collection<Film> films;
	private String searchText;
	
	@PostConstruct
	private void init(){
		films = dao.selectAll();
		logger.info("FilmDAO implementing class: "+dao.getClass().getName());	
	}
	
	public Collection<Film> getFilms() {
		return films;
	}
	
	public String getSearchText() {
		return searchText;
	}
	
	public void setSearchText(String searchText) {
		this.searchText = searchText;
		films = dao.selectByTitle(searchText);
		logger.info("setSearchText");
	}
}
