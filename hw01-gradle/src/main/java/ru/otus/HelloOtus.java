package ru.otus;

import com.google.common.base.Strings;

@SuppressWarnings("java:S106")
public class HelloOtus {
    public static void main(String[] args) {
        String testString = "";
        boolean isEmpty = Strings.isNullOrEmpty(testString);
        System.out.println("Строка пуста: " + isEmpty);
    }
}
