package com.kodilla.restaurantfrontend.service;

import com.kodilla.restaurantfrontend.ProductType;
import com.kodilla.restaurantfrontend.domain.Product;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductService {
    private Set<Product> products;
    private static ProductService productService;

    private ProductService() {
        this.products = exampleData();
    }

    public static ProductService getInstance() {
        if (productService == null) {
            productService = new ProductService();
        }
        return productService;
    }

    public Set getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    private Set exampleData() {
        Set<Product> products = new HashSet<>();
       products.add(new Product("Pizza", "32","true", "1", ProductType.MAIN_COURSE));
       products.add(new Product("Pomodoro", "12","true", "1", ProductType.SOUP));
       products.add(new Product("Ice", "22","true", "2", ProductType.DESSERT));
       products.add(new Product("Coca-cola", "6","true", "2", ProductType.DRINKS));
        return products;
    }
    public Set findByProductName(String productName) {
        return  products.stream()
                .filter(product -> product.getProductName().contains(productName))
                .collect(Collectors.toSet());
    }
    public Set findByProductPrice(String price) {
        return  products.stream()
                .filter(product -> product.getPrice().contains(price))
                .collect(Collectors.toSet());
    }

    public void save(Product product) {
        this.products.add(product);
    }

    public void delete(Product product) {
        this.products.remove(product);
    }

}
