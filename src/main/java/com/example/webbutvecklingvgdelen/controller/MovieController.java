package com.example.webbutvecklingvgdelen.controller;

import com.example.webbutvecklingvgdelen.dto.MovieDTOConverter;
import com.example.webbutvecklingvgdelen.dto.MovieRequestDTO;
import com.example.webbutvecklingvgdelen.dto.MovieResponseDTO;
import com.example.webbutvecklingvgdelen.entities.MovieRating;
import com.example.webbutvecklingvgdelen.service.MovieService;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;




// Controller Anropar de olika funktionerna som ska köras  ( själva funktionerna är definerade i service klassen )
// Dessa funktioner skickas till den angivna adressen/hemsidan "movie/" med hjälp av annotationen   RequestMapping("movie/")

@Controller
@RequestMapping("/movie")

public class MovieController {


    // Kopplar MovieController med MovieService klassen.  = MovieService kan användas i MovieController klassen

    MovieService movieService;


    // Kopplar MovieController med MovieDTOConverter klassen.  = MovieDTOConverter kan användas i MovieController klassen

    MovieDTOConverter movieDTOConverter;

    public MovieController(MovieService movieService, MovieDTOConverter movieDTOConverter){
        this.movieService = movieService;
        this.movieDTOConverter = movieDTOConverter;
    }



    // Hämtar ut movieRating listan med alla movieRates inlägg
    // Använder stream för hämta ut alla movieRates inlägg i en lista
    // .map för att ändra movieRating till MovieResponseDTO
    // .list gör en ny lista av det
    // Använder @RequestParam för att filtrera en lista som endast består utav namn(names)

    @GetMapping
    public String getMovieRatingList(@RequestParam(required = false) Model model) {

        List<MovieRating> movieRatingList = movieService.findAll();
        movieRatingList.forEach(System.out::println);

        //Kopplar Data till template engine:  så att movieRatingList kan användas i movie.html templaten
        model.addAttribute("movieRatingList",movieRatingList);

        // Returnerar namnet på template sidan
        return "movie";

    }

// När Add movieRating knappen trycks så tar den användaren till adressen /Addmovie
    // På addmovie addressen finns en newMovieForm med title och message till nya movieRatings som skall skapas

    @GetMapping("/addmovie")
    public String addMovieRating(){
        return "newMovieForm";

    }


// Hämtar vald movieRating inlägg/post baserat på angivet id

    @GetMapping("/{id}")

    public MovieResponseDTO getMovieRatingById(@PathVariable("id") int id){
        MovieRating movieRating = movieService.GetMovieRatingById(id);
        return new MovieResponseDTO(
                movieRating.getId(),
                movieRating.getTitle(),
                movieRating.getRating(),
                movieRating.getAppUser().getId()
        );

    }


    // Tar bort movieRating på det id som valts att raderas

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovieRatingById(@PathVariable("id") int id){
        movieService.deleteById(id);

        // Skapar status respons till Web-adressen /movie
        return ResponseEntity.status(303).header("Location", "/movie").build();
    }




    // Gör en Request som skapar ett ny movieRating inlägg/post


    @PostMapping

    public String createMovieRating(@ModelAttribute MovieRating movieRating){


        // sparar movieRating inlägget/post och dess innehåll.

        movieService.save(movieRating);

        // Skicka tillbaka användaren till /movie efter ny movieRating lagts till

        System.out.println("title" + movieRating.getTitle());
        return "redirect:/movie";
    }



    // Ändra innehållet i en movieRating inlägg

    @PutMapping("/{id}")

    public MovieResponseDTO updateById(
            @RequestBody MovieRequestDTO changedMovieRatingDTO,
            @PathVariable("id") int id){

        // changedMovieRatingDTO convertera från DTO till entiteten
        // MovieRequest ändras till changedMovieRating

        MovieRating changedMovieRating = movieDTOConverter
                .MovieRequestDTOToEntity(changedMovieRatingDTO);


        // Returnerar movieRating utifrån dess id tillsammans med dess ändringar och skickar iväg en response på requesten

            MovieRating movieRating = movieService.updateMovieRatingById(id,changedMovieRating);
        return movieDTOConverter.entityToMovieResponseDTO(movieRating);






    }



}
