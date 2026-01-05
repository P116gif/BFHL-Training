/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.booking.infrastructure.persistence.Entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "auditoriums")
public class AuditoriumEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(optional=false)
    private VenueEntity venue;

    @OneToMany(mappedBy="auditorium", cascade=CascadeType.ALL)
    private final List<SeatEntity> seats = new ArrayList<>();

    private boolean locked;


    //getters
    public Long getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public boolean isLocked(){
        return locked;
    }
    public VenueEntity getVenue(){
        return venue;
    }
    public List<SeatEntity> getSeats(){
        return seats;
    }

    //setters
    public void setName(String name){
        this.name = name;
    }
    public void setVenue(VenueEntity venue){
        this.venue = venue;
    }
    public void setLocked(boolean lock){
        this.locked = lock;
    }


}
