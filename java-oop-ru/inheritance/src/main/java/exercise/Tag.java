package exercise;

import java.util.stream.Collectors;
import java.util.Map;

// BEGIN
abstract class Tag {
    private final String TAG_FORMAT = "<%s%s>";
    private final String ATTRIBUTE_FORMAT = " %s=\"%s\"";
    private String tag;
    private Map<String, String> attributes;

    public Tag(String tag, Map<String, String> attributes) {
        this.tag = tag;
        this.attributes = attributes;
    }

    public String getTag() {
        return tag;
    }

    String getAttributesAsString() {
        return attributes.entrySet().stream()
                .map(entry -> String.format(ATTRIBUTE_FORMAT, entry.getKey(), entry.getValue()))
                .collect(Collectors.joining(""));
    }

    public String toString() {
        return String.format(TAG_FORMAT, tag, getAttributesAsString());
    }
}
// END
