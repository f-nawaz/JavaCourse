package servlets;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import session.FilmDAO;
import session.FilmDAO.Type;
import entity.Film;
import session.JpaFilmDAO;

@WebServlet("/RestServlet")
public class RestServlet extends HttpServlet {

	private FilmDAO dao = new JpaFilmDAO();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Gson gson = new Gson();
		Collection<Film> films = dao.selectAll();
		resp.getWriter().print(gson.toJson(films));
	}

}
