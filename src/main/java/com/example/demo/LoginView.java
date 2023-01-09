package com.example.demo;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.Route;

@Route("login")
public class LoginView extends Composite<LoginOverlay> {

        public LoginView() {
                LoginOverlay loginOverlay = getContent();
                loginOverlay.setTitle("Ajie Store");
                loginOverlay.setDescription("TopUp Game Terpercaya");
                loginOverlay.setOpened(true);

                loginOverlay.addLoginListener(event ->{
                        if ("user". equals(event.getUsername())) {
                                UI.getCurrent().navigate("user");
                        } else if("admin".equals(event.getUsername())) {
                                UI.getCurrent().navigate(AdminView.class);
                        } else {
                                Notification.show("Wrong credential");
                        }
                });
        }
}