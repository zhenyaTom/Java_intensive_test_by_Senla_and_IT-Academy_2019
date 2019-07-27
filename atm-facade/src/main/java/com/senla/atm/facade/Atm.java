package com.senla.atm.facade;

import com.senla.atm.api.facade.IAtm;
import com.senla.atm.api.service.*;
import com.senla.atm.entity.Card;
import com.senla.atm.service.CardService;
import com.senla.atm.service.CashMachineService;
import org.apache.log4j.Logger;
import java.io.FileNotFoundException;
import java.util.Date;

public class Atm implements IAtm {
    private static Logger logger = Logger.getLogger(Atm.class);
    private static Atm instance;
    private ICardService cardService = CardService.getInstance();
    private ICashMachineService cashMachineService = CashMachineService.getInstance();
    private Card sessionCard;
    private Boolean isUserAuthorized = false;

    private Atm (){
    }

    public static Atm getInstance(){
        if (instance == null) {
            instance = new Atm();
        }
        return instance;
    }

    public Boolean checkCardExistence (String cardNumber){
        Boolean cardExistence = cardService.checkCardExistence(cardNumber);
        if (cardExistence != null){
            if (cardExistence){
                sessionCard = cardService.getCardByNumber(cardNumber);
                return true;
            }
        }
        return cardExistence;
    }

    public Boolean checkCardIsLocked (){
        return cardService.checkCardIsLocked(sessionCard);
    }

    public Date getUnlockDate (){
        return cardService.getUnlockDate(sessionCard);
    }

    public Boolean logIn (Integer pinCode){
        if (sessionCard.getWrongPinCodeCounter() < 3){
            if (cardService.checkPinCode(sessionCard, pinCode)){
                isUserAuthorized = true;
                return true;
            }
            if (sessionCard.getWrongPinCodeCounter() < 3){
                return false;
            }
        }
        cardService.lockCard(sessionCard);
        return null;
    }

    public Integer getWrongPinCodeCounter(){
        return sessionCard.getWrongPinCodeCounter();
    }

    public Boolean getUserIsAuthorized() {
        return isUserAuthorized;
    }

    public Double checkBalance (){
        if (isUserAuthorized){
            return cardService.getBalance(sessionCard);
        }
        return null;
    }

    public Double getAvailableCash (){
        return cashMachineService.getAvailableCash();
    }

    public Double getTopUpLimit(){
        return cashMachineService.getTopUpLimit();
    }

    public int giveCash(Double amount) {
        if (isUserAuthorized) {
            Double availableCash = cashMachineService.getAvailableCash();
            Double balance = cardService.getBalance(sessionCard);
            if (availableCash == null){
                return 1;
            } else if (balance < amount){
                return 2;
            } else if (availableCash < amount){
                return 3;
            } else {
                try {
                    cardService.reduceBalance(sessionCard, amount);
                    cashMachineService.reduceAvailableCash(amount);
                    return 4;
                } catch (FileNotFoundException e){
                    logger.error("File not found! Transaction canceled!", e);
                    return 1;
                }
            }
        }
        return 5;
    }

    public int topUpBalance(Double amount) {
        if (isUserAuthorized) {
            Double topUpLimit = cashMachineService.getTopUpLimit();
            if (topUpLimit == null){
                return 1;
            } else if (topUpLimit < amount){
                return 2;
            } else {
                try {
                    cardService.increaseBalance(sessionCard, amount);
                    cashMachineService.increaseAvailableCash(amount);
                    return 3;
                } catch (FileNotFoundException e){
                    logger.error("File not found! Transaction canceled!", e);
                    return 1;
                }
            }
        }
        return 4;
    }

    public void logOut (){
        sessionCard = null;
        isUserAuthorized = false;
    }
}