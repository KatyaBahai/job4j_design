package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenMoreThan2ThenRemoved() {
        ListUtils.removeIf(input, n -> n > 2);
        assertThat(input).hasSize(1);
        assertThat(input).containsExactly(1);
    }

    @Test
    void whenHas3ReplaceFor7() {
        ListUtils.replaceIf(input, n -> n < 3, 7);
        assertThat(input).containsSequence(7, 3);
    }

    @Test
    void whenRemovedAll() {
        List<Integer> elements = new ArrayList<>(Arrays.asList(4, 5, 6, 1, 8));
        ListUtils.removeAll(input, elements);
        assertThat(input).containsExactly(3);
    }
}