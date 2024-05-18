package com.techelevator;

public class Chip extends Product{


    public Chip(String slotLocation, String productName, double productPrice, String productType) {
        super(slotLocation, productName, productPrice, productType);
    }

    @Override
    public String toString(){
        return "Crunch Crunch, Yum!";
    }
}

