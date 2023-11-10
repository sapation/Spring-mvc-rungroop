package com.rungroop.web.services.impl;

import com.rungroop.web.dto.EventDto;
import com.rungroop.web.models.Club;
import com.rungroop.web.models.Event;
import com.rungroop.web.repository.IClubRepository;
import com.rungroop.web.repository.IEventRepository;
import com.rungroop.web.services.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements IEventService{
    private IEventRepository _eventRepository;
    private IClubRepository _clubRepository;

    @Autowired
    public EventServiceImpl(IEventRepository _eventRepository, IClubRepository _clubRepository) {
        this._eventRepository = _eventRepository;
        this._clubRepository = _clubRepository;
    }


    public void createEvent(Long clubId, EventDto eventDto) {
        Club club = _clubRepository.findById(clubId).get();
        Event event = mapToEvent(eventDto);
        event.setClub(club);
    }

    private Event mapToEvent(EventDto eventDto) {
        return Event.builder()
                .id(eventDto.getId())
                .name(eventDto.getName())
                .startTime(eventDto.getStartTime())
                .endTime(eventDto.getEndTime())
                .type(eventDto.getType())
                .photoUrl(eventDto.getPhotoUrl())
                .createdOn(eventDto.getCreatedOn())
                .updatedOn(eventDto.getUpdatedOn())
                .build();
    }
}
