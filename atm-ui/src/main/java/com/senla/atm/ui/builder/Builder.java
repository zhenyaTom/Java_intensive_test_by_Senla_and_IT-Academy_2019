package com.senla.atm.ui.builder;

import com.senla.atm.ui.actions.*;

import com.senla.atm.ui.item.MenuItem;
import com.senla.atm.ui.menu.Menu;

public class Builder {
    private Menu rootMenu = new Menu("Menu");
    private Menu cardMenu = new Menu("Card menu");

    public void buildMenu() {
        rootMenu.addItem(new MenuItem("1-Log in", new LogIn(), rootMenu));
        rootMenu.addItem(new MenuItem("2-Card menu", cardMenu));
        rootMenu.addItem(new MenuItem("3-Exit program", new ExitProgram(), null ));

        cardMenu.addItem(new MenuItem("1-Check Balance", new CheckBalance(), cardMenu));
        cardMenu.addItem(new MenuItem("2-Get cash", new GetCash(), cardMenu));
        cardMenu.addItem(new MenuItem("3-Top up balance", new TopUpBalance(), cardMenu));
        cardMenu.addItem(new MenuItem("4-Log out", new LogOut(), rootMenu));

    }
    public Menu getRootMenu() {
        return rootMenu;
    }
}