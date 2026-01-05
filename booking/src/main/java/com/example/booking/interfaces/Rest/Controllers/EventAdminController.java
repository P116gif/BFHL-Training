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

import com.example.booking.application.Events.EventUseCase;
import com.example.booking.infrastructure.persistence.Entities.EventEntity;
import com.example.booking.interfaces.Rest.DTOs.EventCreationRequest;

@RestController
@RequestMapping("admin/events")
@PreAuthorize("hasRole('ADMIN')")
public class EventAdminController {
    
    private final EventUseCase eventUseCase;

    public EventAdminController(EventUseCase eventUseCase){
        this.eventUseCase = eventUseCase;
    }

    @PostMapping("/create")
    public EventEntity createEvent(@RequestBody EventCreationRequest event){
        return eventUseCase.createEvent(event.title(), event.language(), event.genre(),
                                    event.duration(), event.rating());
    }

    @PostMapping("/update/{id}")
    public EventEntity updateEvent(@PathVariable Long id, @RequestBody EventCreationRequest event){
        return eventUseCase.updateEvent(id, event.title(), event.language(), event.genre(),
                                    event.duration(), event.rating());
    }

    @GetMapping("/eventList")
    public List<EventEntity> list(){
        return eventUseCase.listEvents();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEvent(@PathVariable Long id){
        eventUseCase.deleteEvent(id);
    }
}
