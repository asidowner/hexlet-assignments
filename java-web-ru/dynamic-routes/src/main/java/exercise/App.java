package exercise;

import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;
import java.util.Map;
import java.util.Optional;

// BEGIN
import io.javalin.http.NotFoundResponse;
// END

public final class App {

    private static final List<Map<String, String>> COMPANIES = Data.getCompanies();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        // BEGIN
        app.get("/companies/{id}", App::getCompanyById);
        // END

        app.get("/companies", ctx -> {
            ctx.json(COMPANIES);
        });

        app.get("/", ctx -> {
            ctx.result("open something like (you can change id): /companies/5");
        });

        return app;

    }

    private static void getCompanyById(Context context) {
        var id = context.pathParam("id");

        Optional<Map<String, String>> company = COMPANIES.stream()
                .filter(c -> c.get("id").equals(id))
                .findFirst();

        if (company.isEmpty()) {
            throw new NotFoundResponse("Company not found");
        }
        context.json(company.get());
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
