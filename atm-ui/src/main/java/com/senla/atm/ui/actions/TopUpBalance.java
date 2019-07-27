package com.senla.atm.ui.actions;

import com.senla.atm.facade.Atm;
import com.senla.atm.ui.utils.PrinterUtil;
import com.senla.atm.ui.utils.ScannerUtil;

public class TopUpBalance implements IAction {
    public void execute() {
        if (Atm.getInstance().getUserIsAuthorized()){
            Double topUpLimit = Atm.getInstance().getTopUpLimit();
            if (topUpLimit == null){
                PrinterUtil.printMessage("Отсутствует связь с данными");
            } else {
                PrinterUtil.printMessage("Внесите наличные. Максимальная сумма пополнения " + topUpLimit);
                Double count = Double.valueOf(ScannerUtil.getScanner().nextLine());
                switch (Atm.getInstance().topUpBalance(count)){
                    case 1:
                        PrinterUtil.printMessage("Отсутствует связь с данными");
                        break;
                    case 2:
                        PrinterUtil.printMessage("Превышен мксимальный лимит пополнения карты");
                        break;
                    case 3:
                        PrinterUtil.printMessage("Операция прошла успешно. Деньги внесены на карту");
                }
            }
        } else {
            PrinterUtil.printMessage("Для просмотра баланса необходимо авторизироваться");
        }
    }
}