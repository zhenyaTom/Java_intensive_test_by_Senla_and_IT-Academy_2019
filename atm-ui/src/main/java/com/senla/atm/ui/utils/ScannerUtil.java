package com.senla.atm.ui.utils;

import java.util.Scanner;

public class ScannerUtil {
    private static Scanner scanner = new Scanner(System.in);

    public static Scanner getScanner(){
        return scanner;
    }
}