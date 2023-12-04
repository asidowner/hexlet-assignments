package exercise.controller;

import io.javalin.validation.ValidationException;
import org.apache.commons.lang3.StringUtils;
import exercise.util.Security;
import exercise.model.User;
import exercise.util.NamedRoutes;

import java.util.Collections;
import java.util.Objects;

import exercise.repository.UserRepository;
import io.javalin.http.NotFoundResponse;
import io.javalin.http.Context;


public class UsersController {

    private static final String TOKEN_NAME = "token";

    public static void build(Context ctx) throws Exception {
        ctx.render("users/build.jte");
    }

    // BEGIN
    public static void create(Context ctx) {
        var firstName = ctx.formParamAsClass("firstName", String.class)
                .check(Objects::nonNull, "")
                .get();
        var lastName = ctx.formParamAsClass("lastName", String.class)
                .check(Objects::nonNull, "")
                .get();
        var email = ctx.formParamAsClass("email", String.class)
                .check(
                        item -> UserRepository.getEntities()
                                .stream()
                                .filter(user -> user.getEmail().equals(item))
                                .findAny()
                                .isEmpty(), "Пользователь уже есть."
                ).get();
        var password = ctx.formParamAsClass("password", String.class)
                .check(Objects::nonNull, "")
                .getOrThrow(ValidationException::new);

        var token = Security.generateToken();
        var encryptedPassword = Security.encrypt(password);

        var user = new User(firstName, lastName, email, encryptedPassword, token);

        UserRepository.save(user);

        ctx.cookie(TOKEN_NAME, token);

        ctx.redirect(NamedRoutes.userPath(user.getId()));
    }

    public static void show(Context ctx) {
        var token = ctx.cookie(TOKEN_NAME);
        var id = ctx.pathParamAsClass("id", Long.class)
                .check(item -> UserRepository.find(item).isPresent(), "Not found.")
                .getOrThrow(stringMap -> new NotFoundResponse());

        var user = UserRepository.find(id).filter(u -> u.getToken().equals(token));

        if (user.isEmpty()) {
            throw new NotFoundResponse();
        }

        ctx.render("users/show.jte", Collections.singletonMap("user", user.get()));
    }
    // END
}
