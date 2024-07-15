package com.techelevator.vendingmachine;

import com.techelevator.util.ConsoleColors;
import com.techelevator.util.Logger;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.techelevator.Application.currency;

public class ChangeDispenser {

    public String giveChange(BigDecimal balance) {
        BigDecimal balanceInCents = balance.multiply(new BigDecimal("100"));
        BigDecimal quarters = balanceInCents.divide(new BigDecimal("25"), 0, RoundingMode.DOWN);
        BigDecimal remainder = balanceInCents.remainder(new BigDecimal("25"));
        BigDecimal dimes = remainder.divide(new BigDecimal("10"), 0, RoundingMode.DOWN);
        remainder = remainder.remainder(new BigDecimal("10"));
        BigDecimal nickels = remainder.divide(new BigDecimal("5"), 0, RoundingMode.DOWN);
        String formattedString = String.format(ConsoleColors.GREEN + "     Change returned %s\n     Quarters: %d\n     Dimes: %d\n     Nickels: %d\n",
                currency.format(balance), quarters.intValue(), dimes.intValue(), nickels.intValue());


        String logMessage = String.format("GIVE CHANGE: %s $0.00", currency.format(balance));
        Logger.log(logMessage);
        return formattedString;

    }
}
