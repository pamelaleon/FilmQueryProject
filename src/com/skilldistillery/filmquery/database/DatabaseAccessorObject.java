package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {

	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;

		try {
			String user = "student";
			String pwd = "student";

			String sql = "SELECT film.id, film.title, film.release_year, film.rating, film.description, language.name FROM film JOIN language ON film.language_id = language.id WHERE film.id = ?";

			Connection conn = DriverManager.getConnection(URL, user, pwd);

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, filmId);

			// System.out.println(stmt);

			ResultSet filmResult = stmt.executeQuery();

			if (filmResult.next() ) {
				film = new Film();
				film.setId(filmResult.getInt("film.id"));
				film.setTitle(filmResult.getString("film.title"));
				film.setReleaseYear(filmResult.getInt("film.release_year"));
				film.setRating(filmResult.getString("film.rating"));
				film.setDescription(filmResult.getString("film.description"));
				film.setLanguage(filmResult.getString("language.name"));

			}

			filmResult.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return film;
	}

	public Actor findActorById(int actorId) throws SQLException {
		Actor actor = null;

		String user = "student";
		String pwd = "student";

		String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";

		Connection conn = DriverManager.getConnection(URL, user, pwd);

		PreparedStatement stmt = conn.prepareStatement(sql);

		stmt.setInt(1, actorId);

		// System.out.println(stmt);

		ResultSet actorResult = stmt.executeQuery();

		if (actorResult.next()) {
			actor = new Actor(); // Create the object
			// Here is our mapping of query columns to our object fields:
			actor.setId(actorResult.getInt("id"));
			actor.setFirstName(actorResult.getString("first_name"));
			actor.setLastName(actorResult.getString("last_name"));

			// actor.setFilms(findFilmsByActorId(actorId)); // An Actor has Films
		}

		actorResult.close();
		stmt.close();
		conn.close();
		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<>();

		try {

			String user = "student";
			String pwd = "student";

			Connection conn = DriverManager.getConnection(URL, user, pwd);
			String sql = "SELECT * FROM actor JOIN film_actor ON film_actor.actor_id = actor.id JOIN film ON film.id = film_actor.film_id WHERE film_actor.film_id = ? ";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, filmId);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				Actor actor = new Actor(id, firstName, lastName);

				actors.add(actor);

			}

			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return actors;

	}

	@Override
	public List<Film> findFilmByKeyword(String keyword) {
		List<Film> filmList = new ArrayList<>();
		List<Actor> actors = new ArrayList<>();
		
		try {
			String user = "student";
			String pwd = "student";

			String sql = "SELECT film.*, language.name FROM film JOIN language ON film.language_id = language.id WHERE film.title LIKE ? OR film.description LIKE ?";

			Connection conn = DriverManager.getConnection(URL, user, pwd);

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, '%' + keyword + '%');
			stmt.setString(2, '%' + keyword + '%');
			// System.out.println(stmt);

			ResultSet filmResult = stmt.executeQuery();

			while (filmResult.next()) {
				Film newSearchKey = new Film();
				newSearchKey.setId(filmResult.getInt("film.id"));
				newSearchKey.setTitle(filmResult.getString("film.title"));
				newSearchKey.setReleaseYear(filmResult.getInt("film.release_year"));
				newSearchKey.setRating(filmResult.getString("film.rating"));
				newSearchKey.setDescription(filmResult.getString("film.description"));
				newSearchKey.setLanguage(filmResult.getString("language.name"));
				newSearchKey.setActors(findActorsByFilmId(newSearchKey.getId()));
				filmList.add(newSearchKey);

			}

			filmResult.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return filmList;
		
	}

	public List<Film> findFilmsByActorId(int actorId) {
		List<Film> films = new ArrayList<>();
		String user = "student";
		String pwd = "student";
		try {
			Connection conn = DriverManager.getConnection(URL, user, pwd);
			String sql = "SELECT * "
					+ " FROM film JOIN film_actor ON film.id = film_actor.film_id WHERE film_actor.actor_id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, actorId);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				int releaseYear = rs.getInt("release_year");
				int languageId = rs.getInt("language_id");
				int rentalDuration = rs.getInt("rental_duration");
				double rentalRate = rs.getDouble("rental_rate");
				int length = rs.getInt("length");
				double replacementCost = rs.getDouble("replacement_cost");
				String rating = rs.getString("rating");
				String specialFeatures = rs.getString("special_features");
				Film film = new Film(id, title, description, releaseYear, languageId, rentalDuration, rentalRate,
						length, replacementCost, rating, specialFeatures);
				films.add(film);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;
	}

}
