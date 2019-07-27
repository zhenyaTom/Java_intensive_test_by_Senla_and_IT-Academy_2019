package com.senla.atm.dao.utils;

import com.senla.atm.entity.Card;
import com.senla.atm.entity.CashMachine;
import com.senla.atm.entity.utils.DateUtil;

public class EntityParser {

    public static CashMachine parseCashMachineFromString(String cashMachineString) {
        CashMachine cashMachine = new CashMachine();
        cashMachine.setAvailableCash(Double.parseDouble(cashMachineString));
        return cashMachine;
    }

    public static Card parseCardFromStringArray(String[] cardStringArray){
        Card card = new Card();
        card.setNumber(cardStringArray[0]);
        card.setPinCode(Integer.parseInt(cardStringArray[1]));
        card.setBalance(Double.parseDouble(cardStringArray[2]));
        card.setIsLocked(Boolean.parseBoolean(cardStringArray[3]));
        card.setLockDate(DateUtil.convertStringToDate(cardStringArray[4]));
        card.setWrongPinCodeCounter(Integer.parseInt(cardStringArray[5]));
        return card;
    }
}