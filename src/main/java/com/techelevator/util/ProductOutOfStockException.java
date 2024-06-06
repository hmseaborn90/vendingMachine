package com.techelevator.util;

public class ProductOutOfStockException extends RuntimeException {

    public ProductOutOfStockException(String message){
        super(message);
    }
}
