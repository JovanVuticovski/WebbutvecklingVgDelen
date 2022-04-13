package com.example.webbutvecklingvgdelen.entities;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
public class MovieRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @NotBlank
    private String title;

    @Column
    @NotBlank
    private String rating;

    @ManyToOne
    @JoinColumn(name = "appuser_id")
    private AppUser appUser;

    public MovieRating(String title, String rating, AppUser appUser) {
        this.title = title;
        this.rating = rating;
        this.appUser = appUser;
    }

    public MovieRating() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "MovieRating{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", rating='" + rating + '\'' +
                ", appUser=" + appUser +
                '}';
    }
}
