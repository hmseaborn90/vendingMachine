//package com.techelevator.vendingmachine;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//
//import static com.techelevator.Application.currency;
//
//
//public class ProductInventory {
//    private List<Product> products;
//
//    public ProductInventory() {
//        this.products = new ArrayList<>();
//    }
//
//    public void addProduct(Product product) {
//        products.add(product);
//    }
//
//    public List<Product> getAllProducts() {
//        return products;
//    }
//
//    public void purchaseDisplayMenu(BigDecimal balance) {
//        System.out.println(
//                "Current Money Provided: " + currency.format(balance) + "\n" +
//                        "    > (1) FeedMoney\n" +
//                        "    > (2) Select Product\n" +
//                        "    > (3) Finish Transaction");
//    }
//
//    public void displayInventory() {
//        for (Product product : products) {
//            if (product.getProductQuantity() == 0) {
//                System.out.println(product.getSlotLocation() + " | " + "Sold Out");
//            } else {
//                System.out.println(product.getSlotLocation() + " | " + product.getProductName() + " | " + currency.format(product.getProductPrice()));
//            }
//
//
//        }
//    }
//
//    public Product getProductBySlot(String slotLocation) {
//        for (Product product : products) {
//            if (product.getSlotLocation().equalsIgnoreCase(slotLocation)) {
//                return product;
//            }
//        }
//        return null;
//    }
//
//    public Product getProductByName(String name) {
//        for (Product product : products) {
//            if (product.getProductName().equalsIgnoreCase(name)) {
//                return product;
//            }
//        }
//        return null;
//    }
//
//}
////
////public class ProductInventory {
////    private Map<String, Product> products;
////
////    public ProductInventory() {
////        this.products = new HashMap<>();
////    }
////
////    public void addProduct(Product product) {
////        products.put(product.getSlotLocation(), product);
////    }
////
////    public Map<String, Product> getAllProducts() {
////        return products;
////    }
////
////    public void purchaseDisplayMenu(double balance) {
////
////        System.out.println(
////                "Current Money Provided: " + currency.format(balance) + "\n" +
////                        "    > (1) FeedMoney\n" +
////                        "    > (2) Select Product\n" +
////                        "    > (3) Finish Transaction");
////    }
////
////    public void displayInventory() {
////        for (Map.Entry<String, Product> entry : products.entrySet()) {
////            String slotLocation = entry.getKey();
////            Product product = entry.getValue();
////            if (product.getProductQuantity() == 0) {
////                System.out.println(slotLocation + " | " + "Sold Out");
////            } else {
////                System.out.println(slotLocation + " | " + product.getProductName() + " | " + currency.format(product.getProductPrice()));
////            }
////        }
////    }
////
////    public Product getProductBySlot(String slotLocation) {
////        return products.get(slotLocation);
////    }
////
////    public Product getProductByName(String name) {
////        for (Product product : products.values()) {
////            if (product.getProductName().equalsIgnoreCase(name)) {
////                return product;
////            }
////        }
////        return null;
////    }
////}
