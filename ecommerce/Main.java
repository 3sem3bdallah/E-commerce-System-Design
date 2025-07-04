package ecommerce;

import ecommerce.models.*;
import ecommerce.users.Customer;
import ecommerce.services.CheckoutService;
import ecommerce.models.NonExpirableProduct;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Define products
        ExpirableProduct cheese = new ExpirableProduct("Cheese", 100, 10, LocalDate.now().plusDays(2));
        ExpirableProduct biscuits = new ExpirableProduct("Biscuits", 150, 5, LocalDate.now().plusDays(1));
        ShippableProduct tv = new ShippableProduct("TV", 5000, 3, 7000);
        Product scratchCard = new NonExpirableProduct("Scratch Card", 50, 20);

        // Customer
        Customer customer = new Customer("Asem", 1000);

        // Add to cart
        customer.getCart().addItem(cheese, 2);
        customer.getCart().addItem(biscuits, 1);

        // Checkout
        CheckoutService checkout = new CheckoutService();
        checkout.checkout(customer);
    }
}

