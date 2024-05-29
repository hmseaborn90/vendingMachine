package com.techelevator;

import java.io.IOException;
import java.util.Scanner;

public class Application  {
	private static Scanner userInput = new Scanner(System.in);

	public static void main(String[] args) {
		VendingMachine vendingMachine = new VendingMachine();
		vendingMachine.loadInventoryFromFile("vendingmachine.csv");
		vendingMachine.displayMainMenu();

		while (true) {
			String userChoice = userInput.nextLine();
			switch (userChoice) {
				case "1":
					vendingMachine.displayInventory();
					break;

				case "2":
					// Display Purchase Menu
					while (true) {
						vendingMachine.displayInventory();
						vendingMachine.displayPurchaseMenu();
						String purchaseMenuChoice = userInput.nextLine();
						switch (purchaseMenuChoice) {
							case "1":
								while (true) {
									System.out.println("Feed money");
									System.out.println("Insert money amount");
									String value = userInput.nextLine();
									vendingMachine.feedMoney(Double.parseDouble(value));
									System.out.println("Current Money Provided: "+vendingMachine.getBalance());
									System.out.println("Do you want to add more money? (Y/N)");
									String addMoreMoney = userInput.nextLine();

									if (!addMoreMoney.equalsIgnoreCase("Y")) {
										break;
									}
								}
								break;

							case "2":
								vendingMachine.displayInventory();
								System.out.println("Enter slot location");
								String productSlot = userInput.nextLine();
								vendingMachine.purchaseProduct(productSlot);
								break; // Proceed to the Purchase

							case "3":
								break; // Exit
						}
						
					}

				case "3":
					System.out.println("Goodbye");
					System.exit(0);
			}
		}
	}
}