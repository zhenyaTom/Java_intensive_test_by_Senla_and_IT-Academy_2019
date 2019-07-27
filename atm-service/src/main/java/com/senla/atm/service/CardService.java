package com.senla.atm.service;

import com.senla.atm.api.dao.ICardDao;
import com.senla.atm.api.service.ICardService;
import com.senla.atm.dao.CardDao;
import com.senla.atm.entity.Card;
import org.apache.log4j.Logger;
import java.io.FileNotFoundException;
import java.util.Date;

public class CardService implements ICardService {
    private static Logger logger = Logger.getLogger(CardService.class);
    private static CardService instance;
    private ICardDao cardDao = CardDao.getInstance();

    private CardService (){
    }

    public static CardService getInstance (){
        if (instance == null){
            instance = new CardService();
        }
        return instance;
    }

    public Boolean checkCardExistence (String cardNumber){
        try {
            if (cardDao.getCardByNumber(cardNumber) != null){
                return true;
            }
            return false;
        } catch (FileNotFoundException e) {
            logger.error("Card file not found!", e);
            return null;
        }
    }

    public Card getCardByNumber (String cardNumber){
        try {
            return cardDao.getCardByNumber(cardNumber);
        } catch (FileNotFoundException e){
            logger.error("Card file not found!", e);
            return null;
        }
    }

    public Boolean checkCardIsLocked (Card card) {
        if (!card.getIsLocked()) {
            return false;
        }
        Date currentDate = new Date();
        Date lockDate = card.getLockDate();
        if (currentDate.getTime() - lockDate.getTime() > 86400000) {
            card.setIsLocked(false);
            card.setLockDate(null);
            card.setWrongPinCodeCounter(0);
            return false;
        }
        return true;
    }

    public Date getUnlockDate(Card card){
        Date lockDate = card.getLockDate();
        return new Date(lockDate.getTime()+ 86400000);
    }

    public Boolean checkPinCode (Card card, Integer pinCode){
        try {
            if (card.getPinCode().equals(pinCode)){
                card.setWrongPinCodeCounter(0);
                cardDao.updateCard(card);
                return true;
            }
            card.setWrongPinCodeCounter(card.getWrongPinCodeCounter() + 1);
            cardDao.updateCard(card);
            return false;
        } catch (FileNotFoundException e){
            logger.error("Card file not found!", e);
            return null;
        }
    }

    public void lockCard (Card card){
        try {
            Date currentDate = new Date();
            card.setIsLocked(true);
            card.setLockDate(currentDate);
            cardDao.updateCard(card);
        } catch (FileNotFoundException e) {
            logger.error("Card file not found!", e);
        }
    }

    public Double getBalance (Card card){
        return card.getBalance();
    }

    public void increaseBalance (Card card, Double amount) throws FileNotFoundException {
        card.setBalance(card.getBalance() + amount);
        cardDao.updateCard(card);
    }

    public void reduceBalance (Card card, Double amount) throws FileNotFoundException {
        card.setBalance(card.getBalance() - amount);
        cardDao.updateCard(card);
    }
}