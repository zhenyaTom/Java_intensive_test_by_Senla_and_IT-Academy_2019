package com.senla.atm.dao;

import com.senla.atm.api.dao.ICardDao;
import com.senla.atm.dao.utils.EntityParser;
import com.senla.atm.dao.utils.FileWorker;
import com.senla.atm.entity.Card;

import java.io.FileNotFoundException;

public class CardDao implements ICardDao {
    private static CardDao instance;
    private final String CARDS_FILE_PATH = "cards.txt";

    private CardDao(){
    }

    public static CardDao getInstance() {
        if (instance == null) {
            instance = new CardDao();
        }
        return instance;
    }

    public Card getCardByNumber(String cardNumber) throws FileNotFoundException {
        String[] cardStringArray = FileWorker.readCardFromFileByNumber(cardNumber, CARDS_FILE_PATH);
        if (cardStringArray != null){
            return EntityParser.parseCardFromStringArray(cardStringArray);
        }
        return null;
    }

    public void updateCard (Card card) throws FileNotFoundException {
        FileWorker.writeCardToFile(card, CARDS_FILE_PATH);
    }
}