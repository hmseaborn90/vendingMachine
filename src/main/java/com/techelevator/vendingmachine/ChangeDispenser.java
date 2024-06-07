package com.techelevator.vendingmachine;

import com.techelevator.util.Logger;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.techelevator.Application.currency;

public class ChangeDispenser {

    public void giveChange(BigDecimal balance) {
        BigDecimal balanceInCents = balance.multiply(new BigDecimal("100"));
        BigDecimal quarters = balanceInCents.divide(new BigDecimal("25"), 0, RoundingMode.DOWN);
        BigDecimal remainder = balanceInCents.remainder(new BigDecimal("25"));
        BigDecimal dimes = remainder.divide(new BigDecimal("10"), 0, RoundingMode.DOWN);
        remainder = remainder.remainder(new BigDecimal("10"));
        BigDecimal nickels = remainder.divide(new BigDecimal("5"), 0, RoundingMode.DOWN);

        System.out.println("Change returned " + currency.format(balance));
        System.out.println("Quarters: " + quarters);
        System.out.println("Dimes: " + dimes);
        System.out.println("Nickels: " + nickels);

        String logMessage = String.format("GIVE CHANGE: %s $0.00", currency.format(balance));
        Logger.log(logMessage);

    }
}
