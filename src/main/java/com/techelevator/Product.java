package com.techelevator;

public class Product {
    private String slotLocation;
    private String productName;
    private double productPrice;
    private String productType;
    private int productQuantity;


    public Product(String slotLocation, String productName, double productPrice, String productType) {
        this.slotLocation = slotLocation;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productType = productType;
        this.productQuantity = 5;
    }

    public String getSlotLocation() {
        return slotLocation;
    }

    public void setSlotLocation(String slotLocation) {
        this.slotLocation = slotLocation;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getProductName(){
        return productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }
    public void decreaseProductQuantity(){
        productQuantity -= 1;
    }

}
