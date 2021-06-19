package com.kodilla.restaurantfrontend.domain;

import com.kodilla.restaurantfrontend.OrderMainView;
import com.kodilla.restaurantfrontend.ProductType;
import com.kodilla.restaurantfrontend.service.ProductService;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.InputEvent;
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
    private Button plus = new Button("+");
    private Button minus = new Button("-");
    private ComboBox<ProductType> type = new ComboBox<>("Product type");
    private Button order = new Button("Order");
    private Button delete = new Button("Delete");
    private Binder<Product> binder = new Binder<Product>(Product.class);
    private OrderMainView orderMainView;
    private ProductService service = ProductService.getInstance();

    public ProductForm(OrderMainView orderMainView) {
        type.setItems(ProductType.values());
        HorizontalLayout buttons = new HorizontalLayout(order, delete);
        order.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        order.addClickListener(event -> order());
        delete.addClickListener(event -> delete());
        HorizontalLayout plusAndMinus = new HorizontalLayout(plus, minus);
        minus.addThemeVariants(ButtonVariant.LUMO_ICON);
        plus.addThemeVariants(ButtonVariant.LUMO_ICON);
        minus.addClickListener(event -> minus());
        plus.addClickListener(event -> plus(quantity.getTabIndex()));
        add(productName, price, quantity, plusAndMinus, type, buttons);
        binder.bindInstanceFields(this);
        this.orderMainView = orderMainView;
    }

    private void minus() {
    }

    private Integer plus(Integer m) {
        service.addQuantity(plus(m));
        orderMainView.refresh();
        return m;
    }

    private void order() {
        Product product = binder.getBean();
        service.save(product);
        orderMainView.refresh();
        setProduct(null);

    }

    private void delete() {
        Product product = binder.getBean();
        service.delete(product);
        orderMainView.refresh();
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
