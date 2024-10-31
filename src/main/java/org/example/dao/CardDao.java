package org.example.dao;

import org.example.dao.config.DatabaseConfig;
import org.example.dao.config.PostgresDatabaseConfig;
import org.example.model.Card;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CardDao {

    DatabaseConfig databaseConfig;
    private static final String REGISTER = "select * from get_my_cards(i_user_id := ?)";
    private static final String DELETE_CARD = "select delete_card(i_password_card := ?, i_card_number := ?, i_user_id := ?)";
    private static final String ADD_CARD = "select add_cart(i_card_name := ?, i_card_number := ?, i_password := ?, i_user_id := ?)";

    public CardDao() {
        this.databaseConfig = new PostgresDatabaseConfig();
    }


    public List<Card> getMyCards(int userId) {
        List<Card> cards = new ArrayList<>();

        try (Connection connect = databaseConfig.connect();
             PreparedStatement statement = connect.prepareStatement(REGISTER)
        ) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Card card = new Card(resultSet);
                cards.add(card);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return cards;
    }

    public int deleteCard(String cardPassword, String cardNumber, int userId) {
        int resultValue = 0;
        try (Connection connect = databaseConfig.connect();
             PreparedStatement statement = connect.prepareStatement(DELETE_CARD)
        ) {
            statement.setString(1, cardPassword);
            statement.setString(2, cardNumber);
            statement.setInt(3, userId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                resultValue = resultSet.getInt(1);
            }
            return resultValue;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int addCard(String cardName, String cardNumber, String password, int userId){

        int resultValue = 0;

        try (Connection connect = databaseConfig.connect();
             PreparedStatement statement = connect.prepareStatement(ADD_CARD)
        ) {

            statement.setString(1, cardName);
            statement.setString(2, cardNumber);
            statement.setString(3, password);
            statement.setInt(4, userId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                resultValue = resultSet.getInt(1);
            }
            return resultValue;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
