package com.senla.atm.ui.actions;

import com.senla.atm.facade.Atm;
import com.senla.atm.ui.utils.PrinterUtil;

public class CheckBalance implements IAction {
    public void execute() {
        if (Atm.getInstance().getUserIsAuthorized()){
            Double balance = Atm.getInstance().checkBalance();
            PrinterUtil.printMessage("На вашей карте: " + balance);
        } else {
            PrinterUtil.printMessage("Для просмотра баланса необходимо авторизироваться");
        }
    }
}