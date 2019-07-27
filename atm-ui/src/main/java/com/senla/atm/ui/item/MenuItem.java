package com.senla.atm.ui.item;

import com.senla.atm.ui.actions.IAction;
import com.senla.atm.ui.menu.Menu;


public class MenuItem {
    private String title;
    private IAction action;
    private Menu nextMenu;

    public MenuItem(String title, IAction action, Menu nextMenu) {
        this.title = title;
        this.action = action;
        this.nextMenu=nextMenu;
    }

    public MenuItem(String title, Menu nextMenu) {
        this.title = title;
        this.nextMenu = nextMenu;
    }

    public String getTitle() {
        return title;
    }

    public Menu getNextMenu() {
        return nextMenu;
    }

    public IAction getAction() {
        return action;
    }

    public void doAction() {
        action.execute();
    }
}