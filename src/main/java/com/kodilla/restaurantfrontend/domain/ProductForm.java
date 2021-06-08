package com.kodilla.restaurantfrontend.domain;

import com.kodilla.restaurantfrontend.MainView;
import com.kodilla.restaurantfrontend.ProductType;
import com.kodilla.restaurantfrontend.service.ProductService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class ProductForm extends FormLayout {
    private TextField productName = new TextField("Product name");
    private TextField price = new TextField("Price");
    private TextField quantity = new TextField("Quantity");
    private ComboBox<ProductType> type = new ComboBox<>("Product type");
    private Button order = new Button("Order");
    private Button delete = new Button("Delete");
    private Binder<Product> binder = new Binder<Product>(Product.class);
    private MainView mainView;
    private ProductService service = ProductService.getInstance();

    public ProductForm(MainView mainView) {
        type.setItems(ProductType.values());
        HorizontalLayout buttons = new HorizontalLayout(order, delete);
        order.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        order.addClickListener(event -> order());
        delete.addClickListener(event -> delete());
        add(productName, price, quantity, type, buttons);
        binder.bindInstanceFields(this);
        this.mainView = mainView;
    }

    private void order() {
        Product product = binder.getBean();
        service.save(product);
        mainView.refresh();
        setProduct(null);

    }

    private void delete() {
        Product product = binder.getBean();
        service.delete(product);
        mainView.refresh();
        setProduct(null);
    }

    public void setProduct(Product product) {
        binder.setBean(product);
        if (product == null) {
            setVisible(false);
        } else {
            setVisible(true);
            productName.focus();
        }
    }
}
