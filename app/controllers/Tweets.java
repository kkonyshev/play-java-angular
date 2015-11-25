package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import play.libs.F.Promise;
import play.libs.Json;
import play.libs.ws.WS;
import play.libs.ws.WSResponse;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.data.Form;
import play.mvc.*;

public class Tweets extends Controller {

    @Security.Authenticated(Secured.class)
    public static Result index() {
        return ok(views.html.index.render("TweetMap"));
    }

    public static Result odin() {
        return ok(views.html.odin.render("odin title"));
    }


    public static Result login() {
        return ok(
                views.html.login.render(Form.form(Login.class))
        );
    }

    public static Result logout() {
        session().clear();
        flash("success", "You've been logged out");
        return redirect(
                routes.Tweets.login()
        );
    }

    public static Result authenticate() {
        Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
        if (loginForm.hasErrors()) {
            return badRequest(views.html.login.render(loginForm));
        } else {
            session().clear();
            session("email", loginForm.get().email);
            return redirect(
                    routes.Tweets.index()
            );
        }
    }


    public static Object authenticate(String email, String password) {
        if (email==null || email.length()==0 || password==null || password.length()==0) {
            return null;
        } else {
            return new Object();
        }
    }

    public static class Login {
        public String email;
        public String password;

        public String validate() {
            if (Tweets.authenticate(email, password) == null) {
                return "Invalid user or password";
            }
            return null;
        }
    }
}
