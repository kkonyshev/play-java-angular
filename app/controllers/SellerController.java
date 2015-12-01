package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Seller;
import models.UserAccount;
import play.Logger;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

import java.util.List;

public class SellerController extends Controller {

    public static Result getSellerList() {
        List<Seller> sellerList = Seller.find.all();

        return ok(Json.toJson(sellerList));
    }

    public static Result getSeller(Long id) {
        Seller seller = Seller.find.byId(id);
        if (seller!=null) {
            return ok(Json.toJson(seller));
        } else {
            return notFound();
        }
    }

    public static Result createSeller() {
        JsonNode r = request().body().asJson();
        Logger.debug("r: " + r);
        Logger.debug("req: " + request().body());
        Seller newSeller = Json.fromJson(r, Seller.class);
        newSeller.save();

        return ok(Json.toJson(newSeller));
    }
}
