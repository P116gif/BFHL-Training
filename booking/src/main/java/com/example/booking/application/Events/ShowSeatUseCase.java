package com.example.booking.application.Events;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.booking.domain.models.SeatStatus;
import com.example.booking.domain.repository.ShowRepository;
import com.example.booking.domain.repository.ShowSeatRepository;
import com.example.booking.infrastructure.persistence.Entities.AuditoriumEntity;
import com.example.booking.infrastructure.persistence.Entities.SeatEntity;
import com.example.booking.infrastructure.persistence.Entities.ShowEntity;
import com.example.booking.infrastructure.persistence.Entities.ShowSeatEntity;

import jakarta.transaction.Transactional;

@Service
public class ShowSeatUseCase {
    
    private final ShowSeatRepository showSeatRepository;
    private final ShowRepository showRepository;
    
    public ShowSeatUseCase(ShowSeatRepository showSeatRepository, ShowRepository showRepository){
        
        this.showSeatRepository = showSeatRepository;
        this.showRepository = showRepository;
    }

    //admin only function
    @Transactional
    public void initialiseSeatsforShow(Long showId){

        ShowEntity show = showRepository.findById(showId)
                            .orElseThrow(() -> new RuntimeException("Show id not found"));
        
        AuditoriumEntity auditorium = show.getAuditorium();
        
        //check if audi has seat layout
        if(auditorium.getSeats().isEmpty()){
            throw new RuntimeException("Auditorium seat layout not created");
        }

        //lock layout
        auditorium.setLocked(true);

        for(SeatEntity seat: auditorium.getSeats()){
            
            ShowSeatEntity ss = new ShowSeatEntity();
            ss.setShow(show);
            ss.setSeat(seat);
            ss.setSeatStatus(SeatStatus.AVAILABLE);
            showSeatRepository.save(ss);
        }
    }

    public List<ShowSeatEntity> getAvailableSeats(Long showId){
        return showSeatRepository.findByShowId(showId)
                .stream()  
                .filter(s -> s.getSeatStatus() == SeatStatus.AVAILABLE)
                .toList();
    }
}
