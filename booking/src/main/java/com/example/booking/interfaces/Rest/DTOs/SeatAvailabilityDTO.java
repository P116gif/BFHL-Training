package com.example.booking.interfaces.Rest.DTOs;

import com.example.booking.domain.models.SeatCategory;

public class SeatAvailabilityDTO {

    private SeatCategory category;
    private long availableSeats;

    // getters/setters

    public SeatCategory getCategory() {
        return category;
    }

    public void setCategory(SeatCategory category) {
        this.category = category;
    }

    public long getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Long seats){
        this.availableSeats = seats;
    }
}
