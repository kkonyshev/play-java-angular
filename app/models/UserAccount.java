package models;

import javax.persistence.*;
import play.db.ebean.*;

@Entity
public class UserAccount extends Model {

    @Id
    public String email;
    public String name;
    public String password;

    public UserAccount(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public static Model.Finder<String,UserAccount> find = new Model.Finder<String,UserAccount>(
            String.class, UserAccount.class
    );

    public static UserAccount authenticate(String email, String password) {
        UserAccount u = find.where().eq("email", email).findUnique();
        System.out.println("by email: " + u);
        return find.where().eq("email", email)
                .eq("password", password).findUnique();
    }

    public String toString() {
        return email + ";" + password + ";" + name;
    }
}