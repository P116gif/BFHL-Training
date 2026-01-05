package com.example.booking.infrastructure.persistence.Entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "events")
public class EventEntity {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String language;
    private String genre;
    private Integer duration;
    private String rating;

    @OneToMany(mappedBy="event", cascade=CascadeType.ALL, orphanRemoval=true)
    private final List<ShowEntity> shows = new ArrayList<>();

    //getters
    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getLanguage() {
        return language;
    }
    public String getGenre() {
        return genre;
    }
    public Integer getDuration() {
        return duration;
    }
    public String getRating() {
        return rating;
    }
    public List<ShowEntity> getShows(){
        return shows;
    }

    //setters
    public void setTitle(String title) {
        this.title = title;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public void setDuration(Integer duration) {
        this.duration = duration;
    }
    public void setRating(String rating) {
        this.rating = rating;
    }
}
