package ecommerce.services;

import ecommerce.models.Product;
import ecommerce.models.CartItem;
import ecommerce.interfaces.Shippable;

import java.util.*;

public class ShoppingCart {
    private List<CartItem> items = new ArrayList<>();

    public void addItem(Product product, int quantity) {
        if (quantity > product.getQuantity())
            throw new IllegalArgumentException("Quantity exceeds stock.");

        items.add(new CartItem(product, quantity));
        product.decreaseQuantity(quantity);
    }

    public boolean isEmpty() { return items.isEmpty(); }

    public List<CartItem> getItems() { return items; }

    public double getSubtotal() {
        return items.stream().mapToDouble(CartItem::getTotalPrice).sum();
    }

    public List<Shippable> getShippableItems() {
        List<Shippable> shippables = new ArrayList<>();
        for (CartItem item : items) {
            Product p = item.getProduct();
            if (p instanceof Shippable) {
                for (int i = 0; i < item.getQuantity(); i++) {
                    shippables.add((Shippable) p);
                }
            }
        }
        return shippables;
    }
}