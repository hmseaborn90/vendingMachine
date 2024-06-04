package com.techelevator;

import java.util.ArrayList;
import java.util.List;

public class ProductInventory {
    private List<Product> products;

    public ProductInventory(){
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product){
        products.add(product);
    }

    public List<Product> getAllProducts(){
        return products;
    }

    public void purchaseDisplayMenu(double balance){
        System.out.println(
                "Current Money Provided: " + balance+"\n" +
                "    > (1) FeedMoney\n" +
                "    > (2) Select Product\n" +
                "    > (3) Finish Transaction");
    }

    public void displayInventory() {
        for (Product product : products) {
            if(product.getProductQuantity() == 0){
                System.out.println(product.getSlotLocation() + " | " + "Sold Out");
            }else{
                System.out.println(product.getSlotLocation() + " | " + product.getProductName() + " | " + product.getProductPrice());
            }


        }
    }

    public Product getProductBySlot(String slotLocation){
        for (Product product : products){
            if(product.getSlotLocation().equalsIgnoreCase(slotLocation)){
                return product;
            }
        }
        return null;
    }


}
