package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        double doubleNumber = 1000d;
        int intNumber = 100;
        float floatNumber = 10.0f;
        boolean bool = false;
        char character = 'k';
        long longNumber = 100100100L;
        byte byteNumber = 120;
        short shortNumber = 50;
        byte bytNumber = 120;
        LOG.debug("Only numbers: {}, {}, {}, {}, {}, {}. Then character and boolean: {}, {}. ",
                doubleNumber, intNumber, floatNumber, longNumber, shortNumber, byteNumber, character, bool);
    }
}