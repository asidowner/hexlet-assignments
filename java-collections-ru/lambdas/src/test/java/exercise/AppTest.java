package exercise;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

// BEGIN
class AppTest {

    @Test
    void testEnlargeArrayImage() {
        String[][] image = {
                {"*", "*", "*", "*"},
                {"*", " ", " ", "*"},
                {"*", " ", " ", "*"},
                {"*", "*", "*", "*"},
        };

        String[][] expected = {
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", " ", " ", " ", " ", "*", "*"},
                {"*", "*", " ", " ", " ", " ", "*", "*"},
                {"*", "*", " ", " ", " ", " ", "*", "*"},
                {"*", "*", " ", " ", " ", " ", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
        };

        assertThat(App.enlargeArrayImage(image))
                .isEqualTo(expected);
    }

    @Test
    void testEnlargeArrayImageWithOneElement() {
        String[][] image = {{"*"}};

        String[][] expected = {
                {"*", "*"},
                {"*", "*"}
        };

        assertThat(App.enlargeArrayImage(image))
                .isEqualTo(expected);
    }

    @Test
    void testEnlargeArrayImageWithEmptyArray() {
        String[][] image = {};
        String[][] expected = {};

        assertThat(App.enlargeArrayImage(image))
                .isEqualTo(expected);
    }
}
// END
