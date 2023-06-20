package exercise;

import java.util.List;

// BEGIN
class App {
    public static Long getCountOfFreeEmails(List<String> emailsList) {
        if (emailsList != null) {
            return emailsList.stream()
                    .filter(App::isFreeDomain)
                    .count();
        } else
            return 0L;
    }

    public static Boolean isFreeDomain(String email) {
        List<String> freeDomains = List.of("@yandex.ru", "@gmail.com", "@hotmail.com");

        return freeDomains.contains(email.substring(email.lastIndexOf("@")));
    }
}
// END
