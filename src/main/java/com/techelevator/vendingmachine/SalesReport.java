package com.techelevator.vendingmachine;

import com.techelevator.util.ConsoleColors;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static com.techelevator.Application.currency;

public class SalesReport {
    private BigDecimal totalSales = BigDecimal.ZERO;
    private Map<String, Integer> productsSold = new HashMap<>();

    public void addToSalesReport(String name, BigDecimal price) {
        totalSales = totalSales.add(price);
        productsSold.put(name, productsSold.getOrDefault(name, 0) + 1);
    }

    public void getSalesReport() {
        for (Map.Entry<String, Integer> entry : productsSold.entrySet()) {
            System.out.println(entry.getKey() + "|" + entry.getValue());
        }
        System.out.println(ConsoleColors.GREEN_BRIGHT + "     ***TOTAL SALES*** " + currency.format(totalSales));
    }

}
