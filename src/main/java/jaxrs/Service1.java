package jaxrs;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import qualifiers.Jpa;
import session.FilmDAO;
import session.FilmDAO.Type;
import entity.Film;

@Path("films")
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class Service1 {
	
	//private FilmDAO dao = FilmDAO.getFilmDAO(Type.JPA);

    @Inject
    @Jpa
    private FilmDAO dao;

   //URI is http://localhost:8080/Web1/rest/films
   @GET
   public Collection<Film> getAllFilms() {
	   Collection<Film> films = dao.selectAll();
      return  films;
      }
   
   //URI is http://localhost:8080/Web1/rest/films/z
   @GET
   @Path("{searchString}")
   public Collection<Film> getFilmsByTitle(@PathParam("searchString") String text) {
         return dao.selectByTitle(text);
   }
   
   @POST
   @Consumes(MediaType.APPLICATION_JSON)
   public Response createFilm(Film f) {
      dao.insert(f);
      return Response.ok().build();
   }
}

