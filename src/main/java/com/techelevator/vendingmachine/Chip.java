package com.techelevator.vendingmachine;

import java.math.BigDecimal;

public class Chip extends Product {


    public Chip(String slotLocation, String productName, BigDecimal productPrice, String productType) {
        super(slotLocation, productName, productPrice, productType);
    }

    @Override
    public String toString(){
        return "Crunch Crunch, Yum!";
    }
}

