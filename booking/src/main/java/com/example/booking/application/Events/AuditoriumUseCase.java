package com.example.booking.application.Events;

import org.springframework.stereotype.Service;

import com.example.booking.domain.repository.AuditoriumRepository;
import com.example.booking.domain.repository.VenueRepository;
import com.example.booking.infrastructure.persistence.Entities.AuditoriumEntity;
import com.example.booking.infrastructure.persistence.Entities.SeatEntity;
import com.example.booking.infrastructure.persistence.Entities.VenueEntity;

@Service
public class AuditoriumUseCase {
    
    private final AuditoriumRepository auditoriumRepository;
    private final VenueRepository venueRepository;

    public AuditoriumUseCase(AuditoriumRepository auditoriumRepo, 
                VenueRepository venueRepository) {
        this.auditoriumRepository = auditoriumRepo;
        this.venueRepository = venueRepository;
    }

    // Admin only funcition

    public AuditoriumEntity createAuditorium(Long venueId, String name){

        VenueEntity venue = venueRepository.findById(venueId)
                            .orElseThrow(() -> new RuntimeException("Venue not found"));
        
        AuditoriumEntity auditorium = new AuditoriumEntity();
        auditorium.setName(name);
        auditorium.setVenue(venue);
        auditorium.setLocked(false);

        return auditoriumRepository.save(auditorium);
    }

    public SeatEntity addSeat(Long auditoriumId, String seatNumber, Integer rowNumber) {
        
        AuditoriumEntity auditorium = auditoriumRepository.findById(auditoriumId)
                .orElseThrow(() -> new RuntimeException("Auditorium not found"));

        if (auditorium.isLocked()) {
            throw new IllegalStateException("Seat layout cannot be modified after shows are created");
        }
        
        SeatEntity seat = new SeatEntity();
        seat.setSeatNumber(seatNumber);
        seat.setRowNumber(rowNumber);
        seat.setAuditorium(auditorium);
        auditorium.getSeats().add(seat);
        auditoriumRepository.save(auditorium);
        return seat;
    }

    // Lock layout once a show is published
    public void lockAuditorium(Long auditoriumId) {
        AuditoriumEntity auditorium = auditoriumRepository.findById(auditoriumId)
                .orElseThrow(() -> new RuntimeException("Auditorium not found"));
        auditorium.setLocked(true);
        auditoriumRepository.save(auditorium);
    }
}
