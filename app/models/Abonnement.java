package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import play.data.format.Formats;
import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

import javax.persistence.*;


@Entity 
public class Abonnement extends Model {
	
	@Id
    public Long id;
    
    public String username_ajout;
    @Formats.DateTime(pattern="MM/dd/yy")
	public Date date_ajout ;
    
    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
    @JoinColumn(name="Proprio_username")
	public Utilisateur user;
	   
    
    public Abonnement() {
	}

	
	public static Finder<Long, Abonnement> find = new Finder<Long, Abonnement>(Long.class, Abonnement.class);
	
	public static List<Abonnement> findall(){
		return find.all();
	}
	
	public static Abonnement findById(Long abonn_id) {
        return find.where().eq("id", abonn_id).findUnique();
    }
	public static List<Abonnement> findByProprioUsername(String  Username){
		 return find.where().eq("Proprio_username", Username).findList();
	}
	public static List<Abonnement> findByAjoutUsername(String  Username){
		 return find.where().eq("username_ajout", Username).findList();
	}
	
}
