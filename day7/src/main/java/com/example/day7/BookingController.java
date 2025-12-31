package com.example.day7;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @GetMapping
    public String getBookings(Authentication authentication) {
        return "Bookings for user: " + authentication.getName();
    }

    @PostMapping
    public String createBooking() {
        return "Booking created";
    }
}

