package exercise.controller;

import exercise.model.User;
import exercise.repository.UserRepository;
import exercise.util.Security;
import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class UserController {

    public static void build(Context ctx) {
        ctx.render("users/build.jte");
    }

    public static void create(Context ctx) {
        var firstName = ctx.formParamAsClass("firstName", String.class)
                .check(Objects::nonNull, "First Name is required.")
                .get()
                .trim()
                .toLowerCase();
        var lastName = ctx.formParamAsClass("lastName", String.class)
                .check(Objects::nonNull, "Last Name is required.")
                .get()
                .trim()
                .toLowerCase();
        var email = ctx.formParamAsClass("email", String.class)
                .check(Objects::nonNull, "Email is required.")
                .get()
                .trim()
                .toLowerCase();
        var password = ctx.formParamAsClass("password", String.class)
                .check(Objects::nonNull, "Password is required.")
                .get();

        firstName = StringUtils.capitalize(firstName);
        lastName = StringUtils.capitalize(lastName);
        password = Security.encrypt(password);

        User user = new User(firstName, lastName, email, password);

        UserRepository.save(user);

        ctx.redirect("/users");
    }
}
