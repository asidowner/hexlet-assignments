package exercise;

import java.nio.file.Path;
import java.util.Map;

// BEGIN
class FileKV implements KeyValueStorage {
    private Path filePath;

    FileKV(Path filePath, Map<String, String> initValue) {
        this.filePath = filePath;
        this.save(initValue);
    }

    @Override
    public void set(String key, String value) {
        Map<String, String> data = this.getFileDataFromJson();
        data.put(key, value);
        this.save(data);
    }

    @Override
    public void unset(String key) {
        Map<String, String> data = this.getFileDataFromJson();
        data.remove(key);
        this.save(data);
    }

    @Override
    public String get(String key, String defaultValue) {
        Map<String, String> data = this.getFileDataFromJson();
        return data.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        return this.getFileDataFromJson();
    }

    private void save(Map<String, String> value) {
        String json = Utils.serialize(value);
        Utils.writeFile(filePath.toString(), json);
    }

    private Map<String, String> getFileDataFromJson() {
        return Utils.unserialize(this.getFileData());
    }

    private String getFileData() {
        return Utils.readFile(filePath.toString());
    }
}
// END
