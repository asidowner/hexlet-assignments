package exercise.controller;

import exercise.dto.MainPage;
import io.javalin.http.Context;

import java.util.Collections;

public class RootController {
    public static void index(Context ctx) {
        var name = ctx.sessionAttribute("name");

        var page = new MainPage(name);

        ctx.render("index.jte", Collections.singletonMap("page", page));
    }
}
