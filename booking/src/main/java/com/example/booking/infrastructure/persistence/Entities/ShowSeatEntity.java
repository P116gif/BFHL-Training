package com.example.booking.infrastructure.persistence.Entities;

import java.time.LocalDateTime;

import com.example.booking.domain.models.SeatStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name="show_seats", 
    uniqueConstraints=@UniqueConstraint(
        columnNames= {"show_id","seat_id"}
    )
)
public class ShowSeatEntity {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    private ShowEntity show;

    @ManyToOne(optional=false)
    private SeatEntity seat;

    @Enumerated(EnumType.STRING)
    private SeatStatus seatStatus;

    private LocalDateTime lockedAt;

    //getters

    public Long getId() {
        return id;
    }

    public ShowEntity getShow() {
        return show;
    }

    public SeatEntity getSeat() {
        return seat;
    }

    public SeatStatus getSeatStatus() {
        return seatStatus;
    }


    //setter
    public void setShow(ShowEntity show){
        this.show = show;
    }

    public void setSeat(SeatEntity seat){
        this.seat = seat;
    }

    public void setSeatStatus(SeatStatus seatStatus) {
        this.seatStatus = seatStatus;
    }

    public LocalDateTime getLockedAt() {
        return lockedAt;
    }

    public void setLockedAt(LocalDateTime lockedAt) {
        this.lockedAt = lockedAt;
    }

}
