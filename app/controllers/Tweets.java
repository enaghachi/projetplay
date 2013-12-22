package controllers;


import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import models.Tweet;
import models.Utilisateur;
import play.mvc.*;
import play.data.DynamicForm;
import play.data.Form;
import views.html.*;

public class Tweets extends Controller {
	
	 public static Result add() {
		 
		 DynamicForm requestfdata = Form.form().bindFromRequest();
         Tweet tweet = new Tweet();
         String username = requestfdata.get("username");
         System.out.println(username);
         String contenu = requestfdata.get("contenu");
         if(!contenu.contains("@")){
        	 if(!contenu.contains("#") ){
        		 tweet.label=contenu;
        		 tweet.sujet="";
        	 }
        	 else{
        		 Pattern p1 = Pattern.compile("(.*) #(.*)");
        			Matcher m1 = p1.matcher(contenu);
        				while(m1.find()){
        								tweet.label=m1.group(1);
        								tweet.sujet=m1.group(2);
        						}		 
        	 	}
         }else{
        	 if(!contenu.contains("#") ){
		        	 Pattern p2 = Pattern.compile("(.*) @(.*)");
		        	 Matcher m2 = p2.matcher(contenu);
		        	 while(m2.find()){
		        		 tweet.label=m2.group(1);
		        		 tweet.sujet="";
		    			 tweet.Taguser=m2.group(2);
		  			}
        	 }
        	 else{
        		 Pattern p3 = Pattern.compile("(.*) @(.*) #(.*)");
        		 Matcher m3 = p3.matcher(contenu);
        		 while(m3.find()){
        			 tweet.label=m3.group(1);
        			 tweet.sujet=m3.group(3);
        			 tweet.Taguser=m3.group(2);
        	 }
         }
        }	
         Utilisateur usernew = Utilisateur.findByusername(username);
         tweet.user = usernew;
         tweet.creationDate = new Date();
		 tweet.save();
		 return redirect(routes.Pageperso.index(username));
     }

}
