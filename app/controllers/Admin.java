package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.UserAccount;
import play.Application;
import play.Logger;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

import java.util.List;

public class Admin extends Controller {

    public static Result getUsers() {
        List<UserAccount> users = UserAccount.find.all();

        return ok(Json.toJson(users));
    }

    public static Result getUser(String email) {
        UserAccount user = UserAccount.find.byId(email);
        if (user!=null) {
            return ok(Json.toJson(user));
        } else {
            return notFound();
        }
    }

    public static class ReqError {
        public String message;
        public ReqError(String message) {
            this.message = message;
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

    @Security.Authenticated(Secured.class)
    public static Result index() {
        return ok(
                views.html.admin.index.render("Admin dashboard")
        );
    }

    public static Result login() {
        return ok(
                views.html.admin.login.render(Form.form(Login.class))
        );
    }

    public static Result logout() {
        session().clear();
        flash("success", "You've been logged out");
        return redirect(
                controllers.routes.Admin.login()
        );
    }

    public static Result authenticate() {
        Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
        Logger.info(loginForm.toString());
        if (loginForm.hasErrors()) {
            return badRequest(views.html.admin.login.render(loginForm));
        } else {
            session().clear();
            session("email", loginForm.get().email);
            return redirect(
                    controllers.routes.Admin.index()
            );
        }
    }

    public static class Login {
        public String email;
        public String password;
        public String validate() {
            if (UserAccount.authenticate(email, password) == null) {
                return "Invalid user or password";
            }
            return null;
        }
    }
}
