package com.example.booking.application.Notifications;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.example.booking.domain.models.BookingConfirmedEvent;
import com.example.booking.infrastructure.Configurations.RabbitMQConfig;


@Component
public class BookingConfirmationEmailConsumer {

    @RabbitListener(queues = RabbitMQConfig.BOOKING_CONFIRMED_QUEUE)
    public void handleBookingConfirmed(BookingConfirmedEvent event) {

        // Simulate email sending
        System.out.println("📧 Sending booking confirmation email...");
        System.out.println("To: " + event.getUserEmail());
        System.out.println("Show ID: " + event.getShowId());
        System.out.println("Seat ID: " + event.getSeatId());
        System.out.println("Booked At: " + event.getBookedAt());
        System.out.println("✅ Email sent successfully");
    }
}