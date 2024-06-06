package com.techelevator.util;

public class BalanceInsufficientException extends RuntimeException{
    public BalanceInsufficientException(String message){
        super(message);
    }
}
