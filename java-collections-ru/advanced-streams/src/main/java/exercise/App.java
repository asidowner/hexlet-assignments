package exercise;

import java.util.StringJoiner;
import java.util.Arrays;
import java.util.stream.Collectors;

// BEGIN
class App {
    public static String getForwardedVariables(String data) {
        if (data.isEmpty()) {
            return "";
        }

        return Arrays.stream(data.split(System.lineSeparator()))
                .filter(line -> line.startsWith("environment="))
                .map(line -> line.substring(line.indexOf('"') + 1, line.lastIndexOf('"')))
                .flatMap(line -> Arrays.stream(line.split(",")))
                .filter(key -> key.startsWith("X_FORWARDED_"))
                .map(key -> key.replace("X_FORWARDED_", ""))
                .collect(Collectors.joining(","));
    }
}
//END
