package com.techelevator;

import com.techelevator.util.Logger;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Scanner;

public class Application {
    private static Scanner userInput = new Scanner(System.in);
    public static NumberFormat currency = NumberFormat.getCurrencyInstance();

    public static void main(String[] args) {
        Logger.log("Vending Machine Started");

        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.loadInventoryFromFile("vendingmachine.csv");

        boolean isShouldExit = false;
        while (!isShouldExit) {
            vendingMachine.displayMainMenu();
            String userChoice = userInput.nextLine();
            switch (userChoice) {
                case "1":
                    vendingMachine.displayInventory();
                    break;
                case "2":
                    handlePurchaseMenu(vendingMachine);
                    break;
                case "3":
                    System.out.println("Goodbye");
                    isShouldExit = true;
                    break;
                case "4":
                    vendingMachine.getSalesReport();
                    break;
                default:
                    System.out.println("Invalid selection please try again");
            }
        }
    }

    private static void handlePurchaseMenu(VendingMachine vendingMachine) {

        boolean isFinished = false;

        while (!isFinished) {
            vendingMachine.displayInventory();
            vendingMachine.displayPurchaseMenu();
            String purchaseMenuChoice = userInput.nextLine();
            switch (purchaseMenuChoice) {
                case "1":
                    handleMoneyInput(vendingMachine);
                    break;
                case "2":
                    System.out.println("Enter slot location");
                    String productSlot = userInput.nextLine();
                    vendingMachine.purchaseProduct(productSlot.toUpperCase());
                    break;
                case "3":
                    vendingMachine.giveChange();
                    isFinished = true;
                    break;
                default:
                    System.out.println("Invalid choice please try again");
                    break;
            }
        }
    }

    private static void handleMoneyInput(VendingMachine vendingMachine) {
        while (true) {
            System.out.println("Feed money");
            System.out.println("Insert money amount");

            try {
                String value = userInput.nextLine();
                double amount = Double.parseDouble(value);
                if (amount < 1.00) {
                    System.out.println("Sorry we can only accept whole dollars at this time please insert acceptable amount");
                    continue;
                }
                vendingMachine.feedMoney(amount);
                System.out.println("Current Money Provided: " + currency.format(vendingMachine.getBalance()));
                String logMessage = String.format("Feed Money: %s %s", currency.format(amount), currency.format(vendingMachine.getBalance()));
                Logger.log(logMessage);
            } catch (NumberFormatException e) {
                System.err.println("Invalid input please insert dollar amount or change to be added");
                continue;
            }
            System.out.println("Do you want to add more money? (Y/N)");
            String moreMoneyChoice = userInput.nextLine();
            if (!moreMoneyChoice.equalsIgnoreCase("Y")) {
                break;
            }
        }
    }
}