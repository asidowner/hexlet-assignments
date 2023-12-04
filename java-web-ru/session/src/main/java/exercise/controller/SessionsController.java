package exercise.controller;

import java.util.Collections;

import exercise.dto.LoginPage;
import exercise.repository.UsersRepository;

import static exercise.util.Security.encrypt;

import exercise.util.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.validation.ValidationException;

public class SessionsController {
    private static final String LOGIN_FAILED_ERROR_MESSAGE = "Wrong username or password";

    // BEGIN
    public static void build(Context ctx) {
        if (ctx.sessionAttribute("name") != null) {
            ctx.redirect(NamedRoutes.rootPath());
        }

        var page = new LoginPage();
        ctx.render("build.jte", Collections.singletonMap("page", page));
    }

    public static void create(Context ctx) {
        var name = ctx.formParam("name");
        try {
            ctx.formParamAsClass("password", String.class)
                    .check(pass -> UsersRepository.findByName(name).getPassword().equals(encrypt(pass)),
                            LOGIN_FAILED_ERROR_MESSAGE).get();

            ctx.sessionAttribute("name", name);
            ctx.redirect(NamedRoutes.rootPath());
        } catch (ValidationException e) {
            var page = new LoginPage(name, e.getErrors());
            ctx.render("build.jte", Collections.singletonMap("page", page));
        }
    }

    public static void delete(Context ctx) {
        if (ctx.sessionAttribute("name") != null) {
            ctx.sessionAttribute("name", null);
        }
        ctx.redirect(NamedRoutes.rootPath());
    }
    // END
}
