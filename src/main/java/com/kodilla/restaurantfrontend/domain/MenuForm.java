package com.kodilla.restaurantfrontend.domain;

import com.kodilla.restaurantfrontend.OrderMainView;
import com.kodilla.restaurantfrontend.ProductType;
import com.kodilla.restaurantfrontend.service.MenuMainView;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;

public class MenuForm extends FormLayout {
    private TextField productName = new TextField("Product name");
    private TextField price = new TextField("Price");
    private TextField quantity = new TextField("Quantity");
    private ComboBox<ProductType> type = new ComboBox<>("Product type");
    private MenuMainView menuMainView;
//    private MenuService service = MenuService.getInstance();
    public MenuForm(MenuMainView menuMainView){

    }

    public void setProduct(Object o) {
    }
}
