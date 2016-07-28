package jaxrsClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.google.gson.GsonBuilder;
import entity.Film;
import entity.Genre;
import session.FilmDAO;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * GSON can't parse LocalDate, so change Film class to use java.util.Date
 */
public class RestClient implements FilmDAO{

	private static String urlString = "http://localhost:8080/IntelliJ-1.0/rest/films/";
	private static String urlStringServlet = "http://localhost:8080/GradleProject-1.0/RestServlet";

	public static void main(String[] args) {
		FilmDAO client = new RestClient();
		Collection<Film>films = client.selectByTitle("z");
		for (Film film : films) {
			System.out.println(film.getTitle());
		}
		
		Film film = new Film("Aliens", 2, LocalDate.of(1985, 1, 27), Genre.SCIENCE_FICTION);
		//long responseCode = client.insert(film);
		//System.out.println(responseCode);
	}

	private int postRequest(String urlString, Film film) {
		try {
			Gson gson = new Gson();
			String jsonString = gson.toJson(film);
			URL url = new URL(urlString);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			// System.out.println(connection.getResponseCode());
			OutputStream os = connection.getOutputStream();
			os.write(jsonString.getBytes());
			os.flush();
			connection.disconnect();
			return connection.getResponseCode();
		} catch (IOException e) {
			return 500;
		}
	}

	//add transient keyword to released field in Film class
	private Collection<Film> getRequest(String urlString) {
		try {
			URL url = new URL(urlString);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/json");
			if (connection.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
			}
			BufferedReader reader = new BufferedReader(new InputStreamReader((connection.getInputStream())));
			String jsonString = reader.readLine();
			System.out.println(jsonString);
			connection.disconnect();
			reader.close();
			Gson gson = new Gson();
			 gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssz").create();




			Object ob = gson.fromJson(jsonString, Film[].class);

			Film[] films = (Film[]) ob;
			return Arrays.asList(films);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}


	@Override
	public boolean delete(Long filmId) {
		return false;
	}

	@Override
	public Long insert(Film film) {
		return Long.valueOf(postRequest(urlString,film));
	}

	@Override
	public Collection<Film> selectAll() {
		return getRequest(urlString);
	}

	@Override
	public Film selectById(Long id) {
		return null;
	}

	@Override
	public Collection<Film> selectByTitle(String search) {
		return getRequest(urlString+search);
	}

	@Override
	public boolean update(Film film) {
		return false;
	}
}
