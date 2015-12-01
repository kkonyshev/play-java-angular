package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.UserAccount;
import play.Logger;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import utils.ReqError;

import java.util.List;

public class UserAccountController extends Controller {

    public static Result getUserList() {
        List<UserAccount> users = UserAccount.find.all();
        return ok(Json.toJson(users));
    }

    public static Result getUser(String email) {
        Logger.debug("looking userAccount by email: " + email);
        UserAccount user = UserAccount.find.byId(email);
        if (user!=null) {
            return ok(Json.toJson(user));
        } else {
            return notFound();
        }
    }

    public static Result createUser() {
        JsonNode r = request().body().asJson();
        Logger.debug("r: " + r);
        Logger.debug("req: " + request().body());
        UserAccount newUser = Json.fromJson(r, UserAccount.class);
        if (UserAccount.find.byId(newUser.email)==null) {
            newUser.save();
            return created(Json.toJson(newUser));
        } else {
            return badRequest(Json.toJson(new ReqError("user already exists")));
        }
    }
}
