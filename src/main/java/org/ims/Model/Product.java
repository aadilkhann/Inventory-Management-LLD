package org.ims.Model;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {
    private final UUID productId;
    private String productName;
    private BigDecimal productPrice;

    public Product(String productName, BigDecimal productPrice) {
        this.productId = UUID.randomUUID();
        if(validateProductName(productName)) this.productName = productName;
        else throw  new IllegalArgumentException("Invalid product name");

        if(validateProductPrice(productPrice)) this.productPrice = productPrice;
        else throw  new IllegalArgumentException("Invalid product price");
    }

    public void setProductName(String productName) {
        if(validateProductName(productName)) this.productName = productName;
        else throw  new IllegalArgumentException("Invalid product name");
    }

    public void setProductPrice(BigDecimal productPrice) {
        if(validateProductPrice(productPrice)) this.productPrice = productPrice;
        else throw  new IllegalArgumentException("Invalid product price");
    }

    public UUID getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    private boolean validateProductName(String productName) {
        if (productName == null || productName.isEmpty()) {
            return false;
        }
        return true;
    }

    private boolean validateProductPrice(BigDecimal productPrice) {
        if (productPrice < 0) {
            return false;
        }
        return true;
    }
}
