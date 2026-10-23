package org.ims.Model;

import java.util.*;

public class ProductCatalog {
    private final Map<UUID, Product> products = new HashMap<>();

    public void addProduct(Product product) {
        Objects.requireNonNull(product, "Product cannot be null");

        if (products.containsKey(product.getProductId())) {
            throw new IllegalStateException(
                    "Product already exists"
            );
        }

        products.put(product.getProductId(), product);
    }

    public Product getProduct(UUID productId) {
        Product product = products.get(productId);

        if (product == null) {
            throw new NoSuchElementException("Product not found");
        }

        return product;
    }
}