package exercise.controller;

import exercise.daytime.Daytime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

// BEGIN
@RestController
public class WelcomeController {
    @Autowired
    private Daytime daytime;

    private static final String MESSAGE_TEMPLATE = "It is %s now! Welcome to Spring!";

    @GetMapping("/welcome")
    public String index() {
        return String.format(MESSAGE_TEMPLATE, daytime.getName());
    }
}
// END
