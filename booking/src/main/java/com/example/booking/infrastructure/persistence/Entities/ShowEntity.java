package com.example.booking.infrastructure.persistence.Entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "shows",
        uniqueConstraints= @UniqueConstraint(columnNames={"auditorium_id", "showTime"}))
public class ShowEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime showTime;

    @ManyToOne(optional=false)
    private EventEntity event;

    @ManyToOne(optional=false)
    private VenueEntity venue;

    @ManyToOne(optional=false)
    private AuditoriumEntity auditorium;

    private boolean bookingOpen;

    //getters
    public Long getId() {
        return id;
    }
    public LocalDateTime getShowTime() {
        return showTime;
    }

    //setters
    public void setShowTime(LocalDateTime showTime) {
        this.showTime = showTime;
    }

    public AuditoriumEntity getAuditorium() {
        return auditorium;
    }

    public void setAuditorium(AuditoriumEntity auditorium) {
        this.auditorium = auditorium;
    }

    public EventEntity getEvent() {
        return event;
    }

    public void setEvent(EventEntity event) {
        this.event = event;
    }

    public VenueEntity getVenue() {
        return venue;
    }

    public void setVenue(VenueEntity venue) {
        this.venue = venue;
    }

    public boolean isBookingOpen() {
        return bookingOpen;
    }

    public void setBookingOpen(boolean bookingOpen) {
        this.bookingOpen = bookingOpen;
    }
    
}
