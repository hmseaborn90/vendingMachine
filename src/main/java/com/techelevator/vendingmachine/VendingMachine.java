package com.techelevator.vendingmachine;

import com.techelevator.util.BalanceInsufficientException;
import com.techelevator.util.InvalidSlotLocationException;
import com.techelevator.util.Logger;
import com.techelevator.util.ProductOutOfStockException;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Scanner;


import static com.techelevator.Application.currency;

public class VendingMachine {
    private BigDecimal balance = BigDecimal.ZERO;
    private SalesReport salesReport;
    private ProductInventory productInventory;
    private UserInterface ui;
    private ChangeDispenser changeDispenser;


    public VendingMachine(Scanner scanner) {
        this.productInventory = new ProductInventory();
        this.ui = new UserInterface(scanner);
        this.salesReport = new SalesReport();
        this.changeDispenser = new ChangeDispenser();
    }

    public void purchaseProduct(String slotLocation)  {
        Product product = productInventory.getProductBySlot(slotLocation);
        try {
            if (isPurchaseValid(product)) {
                performPurchase(product);
            }
        } catch (ProductOutOfStockException | BalanceInsufficientException | InvalidSlotLocationException e) {
            System.err.println(e.getMessage());
        }
    }

    private boolean isPurchaseValid(Product product) throws ProductOutOfStockException, BalanceInsufficientException, InvalidSlotLocationException {
        if(product == null){
            throw new InvalidSlotLocationException("Invalid slot location. Please select a valid slot");
        }
        int quantity = productInventory.getProducts().getOrDefault(product, 0);

        if (quantity == 0) {
            throw new ProductOutOfStockException("Sorry, this item is currently sold out. Please make another selection.");
        }
        if (product.getProductPrice().compareTo(balance) >= 0) {
            throw new BalanceInsufficientException("Your balance is insufficient for this product. Please insert more money.");
        }
        return true;

    }

    private void performPurchase(Product product) {
        int quantity = productInventory.getProducts().getOrDefault(product, 0);
        balance = balance.subtract(product.getProductPrice());
        productInventory.getProducts().put(product, quantity - 1);
        addToSalesReport(product.getProductName(), product.getProductPrice());
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
    public void displayCurrentBalance(){
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
//    public boolean isSlotValid(String slot){
//       Product product = productInventory.getProductBySlot(slot);
//        return product == null;
//    }

}

