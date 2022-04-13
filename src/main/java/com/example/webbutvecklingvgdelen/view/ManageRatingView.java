package com.example.webbutvecklingvgdelen.view;

import com.example.webbutvecklingvgdelen.components.MovieForm;
import com.example.webbutvecklingvgdelen.entities.AppUser;
import com.example.webbutvecklingvgdelen.entities.MovieRating;
import com.example.webbutvecklingvgdelen.repository.AppUserRepository;
import com.example.webbutvecklingvgdelen.security.PrincipalUtil;
import com.example.webbutvecklingvgdelen.service.MovieService;




import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;


@Route(value = "/manageratings", layout = AppView.class)
// Permit tillåter bara inloggade användare att redigera movieRating reviews
@PermitAll
public class ManageRatingView extends VerticalLayout {

    Grid<MovieRating> grid = new Grid<>(MovieRating.class, false);
    MovieService movieService;
    MovieForm movieForm;
    AppUserRepository appUserRepository;

    public ManageRatingView(MovieService movieService, AppUserRepository appUserRepository) {
        this.movieService = movieService;
        this.movieForm = new MovieForm(movieService, this);
        setAlignItems(Alignment.CENTER);
        add(new H2("Write a movie review if you'd like"));

        grid.setItems(movieService.findRatingByAuthorUsername(PrincipalUtil.getPrincipalName()));
        grid.setWidthFull();


        // Skapar Delete knapp med Icon som representera Delete
        // När en blogPost klickas på så körs ett evt(event) som hämtar blogPost .getTitle
        // blogPostService.deleteById(blogPost.getId()); hämtar det i klickade blogPostens ID

        grid.addComponentColumn(movieRating -> {
            Button button = new Button(new Icon(VaadinIcon.CLOSE), evt -> {
                Notification.show(movieRating.getTitle());
                movieService.deleteById(movieRating.getId());
                updateItems();
            });

            // Ger delete knappen extra styling
            button.addThemeVariants(
                    ButtonVariant.LUMO_ERROR,
                    ButtonVariant.LUMO_PRIMARY,
                    ButtonVariant.LUMO_SMALL
            );

            return button;
        });

        // (asSingleSelect) tillåter endast redigering av movieRating på den klickade Movien
        grid.addColumn(MovieRating::getId).setHeader("Id").setSortable(true).setResizable(true);
        grid.addColumn(MovieRating::getTitle).setHeader("Title");
        grid.addColumn(MovieRating::getRating).setHeader("Rating");
        grid.asSingleSelect().addValueChangeListener(evt -> {
            movieForm.setMovieRating(evt.getValue());
        });

        HorizontalLayout mainContent = new HorizontalLayout(grid, movieForm);
        mainContent.setSizeFull();


        // Gör en knapp som går att klicka på "Add Rating"
        // När knappen klickas på så öppnas en modal
        // I modalen finns det som krävs för att skapa en ny movieRating. d.v.s Title och Rating för movieRatingen
        // movieRating sparas ner i databasen. d.v.s vid refresh av hemsidan så är ändringarna kvar
        Button button = new Button("Add Rating", evt -> {
            Dialog dialog = new Dialog();
            MovieForm dialogForm = new MovieForm(movieService, this);

            MovieRating movieRating = new MovieRating();
            AppUser currentUser = appUserRepository.findByUsername(PrincipalUtil.getPrincipalName())
                    .orElseThrow();

            movieRating.setAppUser(currentUser);

            dialogForm.setMovieRating(movieRating);

            dialog.add(dialogForm);
            dialog.open();
        });

        add(mainContent, button);


    }

    // Updatera existerande MovieRating

    public void updateItems(){
        grid.setItems(
                movieService
                        .findRatingByAuthorUsername(PrincipalUtil.getPrincipalName()));
    }
}



