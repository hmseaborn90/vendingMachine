package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class VendingMachine {
    private double balance;

    ProductInventory inventory = new ProductInventory();

    public void purchaseProduct(String slotLocation) {
        Product product = inventory.getProductBySlot(slotLocation);
        if (product.getProductPrice() <= balance) {
            withdraw(product.getProductPrice());
            System.out.println("Balance:" + balance);
            System.out.println("Name: " + product.getProductName() + "Price: " + product.getProductPrice() + " " + product);
            inventory.displayInventory();
        }

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
                    inventory.addProduct(new Chip(productSlotNumber, productName, productPrice, productType));
                }
                if (productType.equalsIgnoreCase("Candy")) {
                    inventory.addProduct(new Candy(productSlotNumber, productName, productPrice, productType));
                }
                if (productType.equalsIgnoreCase("Drink")) {
                    inventory.addProduct(new Drink(productSlotNumber, productName, productPrice, productType));
                }
                if (productType.equalsIgnoreCase("Gum")) {
                    inventory.addProduct(new Gum(productSlotNumber, productName, productPrice, productType));
                }

            }
        } catch (FileNotFoundException e) {
            System.err.println("The file was not found " + csvFile.getAbsolutePath());
        }

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

    public void withdraw(double amountToWithdraw) {
        balance -= amountToWithdraw;
    }

    public void displayInventory() {
        inventory.displayInventory();
    }

    public void displayPurchaseMenu() {
        inventory.purchaseDisplayMenu(balance);
    }

    public void displayMainMenu() {
        System.out.println(
                "    > (1) Display Vending Machine Items\n" +
                "    > (2) Purchase\n" +
                "    > (3) Exit");
    }
}
