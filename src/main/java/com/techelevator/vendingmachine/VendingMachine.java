package com.techelevator.vendingmachine;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Scanner;

public class VendingMachine {
    private BigDecimal balance = BigDecimal.ZERO;
    private SalesReport salesReport;
    private ProductInventory productInventory;
    private PurchaseManager purchaseManager;
    private UserInterface ui;
    private ChangeDispenser changeDispenser;


    public VendingMachine(Scanner scanner) {
        this.productInventory = new ProductInventory();
        this.ui = new UserInterface(scanner);
        this.salesReport = new SalesReport();
        this.changeDispenser = new ChangeDispenser();
        this.purchaseManager = new PurchaseManager(salesReport);
    }

    public void purchaseProduct(String slotLocation) {
        Product product = productInventory.getProductBySlot(slotLocation);
        BigDecimal updatedBalance = purchaseManager.purchaseProduct(product, balance, productInventory);
        setBalance(updatedBalance);

    }

    public void giveChange() {
        ui.displayMessage(changeDispenser.giveChange(balance));
        setBalance(BigDecimal.ZERO);
    }

    private void addToSalesReport(String name, BigDecimal price) {
        salesReport.addToSalesReport(name, price);
    }

    public void getSalesReport() {
        salesReport.getSalesReport();
    }

    public void loadInventory(String filePath) throws FileNotFoundException {
        try {
            productInventory.loadInventoryFromFile(filePath);
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }

    }

    public void displayInventory() {
        ui.displayInventory(productInventory.getProducts());
    }

    public void displayPurchaseMenu() {
        ui.displayPurchaseMenu(balance);
    }

    public String displayMainMenu() {
        return ui.displayMainMenu();
    }

    public void displayMessage(String message) {
        ui.displayMessage(message);

    }

    public void displayCurrentBalance() {
        ui.displayCurrentBalance(balance);
    }

    public BigDecimal promptForMoney() {
        return ui.promptForMoney();
    }

    public String promptForSlotLocation() {
        return ui.promptForSlotSelection();
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

//    public boolean isSlotValid(String slot) {
//        Product product = productInventory.getProductBySlot(slot);
//        return product != null;
//    }

}

