package com.techelevator.vendingmachine;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;


public class ChangeDispensarTest {
    private ChangeDispenser changeDispenser;

    @Before
    public void setUp() {

        this.changeDispenser = new ChangeDispenser();
    }

    @Test
    public void testNoGiveChange() {

        // Test for balance with no change
        BigDecimal balanceNoChange = BigDecimal.ZERO;
        String expectedNoChange = "Change returned $0.00\nQuarters: 0\nDimes: 0\nNickels: 0";
        String actualNoChange = changeDispenser.giveChange(balanceNoChange);
        Assert.assertEquals(expectedNoChange, actualNoChange);
    }

    @Test
    public void testOnlyQuartersGiven() {
        BigDecimal balanceWithChange = new BigDecimal("1.25");
        String expectedWithChange = "Change returned $1.25\nQuarters: 5\nDimes: 0\nNickels: 0";
        String actualWithChange = changeDispenser.giveChange(balanceWithChange);
        Assert.assertEquals(expectedWithChange, actualWithChange);
    }


    @Test
    public void testForCorrectDenonimations() {
        BigDecimal balanceWithLargeChange = new BigDecimal("3.90");
        String expectedWithLargeChange = "Change returned $3.90\nQuarters: 15\nDimes: 1\nNickels: 1";
        String actualWithLargeChange = changeDispenser.giveChange(balanceWithLargeChange);
        Assert.assertEquals(expectedWithLargeChange, actualWithLargeChange);
    }


}
