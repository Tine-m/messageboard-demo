package app.controllers;

import app.entities.User;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.UserMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class UserController {

    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.get("/opretbruger", ctx -> ctx.render("registrerbruger.html"));
        app.post("/opretbruger", ctx -> registrerBruger(ctx, connectionPool));
        app.get("/login", ctx -> ctx.render("login.html"));
        app.post("/login", ctx -> login(ctx, connectionPool));
        app.get("/logout", ctx -> logout(ctx));
    }

    public static void registrerBruger(Context ctx, ConnectionPool connectionPool) {
        String username = ctx.formParam("username");
        String password = ctx.formParam("password");

        String error = validateUser(username, password);
        if (!error.isEmpty())
        {
            ctx.attribute("msg", error);
            ctx.render("registrerbruger.html");
        } else{

            try {
                UserMapper.createuser(username, password, connectionPool);
                ctx.render("index.html");
                // Better solution:
                //ctx.redirect("/");
            } catch (DatabaseException e) {
                ctx.attribute("msg", e.getMessage());
                ctx.render("registrerbruger.html");
            }
        }
    }

    public static void login(Context ctx, ConnectionPool connectionPool) {
        String username = ctx.formParam("username");
        String password = ctx.formParam("password");
        try {
            User user = UserMapper.login(username, password, connectionPool);
            ctx.sessionAttribute("currentUser", user);
            ctx.redirect("/posts");
        } catch (DatabaseException e) {
            ctx.attribute("msg", e.getMessage());
            ctx.render("login.html");
        }
    }

    public static void logout(Context ctx) {
        ctx.req().getSession().invalidate();
        ctx.redirect("/");
    }

    public static String validateUser(String username, String password) {
        if (username.isEmpty()) {
            return "Brugernavn skal udfyldes";
        }
        else if (password.isEmpty()) {
            return "Password skal udfyldes";
        }
        else if (username.length()<3) {
            return "Brugernavn skal være mindst 3 tegn";
        }
        else return "";
    }
}
