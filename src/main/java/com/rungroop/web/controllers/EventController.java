package com.rungroop.web.controllers;

import com.rungroop.web.dto.EventDto;
import com.rungroop.web.models.Event;
import com.rungroop.web.services.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EventController {
    private IEventService _eventService;
    @Autowired
    public EventController(IEventService _eventService) {
        this._eventService = _eventService;
    }

    @GetMapping("/events/{clubId}/new")
    public String createEventForm(@PathVariable("clubId") Long clubId, Model model) {
        Event event = new Event();
        model.addAttribute("clubId", clubId);
        model.addAttribute("event", event);
        return "event-create";
    }

    @PostMapping("/events/{clubid}/new")
    public String createEvent(@PathVariable("clubId") Long clubId,
                              @ModelAttribute("event")
                              EventDto eventDto,
                              Model model) {
        _eventService.createEvent(clubId, eventDto);
        return "redirect:/clubs/" + clubId;
    }
}
