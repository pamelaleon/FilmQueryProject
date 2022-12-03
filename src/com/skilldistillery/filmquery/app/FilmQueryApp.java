package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) throws SQLException {
		FilmQueryApp app = new FilmQueryApp();
		// app.test();
		app.launch();
	}

//	private void test() throws SQLException {
//		Film film = db.findFilmById(1);
//		System.out.println(film);
//	}

	private void launch() throws SQLException {
		Scanner input = new Scanner(System.in);

		// only scanner here

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) {
		boolean beginQuery = true;

		while (beginQuery) {
			System.out.println("========================================");
			System.out.println("Welcome to the search DataBase Query Menu");
			System.out.println("1. Look up film by its id");
			System.out.println("2. Look up film by a search keyword");
			System.out.println("3. Exit the application");
			System.out.println("========================================");

			int userInput = input.nextInt();
			input.nextLine();
			switch (userInput) {

			case 1:
				System.out.println("Please enter film number id!");
				userInput = input.nextInt();
				input.nextLine();
				try {
					if (db.findFilmById(userInput) != null) {
						System.out.println(db.findFilmById(userInput));
					}
					Film film = db.findFilmById(userInput);
					if (film == null) {
						System.out.println("Film has not been found please try again!");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;

			case 2:
				System.out.println("Please enter a film keyword to search in film database!");
				String keyword = input.next();
				input.nextLine();
				List<Film> filmKeyword = db.findFilmByKeyword(keyword);

				if (filmKeyword.size() == 0) {
					System.out.println("No matches were found with your film search keyword...");
				} {
				for (Film film2 : filmKeyword) {
					displayFilms(film2);
				}
			}
				break;

			case 3:
				System.out.println("Thank you for using the program!");
				beginQuery = false;
				break;

			default:
				System.out.println("Please input valid menu option number!");
				break;

			}
		}
	}

	private void displayFilms(Film film) {
		System.out.println("=======++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++=======");
		// System.out.println(film.toString() + film.getActors());
		System.out.println("Title: " + film.getTitle());
		System.out.println("Year Released: " + film.getReleaseYear());
		System.out.println("Rating: " + film.getRating());
		System.out.println("Description: " + film.getDescription());
		System.out.println("Language: " + film.getLanguage());
		System.out.println("Cast: " + film.getActors());
		System.out.println("=======++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++=======");

	}
}
