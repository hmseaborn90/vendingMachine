package com.techelevator;

import java.io.IOException;
import java.util.Scanner;

public class Application {
	private static Scanner userInput = new Scanner(System.in);

	public static void main(String[] args) {
//  $$\    $$\                        $$\                $$\      $$\          $$\    $$\                 $$$$$$\  $$$$$$\  $$$$$$\  $$$$$$\
//  $$ |   $$ |                       $$ |               $$$\    $$$ |         $$ |   \__|               $$  __$$\$$$ __$$\$$$ __$$\$$$ __$$\
//  $$ |   $$ |$$$$$$\ $$$$$$$\  $$$$$$$ |$$$$$$\        $$$$\  $$$$ |$$$$$$\$$$$$$\  $$\ $$$$$$$\       $$ /  $$ $$$$\ $$ $$$$\ $$ $$$$\ $$ |
//  \$$\  $$  $$  __$$\$$  __$$\$$  __$$ $$  __$$\       $$\$$\$$ $$ |\____$$\_$$  _| $$ $$  _____|      \$$$$$$$ $$\$$\$$ $$\$$\$$ $$\$$\$$ |
//   \$$\$$  /$$$$$$$$ $$ |  $$ $$ /  $$ $$ /  $$ |      $$ \$$$  $$ |$$$$$$$ |$$ |   $$ $$ /             \____$$ $$ \$$$$ $$ \$$$$ $$ \$$$$ |
//    \$$$  / $$   ____$$ |  $$ $$ |  $$ $$ |  $$ |      $$ |\$  /$$ $$  __$$ |$$ |$$\$$ $$ |            $$\   $$ $$ |\$$$ $$ |\$$$ $$ |\$$$ |
//     \$  /  \$$$$$$$\$$ |  $$ \$$$$$$$ \$$$$$$  |      $$ | \_/ $$ \$$$$$$$ |\$$$$  $$ \$$$$$$$\       \$$$$$$  \$$$$$$  \$$$$$$  \$$$$$$  /
//      \_/    \_______\__|  \__|\_______|\______/       \__|     \__|\_______| \____/\__|\_______|       \______/ \______/ \______/ \______/
//
//
//
		VendingMachine vendingMachine = new VendingMachine();
		vendingMachine.loadInventoryFromFile("vendingmachine.csv");

		boolean isShouldExit = false;
		while (!isShouldExit) {
			vendingMachine.displayMainMenu();
			String userChoice = userInput.nextLine();
			switch (userChoice) {
				case "1":
					vendingMachine.displayInventory();
					System.out.println("(2) purchase");
					break;

				case "2":
					// Display Purchase Menu
					handlePurchaseMenu(vendingMachine);
					break;
				case "3":
					System.out.println("Goodbye");
					isShouldExit = true;
					break;
				default:
					System.out.println("Invalid selection please try again");
			}
		}
	}

	private static void handlePurchaseMenu(VendingMachine vendingMachine) {

		boolean isFinished = false;

		while (!isFinished) {
			vendingMachine.displayInventory();
			vendingMachine.displayPurchaseMenu();
			String purchaseMenuChoice = userInput.nextLine();
			switch (purchaseMenuChoice) {
				case "1":
					handleMoneyInput(vendingMachine);
					break;
				case "2":
					System.out.println("Enter slot location");
					String productSlot = userInput.nextLine();
					vendingMachine.purchaseProduct(productSlot.toUpperCase());
					break;
				case "3":
					isFinished = true;
					break;
				default:
					System.out.println("Invalid choice please try again");
					break;
			}
		}
	}
	private static void handleMoneyInput(VendingMachine vendingMachine){
		while (true){
			System.out.println("Feed money");
			System.out.println("Insert money amount");

			try{
				String value = userInput.nextLine();
				double amount = Double.parseDouble(value);
				vendingMachine.feedMoney(amount);
				System.out.println("Current Money Provided: " + vendingMachine.getBalance());
			}catch(NumberFormatException e){
				System.err.println("Invalid input please insert dollar amount or change to be added");
				continue;
			}
			System.out.println("Do you want to add more money? (Y/N)");
			String moreMoneyChoice = userInput.nextLine();
			if(!moreMoneyChoice.equalsIgnoreCase("Y")){
				break;
			}
		}
	}
}