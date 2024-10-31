package org.example.service;

import org.example.dao.UserDao;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserService {
    private final UserDao userDao = new UserDao();
    public int register(String name, String userName, String password, String email) {
        int i = 0;
        if (validate(password, email)) {
            i = userDao.addUser(name, userName, password, email);
        }
        return i;
    }

    public int signIn(String username, String password){
        int i = 0;
        i = userDao.signIn(username, password);
        return i;
    }

    public static boolean validate(String password, String email) {
        if (password.length() <= 8) {
            System.out.println("Password must be at least 8 characters long.");
            return false;
        }

        Pattern pattern = Pattern.compile("[^a-zA-Z0-9 ]");
        Matcher matcher = pattern.matcher(password);

        if (!matcher.find()){
            System.out.println("Does not have special character.");
            return false;
        }

        if (!(email.endsWith("@gmail.com") || email.endsWith("@mail.ru"))) {
            System.out.println("Email must end with @gmail.com or @mail.ru.");
            return false;
        }

        return true;
    }
}
