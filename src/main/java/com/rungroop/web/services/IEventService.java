package com.rungroop.web.services;

import com.rungroop.web.dto.EventDto;

public interface IEventService {
    void createEvent(Long clubId, EventDto eventDto);
}
