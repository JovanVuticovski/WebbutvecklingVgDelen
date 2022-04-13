package com.example.webbutvecklingvgdelen.dto;


// DTO används för att anpassa informationen som ska skickas ut baserat på om det är en förfrågan från klienten
// Eller om det är ett svar(Response) till klienten
// Därför skapas DTO objekt för att skilja på de olika anropen



// Använder klassen BlogResponseDTO för att specifikt hämta ut info som ska returneras som (Response) på klientens request

public class MovieResponseDTO {

    // Används för att skicka iväg responds(svar) till klienten
    // När MovieResponseDTO kallas så printas bara dessa kolumner med information ut till klienten

    private int id;
    private String title;
    private String rating;
    private int appUserId;

    public MovieResponseDTO(int id, String title, String rating, int appUserId) {
        this.id = id;
        this.title = title;
        this.rating = rating;
        this.appUserId = appUserId;
    }

    public MovieResponseDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(int appUserId) {
        this.appUserId = appUserId;
    }
}



