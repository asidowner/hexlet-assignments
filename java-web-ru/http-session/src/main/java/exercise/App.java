package exercise;

import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;
import java.util.Map;

public final class App {

    private static final List<Map<String, String>> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        // BEGIN
        app.get("/users", App::getUserListWithPaging);
        // END

        return app;

    }

    public static Context getUserListWithPaging(Context context) {
        Integer page = context.queryParamAsClass("page", Integer.class).getOrDefault(1);
        Integer per = context.queryParamAsClass("per", Integer.class).getOrDefault(5);

        int fromIndex = (page - 1) * per;
        int toIndex = page * per;

        return context.json(USERS.subList(fromIndex, toIndex));
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
