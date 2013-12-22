package controllers;


import play.*;
import play.mvc.*;
import play.data.*;
import models.*;
import views.html.*;

public class Inscription extends Controller {

    public static Result index() {
  
		return ok(inscription.render(
        		"inscription",
        		Utilisateur.findall()
        		));
    } 

}
