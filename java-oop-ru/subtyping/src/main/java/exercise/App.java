package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

// BEGIN
class App {
    public static void swapKeyValue(KeyValueStorage storage) {
        for (Map.Entry<String, String> item : storage.toMap().entrySet()) {
            storage.unset(item.getKey());
            storage.set(item.getValue(), item.getKey());
        }
    }
}
// END
