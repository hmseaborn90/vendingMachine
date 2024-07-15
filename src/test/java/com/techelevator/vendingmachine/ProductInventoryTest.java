package com.techelevator.vendingmachine;

import com.techelevator.exceptions.InvalidProductTypeException;
import com.techelevator.exceptions.InvalidSlotLocationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.FileNotFoundException;
import java.util.Map;

public class ProductInventoryTest {

    private ProductInventory productInventory;

    @Before
    public void setUp() {
        productInventory = new ProductInventory();
    }

    @Test
    public void testLoadInventoryFromFile() throws FileNotFoundException, InvalidProductTypeException {
        String filePath = "vendingmachine.csv";

        productInventory.loadInventoryFromFile(filePath);

        Map<Product, Integer> products = productInventory.getProducts();

        Assert.assertNotNull(products);
    }

    @Test
    public void testGetProductBySlot() throws FileNotFoundException, InvalidSlotLocationException, InvalidProductTypeException {
        String filePath = "vendingmachine.csv";
        productInventory.loadInventoryFromFile(filePath);

        String slotLocation = "A4";

        Product product = productInventory.getProductBySlot(slotLocation);

        Assert.assertEquals("Invalid slot location",slotLocation, product.getSlotLocation());
    }

    @Test
    public void testGetProductBySlotInvalidSlotLocation() throws FileNotFoundException, InvalidProductTypeException {
        String filePath = "vendingmachine.csv";
        productInventory.loadInventoryFromFile(filePath);

        String invalidSlotLocation = "Z9";

        try {
            Product product = productInventory.getProductBySlot(invalidSlotLocation);
        } catch (InvalidSlotLocationException e) {

            return;
        }


        Assert.assertNull("Expected InvalidSlotLocationException to be thrown", null);
    }
}