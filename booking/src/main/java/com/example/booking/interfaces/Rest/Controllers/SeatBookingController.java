package com.example.booking.interfaces.Rest.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.booking.application.Events.SeatBookingUseCase;
import com.example.booking.interfaces.Rest.DTOs.JwtPrincipal;

@RestController
@RequestMapping("/shows/{showId}/seats/{seatId}")
public class SeatBookingController {

    private final SeatBookingUseCase seatBookingUseCase;

    public SeatBookingController(SeatBookingUseCase seatBookingUseCase) {
        this.seatBookingUseCase = seatBookingUseCase;
    }

    @PostMapping("/book/{showId}/{seatId}")
    public ResponseEntity<Void> bookSeat(
            @PathVariable Long showId,
            @PathVariable Long seatId,
            @AuthenticationPrincipal JwtPrincipal jwtPrincipal) {

        seatBookingUseCase.bookSeat(showId, seatId, jwtPrincipal.uuid());
        return ResponseEntity.noContent().build();
    }
}
