package controllers;

import models.*;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.*;
@Security.Authenticated(Secured.class)
public class Pageperso extends Controller{
	
	public static Result index(String username){
		return ok(index.render("Accueil",
            		Utilisateur.findByusername(username),
            		Tweet.find.orderBy("label").findList(),
            		Commentaire.find.findList()
            		));
		
	}

}
