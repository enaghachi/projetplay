package controllers;

import models.Tweet;
import models.Utilisateur;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class Sujets extends Controller{

	public static Result affSujet(String s){
		return ok(sujetlist.render("page sujet",
				s,
				Utilisateur.findByEmail(session("email")),
				Tweet.findBySujet(s)));
	}
}
