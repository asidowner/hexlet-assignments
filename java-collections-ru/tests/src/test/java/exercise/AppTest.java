package exercise;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AppTest {
    List<Integer> list = new ArrayList<>();
    List<Integer> slicedList = new ArrayList<>();

    final int slice = 1;

    @BeforeEach
    void beforeEach() {
        this.list = List.of(1, 2, 3, 4);

        for (int i = 0; i < (this.list.size() - this.slice); i++) {
            this.slicedList.add(this.list.get(i));
        }
    }

    @Test
    void testTake() {
        // BEGIN
        int range = this.list.size();

        assertThat(App.take(this.list, range)).isEqualTo(this.list);

        assertThat(App.take(this.list, range + 1)).isEqualTo(this.list);

        assertThat(App.take(this.list, 0)).isEqualTo(List.of());

        assertThat(App.take(this.list, -1)).isEqualTo(List.of());

        assertThat(App.take(this.list, range - this.slice)).isEqualTo(this.slicedList);

        assertThat(App.take(List.of(), range - this.slice)).isEqualTo(List.of());
        // END
    }
}
