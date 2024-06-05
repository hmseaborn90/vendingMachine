package com.techelevator;

import com.techelevator.util.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static com.techelevator.Application.currency;


public class VendingMachine {
    private double totalSales = 0;
    private double balance;
    private Map<String, Integer> productsSold = new HashMap<>();
    ProductInventory productInventory = new ProductInventory();


    public void purchaseProduct(String slotLocation) {
        Product product = productInventory.getProductBySlot(slotLocation);
        if (product == null) {
            System.out.println("Invalid Slot location enter valid slot location");
        } else {
            if (isPurchaseValid(product)) {
                performPurchase(product);
            }
        }
    }

    private boolean isPurchaseValid(Product product) {
        if (product.getProductPrice() <= balance) {
            if (product.getProductQuantity() == 0) {
                System.out.println("Sorry, this item is currently sold out. Please make another selection.");
                return false;
            }
            return true;
        } else {
            System.out.println("Your balance is insufficient for this product. Please insert more money.");
            return false;
        }
    }

    private void performPurchase(Product product) {
        purchase(product.getProductPrice());
        totalSales += product.getProductPrice();
        product.decreaseProductQuantity();
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
        productInventory.displayInventory();
    }
//    public void purchaseProduct(String slotLocation) {
//        Product product = productInventory.getProductBySlot(slotLocation);
//        if(product ==  null){
//            System.out.println("Invalid Slot location enter valid slot location");
//        }else{
//            if (product.getProductPrice() <= balance) {
//
//                if(product.getProductQuantity() == 0){
//                    System.out.println("sorry item is currently sold out please make another selection");
//                }else{
//                    purchase(product.getProductPrice());
//                    totalSales += product.getProductPrice();
//                    product.decreaseProductQuantity();
//                    addToProductsSold(product.getProductName());
//                    String balanceRemaining = currency.format(balance);
//                    String logMessage = String.format("%s %s %s", product.getProductName(), product.getSlotLocation(), currency.format(product.getProductPrice()));
//                    System.out.println("Balance:" + balanceRemaining);
//                    System.out.println("Name: " + product.getProductName() + " Price: " + currency.format(product.getProductPrice()) + " " + product);
//                    Logger.log(logMessage + " " + balanceRemaining);
//                    productInventory.displayInventory();
//                }
//
//            }else{
//                System.out.println("You're balance is insufficient for this product please insert more money");
//            }
//        }
//    }

    public void giveChange() {
        int balanceInCents = (int) (balance * 100);
        int quarters = balanceInCents / 25;
        int remainder = balanceInCents % 25;
        int dimes = remainder / 10;
        remainder %= 10;
        int nickels = remainder / 5;
        System.out.println("Change returned " + currency.format(balance));
        System.out.println("Quarters: " + quarters);
        System.out.println("Dimes: " + dimes);
        System.out.println("Nickels: " + nickels);
        String logMessage = String.format("GIVE CHANGE: %s $0.00", currency.format(balance));
        Logger.log(logMessage);
        balance = 0;
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

    public void loadInventoryFromFile(String filePath) {
        File csvFile = new File(filePath);
        try (Scanner fileInput = new Scanner(csvFile)) {
            while (fileInput.hasNextLine()) {
                String[] productData = fileInput.nextLine().split("\\|");
                String productSlotNumber = productData[0];
                String productName = productData[1];
                double productPrice = Double.parseDouble(productData[2]);
                String productType = productData[3];
                if (productType.equalsIgnoreCase("Chip")) {
                    productInventory.addProduct(new Chip(productSlotNumber, productName, productPrice, productType));
                }
                if (productType.equalsIgnoreCase("Candy")) {
                    productInventory.addProduct(new Candy(productSlotNumber, productName, productPrice, productType));
                }
                if (productType.equalsIgnoreCase("Drink")) {
                    productInventory.addProduct(new Drink(productSlotNumber, productName, productPrice, productType));
                }
                if (productType.equalsIgnoreCase("Gum")) {
                    productInventory.addProduct(new Gum(productSlotNumber, productName, productPrice, productType));
                }

            }
        } catch (FileNotFoundException e) {
            System.err.println("The file was not found " + csvFile.getAbsolutePath());
        }

    }

    public void displayInventory() {
        productInventory.displayInventory();
    }

    public void displayPurchaseMenu() {
        productInventory.purchaseDisplayMenu(balance);
    }

    public void displayMainMenu() {
        System.out.println(
                "    > (1) Display Vending Machine Items\n" +
                        "    > (2) Purchase\n" +
                        "    > (3) Exit");
    }


    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void feedMoney(double amountFed) {
        balance += amountFed;
    }

    public void purchase(double amountToWithdraw) {
        balance -= amountToWithdraw;
        totalSales += amountToWithdraw;
    }

}
