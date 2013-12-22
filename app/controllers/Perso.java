package controllers;

import java.util.List;

import models.*;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class Perso extends Controller{
	
	public static Result index(String username){
		Boolean isEditable = true;
		Utilisateur userconnect= Utilisateur.findByEmail(session("email"));
		String usernameconnect = userconnect.username;
		List<Abonnement> listAbon = Abonnement.findByProprioUsername(usernameconnect);
		for(int i=0; i< listAbon.size();i++){
			
			if(listAbon.get(i).username_ajout.equals(username)){
				
				isEditable = false;
				System.out.println(isEditable);
			}
		}
		System.out.println(isEditable);
	return ok(perso.render("Profil de :",
    		Utilisateur.find.byId(username),
    		Tweet.findByusername(username),
    		Utilisateur.findByEmail(session("email")),
    		isEditable
    		));
}


}
