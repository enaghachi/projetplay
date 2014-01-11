package models;


import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.avaje.ebean.Ebean;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
public class Tweet extends Model {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long id;
	public int num;
	public Date creationDate;
	public String label;
	public String sujet;
	public String Taguser;
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name="Tweet_userID")
	public Utilisateur user;
	
	@OneToMany(mappedBy="tweet")  //un User pour plusieurs tweet, "mappedBy" qui référence le nom de l'attribut User dans la classe Event
	private List<Commentaire> commentList;


	public Tweet() {
		super();
	}

	public Tweet(Long id, int num, String label) {
		super();
		this.id = id;
		this.num = num;
		this.label = label;
	}

	public static Finder<Long, Tweet> find = 
            new Finder<Long, Tweet>(Long.class, Tweet.class);
	
	public static List<Tweet> findall(){
		return find.all();
	}
	public static List<Tweet> findByEmail(String email) {
       return find.where().eq("Tweet_userID", email).findList();
	}
	public static List<Tweet> findByusername(String username) {
        return find.where().eq("Tweet_userID", username).findList();
    }
	
	public static List<Tweet> ajoutTweetAmis(String Username){


         List<Abonnement> listdeabonnement =Abonnement.findByProprioUsername(Username);

         List<Tweet> listTweetuserconnec = Tweet.findByusername(Username);

         List<Tweet> listTweetabonnuser;

         for(int i=0; i< listdeabonnement.size();i++){

        	 listTweetabonnuser = Tweet.findByusername(listdeabonnement.get(i).username_ajout);

        	 for(int j=0;j<listTweetabonnuser.size();j++){
			 
        		 if(listTweetabonnuser.get(j).creationDate.compareTo(listdeabonnement.get(i).date_ajout)>0)
				 
        			 	listTweetuserconnec.add(listTweetabonnuser.get(j));

  			}

         }

         return listTweetuserconnec;

	}

public static List<Tweet> findNext(int from, int nb)
{
        return find.setFirstRow(from).setMaxRows(nb).findList();
}
	
	//lister les tweet avec ce sujet
	public static List<Tweet> findBySujet(String sujet) {
		return find.where().eq("sujet",sujet).findList();
	}
	public static Tweet findById(long id){
		return find.byId(id);
	}
	
	public static void supprimer(Long id){
		Tweet tweet =Ebean.find(Tweet.class).select("*").where().idEq(id).findUnique();
		
		List commentaires =Ebean.find(Commentaire.class).select("*").where().eq("tweet", tweet).findList();
		Ebean.delete(commentaires);
		Ebean.delete(tweet);
	 }

}



