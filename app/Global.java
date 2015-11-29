import play.*;

public class Global extends GlobalSettings {

    @Override
    public void onStart(Application app) {
        Logger.info("Application has started");
        /*
        User admin = User.find.where().eq("email", "hs@ngs.ru").findUnique();
        if (admin==null) {
            Logger.info("Creating default admin");
            new User("hs@ngs.ru", "Konstanint K", "123456").save();
            System.out.println(User.find.where().eq("email", "hs@ngs.ru").findUnique());
        }
        */
    }

    @Override
    public void onStop(Application app) {
        Logger.info("Application shutdown...");
    }

}