package com.senla.atm.ui;


import com.senla.atm.ui.controller.MenuController;
import com.senla.atm.ui.utils.ScannerUtil;

public class Main {
    public static void main(String[] args) {

        MenuController controller = new MenuController();
        controller.run();

        ScannerUtil.getScanner().close();
    }
}