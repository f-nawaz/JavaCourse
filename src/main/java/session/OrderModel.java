package session;

import java.util.Set;

import entity.Film;

public interface OrderModel {

	boolean addToOrder(Long filmId);

	Set<Film> getFilmsInOrder();

	int persistOrder();

}