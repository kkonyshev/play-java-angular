package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.SellPoint;
import models.Seller;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

public class SellPointController extends Controller {

    public static Result getSellPointList() {
        List<SellPoint> sellPointList = SellPoint.find.all();

        return ok(Json.toJson(sellPointList));
    }

    public static Result getSellPoint(Long id) {
        SellPoint sellPoint = SellPoint.find.byId(id);
        if (sellPoint!=null) {
            return ok(Json.toJson(sellPoint));
        } else {
            return notFound();
        }
    }

    public static Result getSellPointBySeller(Long id) {
        Logger.debug("get seller by id: " + id);
        List<SellPoint> sellPointList = SellPoint.find.where().eq("seller.id",id).findList();
        //
        return ok(Json.toJson(sellPointList));
    }

    public static Result createSellPoint() {
        JsonNode r = request().body().asJson();
        Logger.debug("r: " + r);
        Logger.debug("req: " + request().body());
        SellPoint sellPoinyTemplate = Json.fromJson(r, SellPoint.class);
        SellPoint created = SellPoint.create(sellPoinyTemplate.seller, sellPoinyTemplate.name, sellPoinyTemplate.lat, sellPoinyTemplate.lon);
        //return ok(Json.toJson(created));
        return ok();
    }
}
