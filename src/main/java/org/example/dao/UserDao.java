package org.example.dao;

import org.example.dao.config.DatabaseConfig;
import org.example.dao.config.PostgresDatabaseConfig;
import org.example.model.Card;
import org.example.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    DatabaseConfig databaseConfig;
    private static final String REGISTER = "select register(i_name := ?, i_user_name := ?, i_password := ?, i_email := ?)";
    private static final String SIGN_IN = "select sign_in(i_username := ?, i_password := ?)";


    public UserDao() {
        this.databaseConfig = new PostgresDatabaseConfig();
    }

    public int addUser(String name, String userName, String password, String email) {

        int resultValue = 0;
        try (Connection connect = databaseConfig.connect();
             PreparedStatement statement = connect.prepareStatement(REGISTER)
        ) {
            statement.setString(1, name);
            statement.setString(2, userName);
            statement.setString(3, password);
            statement.setString(4, email);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                resultValue = resultSet.getInt(1);
            }
            return resultValue;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int signIn(String username, String password) {
        int resultValue = 0;
        try (Connection connect = databaseConfig.connect();
             PreparedStatement statement = connect.prepareStatement(SIGN_IN)
        ) {
            statement.setString(1, username);
            statement.setString(2, password);

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
