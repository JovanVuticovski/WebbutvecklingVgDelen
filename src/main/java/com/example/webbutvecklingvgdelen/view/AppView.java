package com.example.webbutvecklingvgdelen.view;

import com.example.webbutvecklingvgdelen.security.PrincipalUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;

//Importerar egen Css kod
@CssImport("/styles/movie.css")
//Targetar hela navbaren med styling(css)
@CssImport(value = "./styles/components/vaadin-navbar-layout.css", themeFor = "vaadin-app-layout")
public class AppView extends AppLayout {

    public AppView(){

        HorizontalLayout navbarLayout = new HorizontalLayout();
        H1 navbarTitle = new H1("Bästa Film Review Sidan");
        // Navbar klass till att styla i css
        navbarTitle.addClassName("navbartitle");

        navbarLayout.add(new DrawerToggle(),navbarTitle);


        Button loginButton = new Button("Login", evt ->
                UI.getCurrent().navigate(LoginView.class));


        Button logoutButton = new Button("Logout", evt -> PrincipalUtil.logout());

        if(PrincipalUtil.isLoggedIn()) {
            navbarLayout.add(logoutButton);
        } else {
            navbarLayout.add(loginButton);
        }



        navbarLayout.setWidthFull();
        navbarLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        navbarLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        navbarLayout.setMargin(true);

        addToNavbar(navbarLayout);


        RouterLink movieRatingViewLink = new RouterLink("Active MovieRatings", MovieView.class);
        VerticalLayout sideDrawerContent = new VerticalLayout(movieRatingViewLink);

        RouterLink manageRatingViewLink = new RouterLink("Modify Ratings", ManageRatingView.class);
        if(PrincipalUtil.isLoggedIn())
            sideDrawerContent.add(manageRatingViewLink);

        addToDrawer(sideDrawerContent);

    }

}

