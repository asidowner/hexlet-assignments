package exercise;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

// BEGIN
class App {

    public static boolean scrabble(String source, String word) {
        if (source.length() < word.length()) {
            return false;
        }

        List<String> sourceCharList = new ArrayList<>(Arrays.asList(source.toLowerCase().split("")));
        List<String> wordCharList = new ArrayList<>(Arrays.asList(word.toLowerCase().split("")));

        for (String character : wordCharList) {
            if (!sourceCharList.contains(character)) {
                return false;
            }
            sourceCharList.remove(character);
        }
        return true;
    }
}
//END
