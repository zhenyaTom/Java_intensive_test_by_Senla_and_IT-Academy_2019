package com.senla.atm.ui.navigator;

import com.senla.atm.ui.item.MenuItem;
import com.senla.atm.ui.menu.Menu;
import com.senla.atm.ui.utils.PrinterUtil;

public class Navigator {
    private Menu currentMenu;

    public Navigator(Menu currentMenu) {
        this.currentMenu = currentMenu;
    }

    public void printMenu() {
        PrinterUtil.printMessage("_____________________________________________");

        PrinterUtil.printMessage(currentMenu.getName());
        for (MenuItem item : currentMenu.getItems()) {
            PrinterUtil.printMessage(item.getTitle());
        }
        PrinterUtil.printMessage("_____________________________________________");
    }

    public void navigate(Integer index) {
        try {
            MenuItem item = currentMenu.getItems().get(index - 1);
            if (item.getAction() != null) {
                item.doAction();
            }
            currentMenu = item.getNextMenu();
        } catch (IndexOutOfBoundsException e) {
            PrinterUtil.printMessage("Invalid input");
        }
    }

    public Menu getCurrentMenu(){
        return currentMenu;
    }
}