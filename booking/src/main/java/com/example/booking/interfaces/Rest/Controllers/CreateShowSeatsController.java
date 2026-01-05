package com.example.booking.interfaces.Rest.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.booking.application.Events.ShowSeatUseCase;

@RestController
@RequestMapping("/api/shows")
@PreAuthorize("hasRole('ADMIN')")
public class CreateShowSeatsController {
    
    private final ShowSeatUseCase show;

    public CreateShowSeatsController(ShowSeatUseCase show){
        this.show = show;
    }

    @PostMapping("/{showId}/initialise")
    public ResponseEntity<Void> initialiseShow(@PathVariable Long showId){
        
        show.initialiseSeatsforShow(showId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
