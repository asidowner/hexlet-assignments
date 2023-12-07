package exercise.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "HelloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    // BEGIN
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var qs = parseQueryString(req.getQueryString());

        var name = qs.getOrDefault("name", "Guest");

        var message = String.format("Hello, %s!", name);

        req.setAttribute("message", message);

        req.getRequestDispatcher("/WEB-INF/hello.jsp").forward(req, resp);
    }

    public Map<String, String> parseQueryString(String queryString) {
        return Arrays.stream(queryString.split("&"))
                .map(item -> item.split("="))
                .collect(Collectors.toMap(item -> item[0], item -> item[1]));
    }
    // END
}
