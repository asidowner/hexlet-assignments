package exercise;

import io.javalin.Javalin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import exercise.model.User;
import exercise.dto.users.UserPage;
import exercise.dto.users.UsersPage;

public final class App {

    // Каждый пользователь представлен объектом класса User
    private static final List<User> USERS = Data.getUsers();
    private static final String USER_NOT_FOUND_ERROR = "User with id=%s not founded.";

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        // BEGIN
        app.get("/users", App::getUsersPage);
        app.get("/users/{id}", App::getUserPage);
        // END

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        return app;
    }

    public static void getUsersPage(Context ctx) {
        String header = "Пользователи";
        List<User> sortedUsers = new ArrayList<>(App.USERS);
        Comparator<User> comparator = Comparator.comparingLong(User::getId);
        sortedUsers.sort(comparator);
        UsersPage page = new UsersPage(sortedUsers, header);
        ctx.render("users/index.jte", Collections.singletonMap("page", page));
    }

    public static void getUserPage(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).getOrThrow(map -> new BadRequestResponse());

        User user = App.USERS.stream()
                .filter(item -> id.equals(item.getId()))
                .findFirst()
                .orElseThrow(() -> new NotFoundResponse(String.format(App.USER_NOT_FOUND_ERROR, id)));

        UserPage page = new UserPage(user);

        ctx.render("users/show.jte", Collections.singletonMap("page", page));
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
