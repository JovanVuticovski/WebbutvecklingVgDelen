package com.example.webbutvecklingvgdelen.view;


import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

@Route("/login")
public class LoginView extends Div implements BeforeEnterObserver {

    LoginOverlay loginOverlay = new LoginOverlay();



    public  LoginView() {

        loginOverlay.setTitle("Bästa MovieRating 2022");
        loginOverlay.setDescription("Vad kul att du är här!");
        loginOverlay.setOpened(true);
        loginOverlay.setAction("login");


        add(loginOverlay);

    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if(beforeEnterEvent
                .getLocation()
                .getQueryParameters()
                .getParameters()
                .containsKey("error")){
            loginOverlay.setError(true);
        }
    }
}
