package ru.job4j.kiss;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MaxMinTest {

    @Test
    void ifStringMaxLengthThenLongestWord() {
        List<String> stringList = List.of("Annie", "Jennifer", "Tom");
        Comparator<String> comparator = Comparator.comparingInt(String::length);
        MaxMin maximum = new MaxMin();
        String rsl = maximum.max(stringList, comparator);
        String expected = "Jennifer";
        assertThat(rsl).isEqualTo(expected);
    }

    @Test
    void ifNumberMaxThenBiggestNumber() {
        List<Integer> integerList = List.of(20, 89, 45);
        Comparator<Integer> comparator = Comparator.comparingInt(o -> o);
        MaxMin minimum = new MaxMin();
        int rsl = minimum.min(integerList, comparator);
        int expected = 20;
        assertThat(rsl).isEqualTo(expected);
    }

    @Test
    void ifNumberMinThenSmallestNumber() {
        List<Integer> integerList = List.of(20, 89, 45);
        Comparator<Integer> comparator = Comparator.comparingInt(o -> o);
        MaxMin minimum = new MaxMin();
        int rsl = minimum.min(integerList, comparator);
        int expected = 20;
        assertThat(rsl).isEqualTo(expected);
    }

    @Test
    void ifStringComparedByCharsThenMinFirstAlphabetically() {
        List<String> stringList = List.of("Jennifer", "Jenni", "Tom");
        Comparator<String> comparator = (o2, o1) -> {
            int length = Math.min(o1.length(), o2.length());
            int rsl = 0;
            for (int i = 0; i < length; i++) {
                rsl = Character.toLowerCase(o2.charAt(i)) - Character.toLowerCase(o1.charAt(i));
                if (rsl != 0) {
                    return rsl;
            }
            }
            if (o1.length() > o2.length()) {
                rsl = -1;
            } else if (o1.length() < o2.length()) {
                rsl = 1;
            }
            return rsl;
        };
        MaxMin maximum = new MaxMin();
        String rsl = maximum.min(stringList, comparator);
        String expected = "Jenni";
        assertThat(rsl).isEqualTo(expected);
    }

    @Test
    void ifStringComparedByCharsThenMaxLastAlphabetically() {
        List<String> stringList = List.of("Jennifer", "Jenni", "Tom");
        Comparator<String> comparator = (o2, o1) -> {
            int length = Math.min(o1.length(), o2.length());
            int rsl = 0;
            for (int i = 0; i < length; i++) {
                rsl = Character.toLowerCase(o2.charAt(i)) - Character.toLowerCase(o1.charAt(i));
                if (rsl != 0) {
                    return rsl;
                }
            }
            if (o1.length() > o2.length()) {
                rsl = -1;
            } else if (o1.length() < o2.length()) {
                rsl = 1;
            }
            return rsl;
        };
        MaxMin maximum = new MaxMin();
        String rsl = maximum.max(stringList, comparator);
        String expected = "Tom";
        assertThat(rsl).isEqualTo(expected);
    }
}