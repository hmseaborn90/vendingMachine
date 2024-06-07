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
//    private Map<String, Integer> productsSold = new HashMap<>();
    private SalesReport salesReport;
    private ProductInventory productInventory;
    private UserInterface ui;
    private ChangeDispenser changeDispenser;


    public VendingMachine(ProductInventory productInventory, Scanner scanner) {
        this.productInventory = productInventory;
        this.ui = new UserInterface(scanner);
        this.salesReport = new SalesReport();
        this.changeDispenser = new ChangeDispenser();
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
        addToSalesReport(product.getProductName());
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
        changeDispenser.giveChange(balance);
        setBalance(BigDecimal.ZERO);
    }

    private void addToSalesReport(String name) {
        salesReport.addToSalesReport(name);
    }

    public void getSalesReport() {
        salesReport.getSalesReport(totalSales);
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

    public void purchase(BigDecimal amountToWithdraw) {
        balance = balance.subtract(amountToWithdraw);
        totalSales = totalSales.add(amountToWithdraw);
    }
}

