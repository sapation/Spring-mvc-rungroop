package com.rungroop.web.services.impl;

import com.rungroop.web.dto.EventDto;
import com.rungroop.web.models.Club;
import com.rungroop.web.models.Event;
import com.rungroop.web.repository.IClubRepository;
import com.rungroop.web.repository.IEventRepository;
import com.rungroop.web.services.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.rungroop.web.mapper.EventMapper.mapToEvent;
import static com.rungroop.web.mapper.EventMapper.mapToEventDto;

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
        _eventRepository.save(event);
    }

    @Override
    public List<EventDto> findAllEvents() {
        List<Event> events = _eventRepository.findAll();
        return events.stream().map(event -> mapToEventDto(event)).collect(Collectors.toList());
    }

    @Override
    public EventDto findByEventId(Long eventId) {
        Event event = _eventRepository.findById(eventId).get();
        return mapToEventDto(event);
    }

    @Override
    public void updateEvent(EventDto eventDto) {
        Event event = mapToEvent(eventDto);
        _eventRepository.save(event);
    }

    @Override
    public void delete(long eventId) {
        _eventRepository.deleteById(eventId);
    }

}
