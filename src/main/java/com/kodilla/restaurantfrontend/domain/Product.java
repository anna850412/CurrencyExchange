package com.kodilla.restaurantfrontend.domain;

import com.kodilla.restaurantfrontend.ProductType;

import java.math.BigDecimal;

public class Product {
    private String productName;
    private BigDecimal price;
    private Boolean available;
    private Long quantity;
    private ProductType type;

    public Product() {
    }

    public Product(String productName, BigDecimal price, Boolean available, Long quantity, ProductType type) {
        this.productName = productName;
        this.price = price;
        this.available = available;
        this.quantity = quantity;
        this.type = type;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        Product product = (Product) o;

        if (getProductName() != null ? !getProductName().equals(product.getProductName()) : product.getProductName() != null)
            return false;
        if (getPrice() != null ? !getPrice().equals(product.getPrice()) : product.getPrice() != null) return false;
        if (getAvailable() != null ? !getAvailable().equals(product.getAvailable()) : product.getAvailable() != null)
            return false;
        if (getQuantity() != null ? !getQuantity().equals(product.getQuantity()) : product.getQuantity() != null)
            return false;
        return getType() == product.getType();
    }

    @Override
    public int hashCode() {
        int result = getProductName() != null ? getProductName().hashCode() : 0;
        result = 31 * result + (getPrice() != null ? getPrice().hashCode() : 0);
        result = 31 * result + (getAvailable() != null ? getAvailable().hashCode() : 0);
        result = 31 * result + (getQuantity() != null ? getQuantity().hashCode() : 0);
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        return result;
    }
}
