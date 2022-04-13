package com.example.webbutvecklingvgdelen.service;

import com.example.webbutvecklingvgdelen.entities.MovieRating;
import com.example.webbutvecklingvgdelen.repository.MovieRatingRepository;
import org.springframework.stereotype.Service;

import java.util.List;



// Definerar / fyller funktionerna som anropas från controller klassen med innehåll
// ex GetMovieRatingByID får findById(id).orElseThrow()   = hitta movieRates utefter ett specfikt angivet id om inte id hittas ignorerar/kasta

@Service
public class MovieService {

    // skapar blogPostRepository variable

    MovieRatingRepository movieRatingRepository;


    // MovieService kopplas med MovieRatingRepository som extendar JpaRepository
    // Möjligör användning av grundläggande Crud Funktioner ( save, delete, get, post, put, etc)


    public MovieService(MovieRatingRepository movieRatingRepository) {
        this.movieRatingRepository = movieRatingRepository;
    }


    public List<MovieRating> findAll() {

        // Sortera manuellt utefter App användaren(AppUser) utefter movieRating.getAppUser().getUsername().equals)username));

        /*
        Stream<MovieRating> movieRatingStream = MovieRatingRepository.findAll().stream();
        if (username != null){
           movieRatingStream = movieRatingStream.filter(movieRating -> movieRating.getAppUser().getUsername().equals(username));

    }
        return blogPostStream.toList();
*/


        // Filtrera Appuser utefter listan som skapades i MovieRatingRepository med findByAppuser_Username(username)

        //if (username != null) {
        //     return MovieRatingRepository.findByAppUser_Username(username);

        //  } else {
        return movieRatingRepository.findAll();
        //   }

    }


    public MovieRating GetMovieRatingById(int id) {
        return movieRatingRepository.findById(id).orElseThrow();
    }

    public void deleteById(int id) {
        movieRatingRepository.deleteById(id);

    }

    public MovieRating CreateAndSave(MovieRating movieRating) {
        return movieRatingRepository.save(movieRating);
    }


    public MovieRating updateMovieRatingById(int id, MovieRating changedMovieRating) {

        MovieRating existingMovieRating = movieRatingRepository.findById(id).orElseThrow();


        // Ändrar innehållet på den existerande movieRating inlägget

        if(changedMovieRating.getTitle() != null )
            existingMovieRating.setTitle(changedMovieRating.getTitle());

        if (changedMovieRating.getRating() != null)
            existingMovieRating.setRating(changedMovieRating.getRating());


        // Sparar den uppdaterade movieRating och dess innehåll

        movieRatingRepository.save(existingMovieRating);


        // returnerar den uppdaterade movieRating

        return  existingMovieRating;
    }


    // Används för att kunna spara movieRatings efter att en movieRating skapats

    public MovieRating save(MovieRating blogPost) {
        movieRatingRepository.save(blogPost);
        return blogPost;
    }

    public List<MovieRating> findRatingByAuthorUsername(String principalName) {
        return movieRatingRepository.findByAppUser_Username(principalName);
    }
}
