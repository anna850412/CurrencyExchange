package com.kodilla.restaurantfrontend;

import com.kodilla.restaurantfrontend.domain.Product;
import com.kodilla.restaurantfrontend.domain.ProductForm;
import com.kodilla.restaurantfrontend.service.ProductService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route("Restaurant")
public class MainView extends VerticalLayout {
    private ProductService productService = ProductService.getInstance();
    private Grid<Product> grid = new Grid<>(Product.class);
    private TextField filter = new TextField();
    private ProductForm form = new ProductForm(this);
    private Button addNewProduct = new Button("Add new product");

    public MainView() {

        filter.setPlaceholder("Filter by product name");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> update());
        grid.setColumns("product name", "price", "available","quantity", "type");
        form.setProduct(null);

        addNewProduct.addClickListener(e -> {
            grid.asSingleSelect().clear(); //"czyścimy" zaznaczenie
            form.setProduct(new Product());      //dodajemy nowy obiekt do formularza
        });
        HorizontalLayout toolbar = new HorizontalLayout(filter, addNewProduct);

        HorizontalLayout mainContent = new HorizontalLayout(grid, form);
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(toolbar, mainContent);
        setSizeFull();
        refresh();

        grid.asSingleSelect().addValueChangeListener(event -> form.setProduct(grid.asSingleSelect().getValue()));

    }
    private void update() {
        grid.setItems(productService.findByProductName(filter.getValue()));
    }

    public void refresh() {
        grid.setItems(productService.getProducts());
    }
}
