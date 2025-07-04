package ecommerce.users;

import ecommerce.services.ShoppingCart;

public class Customer {
    private String name;
    private double balance;
    private ShoppingCart cart;

    public Customer(String name, double balance) {
        this.name = name;
        this.balance = balance;
        this.cart = new ShoppingCart();
    }

    public String getName() { return name; }
    public double getBalance() { return balance; }
    public void deductBalance(double amount) { balance -= amount; }

    public ShoppingCart getCart() { return cart; }
}
