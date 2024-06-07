package com.techelevator.vendingmachine;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import static com.techelevator.Application.currency;

public class ProductInventory {
    private Map<String, Product> products;
    private Map<String, Integer> productQuantities;


    public void loadInventoryFromFile(String filePath) throws FileNotFoundException {
        File csvFile = new File(filePath);
        try (Scanner fileInput = new Scanner(csvFile)) {
            while (fileInput.hasNextLine()) {
                String[] productData = fileInput.nextLine().split("\\|");
                String productSlotNumber = productData[0];
                String productName = productData[1];
                try {
                    BigDecimal productPrice = new BigDecimal(productData[2]);
                    String productType = productData[3];
                    int productQuantity = 5;
                    Product product = null;
                    if (productType.equalsIgnoreCase("Chip")) {
                        product = new Chip(productSlotNumber, productName, productPrice, productType);
                    } else if (productType.equalsIgnoreCase("Candy")) {
                        product = new Candy(productSlotNumber, productName, productPrice, productType);
                    } else if (productType.equalsIgnoreCase("Drink")) {
                        product = new Candy.Drink(productSlotNumber, productName, productPrice, productType);
                    } else if (productType.equalsIgnoreCase("Gum")) {
                        product = new Gum(productSlotNumber, productName, productPrice, productType);
                    }
                    if (product != null) {
                        products.put(productSlotNumber, product);
                        productQuantities.put(productSlotNumber, productQuantity);
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Error parsing product price for: " + productName);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("The file was not found " + csvFile.getAbsolutePath());
        } catch (Exception e) {
            System.err.println("An error occurred while loading inventory from file: " + e.getMessage());
        }
    }

    public void displayInventory() {
        for (Map.Entry<String, Product> entry : products.entrySet()) {
            String slotLocation = entry.getKey();
            Product product = entry.getValue();
            int quantity = productQuantities.getOrDefault(slotLocation, 0);
            if (quantity == 0) {
                System.out.println(slotLocation + " | " + "Sold Out");
            } else {
                System.out.println(slotLocation + " | " + product.getProductName() + " | " + currency.format(product.getProductPrice()));
            }
        }
    }
    public Map<String, Product> getProducts() {
        return products;
    }

    public void setProducts(Map<String, Product> products) {
        this.products = products;
    }

    public Map<String, Integer> getProductQuantities() {
        return productQuantities;
    }

    public void setProductQuantities(Map<String, Integer> productQuantities) {
        this.productQuantities = productQuantities;
    }

    public ProductInventory() {
        this.products = new TreeMap<>();
        this.productQuantities = new HashMap<>();
    }
}
