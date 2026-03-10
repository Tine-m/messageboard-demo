package app.controllers;

import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.UserMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class UserController {

    public void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.get("/new", ctx -> ctx.render("registrerbruger.html"));
        app.post("/new", ctx -> registrerBruger(ctx, connectionPool));
    }

    public void registrerBruger(Context ctx, ConnectionPool connectionPool) {
         //hent data fra html form
        String username = ctx.formParam("username");
        String password = ctx.formParam("password");
        try {
            UserMapper.createuser(username, password, connectionPool);
           // ctx.render("index.html");
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }
        // gemme bruger i db
        // tilbage til forside

    }
}
