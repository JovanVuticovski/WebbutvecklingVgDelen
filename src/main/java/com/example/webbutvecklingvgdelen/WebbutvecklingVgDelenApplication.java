package com.example.webbutvecklingvgdelen;

import com.example.webbutvecklingvgdelen.entities.AppUser;
import com.example.webbutvecklingvgdelen.entities.MovieRating;
import com.example.webbutvecklingvgdelen.repository.AppUserRepository;
import com.example.webbutvecklingvgdelen.repository.MovieRatingRepository;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class WebbutvecklingVgDelenApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebbutvecklingVgDelenApplication.class, args);
    }

    @Bean
    CommandLineRunner init(
            MovieRatingRepository movieRatingRepository,
            AppUserRepository appUserRepository){
        return args -> {

            // Alla AppAnvändare
           // AppUser Jovan = new AppUser("Jovan","jove2001");
          //  AppUser Simon = new AppUser("Simon","Simon");

            // Sparar alla AppAnvändare på webbsidan
          //  appUserRepository.saveAll(List.of(Jovan, Simon));

            // Jovans MovieRatings
          //  MovieRating movieRating = new MovieRating("Film Rating 1", "Rolig Film, men 7/10 poäng ",Jovan);
          //  movieRatingRepository.save(movieRating);

            // Simons MovieRatings
        //    MovieRating movieRating2 = new MovieRating("Film Rating 2", "Filmen får 6/10 poäng", Simon);

            // Sparar alla MovieRatings som skapats
          //  movieRatingRepository.saveAll(List.of(movieRating, movieRating2));




        };

    }

}
