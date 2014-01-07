package models;


import javax.persistence.*;

import play.db.ebean.*;
import static play.data.validation.Constraints.*;


import java.io.File;

@Entity
public class Image extends Model {



public static enum ImageType {
    PNG("image/png"),
    JPEG("image/jpeg");

    public String contentType;

    private ImageType(String contentType) {
      this.contentType = contentType;
    }

    public static ImageType get(String contentType) {
      for (ImageType type: values()) {
        if (type.contentType.equals(contentType)) {
          return type;
        }
      }
      return null;
    }
  }

  @Id @GeneratedValue
  //@JoinColumn(name="image_username", referencedColumnName="utilisateur_username")
  public int idimage;

  public Image(int idimage){
	  this.idimage = idimage;
  }

  //memoization of pic
  @Transient
  public File pic;

  @Column(length= 2000)
  public String filePath;
  
  
  public static Finder<Integer, Image> find = new Finder<Integer, Image>(Integer.class, Image.class);
  
  public static Image findByID(int idimage) {
      return find.where().eq("idimage", idimage).findUnique();
  }
  
  public Image() {}

  public File pic() {
    if (filePath != null) {
      if (pic == null || !pic.getPath().equals(filePath)) {
        pic = new File(filePath);
      }
    }
    return pic;
  }
}