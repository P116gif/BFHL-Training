package com.example.booking.interfaces.Rest.DTOs;

import java.time.LocalDateTime;
import java.util.List;

public class ShowDetailsDTO {

    private Long showId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private String venueName;
    private String city;

    private String auditoriumName;

    private List<SeatAvailabilityDTO> seatAvailability;

    // getters/setters

    public Long getShowId() {
        return showId;
    }
    public void setShowId(Long id){
        this.showId = id;
    }
    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAuditoriumName() {
        return auditoriumName;
    }

    public void setAuditoriumName(String auditoriumName) {
        this.auditoriumName = auditoriumName;
    }

    public List<SeatAvailabilityDTO> getSeatAvailability() {
        return seatAvailability;
    }

    public void setSeatAvailability(List<SeatAvailabilityDTO> seats){
        this.seatAvailability = seats;
    }
}
