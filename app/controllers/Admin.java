package controllers;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

public class Admin extends Controller {

    //@Security.Authenticated(Secured.class)
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
                routes.Admin.login()
        );
    }

    public static Result authenticate() {
        Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
        if (loginForm.hasErrors()) {
            return badRequest(views.html.admin.login.render(loginForm));
        } else {
            session().clear();
            session("email", loginForm.get().email);
            return redirect(
                    routes.Admin.index()
            );
        }
    }


    public static Object authenticateUser(String email, String password) {
        System.out.println("email: " + email);
        System.out.println("password: " + password);
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
            if (Admin.authenticateUser(email, password) == null) {
                return "Invalid user or password";
            }
            return null;
        }
    }
}
