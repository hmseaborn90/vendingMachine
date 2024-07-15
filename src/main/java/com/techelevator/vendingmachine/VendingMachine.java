package com.techelevator.vendingmachine;

import com.techelevator.exceptions.InvalidProductTypeException;
import com.techelevator.exceptions.InvalidSlotLocationException;

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

    public void getSalesReport() {
        salesReport.getSalesReport();
    }

    public void loadInventory(String filePath) throws FileNotFoundException, InvalidProductTypeException {
        try {
            productInventory.loadInventoryFromFile(filePath);
        } catch (FileNotFoundException | InvalidProductTypeException e) {
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
    public String promptUser(String message){
        return ui.promptUser(message);
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

}

