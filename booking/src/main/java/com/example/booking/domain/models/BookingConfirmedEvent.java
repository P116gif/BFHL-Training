package com.example.booking.domain.models;

import java.time.LocalDateTime;

public class BookingConfirmedEvent {

    private Long showId;
    private Long seatId;
    private String userEmail;
    private LocalDateTime bookedAt;

    public BookingConfirmedEvent(Long showId, Long seatId, String userEmail) {
        this.showId = showId;
        this.seatId = seatId;
        this.userEmail = userEmail;
        this.bookedAt = LocalDateTime.now();
    }

    public Long getShowId() {
        return showId;
    }

    public Long getSeatId() {
        return seatId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public LocalDateTime getBookedAt() {
        return bookedAt;
    }
}