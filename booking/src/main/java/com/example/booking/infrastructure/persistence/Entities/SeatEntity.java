/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.booking.infrastructure.persistence.Entities;

import com.example.booking.domain.models.SeatCategory;

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
@Table(name = "seats", 
    uniqueConstraints= @UniqueConstraint(columnNames={"auditorium_id", "seatNumber"})
)

public class SeatEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    private String seatNumber;

    private Integer rowNumber;


    @Enumerated(EnumType.STRING)
    private SeatCategory seatCategory;

    @ManyToOne(optional=false)
    private AuditoriumEntity auditorium;

    //getters
    public long getId() {
        return id;
    }
    public String getSeatNumber() {
        return seatNumber;
    }
    public Integer getRowNumber() {
        return rowNumber;
    }
    public SeatCategory getSeatCategory(){
        return seatCategory;
    }
    public AuditoriumEntity getAuditorium(){
        return auditorium;
    }

    //setters
    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }
    public void setRowNumber(Integer rowNumber) {
        this.rowNumber = rowNumber;
    }
    public void setSeatCategory(SeatCategory seat){
        this.seatCategory = seat;
    }
    public void setAuditorium(AuditoriumEntity auditorium){
        this.auditorium = auditorium;
    }

}
