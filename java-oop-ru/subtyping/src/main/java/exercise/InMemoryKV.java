package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class InMemoryKV implements KeyValueStorage {
    private Map<String, String> storage;

    public InMemoryKV(Map<String, String> storage) {
        this.storage = new HashMap<>(storage);
    }

    @Override
    public void set(String key, String value) {
        this.storage.put(key, value);
    }

    @Override
    public void unset(String key) {
        this.storage.remove(key);
    }

    @Override
    public String get(String key, String defaultValue) {
        return this.storage.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        return new HashMap<>(storage);
    }
}
// END
