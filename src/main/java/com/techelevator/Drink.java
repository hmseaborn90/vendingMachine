package com.techelevator;

public class Drink extends Product{


    public Drink(String slotLocation, String productName, double productPrice, String productType) {
        super(slotLocation, productName, productPrice, productType);
    }

    @Override
    public String toString(){
        return "Glug Glug, Yum!";
    }
}
