package com.techelevator.vendingmachine;

import com.techelevator.util.ConsoleColors;

import java.math.BigDecimal;
import java.util.*;

import static com.techelevator.Application.currency;

public class UserInterface {

    private Scanner scanner;

    public UserInterface(Scanner scanner) {
        this.scanner = scanner;
    }

    public String displayMainMenu() {
        System.out.println(ConsoleColors.BLUE + "     (1) Display Vending Machine Items");
        System.out.println("     (2) Purchase");
        System.out.println(ConsoleColors.RED + "     (3) Exit" + ConsoleColors.RESET);
        printUnderline(50);
        return scanner.nextLine();

    }

    public void displayPurchaseMenu(BigDecimal balance) {

        displayCurrentBalance(balance);
        System.out.println(ConsoleColors.BLUE + "     (1) Feed Money");
        System.out.println("     (2) Select Product" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.RED + "     (3) Finish Transaction" + ConsoleColors.RESET);
        printUnderline(50);
    }

    public void displayCurrentBalance(BigDecimal balance) {
        System.out.println(ConsoleColors.GREEN + "\n     Current Balance: " + currency.format(balance) + ConsoleColors.RESET);
    }

    public void displayInventory(Map<Product, Integer> products) {
        TreeMap<Product, Integer> sortedProducts = new TreeMap<>(Comparator.comparing(Product::getSlotLocation));
        sortedProducts.putAll(products);
//        List<Map.Entry<Product, Integer>> sortedProducts = new ArrayList<>(products.entrySet());
//        sortedProducts.sort(Comparator.comparing(entry -> entry.getKey().getSlotLocation()));
        for (Map.Entry<Product, Integer> entry : sortedProducts.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            System.out.println(ConsoleColors.YELLOW + product.getSlotLocation() + " | " +
                    product.getProductName() + " | " +
                    (quantity == 0 ?  ConsoleColors.RED_UNDERLINED + ConsoleColors.RED_BOLD_BRIGHT + "Sold Out" + ConsoleColors.RESET: currency.format(product.getProductPrice()) + " | Items left: " + quantity) + ConsoleColors.RESET);
        }
        printUnderline(50);
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public BigDecimal promptForMoney() {
        System.out.print(ConsoleColors.CYAN+"Enter the amount of money to feed: "+ ConsoleColors.RESET);
        BigDecimal amount = scanner.nextBigDecimal();
        scanner.nextLine();
        return amount;
    }

    public void printUnderline(int length){
        System.out.println(ConsoleColors.WHITE_UNDERLINED + " ".repeat(length) + ConsoleColors.RESET);
        System.out.println();
    }
    public String promptForSlotSelection() {
        System.out.print("Enter the slot number: ");
        return scanner.nextLine().toUpperCase();
    }

    public String promptUser(String message) {
        System.out.print(ConsoleColors.CYAN + message + ConsoleColors.RESET);
        return scanner.nextLine();
    }


}
