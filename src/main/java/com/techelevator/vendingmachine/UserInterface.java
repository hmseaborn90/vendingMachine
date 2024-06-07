package com.techelevator.vendingmachine;
import java.math.BigDecimal;
import java.util.Scanner;

import static com.techelevator.Application.currency;

public class UserInterface {
    private Scanner scanner;

    public UserInterface(Scanner scanner) {
        this.scanner = scanner;
    }
    public void displayMainMenu() {
        System.out.println("(1) Display Vending Machine Items");
        System.out.println("(2) Purchase");
        System.out.println("(3) Exit");
    }

    public void displayPurchaseMenu(BigDecimal balance) {
        System.out.println("\nCurrent Balance: " + currency.format(balance));
        System.out.println("(1) Feed Money");
        System.out.println("(2) Select Product");
        System.out.println("(3) Finish Transaction");
    }

    public BigDecimal promptForMoney() {
        System.out.print("Enter the amount of money to feed: ");
        BigDecimal amount = scanner.nextBigDecimal();
        scanner.nextLine();
        return amount;
    }

    public void displayCurrentBalance(BigDecimal balance){
        System.out.println("\nCurrent Balance: " + currency.format(balance));
    }

    public String promptForSlotSelection() {
        System.out.print("Enter the slot number: ");
        return scanner.nextLine().toUpperCase();
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
}
