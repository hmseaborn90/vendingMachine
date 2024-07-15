package com.techelevator.vendingmachine;

import com.techelevator.exceptions.InvalidProductTypeException;
import com.techelevator.exceptions.InvalidSlotLocationException;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ProductInventory {
    //    private Map<String, Product> products;
    private Map<Product, Integer> products;

    public ProductInventory() {
        this.products = new HashMap<>();

    }

    public void loadInventoryFromFile(String filePath) throws FileNotFoundException, InvalidProductTypeException{
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
                    switch (productType.toLowerCase()) {
                        case "chip":
                            product = new Chip(productSlotNumber, productName, productPrice, productType);
                            break;
                        case "candy":
                            product = new Candy(productSlotNumber, productName, productPrice, productType);
                            break;
                        case "drink":
                            product = new Drink(productSlotNumber, productName, productPrice, productType);
                            break;
                        case "gum":
                            product = new Gum(productSlotNumber, productName, productPrice, productType);
                            break;
                        default:
                            throw new InvalidProductTypeException("Invalid product type: " + productType);
                    }
                    products.put(product, productQuantity);

                } catch (NumberFormatException e) {
                    throw new NumberFormatException("Invalid product price for "+ productName + e.getMessage());
                } catch (InvalidProductTypeException e){
                    throw new InvalidProductTypeException("incorrect product type found " + e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("The file was not found " + csvFile.getAbsolutePath());
        }
    }


    public Product getProductBySlot(String slotLocation) throws InvalidSlotLocationException {
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            if (product.getSlotLocation().equalsIgnoreCase(slotLocation)) {
                return product;
            }
        }
        return null;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }




}
