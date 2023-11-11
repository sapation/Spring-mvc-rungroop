package com.rungroop.web.services;

import com.rungroop.web.dto.EventDto;

import java.util.List;

public interface IEventService {
    void createEvent(Long clubId, EventDto eventDto);

    List<EventDto> findAllEvents();

    EventDto findByEventId(Long eventId);

    void updateEvent(EventDto eventDto);

    void delete(long eventId);
}
