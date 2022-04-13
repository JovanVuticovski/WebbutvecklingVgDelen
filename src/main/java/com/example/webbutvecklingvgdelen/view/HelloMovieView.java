package com.example.webbutvecklingvgdelen.view;



import com.example.webbutvecklingvgdelen.service.MovieService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;


@Route("/helloMovieView")
public class HelloMovieView extends VerticalLayout {

    MovieService movieService;

    public HelloMovieView(MovieService movieService){
        this.movieService = movieService;
        setAlignItems(Alignment.CENTER);

        H1 title = new H1("Hi Every Movie Rating Users");
        add(title);

        renderPosts();

    }

    private void updateBlogPost(){
        this.getChildren()
                .filter(component -> component.getElement().getClassList().contains("movierating"))
                .forEach(this::remove);

        renderPosts();
    }

    private void renderPosts(){
        movieService.findAll().forEach(movieRating -> {

            VerticalLayout movieLayout = new VerticalLayout();

            H2 movieTitle = new H2(movieRating.getTitle());
            Paragraph moviePointRating = new Paragraph(movieRating.getRating());

            // Delete knapp
            // När Delete klickas på så körs ett event(evt) som tar bort BlogPoster utefter dess id
            // Id hämtas med blogPost.getid
            // BlogPosten tas bort med remove(blogLayout) då hela layouten för den raderade BlogPosten tas bort
            Button button = new Button("Delete", evt -> {
                movieService.deleteById(movieRating.getId());
                remove(movieLayout);
            });


            // Lägga till BlogPoster
            // Berättar vad som ska skrivas med vid skapande av BlogPosten, d.v.s blogTitle, blogMessage, button.
            // Ger varje ny BlogPost ett id som representerar den nya BlogPosten som skapats

            movieLayout.add(movieTitle, moviePointRating, button, new Hr());
            movieLayout.setAlignItems(Alignment.CENTER);
            movieLayout.setId(String.valueOf(movieRating.getId()));
            movieLayout.addClassName("movierating");

            // Lägger till BlogPosten med en utvald blogLayout
            // blogLayout visar hur BlogPosten ska visas när den blivit tillagd(add)
            add(movieLayout);
        });
    }

}
