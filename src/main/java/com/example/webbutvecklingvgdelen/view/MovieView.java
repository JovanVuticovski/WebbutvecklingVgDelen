package com.example.webbutvecklingvgdelen.view;

import com.example.webbutvecklingvgdelen.entities.AppUser;
import com.example.webbutvecklingvgdelen.service.MovieService;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;


@Route(value = "/", layout = AppView.class)
@AnonymousAllowed

public class MovieView extends VerticalLayout {

    public MovieView(MovieService movieService){

        add(new H2("Alla Film-Ratings"));

        setAlignItems(Alignment.CENTER);

        movieService.findAll().forEach(movieRating -> {

            H3 movieTitle = new H3(movieRating.getTitle());
            Paragraph moviePointsRating = new Paragraph(movieRating.getRating());

            Paragraph movieRateWrittenBy = new Paragraph(" Rating Skriven Av: ");
            Span author = new Span(movieRating.getAppUser().getUsername());
            author.getStyle().set("font-weight", "bold");
            // Lägger till author för personen som gjort movieRating inlägget
            movieRateWrittenBy.add(author);

            add(movieTitle, moviePointsRating,movieRateWrittenBy, new Hr());

        });

    }

}
