package com.techelevator.vendingmachine;

import java.math.BigDecimal;

public class Gum extends Product {

    public Gum(String slotLocation, String productName, BigDecimal productPrice, String productType) {
        super(slotLocation, productName, productPrice, productType);
    }

    @Override
    public String toString(){
        return "Chew Chew, Yum!";
    }
}
