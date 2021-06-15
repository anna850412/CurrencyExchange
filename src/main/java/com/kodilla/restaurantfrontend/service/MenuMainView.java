package com.kodilla.restaurantfrontend.service;

import com.kodilla.restaurantfrontend.OrderMainView;
import com.kodilla.restaurantfrontend.domain.Menu;
import com.kodilla.restaurantfrontend.domain.Product;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route("Menu")
public class MenuMainView extends VerticalLayout {
    private MenuService menuService = MenuService.getInstance();
    private Grid<Menu> grid = new Grid<>(Menu.class);
    private TextField filter = new TextField();
    private TextField filter1 = new TextField();
    private Button order = new Button("Order");
    private OrderMainView orderMainView = new OrderMainView();

    public MenuMainView() {
        filter.setPlaceholder("Filter by product name");
        filter1.setPlaceholder("Filter by price");
        filter.setClearButtonVisible(true);
        filter1.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter1.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> updateName());
        filter1.addValueChangeListener(e -> updatePrice());
        grid.setColumns("productName", "price", "available", "quantity", "type");

        order.addClickListener(e -> {
            UI.getCurrent().navigate("Order");
        });
        HorizontalLayout toolbar = new HorizontalLayout(filter, filter1, order);

        HorizontalLayout mainContent = new HorizontalLayout(grid);
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(toolbar, mainContent);
        setSizeFull();
        refresh();

    }

    private void updateName() {
        grid.setItems(menuService.findByProductName(filter.getValue()));
    }

    private void updatePrice() {
        grid.setItems(menuService.findByProductPrice(filter1.getValue()));
    }

    public void refresh() {
        grid.setItems(menuService.getMenus());
    }
}

