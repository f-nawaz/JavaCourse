package entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "OrderTable")
public class Order {
	private boolean dispatched;


//	@JoinTable(name = "bridge",
//	           joinColumns = @JoinColumn(name = "order_id"),
//	           inverseJoinColumns = @JoinColumn(name = "film_id"))
	@ManyToMany
	private Set<Film> films = new HashSet<>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;

	public boolean addFilm(Film film) {
		return films.add(film);
	}

	public void removeFilm(Film film) {
		films.remove(film);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isDispatched() {
		return dispatched;
	}

	public void setDispatched(boolean dispatched) {
		this.dispatched = dispatched;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<Film> getFilms() {
		return films;
	}

	public void setFilms(Set<Film> films) {
		this.films = films;
	}
}