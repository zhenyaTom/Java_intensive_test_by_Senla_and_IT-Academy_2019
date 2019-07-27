package com.senla.atm.ui.actions;

import com.senla.atm.facade.Atm;
import com.senla.atm.ui.utils.PrinterUtil;
import com.senla.atm.ui.utils.ScannerUtil;

public class GetCash implements IAction {
    public void execute() {
        if (Atm.getInstance().getUserIsAuthorized()){
            PrinterUtil.printMessage("Введите сумму для снятия наличных...");
            Double count = Double.valueOf(ScannerUtil.getScanner().nextLine());
            switch (Atm.getInstance().giveCash(count)){
                case 1:
                    PrinterUtil.printMessage("Отсутствует связь с данными");
                    break;
                case 2:
                    PrinterUtil.printMessage("На карте недостаточно средств");
                    break;
                case 3:
                    Double availableCash = Atm.getInstance().getAvailableCash();
                    PrinterUtil.printMessage("В банкомате недостаточно наличных. Доступно " + availableCash);
                    break;
                case 4:
                    PrinterUtil.printMessage("Операция прошла успешно. Заберите деньги");
            }
        } else {
            PrinterUtil.printMessage("Для просмотра баланса необходимо авторизироваться");
        }
    }
}