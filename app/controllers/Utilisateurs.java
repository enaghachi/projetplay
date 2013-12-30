package controllers;

import java.util.Date;

import models.Utilisateur;
import play.mvc.*;
import play.data.DynamicForm;
import play.data.Form;
import views.html.*;

public class Utilisateurs extends Controller{
	
	public static Result add() {
		
		 DynamicForm requestfdata = Form.form().bindFromRequest();
		 Utilisateur user = new Utilisateur();
		 String passwordhashe = MD5Password.getEncodedPassword(requestfdata.get("password"));
		 user.password = passwordhashe;
		 user.email = requestfdata.get("email");
		 user.sexe = requestfdata.get("sexe");
		 user.username = requestfdata.get("username"); 		 
		 user.date_inscription = new Date();
     	 user.save();//permet d'ajouter le Usermark en base 
        	return redirect(routes.Authentication.login());//fait une redirection vers routes.Application.index()

}
	public static Result modifier(){
		DynamicForm requestfdata = Form.form().bindFromRequest();
		 Utilisateur user = Utilisateur.findByEmail(session("email"));
		

		 if(requestfdata.get("password")!=""){
			 String passwordhashe = MD5Password.getEncodedPassword(requestfdata.get("password"));
		 	user.password = passwordhashe;
		 	user.save();
		 }else{
			 user.password=user.password;
			 user.save();
		 }
		 if(requestfdata.get("description")!=""){
			 user.description=requestfdata.get("description");
			 user.save();
		 }else {
			 user.description=user.description;
			 user.save();
		 }
		 
		 	return redirect(routes.Pageperso.index(requestfdata.get("username")));
	}

}
