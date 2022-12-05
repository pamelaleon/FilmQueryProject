# FilmQueryProject
A Java Base Connectivity application that connects with a SQL database to retrieve
information from the database tables containing films, actors, etc. The user is then
able to find specific id, film titles or descriptions to complete their search.

# Description
This command-line application retrieves and displays data with correct JDBC
encapsulation code. The user is prompted with a display menu asking if they would like
to:

1. Look up a film by its id
2. Look up a film by a search keyword
3. Exit the application

In order to find the film id, the program uses a SQL prepared statement and assigns the the data to their correct variables to
execute the Query. Using constructors fields to set the sql data into Java to display information.

The following are the sql String used:
For 1."SELECT film.id, film.title, film.release_year, film.rating, film.description, language.name FROM film JOIN language ON
film.language_id = language.id WHERE film.id = ?";

For 2."SELECT film.*, language.name FROM film JOIN language ON film.language_id = language.id WHERE film.title LIKE ? OR
film.description LIKE ?";

# Technologies Used
- MySQL
- Eclipse
- MAMP
- Atom
- Java Database Connectivity

# Lessons Learned
The problem that I struggled with the most was getting that list of actors that played in the film to display. At first I created a
separate method that displays title, year released, rating, description, language and cast. I called the toString that included the
Array List of actors. When the program compiled I noticed that it did return an actor but it was null. I over complicated the
situation thinking I may need to create a whole new method which involved a new prepared statement that would display the cast of
actors that preformed in that film. Instead I looked at the methods that I already have created and used findActorsByFilmId method.
My thinking was that if I am able to get the film id then in return it should display all the actors in the film. I created a new
Film instance and set Actors to findActorByFieldId which then calls the created instance to get id. When displayed, the cast of the
film was displayed.
