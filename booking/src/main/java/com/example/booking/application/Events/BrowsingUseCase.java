package com.example.booking.application.Events;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.booking.domain.models.SeatCategory;
import com.example.booking.domain.repository.EventRepository;
import com.example.booking.domain.repository.ShowRepository;
import com.example.booking.domain.repository.ShowSeatRepository;
import com.example.booking.infrastructure.persistence.Entities.ShowEntity;
import com.example.booking.interfaces.Rest.DTOs.EventBrowseDTO;
import com.example.booking.interfaces.Rest.DTOs.SeatAvailabilityDTO;
import com.example.booking.interfaces.Rest.DTOs.ShowDetailsDTO;
import com.example.booking.interfaces.Rest.DTOs.ShowSummaryDTO;


@Service
public class BrowsingUseCase {

    private final EventRepository eventRepo;
    private final ShowRepository showRepo;
    private final ShowSeatRepository showSeatRepo;

    public BrowsingUseCase(EventRepository eventRepo,
                         ShowRepository showRepo,
                         ShowSeatRepository showSeatRepo) {
        this.eventRepo = eventRepo;
        this.showRepo = showRepo;
        this.showSeatRepo = showSeatRepo;
    }

    // Browse events
    public List<EventBrowseDTO> browseEvents(
            String city,
            LocalDate date,
            String genre,
            String language) {

        return eventRepo.searchEvents(city, genre, language, date)
                .stream()
                .map(e -> {
                    EventBrowseDTO dto = new EventBrowseDTO();
                    dto.setEventId(e.getId());
                    dto.setTitle(e.getTitle());
                    dto.setGenre(e.getGenre());
                    dto.setLanguage(e.getLanguage());
                    dto.setDuration(e.getDuration());
                    dto.setRating(e.getRating());
                    return dto;
                })
                .toList();
    }

    // Shows grouped by date & venue
    public List<ShowSummaryDTO> getShows(Long eventId, LocalDate date) {

        return showRepo.findShowsForEvent(eventId, date)
                .stream()
                .map(s -> {
                    ShowSummaryDTO dto = new ShowSummaryDTO();
                    dto.setShowId(s.getId());
                    dto.setStartTime(s.getShowTime());
                    dto.setEndTime(s.getShowTime().plusMinutes(s.getEvent().getDuration()));
                    dto.setVenueName(s.getVenue().getName());
                    dto.setCity(s.getVenue().getCity());
                    dto.setAuditoriumName(s.getAuditorium().getName());
                    return dto;
                })
                .toList();
    }

    // Show details + seat availability
    public ShowDetailsDTO getShowDetails(Long showId) {

        ShowEntity show = showRepo.findById(showId)
                .orElseThrow(() -> new RuntimeException("Show not found"));

        List<SeatAvailabilityDTO> availability =
                showSeatRepo.countAvailableSeatsByCategory(showId)
                        .stream()
                        .map(row -> {
                            SeatAvailabilityDTO dto = new SeatAvailabilityDTO();
                            dto.setCategory((SeatCategory) row[0]);
                            dto.setAvailableSeats((Long) row[1]);
                            return dto;
                        })
                        .toList();

        ShowDetailsDTO dto = new ShowDetailsDTO();
        dto.setShowId(show.getId());
        dto.setStartTime(show.getShowTime());
        dto.setEndTime(show.getShowTime().plusMinutes(show.getEvent().getDuration()));
        dto.setVenueName(show.getVenue().getName());
        dto.setCity(show.getVenue().getCity());
        dto.setAuditoriumName(show.getAuditorium().getName());
        dto.setSeatAvailability(availability);

        return dto;
    }
}
