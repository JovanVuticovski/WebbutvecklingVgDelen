package com.example.webbutvecklingvgdelen.repository;

import com.example.webbutvecklingvgdelen.entities.MovieRating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


// Gör det möjligt att använda grundläggande Crud funktioner
// Repository extend med JpaRepository tillåter oss använda, Delete, Get, Post, Put, Save osv.

@Repository
public interface MovieRatingRepository extends JpaRepository<MovieRating,Integer> {

    List<MovieRating> findByAppUser_Username(String username);


}
