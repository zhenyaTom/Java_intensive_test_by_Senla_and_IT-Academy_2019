package com.senla.atm.api.dao;

import com.senla.atm.entity.Card;
import java.io.FileNotFoundException;

public interface ICardDao {
    Card getCardByNumber(String cardNumber) throws FileNotFoundException;
    void updateCard (Card card) throws FileNotFoundException;
}