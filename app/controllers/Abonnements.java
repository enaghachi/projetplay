package controllers;

import java.util.Date;
import java.util.List;

import models.Abonnement;
import models.Tweet;
import models.Utilisateur;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class Abonnements extends Controller {
	
	 public static Result add() {
		 Boolean isEditable = true;
		 
		 Abonnement abonnement= new Abonnement();
		 
         DynamicForm requestfdata = Form.form().bindFromRequest();
         
         abonnement.username_ajout = requestfdata.get("username_ajout");
         
         abonnement.date_ajout = new Date();
         String sessionemail = session().get("email");
         Utilisateur userconnec = Utilisateur.findByEmail(sessionemail) ;
         abonnement.user = userconnec;
         abonnement.save();
         List<Abonnement> listAbon = Abonnement.findByProprioUsername(session("email"));
 		for(int i=0; i< listAbon.size();i++){
 			if(listAbon.get(i).username_ajout.equals(requestfdata.get("username_ajout"))){
 				isEditable = false;
 			}
 		}
     
	     return  ok(perso.render("Profil de :",
	     		Utilisateur.findByusername(requestfdata.get("email_ajout")),
	    		Tweet.findByusername(requestfdata.get("username_ajout")),
	    		userconnec,
	    		isEditable
	    		));   
	    }
	 
	 public static Result abonnement(String username){
		 List<Abonnement> abonnements = Abonnement.findByProprioUsername(username);
		 return ok(abonnement.render("Liste d'abonnements :",
				 abonnements,
				 null,
				 Utilisateur.findByEmail(session("email"))));	 
	 }
	 
	 public static Result abonne(String username){
		 List<Abonnement> abonnes = Abonnement.findByAjoutUsername(username);
		 return ok(abonnement.render("Liste des Abonnés :",
				 null,
				 abonnes,
				 Utilisateur.findByEmail(session("email"))));	 
	 }

}
