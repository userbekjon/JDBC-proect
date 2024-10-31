package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Card {
    private String card_name;
    private String card_number;

    public Card(ResultSet resultSet) throws SQLException {
        this.card_name = resultSet.getString("card_name");
        this.card_number = resultSet.getString("card_number");
    }
}
