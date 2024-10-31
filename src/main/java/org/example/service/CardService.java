package org.example.service;

import org.example.dao.CardDao;
import org.example.model.Card;

import java.util.List;

public class CardService {
    private final CardDao cardDao = new CardDao();

    public List<Card> getMyCards(int userId){
        return cardDao.getMyCards(userId);
    }

    public int deleteCard(String cardPassword, String cardNumber, int userId){
        return cardDao.deleteCard(cardPassword, cardNumber, userId);
    }

    public int addCard(String cardName, String cardNumber, String password, int userId){
        return cardDao.addCard(cardName, cardNumber, password, userId);
    }
}
