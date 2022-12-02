package com.skilldistillery.filmquery.app;

import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {
  
  DatabaseAccessor db = new DatabaseAccessorObject();

  public static void main(String[] args) {
    FilmQueryApp app = new FilmQueryApp();
    app.test();
//    app.launch();
  }

  private void test() {
    Film film = db.findFilmById(1);
    System.out.println(film);
  }

  private void launch() {
    Scanner input = new Scanner(System.in);
    
    startUserInterface(input);
    
    input.close();
  }

  private void startUserInterface(Scanner input) {
	  // (USE MENU LOOP)
	  // allow user to enter film id 
	  //enter number
	  //eneter film number 
	  // or return null or film not found 
	  // ask user to find actor 
    
  }
  
  // some methods that woul have code like 
  // (findFilmsbYActorId(someActorId).isEmpty()) {
//        }
// else {
//	sysout(" no films for that unemployed actor")
//}
//	{
}
