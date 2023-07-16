package com.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomUtil {
    public static String generateRandomName() {
        return "Automation" + RandomStringUtils.randomAlphabetic(10);
    }

    public static String generateRandomName(int additionalLength) {
        return "Automation" + RandomStringUtils.randomAlphabetic(additionalLength);
    }
}
