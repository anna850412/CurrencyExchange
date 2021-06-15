package com.kodilla.restaurantfrontend.service;

import com.kodilla.restaurantfrontend.ProductType;
import com.kodilla.restaurantfrontend.domain.Menu;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class MenuService {
    private Set<Menu> menus;
    private static MenuService menuService;

    private MenuService() {this.menus = exampleData();}
    public static MenuService getInstance(){
        if(menuService == null){
            menuService = new MenuService();
        } return  menuService;
    }

    public Set getMenus() {
        return menus;
    }

    private Set exampleData() {
        Set<Menu> menus = new HashSet<>();
        menus.add(new Menu("Pizza", "32","true", "1", ProductType.MAIN_COURSE));
        menus.add(new Menu("Pomodoro", "12","true", "1", ProductType.SOUP));
        menus.add(new Menu("Ice", "22","true", "2", ProductType.DESSERT));
        menus.add(new Menu("Coca-cola", "6","true", "2", ProductType.DRINKS));
   return menus;
    }
    public Set findByProductName(String productName) {
        return  menus.stream()
                .filter(product -> product.getProductName().contains(productName))
                .collect(Collectors.toSet());
    }
    public Set findByProductPrice(String price) {
        return  menus.stream()
                .filter(product -> product.getPrice().contains(price))
                .collect(Collectors.toSet());
    }



}
