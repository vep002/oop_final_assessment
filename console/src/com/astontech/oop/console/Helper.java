package com.astontech.oop.console;

import java.util.Optional;
import java.util.Scanner;

public class Helper {

    public static String getExtensionByStringHandling(String filename) {
         Optional O = Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
         String S = O.toString();
         return S;
    }

    public static Double bytesToMegabytes (Long file) {
        long bytes = file;
        long kilobytes = (bytes / 1024);
        int longToDouble = (int) kilobytes;
        Double size = (double) longToDouble;
        return size;
    }

}
