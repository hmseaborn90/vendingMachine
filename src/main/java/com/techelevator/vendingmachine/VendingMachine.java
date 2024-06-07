package com.techelevator.vendingmachine;

import com.techelevator.util.BalanceInsufficientException;
import com.techelevator.util.InvalidSlotLocationException;
import com.techelevator.util.Logger;
import com.techelevator.util.ProductOutOfStockException;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


import static com.techelevator.Application.currency;

public class VendingMachine {
    private BigDecimal totalSales = BigDecimal.ZERO;
    private BigDecimal balance = BigDecimal.ZERO;
    private Map<String, Integer> productsSold = new HashMap<>();
    private ProductInventory productInventory;
    private UserInterface ui;


    public VendingMachine(ProductInventory productInventory) {
        this.productInventory = productInventory;
        this.ui = new UserInterface();
    }

    public void purchaseProduct(String slotLocation) throws InvalidSlotLocationException {
        Product product = productInventory.getProducts().get(slotLocation);

        if (product == null) {
            throw new InvalidSlotLocationException("Invalid Slot location enter valid slot location");
        }
        try {
            if (isPurchaseValid(product)) {
                performPurchase(product);
            }
        } catch (ProductOutOfStockException | BalanceInsufficientException e) {
            System.err.println(e.getMessage());
        }

    }

    private boolean isPurchaseValid(Product product) throws ProductOutOfStockException, BalanceInsufficientException {
        int quantity = productInventory.getProductQuantities().getOrDefault(product.getSlotLocation(), 0);

        if (quantity == 0) {
            throw new ProductOutOfStockException("Sorry, this item is currently sold out. Please make another selection.");
        }
        if (product.getProductPrice().compareTo(balance) >= 0) {
            throw new BalanceInsufficientException("Your balance is insufficient for this product. Please insert more money.");
        }
        return true;

    }

    private void performPurchase(Product product) {
        int quantity = productInventory.getProductQuantities().getOrDefault(product.getSlotLocation(), 0);
        purchase(product.getProductPrice());
        totalSales = totalSales.add(product.getProductPrice());
        productInventory.getProductQuantities().put(product.getSlotLocation(), quantity - 1);
        addToProductsSold(product.getProductName());
        String balanceRemaining = currency.format(balance);
        String logMessage = String.format("%s %s %s",
                product.getProductName(),
                product.getSlotLocation(),
                currency.format(product.getProductPrice()));
        System.out.println("Balance: " + balanceRemaining);
        System.out.println("Name: " +
                product.getProductName() +
                " Price: " +
                currency.format(product.getProductPrice()) + " " + product);
        Logger.log(logMessage + " " + balanceRemaining);
    }

    public void giveChange() {
        BigDecimal balanceInCents = balance.multiply(new BigDecimal("100"));
        BigDecimal quarters = balanceInCents.divide(new BigDecimal("25"), 0, RoundingMode.DOWN);
        BigDecimal remainder = balanceInCents.remainder(new BigDecimal("25"));
        BigDecimal dimes = remainder.divide(new BigDecimal("10"), 0, RoundingMode.DOWN);
        remainder = remainder.remainder(new BigDecimal("10"));
        BigDecimal nickels = remainder.divide(new BigDecimal("5"), 0, RoundingMode.DOWN);

        System.out.println("Change returned " + currency.format(balance));
        System.out.println("Quarters: " + quarters);
        System.out.println("Dimes: " + dimes);
        System.out.println("Nickels: " + nickels);

        String logMessage = String.format("GIVE CHANGE: %s $0.00", currency.format(balance));
        Logger.log(logMessage);

        balance = BigDecimal.ZERO;
    }

    private void addToProductsSold(String name) {
        productsSold.put(name, productsSold.getOrDefault(name, 0) + 1);
    }

    public void getSalesReport() {
        for (Map.Entry<String, Integer> entry : productsSold.entrySet()) {
            System.out.println(entry.getKey() + "|" + entry.getValue());
        }
        System.out.println("**TOTAL SALES** " + currency.format(totalSales));
    }

    public void loadInventory(String filePath) throws FileNotFoundException {
        try {
            productInventory.loadInventoryFromFile(filePath);
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }

    }

    public void displayInventory() {
        productInventory.displayInventory();
    }

    public void displayPurchaseMenu() {
        ui.displayPurchaseMenu(balance);
    }

    public void displayMainMenu() {
        ui.displayMainMenu();
    }
    public String promptForSlotLocation(){
        return ui.promptForSlotSelection();
    }
    public void displayMessage(String message){
        ui.displayMessage(message);
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void feedMoney(BigDecimal amountFed) {
        balance = balance.add(amountFed);
    }

    public void purchase(BigDecimal amountToWithdraw) {
        balance = balance.subtract(amountToWithdraw);
        totalSales = totalSales.add(amountToWithdraw);
    }
}

//    public static void loadInventoryFromFile(String filePath) throws FileNotFoundException {
//        File csvFile = new File(filePath);
//        try (Scanner fileInput = new Scanner(csvFile)) {
//            while (fileInput.hasNextLine()) {
//                String[] productData = fileInput.nextLine().split("\\|");
//                String productSlotNumber = productData[0];
//                String productName = productData[1];
//                try {
//                    BigDecimal productPrice = new BigDecimal(productData[2]);
//                    String productType = productData[3];
//                    int productQuantity = 5;
//                    if (productType.equalsIgnoreCase("Chip")) {
//                        products.put(productSlotNumber, new Chip(productSlotNumber, productName, productPrice, productType));
//                    }
//                    if (productType.equalsIgnoreCase("Candy")) {
//                        products.put(productSlotNumber, new Candy(productSlotNumber, productName, productPrice, productType));
//                    }
//                    if (productType.equalsIgnoreCase("Drink")) {
//                        products.put(productSlotNumber, new Candy.Drink(productSlotNumber, productName, productPrice, productType));
//                    }
//                    if (productType.equalsIgnoreCase("Gum")) {
//                        products.put(productSlotNumber, new Gum(productSlotNumber, productName, productPrice, productType));
//                    }
//                    productQuantities.put(productSlotNumber, productQuantity);
//                } catch (NumberFormatException e) {
//                    System.err.println("Error parsing product price for: " + productName);
//                }
//
//            }
//        } catch (FileNotFoundException e) {
//            System.err.println("The file was not found " + csvFile.getAbsolutePath());
//        } catch (Exception e) {
//            System.err.println("An error occurred while loading inventory from file: " + e.getMessage());
//        }
//
//    }

//    public void displayInventory() {
//        for (Map.Entry<String, Product> entry : products.entrySet()) {
//            String slotLocation = entry.getKey();
//            Product product = entry.getValue();
//            int quantity = productQuantities.getOrDefault(slotLocation, 0);
//            if (quantity == 0) {
//                System.out.println(slotLocation + " | " + "Sold Out");
//            } else {
//                System.out.println(slotLocation + " | " + product.getProductName() + " | " + currency.format(product.getProductPrice()));
//            }
//        }
//    }
