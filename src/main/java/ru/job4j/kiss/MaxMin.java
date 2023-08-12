package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
       return findValue((temp, element) -> comparator.compare(temp, element) < 0, value);
        }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return findValue((temp, element) -> comparator.compare(temp, element) > 0, value);
    }

    private <T> T findValue(BiPredicate<T, T> predicate, List<T> value) {
        T temp = value.get(0);
        for (int i = 1; i < value.size(); i++) {
            if (predicate.test(temp, value.get(i))) {
                temp = value.get(i);
            }
        }
        return temp;
    }
}

