package com.example.webbutvecklingvgdelen.dto;


// Klassen är till för klientens förfrågningar, d.v.s klienten requestar(frågar) om något

public class MovieRequestDTO {

    private String title;
    private String rating;
    private int appuser_id;

    public MovieRequestDTO(String title, String rating, int appuser_id) {
        this.title = title;
        this.rating = rating;
        this.appuser_id = appuser_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getAppuser_id() {
        return appuser_id;
    }

    public void setAppuser_id(int appuser_id) {
        this.appuser_id = appuser_id;
    }
}
