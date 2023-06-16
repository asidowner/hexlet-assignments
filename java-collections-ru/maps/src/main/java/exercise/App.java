package exercise;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

// BEGIN
class App {
    public static Map<String, Integer> getWordCount(String sentence) {
        Map<String, Integer> result = new HashMap<>();

        if (sentence.length() == 0) {
            return result;
        }

        List<String> words = List.of(sentence.split(" "));

        for (String word : words) {
            Integer count = result.getOrDefault(word, 0) + 1;
            result.put(word, count);
        }
        
        return result;
    }

    public static String toString(Map<String, Integer> wordsCount) {
        if (wordsCount.size() == 0) {
            return "{}";
        }

        StringJoiner result = new StringJoiner("\n", "{\n", "\n}");

        for (Map.Entry<String, Integer> word : wordsCount.entrySet()) {
            result.add(String.format("  %s: %s", word.getKey(), word.getValue()));
        }

        return result.toString();
    }
}
//END
