package com.techelevator.vendingmachine;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static com.techelevator.Application.currency;

public class SalesReport {
    private Map<String, Integer> productsSold = new HashMap<>();


    public SalesReport(){

    }

    public void addToSalesReport(String name) {
        productsSold.put(name, productsSold.getOrDefault(name, 0) + 1);
    }

    public void getSalesReport(BigDecimal totalSales) {
        for (Map.Entry<String, Integer> entry : productsSold.entrySet()) {
            System.out.println(entry.getKey() + "|" + entry.getValue());
        }
        System.out.println("**TOTAL SALES** " + currency.format(totalSales));
    }

}
