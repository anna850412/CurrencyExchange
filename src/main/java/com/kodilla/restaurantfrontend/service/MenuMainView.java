package com.kodilla.restaurantfrontend.service;

import com.kodilla.restaurantfrontend.OrderMainView;
import com.kodilla.restaurantfrontend.domain.Product;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;

public class MenuMainView extends VerticalLayout {
    private ProductService productService = ProductService.getInstance();
    private Grid<Product> grid = new Grid<>(Product.class);
    private TextField filter = new TextField();
    private TextField filter1 = new TextField();
//    private MenuForm form = new MenuForm(this);
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
//        form.setProduct(null);

        order.addClickListener(e -> {
 orderMainView.refresh();
        });
        HorizontalLayout toolbar = new HorizontalLayout(filter, filter1, order);

        HorizontalLayout mainContent = new HorizontalLayout(grid);
//                form);
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(toolbar, mainContent);
        setSizeFull();
        refresh();

//        grid.asSingleSelect().addValueChangeListener(event -> form.setProduct(grid.asSingleSelect().getValue()));

    }

    private void updateName() {
        grid.setItems(productService.findByProductName(filter.getValue()));
    }

    private void updatePrice() {
        grid.setItems(productService.findByProductPrice(filter1.getValue()));
    }

    public void refresh() {
        grid.setItems(productService.getProducts());
    }
}

