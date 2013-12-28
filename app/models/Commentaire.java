package models;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import play.db.ebean.Model.Finder;
import com.avaje.ebean.Ebean;

import play.db.ebean.Model;


@Entity
public class Commentaire extends Model {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long id;
	public Date creationDate;
	public String label;
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name="Comm_username")
	public Utilisateur user;
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name="Comm_tweetID")
	public Tweet tweet;
	
	
	public Commentaire(){
		super();
	}
	public Commentaire(Long id,String label,Date creationDate,Utilisateur user,Tweet tweet){
		super();
		this.id=id;
		this.label=label;
		this.creationDate=creationDate;
		this.user=user;
		this.tweet=tweet;
	}
	
	public static Finder<Long, Commentaire> find = 
            new Finder<Long, Commentaire>(Long.class, Commentaire.class);
	
	public static List<Commentaire> findByTweet(Long id){
		return find.where().eq("Comm_tweetID",id).findList();
	}
	

}
