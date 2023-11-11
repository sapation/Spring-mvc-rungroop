package com.rungroop.web.controllers;

import com.rungroop.web.dto.ClubDto;
import com.rungroop.web.dto.EventDto;
import com.rungroop.web.models.Event;
import com.rungroop.web.services.IEventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EventController {
    private IEventService _eventService;
    @Autowired
    public EventController(IEventService _eventService) {
        this._eventService = _eventService;
    }

    @GetMapping("/events")
    public String eventList(Model model) {
        List<EventDto> events = _eventService.findAllEvents();
        model.addAttribute("events", events);
        return "event-list";
    }

    @GetMapping("/events/{eventId}")
    public String viewEvent(@PathVariable("eventId")Long eventId, Model model) {
        EventDto eventDto = _eventService.findByEventId(eventId);
        model.addAttribute("event", eventDto);

        return  "event-detail";
    }


    @GetMapping("/events/{clubId}/new")
    public String createEventForm(@PathVariable("clubId") Long clubId, Model model) {
        Event event = new Event();
        model.addAttribute("clubId", clubId);
        model.addAttribute("event", event);
        return "event-create";
    }

    @PostMapping("/events/{clubId}/new")
    public String createEvent(@PathVariable("clubId") Long clubId,
                              @ModelAttribute("event")
                              EventDto eventDto,
                              Model model, BindingResult result) {
        if (result.hasErrors()){
            model.addAttribute("event", eventDto);
            return "event-create";
        }
        _eventService.createEvent(clubId, eventDto);
        return "redirect:/clubs/" + clubId;
    }

    @GetMapping("events/{eventId}/edit")
    public String editClubForm(@PathVariable("eventId") long eventId, Model model) {
        EventDto event = _eventService.findByEventId(eventId);
        model.addAttribute("event",event);

        return "event-edit";
    }

    @PostMapping("events/{eventId}/edit")
    public String updateClub(@PathVariable("eventId") Long eventId,
                             @Valid @ModelAttribute("event") EventDto event,
                             BindingResult result, Model model) {
        if (result.hasErrors()){
            model.addAttribute("event", event);
            return "event-edit";
        }
        EventDto eventDto = _eventService.findByEventId(eventId);
        event.setId(eventId);
        event.setClub(eventDto.getClub());
        _eventService.updateEvent(event);

        return "redirect:/events";
    }

    @GetMapping("/events/{eventId}/delete")
    public String deleteClub(@PathVariable("eventId") long eventId) {
        _eventService.delete(eventId);
        return "redirect:/events";
    }
}
