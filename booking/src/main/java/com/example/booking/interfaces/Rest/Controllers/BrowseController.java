package com.example.booking.interfaces.Rest.Controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.booking.application.Events.BrowsingUseCase;
import com.example.booking.interfaces.Rest.DTOs.EventBrowseDTO;
import com.example.booking.interfaces.Rest.DTOs.ShowDetailsDTO;
import com.example.booking.interfaces.Rest.DTOs.ShowSummaryDTO;

@RestController
@RequestMapping("/api/browse")
public class BrowseController {

    private final BrowsingUseCase browsingUseCase;

    public BrowseController(BrowsingUseCase browsingUseCase) {
        this.browsingUseCase = browsingUseCase;
    }

    // Browse events
    @GetMapping("/events")
    public List<EventBrowseDTO> browseEvents(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String language,
            @RequestParam(required = false) @DateTimeFormat(iso = ISO.DATE)
                    LocalDate date) {

        return browsingUseCase.browseEvents(city, date, genre, language);
    }

    // Shows by event
    @GetMapping("/events/{eventId}/shows")
    public List<ShowSummaryDTO> getShows(
            @PathVariable Long eventId,
            @RequestParam(required = false)
            @DateTimeFormat(iso = ISO.DATE) LocalDate date) {

        return browsingUseCase.getShows(eventId, date);
    }

    // Show details
    @GetMapping("/shows/{showId}")
    public ShowDetailsDTO getShowDetails(@PathVariable Long showId) {
        return browsingUseCase.getShowDetails(showId);
    }
}
