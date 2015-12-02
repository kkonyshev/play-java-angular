package models;

import javax.persistence.*;
import play.db.ebean.*;
import com.avaje.ebean.*;
import java.lang.*;
import java.util.*;

@Entity
public class Seller extends Model {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    public Long id;
    public String name;
    //@ManyToMany(cascade = CascadeType.REMOVE)
    //public List<SellPoint> sellPoints = new ArrayList<SellPoint>();

    public Seller(String name) {
        this.name = name;
    }

    public static Model.Finder<Long,Seller> find = new Model.Finder<Long,Seller>(
            Long.class, Seller.class
    );

}