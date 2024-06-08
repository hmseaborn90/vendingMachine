package com.techelevator.vendingmachine;

import com.techelevator.util.BalanceInsufficientException;
import com.techelevator.util.InvalidSlotLocationException;
import com.techelevator.util.Logger;
import com.techelevator.util.ProductOutOfStockException;

import java.math.BigDecimal;

import static com.techelevator.Application.currency;

public class PurchaseManager {

    private ProductInventory productInventory;
    private BigDecimal balance;
    private SalesReport salesReport;

    public PurchaseManager(SalesReport salesReport){
        this.salesReport = salesReport;
    }

    public BigDecimal purchaseProduct(Product product, BigDecimal balance, ProductInventory productInventory) {
//        Product product = productInventory.getProductBySlot(slotLocation);
        BigDecimal updatedBalance = null;
        try {
            if (isPurchaseValid(product, balance, productInventory)) {
                updatedBalance = performPurchase(product, balance, productInventory);
            }
        } catch (ProductOutOfStockException | BalanceInsufficientException | InvalidSlotLocationException e) {
            System.err.println(e.getMessage());
        }
        return updatedBalance;
    }

    public boolean isPurchaseValid(Product product, BigDecimal balance, ProductInventory productInventory) throws ProductOutOfStockException, BalanceInsufficientException, InvalidSlotLocationException {
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

    public BigDecimal performPurchase(Product product,BigDecimal balance, ProductInventory productInventory) {
        int quantity = productInventory.getProducts().getOrDefault(product, 0);
        BigDecimal updatedBalance = balance.subtract(product.getProductPrice());

        productInventory.getProducts().put(product, quantity - 1);
        salesReport.addToSalesReport(product.getProductName(), product.getProductPrice());
        String balanceRemaining = currency.format(updatedBalance);
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
        return updatedBalance;
    }
}
