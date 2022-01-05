package com.example.Neo4jDemo.controller.event;

import com.example.Neo4jDemo.model.Event;
import com.example.Neo4jDemo.model.User;
import com.example.Neo4jDemo.model.repository.EventRepository;
import com.example.Neo4jDemo.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @PostMapping
    public Event createEvent(@RequestBody CreateEventDTO createEventDTO) {
        Optional<User> optionalHost = userRepository.findById(createEventDTO.userId);
        if (!optionalHost.isPresent()) {
            throw new IllegalArgumentException("User with id {" + createEventDTO.userId + "} doesn't exist");
        }
        return eventRepository.save(new Event(optionalHost.get(), createEventDTO.tag, createEventDTO.openDate,
                createEventDTO.closeDate));
    }

    @PutMapping
    public Event updateEvent(@RequestBody UpdateEventDTO updateEventDTO) {
        Optional<Event> optionalEvent = eventRepository.findById(updateEventDTO.eventId);
        if (!optionalEvent.isPresent()) {
            throw new IllegalArgumentException("Event with id {" + updateEventDTO.eventId +"} doesn't exist");
        }
        Event event = optionalEvent.get();
        Set<User> participantsToRemove = userRepository.findAllByIdIn(updateEventDTO.participantsToRemove);
        Set<User> participantsToAdd = userRepository.findAllByIdIn(updateEventDTO.participantsToAdd);

        event.getParticipants().removeAll(participantsToRemove);
        event.getParticipants().addAll(participantsToAdd);
        if (updateEventDTO.tag != null) {
            event.setTag(updateEventDTO.tag);
        }
        if (updateEventDTO.openDate != null) {
            event.setOpenDate(updateEventDTO.openDate);
        }
        if (updateEventDTO.closeDate != null) {
            event.setCloseDate(updateEventDTO.closeDate);
        }

        return eventRepository.save(event);
    }

    @GetMapping("/all")
    public Collection<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @GetMapping("/{id}")
    public Event getEventById(@PathVariable Long id) {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if (!optionalEvent.isPresent()) {
            throw new IllegalArgumentException("Event with id {" + id +"} doesn't exist");
        }
        return optionalEvent.get();
    }

    @DeleteMapping("/{id}")
    public String deleteEventById(@PathVariable Long id) {
        if (eventRepository.existsById(id)) {
            eventRepository.deleteById(id);
            return "Deleted";
        }
        return "Doesn't exist";
    }

}

