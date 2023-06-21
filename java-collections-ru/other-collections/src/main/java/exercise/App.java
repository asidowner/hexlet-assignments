package exercise;

import java.util.*;
import java.util.function.Function;

// BEGIN
class App {
    public static <V> LinkedHashMap<String, String> genDiff(Map<String, V> data1, Map<String, V> data2) {
        TreeSet<String> keys = new TreeSet<>();
        keys.addAll(data1.keySet());
        keys.addAll(data2.keySet());

        Function<String, Map<String, String>> comparator = (key) -> {
            if (!data1.containsKey(key)) {
                return Map.of(key, "added");
            }
            if (!data2.containsKey(key)) {
                return Map.of(key, "deleted");
            }
            if (data1.get(key).equals(data2.get(key))) {
                return Map.of(key, "unchanged");
            }
            return Map.of(key, "changed");
        };

        return keys.stream()
                .map(comparator)
                .collect(
                        LinkedHashMap::new,
                        LinkedHashMap::putAll,
                        LinkedHashMap::putAll
                );
    }

}
//END
