package com.example.booking.interfaces.Rest.DTOs;

public class EventBrowseDTO {

    private Long eventId;
    private String title;
    private String genre;
    private String language;
    private Integer duration;
    private String rating;

    // getters/setters

    public Long getEventId() {
        return eventId;
    }
    public void setEventId(Long id){
        this.eventId = id;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
