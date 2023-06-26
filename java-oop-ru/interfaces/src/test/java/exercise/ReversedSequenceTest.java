package exercise;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ReversedSequenceTest {
    String text;
    String reversedText;
    CharSequence sequence;

    @BeforeEach
    void beforeEach() {
        this.text = "abcdef";
        this.reversedText = "fedcba";
        this.sequence = new ReversedSequence(this.text);
    }

    @Test
    void length() {
        assertThat(this.sequence.length()).isEqualTo(this.text.length());
    }

    @Test
    void charAt() {
        assertThat(this.sequence.charAt(2)).isEqualTo(this.reversedText.charAt(2));

        assertThatThrownBy(() -> this.sequence.charAt(-1))
                .isInstanceOf(IndexOutOfBoundsException.class);

        assertThatThrownBy(() -> this.sequence.charAt(this.text.length() + 1))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void subSequence() {
        assertThat(this.sequence.subSequence(1, 3)).isEqualTo(this.reversedText.subSequence(1, 3));

        assertThatThrownBy(() -> this.sequence.subSequence(-1, 3))
                .isInstanceOf(IndexOutOfBoundsException.class);

        assertThatThrownBy(() -> this.sequence.subSequence(1, this.sequence.length() + 1))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void testToString() {
        assertThat(this.sequence.toString()).isEqualTo(this.reversedText);
    }
}