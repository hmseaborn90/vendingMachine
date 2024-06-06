package com.techelevator.vendingmachine;

import java.math.BigDecimal;

public class Product {
    private String slotLocation;
    private String productName;
    private BigDecimal productPrice;
    private String productType;


    public Product(String slotLocation, String productName, BigDecimal productPrice, String productType) {
        this.slotLocation = slotLocation;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productType = productType;

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

    public String getProductName() {
        return productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }


}
