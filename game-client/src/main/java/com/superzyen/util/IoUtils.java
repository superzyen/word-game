package com.superzyen.util;

import java.io.PrintStream;
import java.util.Scanner;

public class IoUtils {
    private static Scanner scanner = null;
    private static PrintStream out = null;

    /**
     * 通用输入
     */
    public static String inputString() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        String input = scanner.nextLine();
        scanner = null;
        return input;
    }

    /**
     * 通用输入
     */
    public static int inputInt() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        int input = scanner.nextInt();
        scanner = null;
        return input;
    }

    /**
     * 通用输出
     */
    public static void print(String val) {
        if (out == null) {
            out = System.out;
        }
        out.print(val);
    }


    /**
     * 通用输出
     */
    public static void println(String val) {
        if (out == null) {
            out = System.out;
        }
        out.println(val);
    }
}
