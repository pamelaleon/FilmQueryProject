package com.skilldistillery.filmquery.database;

import java.sql.SQLException;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public interface DatabaseAccessor {
  public Film findFilmById(int filmId)throws SQLException;
  
  
  public Actor findActorById(int actorId) throws SQLException;
  
  public List<Actor>findActorsByFilmId(int filmId) ;// USE THIS
  
  public List<Film>findFilmByKeyword(String keyword);
  
  public List<Film> findFilmsByActorId(int actorId);  // NOT REQUIRED for the hw!  // did it already but do not need to use it
  
  
}
