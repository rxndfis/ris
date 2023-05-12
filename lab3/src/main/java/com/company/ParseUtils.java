package com.company;

public class ParseUtils {

    public static Long safeParseLong(String value) {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }
}
