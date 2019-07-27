package com.senla.atm.ui.actions;

import com.senla.atm.facade.Atm;
import com.senla.atm.ui.utils.PrinterUtil;
import com.senla.atm.ui.utils.ScannerUtil;

import java.util.Date;

public class LogIn implements IAction {

    public void execute() {
        String cardNumber;
        do {
            PrinterUtil.printMessage("Введите номер карты в формате: ХХХХ-ХХХХ-ХХХХ-ХХХХ");
            cardNumber = ScannerUtil.getScanner().nextLine();
            if (!cardNumber.matches("([0-9]{4}-){3}[0-9]{4}")){
                PrinterUtil.printMessage("Введенный номер карты " + cardNumber + " не соответствует требуемому формату");
            }
        } while (!cardNumber.matches("([0-9]{4}-){3}[0-9]{4}"));

        Boolean cardExistence = Atm.getInstance().checkCardExistence(cardNumber);
        if (cardExistence == null) {
            PrinterUtil.printMessage("Отсутствует связь с данными");
        } else if (!cardExistence) {
            PrinterUtil.printMessage("Карты с номером " + cardNumber + " не существует");
        } else {

            if (Atm.getInstance().checkCardIsLocked()) {
                Date unlockDate = Atm.getInstance().getUnlockDate();
                PrinterUtil.printMessage("Ваша карта заблокирована. Карты будет разблокирована автоматически " + unlockDate);
            } else {

                while (Atm.getInstance().getWrongPinCodeCounter() < 3) {
                    int attemptsLeft = (3 - Atm.getInstance().getWrongPinCodeCounter());
                    String pinCodeString;
                    do {
                        PrinterUtil.printMessage("Введите пин код в формате: ХХХХ");
                        PrinterUtil.printMessage("Количество оставшихся попыток: " + attemptsLeft);
                        pinCodeString = ScannerUtil.getScanner().nextLine();
                        if (!pinCodeString.matches("([0-9]{4})")){
                            PrinterUtil.printMessage("Введенный пин код не соответствует требуемому формату");
                        }
                    } while (!pinCodeString.matches("([0-9]{4})"));
                    Integer pinCode = Integer.parseInt(pinCodeString);
                    Boolean logInResult = Atm.getInstance().logIn(pinCode);
                    if (logInResult == null) {
                        PrinterUtil.printMessage("Вы ввели неверный пин код. Ваша карта заблокирована на сутки");
                        break;
                    } else if (!logInResult) {
                        PrinterUtil.printMessage("Вы ввели неверный пин код.");
                    } else if (logInResult) {
                        PrinterUtil.printMessage("Пин код верен");
                        break;
                    }
                }
            }
        }
    }
}