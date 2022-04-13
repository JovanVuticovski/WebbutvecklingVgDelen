package com.example.webbutvecklingvgdelen.components;

import com.example.webbutvecklingvgdelen.entities.MovieRating;
import com.example.webbutvecklingvgdelen.service.MovieService;
import com.example.webbutvecklingvgdelen.view.ManageRatingView;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;

public class MovieForm extends FormLayout {

    // Skapar de olika fälten som krävs för en movieRating inlägg(post)
    // Därefter skrivs eventuella förändringar
    // Sist finns en Save knapp som sparar Updates(ändringarna) för vår movieRating
    TextField title = new TextField("Title");
    TextArea rating = new TextArea("Rating");
    Button saveButton = new Button("Save");

    // Binder formulär fältet med en binder till java klassen
    // Binder tillåter användande utav title,message, saveButton
    Binder<MovieRating> binder = new BeanValidationBinder<>(MovieRating.class);
    MovieService movieService;
    ManageRatingView manageRatingView;


    public MovieForm(MovieService movieService, ManageRatingView manageRatingView){
        this.manageRatingView = manageRatingView;
        this.movieService = movieService;
        binder.bindInstanceFields(this);
        setVisible(false);

        // När knappen Save klickas på körs eventet(evt) och tar in metoden handleSave
        // handleSave tillåter sparande av movieRatings inlägg(posts)
        saveButton.addClickListener(evt -> handleSave());

        add(title, rating, saveButton);

    }


    // VALIDATE kollar så input(Det vi skriver in på title och message) är fullständigt.
    // Kollar om movieRating har värdet 0 som id
    // Om id för movieRating är 0 innebär det att movieRatingen är ny
    // Save metoden anropas
    // movieRating sparas
    private void handleSave(){
        MovieRating movieRating = binder.validate().getBinder().getBean();
        if(movieRating.getId() == 0){
            movieService.save(movieRating);

            // Om Id inte är 0 kör metoden updateMoveRatingById & updatera movieRating
            // Hämtar movieRatingens id och kör metoden updateMovieRatingById
        } else {
            movieService.updateMovieRatingById(movieRating.getId(), movieRating);
        }

        // Sätter ändringar som gjorts på movieRating och kör metoden updateItems.
        setMovieRating(null);
        manageRatingView.updateItems();

        this.getParent().ifPresent(component -> {
            if(component instanceof Dialog){
                ((Dialog) component).close();
            }
        });

    }




    public void setMovieRating(MovieRating movieRating){

        // När en movieRating markeras(klickas) så får den markerad(i klickade) movieRating ett värde
        // Värdet används för att visa formuläret
        if(movieRating != null){
            binder.setBean(movieRating);
            setVisible(true);

            // När en movieRating avmarkeras(klickas på igen) så får movieRating värdet null igen
            // Om movieRating värdet är null så visas ej(stängs formuläret)
        } else {
            setVisible(false);
        }
    }




}

