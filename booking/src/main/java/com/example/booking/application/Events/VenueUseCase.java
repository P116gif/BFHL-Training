package com.example.booking.application.Events;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.booking.domain.repository.VenueRepository;
import com.example.booking.infrastructure.persistence.Entities.VenueEntity;

@Service
public class VenueUseCase {

    private final VenueRepository venueRepo;

    public VenueUseCase(VenueRepository venueRepo) {
        this.venueRepo = venueRepo;
    }

    // Admin only functions
    public VenueEntity createVenue(VenueEntity venue) {
        return venueRepo.save(venue);
    }

    public List<VenueEntity> listVenues() {
        return venueRepo.findAll();
    }

    // ADMIN ONLY
    public void deleteVenue(Long venueId) {
        venueRepo.deleteById(venueId);
    }
}
