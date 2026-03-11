package app.controllers;

import app.entities.Post;
import app.persistence.ConnectionPool;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class PostController {

    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.get("/posts/{id}", ctx -> findPost(ctx, connectionPool));
    }

    public static void findPost(Context ctx, ConnectionPool connectionPool) {
        int id = Integer.parseInt(ctx.pathParam("id"));

        // Testkode start - skal udskiftes med PostMapper kode, der kalder DB
        List<Post> posts = List.of(
                new Post(1, "Min første post", "cat.jpg"),
                new Post(2, "Hej fra Javalin", "coffee.jpg"),
                new Post(3, "Syntax er svær", "code.jpg")
        );
        Post foundPost = null;

        for (Post post : posts) {
            if (post.getPost_id() == id) {
                foundPost = post;
                break;
            }
        } // test kode slut

        ctx.attribute("post", foundPost);
        ctx.render("post-details.html");
    }

}
