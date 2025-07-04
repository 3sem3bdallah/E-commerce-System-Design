package ecommerce.services;
import java.util.Map;
import java.util.LinkedHashMap;
import ecommerce.interfaces.Shippable;

import java.util.List;

public class ShippingService {
    public void ship(List<Shippable> items) {
        if (items.isEmpty()) return;
        System.out.println("** Shipment notice **");
        double totalWeight = 0.0;
        Map<String, Integer> grouped = new LinkedHashMap<>();

        for (Shippable item : items) {
            grouped.put(item.getName(), grouped.getOrDefault(item.getName(), 0) + 1);
            totalWeight += item.getWeight();
        }

        for (var entry : grouped.entrySet()) {
            System.out.printf("%dx %s\n", entry.getValue(), entry.getKey());
        }

        System.out.printf("Total package weight %.1fkg\n", totalWeight / 1000);
    }
}
