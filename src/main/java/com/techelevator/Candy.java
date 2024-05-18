package com.techelevator;

public class Candy extends Product{


    public Candy(String slotLocation, String productName, double productPrice, String productType) {
        super(slotLocation, productName, productPrice, productType);
    }

    @Override
    public String toString(){
        return "Munch Munch, Yum!";
    }
}
