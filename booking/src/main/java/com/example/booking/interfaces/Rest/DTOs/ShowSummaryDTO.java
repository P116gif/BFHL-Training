package com.example.booking.interfaces.Rest.DTOs;

import java.time.LocalDateTime;

public class ShowSummaryDTO {

    private Long showId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private String venueName;
    private String city;
    private String auditoriumName;

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

    public String getAuditoriumName() {
        return auditoriumName;
    }

    public void setAuditoriumName(String auditoriumName) {
        this.auditoriumName = auditoriumName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
