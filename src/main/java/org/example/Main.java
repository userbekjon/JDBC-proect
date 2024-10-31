package org.example;

import org.example.dao.CardDao;
import org.example.dao.UserDao;
import org.example.model.Card;
import org.example.model.User;
import org.example.service.CardService;
import org.example.service.UserService;

import java.util.List;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static UserService userService = new UserService();
    public static CardService cardService = new CardService();
    public static Scanner scannerStr = new Scanner(System.in);
    public static Scanner scannerInt = new Scanner(System.in);

    public static void main(String[] args) {

        boolean isActive = true;
        while (isActive) {
            System.out.println("1. Register  2. Sign in  0. Exit");
            int i = scannerInt.nextInt();
            switch (i) {
                case 1 -> {
                    System.out.print("enter name: ");
                    String name = scannerStr.nextLine();
                    System.out.print("enter username: ");
                    String username = scannerStr.nextLine();
                    System.out.print("enter password: ");
                    String password = scannerStr.nextLine();
                    System.out.print("enter email: ");
                    String email = scannerStr.nextLine();
                    int register = userService.register(name, username, password, email);
                    if (register == 0) {
                        System.out.println("something is wrong.");
                    } else {
                        boolean isActive2 = true;
                        while (isActive2) {
                            System.out.println("1. Get my cards 2. Delete card 3. Add card");
                            int i2 = scannerInt.nextInt();
                            switch (i2){
                                case 1 -> {
                                    List<Card> myCards = cardService.getMyCards(register);
                                    for (int j = 0; j < myCards.size(); j++) {
                                        System.out.println(j + 1 + " " + myCards.get(j));
                                    }
                                }
                                case 2 -> {
                                    System.out.print("enter card password.");
                                    String cardPassword = scannerStr.nextLine();
                                    System.out.print("enter card number.");
                                    String cardNumber = scannerStr.nextLine();
                                    int i1 = cardService.deleteCard(cardPassword, cardNumber, register);
                                    if (i1 == 0){
                                        System.out.println("card number or password is wrong.");
                                    }else {
                                        System.out.println("deleted.");
                                    }
                                }
                                case 3 -> {
                                    System.out.print("enter card name: ");
                                    String cardName = scannerStr.nextLine();
                                    System.out.print("enter card password: ");
                                    String cardPassword = scannerStr.nextLine();
                                    System.out.print("enter card number: ");
                                    String cardNumber = scannerStr.nextLine();
                                    int i1 = cardService.addCard(cardName, cardNumber, cardPassword, register);
                                    if (i1 == 0){
                                        System.out.println("something is wrong.");
                                    }else {
                                        System.out.println("succesfuly added");
                                    }
                                }
                            }
                        }

                    }
                }
                case 2 -> {
                    System.out.print("enter username: ");
                    String username = scannerStr.nextLine();
                    System.out.print("enter password: ");
                    String password = scannerStr.nextLine();
                    int i1 = userService.signIn(username, password);
                    if (i1 == 0) {
                        System.out.println("Something is wrong.");
                    } else {
                        boolean isActive2 = true;
                        while (isActive2) {
                            System.out.println("1. Get my cards 2. Delete card 3. Add card 0. Exit");
                            int i2 = scannerInt.nextInt();
                            switch (i2){
                                case 1 -> {
                                    List<Card> myCards = cardService.getMyCards(i1);
                                    for (int j = 0; j < myCards.size(); j++) {
                                        System.out.println(j + 1 + " " + myCards.get(j));
                                    }
                                }
                                case 2 -> {
                                    System.out.print("enter card password: ");
                                    String cardPassword = scannerStr.nextLine();
                                    System.out.print("enter card number: ");
                                    String cardNumber = scannerStr.nextLine();
                                    int i3 = cardService.deleteCard(cardPassword, cardNumber, i1);
                                    if (i3 == 0){
                                        System.out.println("card number or password is wrong.");
                                    }else {
                                        System.out.println("deleted.");
                                    }
                                }
                                case 3 -> {
                                    System.out.print("enter card name: ");
                                    String cardName = scannerStr.nextLine();
                                    System.out.print("enter card password.");
                                    String cardPassword = scannerStr.nextLine();
                                    System.out.print("enter card number.");
                                    String cardNumber = scannerStr.nextLine();
                                    int i4 = cardService.addCard(cardName, cardNumber, cardPassword, i1);
                                    if (i4 == 0){
                                        System.out.println("something is wrong.");
                                    }else {
                                        System.out.println("succesfuly added");
                                    }
                                }
                                case 0 -> {
                                    isActive2 = false;
                                }
                            }
                        }
                    }
                }
                case 0 -> {
                    isActive = false;
                }
            }
        }

    }
}