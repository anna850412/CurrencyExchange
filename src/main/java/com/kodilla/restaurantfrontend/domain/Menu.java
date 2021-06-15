package com.kodilla.restaurantfrontend.domain;

import com.kodilla.restaurantfrontend.ProductType;

public class Menu {
    private String productName;
    private String price;
    private String available;
    private String quantity;
    private ProductType type;

    public Menu() {
    }

    public Menu(String productName, String price, String available, String quantity, ProductType type) {
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
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
        if (!(o instanceof Menu)) return false;

        Menu menu = (Menu) o;

        if (getProductName() != null ? !getProductName().equals(menu.getProductName()) : menu.getProductName() != null)
            return false;
        if (getPrice() != null ? !getPrice().equals(menu.getPrice()) : menu.getPrice() != null) return false;
        if (getAvailable() != null ? !getAvailable().equals(menu.getAvailable()) : menu.getAvailable() != null)
            return false;
        if (getQuantity() != null ? !getQuantity().equals(menu.getQuantity()) : menu.getQuantity() != null)
            return false;
        return getType() == menu.getType();
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
