package com.example.booking.application.Events;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.booking.domain.models.BookingConfirmedEvent;
import com.example.booking.domain.models.SeatStatus;
import com.example.booking.domain.models.User;
import com.example.booking.domain.repository.ShowSeatRepository;
import com.example.booking.domain.repository.UserRepository;
import com.example.booking.infrastructure.Messaging.BookingEventPublisher;
import com.example.booking.infrastructure.persistence.Entities.ShowSeatEntity;

import jakarta.transaction.Transactional;


@Service
public class SeatBookingUseCase {

    private final ShowSeatRepository showSeatRepository;
    private final BookingEventPublisher bookingEventPublisher;
    private final UserRepository userRepository;

    public SeatBookingUseCase(ShowSeatRepository showSeatRepository, 
            BookingEventPublisher bookingEventPublisher, UserRepository userRepository){
        
                this.showSeatRepository = showSeatRepository;
        this.bookingEventPublisher = bookingEventPublisher;
        this.userRepository = userRepository;
    }


    @Transactional
    public void bookSeat(Long showId, Long seatId, UUID userId) {

        //first LOCK
        ShowSeatEntity seat = showSeatRepository.findByShowIdAndSeatId(showId, seatId)
                                .orElseThrow(() -> new RuntimeException("Seat not found"));
    
        if(seat.getSeatStatus() != SeatStatus.AVAILABLE){
            throw new IllegalStateException("Seat not available");
        }

        seat.setSeatStatus(SeatStatus.LOCKED);
        seat.setLockedAt(LocalDateTime.now());

        //then BOOK
        seat.setSeatStatus(SeatStatus.BOOKED);
        showSeatRepository.save(seat);

        User user = userRepository.findById(userId)
                        .orElseThrow();

        BookingConfirmedEvent event =
                new BookingConfirmedEvent(showId, seatId, user.getEmail());

        bookingEventPublisher.publishBookingConfirmed(event);
    }

    @Transactional
    public void releaseSeat(Long showId, Long seatId){

        ShowSeatEntity seat = showSeatRepository
                .findByShowIdAndSeatId(showId, seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found"));

        if(seat.getSeatStatus() == SeatStatus.LOCKED){
            seat.setSeatStatus(SeatStatus.AVAILABLE);
        }
    }

    @Scheduled(fixedDelay=60000)
    @Transactional
    public void releaseExpiredLocks() {
        
        LocalDateTime cutoff = LocalDateTime.now().minusMinutes(5);

        List<ShowSeatEntity> expired = showSeatRepository
                                    .findBySeatStatusAndLockedAtBefore(SeatStatus.LOCKED, cutoff);

        for(ShowSeatEntity seat: expired){
            seat.setSeatStatus(SeatStatus.AVAILABLE);
            seat.setLockedAt(null);
        }
    } 
}
