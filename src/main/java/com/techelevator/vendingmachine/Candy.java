package com.techelevator.vendingmachine;

import java.math.BigDecimal;

public class Candy extends Product {


    public Candy(String slotLocation, String productName, BigDecimal productPrice, String productType) {
        super(slotLocation, productName, productPrice, productType);
    }

    @Override
    public String toString(){
        return "Munch Munch, Yum!";
    }

}
