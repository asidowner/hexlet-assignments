package exercise;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag {
    static final String TAG_FORMAT = "%s%s%s</%s>";
    private final String tagBody;
    private final List<Tag> children;

    public PairedTag(String tag, Map<String, String> attributes, String tagBody, List<Tag> children) {
        super(tag, attributes);
        this.tagBody = tagBody;
        this.children = children;
    }

    private String getChildrenAsString() {
        return children.stream()
                .map(Tag::toString)
                .collect(Collectors.joining());
    }

    @Override
    public String toString() {
        return String.format(
                TAG_FORMAT,
                super.toString(),
                tagBody,
                getChildrenAsString(),
                super.getTag()
        );
    }
}
// END
