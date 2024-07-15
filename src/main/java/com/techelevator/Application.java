package com.techelevator;

import com.techelevator.exceptions.InvalidAmountException;
import com.techelevator.exceptions.InvalidProductTypeException;
import com.techelevator.exceptions.InvalidSlotLocationException;
import com.techelevator.util.Logger;
import com.techelevator.vendingmachine.VendingMachine;


import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Scanner;

public class Application {
    private static Scanner userInput = new Scanner(System.in);
    public static NumberFormat currency = NumberFormat.getCurrencyInstance();

    public static void main(String[] args) {
        Logger.log("Vending Machine Started");
        VendingMachine vendingMachine = new VendingMachine(userInput);

        try {
            vendingMachine.loadInventory("vendingmachine.csv");
        } catch (FileNotFoundException | InvalidProductTypeException e) {
            System.err.println("Error: Inventory file not found"+ e.getMessage());
            return;
        }
        boolean isShouldExit = false;
        while (!isShouldExit) {

            String userChoice = vendingMachine.displayMainMenu();
            switch (userChoice) {
                case "1":
                    vendingMachine.displayInventory();
                    break;
                case "2":
                    handlePurchaseMenu(vendingMachine);
                    break;
                case "3":
                    vendingMachine.displayMessage("Goodbye");
                    isShouldExit = true;
                    break;
                case "4":
                    vendingMachine.getSalesReport();
                    break;
                default:
                    System.out.println("Invalid selection please try again");
            }
        }
        userInput.close();
    }


    private static void handlePurchaseMenu(VendingMachine vendingMachine) {

        boolean isFinished = false;

        while (!isFinished) {
            vendingMachine.displayMessage("");
            vendingMachine.displayInventory();
            vendingMachine.displayPurchaseMenu();
            String purchaseMenuChoice = userInput.nextLine();
            switch (purchaseMenuChoice) {
                case "1":
                    handleMoneyInput(vendingMachine);
                    break;
                case "2":
                    String productSlot = vendingMachine.promptForSlotLocation();
                        vendingMachine.purchaseProduct(productSlot);
                    break;
                case "3":
                    vendingMachine.giveChange();
                    isFinished = true;
                    break;
                default:
                    System.err.println("Invalid choice please try again");
                    break;
            }
        }
    }

    //    private static boolean isValidSlot(VendingMachine vendingMachine, String slot) {
//        return vendingMachine.isSlotValid(slot);
//    }
    private static void handleMoneyInput(VendingMachine vendingMachine) {
        while (true) {

            try {
                BigDecimal amount = vendingMachine.promptForMoney();

                if (amount.compareTo(new BigDecimal("1.00")) < 0) {
                    throw new InvalidAmountException("Sorry, we can only accept whole dollars at this time. Please insert an acceptable amount.");

                }
                vendingMachine.feedMoney(amount);
                vendingMachine.displayCurrentBalance();

                String logMessage = String.format("Feed Money: %s %s", currency.format(amount), currency.format(vendingMachine.getBalance()));
                Logger.log(logMessage);
            } catch (NumberFormatException e) {
                System.err.println("Invalid input please insert dollar amount or change to be added");
                continue;
            } catch (InvalidAmountException e){
                System.err.println(e.getMessage());
                continue;
            }
            String moreMoneyChoice = vendingMachine.promptUser("Do you want to add more money? (Y/N)");
            if (!moreMoneyChoice.equalsIgnoreCase("Y")) {
                break;
            }
        }
    }

}