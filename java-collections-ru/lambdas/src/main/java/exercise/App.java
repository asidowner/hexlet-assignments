package exercise;

import java.util.Arrays;
import java.util.stream.Stream;

// BEGIN
class App {
    public static final String[][] EMPTY_ARRAYS = {};

    public static String[][] enlargeArrayImage(String[][] image) {
        if (image.length == 0) {
            return App.EMPTY_ARRAYS;
        }

        return Arrays.stream(image)
                .map(array -> Arrays.stream(array)
                        .flatMap(item -> Stream.of(item, item))
                        .toArray(String[]::new))
                .flatMap(array -> Stream.of(array, array))
                .toArray(String[][]::new);
    }
}
// END
