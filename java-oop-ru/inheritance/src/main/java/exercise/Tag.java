package exercise;

import java.util.stream.Collectors;
import java.util.Map;

// BEGIN
public abstract class Tag {
    private final String tag;
    private final Map<String, String> attributes;

    public Tag(String tag, Map<String, String> attributes) {
        this.tag = tag;
        this.attributes = attributes;
    }

    public String getTag() {
        return tag;
    }

    String getAttributesAsString() {
        String attributeFormat = " %s=\"%s\"";
        return attributes.entrySet().stream()
                .map(entry -> String.format(attributeFormat, entry.getKey(), entry.getValue()))
                .collect(Collectors.joining(""));
    }

    public String toString() {
        String tagFormat = "<%s%s>";
        return String.format(tagFormat, tag, getAttributesAsString());
    }
}
// END
