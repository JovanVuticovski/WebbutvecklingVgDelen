package com.example.webbutvecklingvgdelen.dto;

import com.example.webbutvecklingvgdelen.entities.AppUser;
import com.example.webbutvecklingvgdelen.entities.MovieRating;
import com.example.webbutvecklingvgdelen.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


// Helper klass för DTO objekten
// MovieDTOConverter används för att slippa skriva samma logik flera gånger, d.vs .getmessage, .getID etc
// MovieDTOConverter gör de olika anropen (request) & (Respond) till entities

@Service
public class MovieDTOConverter {

    @Autowired
    AppUserRepository appUserRepository;


    // MovieDTOConverter för de inkommande(request) anropen, d.v.s anrop från klienten
    public MovieRating MovieRequestDTOToEntity(MovieRequestDTO movieRequestDTO){

        AppUser appUser = appUserRepository
                .findById(movieRequestDTO.getAppuser_id())
                .orElseThrow();

        return new MovieRating(movieRequestDTO.getTitle(),
                movieRequestDTO.getRating(),
                appUser
        );
    }


    // MovieDTOConverter för svar(response) anropen, d.v.s returnerar informationen klienten har frågat efter
    public MovieResponseDTO entityToMovieResponseDTO(MovieRating movieRating){

        return new MovieResponseDTO(
                movieRating.getId(),
                movieRating.getTitle(),
                movieRating.getRating(),
                movieRating.getAppUser().getId()
        );

    }

}
