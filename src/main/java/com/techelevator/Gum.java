package com.techelevator;

public class Gum extends Product{

    public Gum(String slotLocation, String productName, double productPrice, String productType) {
        super(slotLocation, productName, productPrice, productType);
    }

    @Override
    public String toString(){
        return "Chew Chew, Yum!";
    }
}
