package servlets;

import entity.Film;
import session.FilmDAO;
import session.FilmDAO.Type;
import session.JpaFilmDAO;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

@WebServlet("/Servlet1")
public class Servlet1 extends HttpServlet {

	private FilmDAO dao = new JpaFilmDAO();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		String str = req.getParameter("searchText");
		Collection<Film> films = str == null ? dao.selectAll() : dao.selectByTitle(str);
		for (Film film : films) {
			out.print(film.getTitle());
			out.println("<br/>");
		}
		out.println("</body>");
		out.println("</html>");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}

}
