package com.example.booking.interfaces.Rest.Controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.booking.application.Events.VenueUseCase;
import com.example.booking.infrastructure.persistence.Entities.VenueEntity;

@RestController
@RequestMapping("/venues")
public class VenueController {
    
    private final VenueUseCase venue;

    
    public VenueController(VenueUseCase venue){
        this.venue = venue;
    }

    @PostMapping("/admin/create")
    public VenueEntity createVenue(@RequestBody VenueEntity venueEntity){
        return venue.createVenue(venueEntity);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/listAllVenues")
    public List<VenueEntity> listVenues(){
        return venue.listVenues();
    }

    @DeleteMapping("admin/delete/{id}")
    public void deleteVenue(@PathVariable Long id){
        venue.deleteVenue(id);
    }
}
