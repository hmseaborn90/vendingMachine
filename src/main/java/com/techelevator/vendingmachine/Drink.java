package com.techelevator.vendingmachine;

import java.math.BigDecimal;

public class Drink extends Product{

        public Drink(String slotLocation, String productName, BigDecimal productPrice, String productType) {
            super(slotLocation, productName, productPrice, productType);
        }

        @Override
        public String toString(){
            return "Glug Glug, Yum!";
        }
    }

