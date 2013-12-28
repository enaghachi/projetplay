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
	//lister les tweet avec ce sujet
	public static List<Tweet> findBySujet(String sujet) {
		return find.where().eq("sujet",sujet).findList();
	}
	public static Tweet findById(long id){
		return find.byId(id);
	}
	
	public static void supprimer(Long id){
		Tweet tweet =Ebean.find(Tweet.class).select("*").where().idEq(id).findUnique();
		Ebean.delete(tweet);
	 }

}



