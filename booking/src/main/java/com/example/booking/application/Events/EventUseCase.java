package com.example.booking.application.Events;

import java.util.List;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.stereotype.Service;

import com.example.booking.domain.repository.EventRepository;
import com.example.booking.domain.repository.ShowRepository;
import com.example.booking.infrastructure.persistence.Entities.EventEntity;
import com.example.booking.infrastructure.persistence.Entities.ShowEntity;

@Service
@DynamicUpdate
public class EventUseCase {
    
    private final EventRepository eventRepository;
    private final ShowRepository showRepository;

    public EventUseCase(EventRepository eventRepository, ShowRepository showRepository) {
        this.eventRepository = eventRepository;
        this.showRepository = showRepository;
    }


    public List<EventEntity> listEvents(){
        return eventRepository.findAll();
    }

    //ADMIN ONLY functions
    public EventEntity createEvent(String title, String language, String genre, Integer duration, String rating) {
        
        EventEntity event = new EventEntity();
        event.setTitle(title);
        event.setDuration(duration);
        event.setLanguage(language);
        event.setGenre(genre);
        event.setRating(rating);
        return eventRepository.save(event);
    }

    //for partial updates we can make a DTO or class and use a mapper b/w the DTO and entity
    //ensuring that only non null values are changed and the rest remain the same
    public EventEntity updateEvent(Long eventId,  String title, String language, String genre, Integer duration, String rating) {

        EventEntity event = eventRepository.findById(eventId)
                            .orElseThrow(() -> new RuntimeException("Event Not found"));
       
        event.setTitle(title);
        event.setDuration(duration);
        event.setLanguage(language);
        event.setGenre(genre);
        event.setRating(rating);
        return eventRepository.save(event);
    }

    public void deleteEvent(Long id){
        eventRepository.deleteById(id);
    }

    public ShowEntity createShow(ShowEntity show){
        return showRepository.save(show);
    }

    public ShowEntity updateShow(Long showId, ShowEntity updatedShow){

        ShowEntity show = showRepository.findById(showId)
                            .orElseThrow(() -> new RuntimeException("Show not found"));

        show.setShowTime(updatedShow.getShowTime());

        return showRepository.save(show);
    }



    


}
