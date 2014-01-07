package controllers;

import models.*;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.*;
import play.*;
import play.mvc.*;
import static play.mvc.Results.*;
import play.data.*;
import play.libs.*;
import play.data.validation.*;
import static play.data.validation.Constraints.*;
import javax.validation.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
@Security.Authenticated(Secured.class)
public class Pageperso extends Controller{
	
	public static Result index(String username){
		return ok(index.render("Accueil",
            		Utilisateur.findByusername(username),
            		Tweet.ajoutTweetAmis(username),
            		Commentaire.find.findList(),
            		Abonnement.find.findList()
            		));
		
	}
	
	
	
	public static Result getImage(int idimage) {
		Image image = Image.findByID(idimage);
	    try {
	      return ok(new FileInputStream(image.pic()));
	    } catch (FileNotFoundException f) {
	      return badRequest("Bad File...");
	    }
	  }
	
	public static Form<Image> imageForm = Form.form(Image.class);
	
	public static Result modification(String username){
		return ok(persoMofi.render("Vous pouvez modifier vos informations",
									Utilisateur.findByusername(username),imageForm));
										
	}
	




	



	public static Result receiveImage(String username) {
	    Utilisateur user = Utilisateur.findByusername(username);
	   

	    //  GET SOME DATA FROM THEN URL FORM ENCODED DATA
	    Form<Image> filledForm = imageForm.bindFromRequest();
	    if(filledForm.hasErrors()) {
	      return badRequest(
	          filledForm.errors().toString()
	      );
	    } else {
	      Http.MultipartFormData body;
	      //  RECOVER THE WHOLE BODY AS MULTIPART
	      body = request().body().asMultipartFormData();

	      //  THE PLAY2 API PROVIDES A WAY TO GET THE FILE
	      //    +> SO EASILY !!!
	      Http.MultipartFormData.FilePart pic = body.getFile("pic");
	      //  CHECK THE IMAGE TYPE IS VALID -- part of the enum
//	      if(Image.ImageType.get(pic.getContentType()) == null) {
//	        return badRequest(persoMofi.render("Pas d'image",
//					Utilisateur.findByusername(username),imageForm));
//	       
//	      }

	      Image image = filledForm.get();
	      

	      image.pic = pic.getFile();
	      image.filePath = pic.getFile().getPath();
	      image.save();
	      user.images=image;
	      user.save();

	      return ok(persoMofi.render("Vous pouvez modifier vos informations",
					Utilisateur.findByusername(username),imageForm));
	    }
	  }

}
