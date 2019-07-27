package com.senla.atm.ui.controller;

import com.senla.atm.ui.builder.Builder;
import com.senla.atm.ui.navigator.Navigator;
import com.senla.atm.ui.utils.ScannerUtil;
import com.senla.atm.ui.utils.PrinterUtil;

public class MenuController {
    private Builder builder;
    private Navigator navigator;

    public MenuController(){
        builder= new Builder();
        builder.buildMenu();
        navigator = new Navigator(builder.getRootMenu());
    }

    public void run() {
        while (navigator.getCurrentMenu() != null) {
            navigator.printMenu();
            try {
                int index = Integer.parseInt(ScannerUtil.getScanner().nextLine());
                navigator.navigate(index);
            } catch (Exception e){
                PrinterUtil.printMessage("Invalid input");
            }
        }
    }
}