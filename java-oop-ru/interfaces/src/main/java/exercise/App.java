package exercise;

import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class App {
    public static List<String> buildApartmentsList(List<Home> apartments, int count) {
        return apartments.stream()
                .limit(count)
                .sorted(Home::compareTo)
                .map(Home::toString)
                .collect(Collectors.toList());
    }
}
// END
