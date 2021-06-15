package com.kodilla.restaurantfrontend;

import com.kodilla.restaurantfrontend.domain.Product;
import com.kodilla.restaurantfrontend.domain.ProductForm;
import com.kodilla.restaurantfrontend.service.ProductService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route("Order")
public class OrderMainView extends VerticalLayout {
    private ProductService productService = ProductService.getInstance();
    private Grid<Product> grid = new Grid<>(Product.class);
    private TextField filter = new TextField();
    private TextField filter1 = new TextField();
    private ProductForm form = new ProductForm(this);
    private Button addNewOrder = new Button("Add new order");
    private Button menu = new Button("Menu");

    public OrderMainView() {

        filter.setPlaceholder("Filter by product name");
        filter1.setPlaceholder("Filter by price");
        filter.setClearButtonVisible(true);
        filter1.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter1.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> updateName());
        filter1.addValueChangeListener(e -> updatePrice());
        grid.setColumns("productName", "price", "available","quantity", "type");
        form.setProduct(null);
        menu.addClickListener(e -> {
            UI.getCurrent().navigate("Menu");

        });
        addNewOrder.addClickListener(e -> {
            grid.asSingleSelect().clear(); //"czyścimy" zaznaczenie
            form.setProduct(new Product());      //dodajemy nowy obiekt do formularza
        });
        HorizontalLayout toolbar = new HorizontalLayout(filter, filter1, addNewOrder, menu);

        HorizontalLayout mainContent = new HorizontalLayout(grid, form);
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(toolbar, mainContent);
        setSizeFull();
        refresh();

        grid.asSingleSelect().addValueChangeListener(event -> form.setProduct(grid.asSingleSelect().getValue()));

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
