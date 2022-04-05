package com.emented.client.util;

/**
 * Класс, отвечающий за вывод информации в консоль
 */
public final class OutputUtil {

    private OutputUtil() { }

    public static void printErrorMessage(Object message) {
        System.out.println("\u001B[31m" + "Error: " + message + "\u001B[0m");
    }

    public static void printSuccessfulMessage(Object message) {
        System.out.println("\u001B[32m" + message + "\u001B[0m");
    }

    public static void printSuccessfulMessageOneStrip(Object message) {
        System.out.print("\u001B[32m" + message + "\u001B[0m");
    }
}
