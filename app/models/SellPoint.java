package models;

import javax.persistence.*;
import play.db.ebean.*;
import com.avaje.ebean.*;
import java.lang.Long;

@Entity
public class SellPoint extends Model {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    public Long id;
    public String name;
    @ManyToOne
    public Seller seller;

    public static Model.Finder<Long, SellPoint> find = new Model.Finder<Long, SellPoint>(
            Long.class, SellPoint.class
    );

    public static SellPoint create(Seller seller, String name) {
        SellPoint sellPoint = new SellPoint();
        sellPoint.seller = Seller.find.ref(seller.id);
        sellPoint.name = name;
        sellPoint.save();
        seller.sellPoints.add(sellPoint);
        seller.save();
        return sellPoint;
    }

}