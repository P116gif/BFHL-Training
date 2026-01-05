package com.example.booking.interfaces.Rest.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.booking.application.Events.AuditoriumUseCase;
import com.example.booking.interfaces.Rest.DTOs.AuditoriumRequest;
import com.example.booking.interfaces.Rest.DTOs.SeatCreationRequest;

@RestController
@RequestMapping("/admin/venues")
@PreAuthorize("hasRole('ADMIN')")
public class AuditoriumController {
    
    private final AuditoriumUseCase auditorium;

    public AuditoriumController(AuditoriumUseCase auditoriumUseCase){
        this.auditorium = auditoriumUseCase;
    }

    @PostMapping("/{venueId}/createAuditorium")
    public ResponseEntity<Void> createAuditorium(@PathVariable Long venueId, 
                @RequestBody AuditoriumRequest auditoriumRequest){
      
        auditorium.createAuditorium(venueId, auditoriumRequest.name());
        
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/{venueId}/{auditoriumId}")
    public ResponseEntity<Void> addSeat(@PathVariable Long auditoriumId, 
        @RequestBody SeatCreationRequest seat){

        auditorium.addSeat(auditoriumId, seat.seatNumber(), seat.rowNumber());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    

    
}
