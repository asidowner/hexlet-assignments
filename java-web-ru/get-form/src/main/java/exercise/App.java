package exercise;

import io.javalin.Javalin;
import java.util.List;
import exercise.model.User;
import exercise.dto.users.UsersPage;
import java.util.Collections;

import io.javalin.http.Context;
import org.apache.commons.lang3.StringUtils;

public final class App {

    // Каждый пользователь представлен объектом класса User
    private static final List<User> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        // BEGIN
        app.get("/users", App::getUsersIndex);
        // END

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        return app;
    }

    public static void getUsersIndex(Context ctx) {
        var term = ctx.queryParam("term");

        List<User> items;

        if (term != null) {
            items = App.USERS.stream()
                    .filter(user -> StringUtils.containsAnyIgnoreCase(user.getFirstName(), term))
                    .toList();
        } else {
            items = App.USERS;
        }

        UsersPage page = new UsersPage(items, term);

        ctx.render("users/index.jte", Collections.singletonMap("page", page));
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
