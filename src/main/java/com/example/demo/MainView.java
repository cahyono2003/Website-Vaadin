package com.example.demo;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.shared.ThemeVariant;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout{
    private HumanRepository repository;
    private TextField nama = new TextField("Nama");
    private EmailField email = new EmailField("Email");
    private TextField username = new TextField("Username");
    private TextField password = new TextField("Password");
    private Binder<Human> binder =  new Binder<>(Human.class);
    private Grid<Human> grid = new Grid<>(Human.class);
    
    public MainView(HumanRepository repository) {
        this.repository = repository;


        grid.setColumns("nama", "email", "username", "password");

        add(getForm(), grid);
        refreshGrid();
    }

    private Component getForm() {
        var layout = new HorizontalLayout();
        layout.setAlignItems(Alignment.BASELINE);

        var addButton = new Button("Daftar");
        addButton.addClickShortcut(Key.ENTER);
        addButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        layout.add(nama, email, username, password, addButton);

        binder.bindInstanceFields(this);

        addButton.addClickListener(click -> {
            try{
                var human = new Human();
                binder.writeBean(human);
                repository.save(human);
                binder.readBean(new Human());
                refreshGrid();
            } catch (ValidationException e) {

            }
        });


        return layout;
    }

    private void refreshGrid() {
        grid.setItems(repository.findAll());
    }
}
