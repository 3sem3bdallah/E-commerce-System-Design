package ecommerce.services;

import ecommerce.users.Customer;
import ecommerce.models.CartItem;
import ecommerce.models.ExpirableProduct;

import java.util.List;

public class CheckoutService {
    private ShippingService shippingService = new ShippingService();

    public void checkout(Customer customer) {
        ShoppingCart cart = customer.getCart();
        List<CartItem> items = cart.getItems();

        if (cart.isEmpty())
            throw new IllegalStateException("Cart is empty.");

        // Check for expiration
        for (CartItem item : items) {
            if (item.getProduct() instanceof ExpirableProduct exp) {
                if (exp.isExpired())
                    throw new IllegalStateException(exp.getName() + " is expired.");
            }
        }

        double subtotal = cart.getSubtotal();
        double shipping = cart.getShippableItems().isEmpty() ? 0 : 30;
        double total = subtotal + shipping;

        if (customer.getBalance() < total)
            throw new IllegalStateException("Insufficient balance.");

        shippingService.ship(cart.getShippableItems()); 

        System.out.println("** Checkout receipt **");
        for (CartItem item : items) {
            System.out.printf("%dx %s\n", item.getQuantity(), item.getProduct().getName());
            System.out.println(item.getTotalPrice());
}
        System.out.println("----------------------");
        System.out.println("Subtotal\n" + subtotal);
        System.out.println("Shipping\n" + shipping);
        System.out.println("Amount\n" + total);

        customer.deductBalance(total);
        System.out.println("Remaining balance: " + customer.getBalance());
    }
}
