package com.senla.atm.api.service;

import com.senla.atm.entity.Card;
import java.io.FileNotFoundException;
import java.util.Date;

public interface ICardService {
    Boolean checkCardExistence (String cardNumber);
    Card getCardByNumber (String cardNumber);
    Boolean checkCardIsLocked (Card card);
    Date getUnlockDate(Card card);
    Boolean checkPinCode (Card card, Integer pinCode);
    void lockCard (Card card);
    Double getBalance (Card card);
    void increaseBalance (Card card, Double amount) throws FileNotFoundException;
    void reduceBalance (Card card, Double amount) throws FileNotFoundException;
}