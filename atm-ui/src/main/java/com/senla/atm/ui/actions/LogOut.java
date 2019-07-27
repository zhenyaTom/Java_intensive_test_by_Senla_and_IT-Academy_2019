package com.senla.atm.ui.actions;

import com.senla.atm.facade.Atm;
import com.senla.atm.ui.utils.PrinterUtil;

public class LogOut implements IAction {
    public void execute() {
        if (Atm.getInstance().getUserIsAuthorized()){
            Atm.getInstance().logOut();
            PrinterUtil.printMessage("Выход из вышего аккаунта осуществлен");
        }
    }
}