package com.br.dynaccurate.dynaccuratesolution.service.implementation;

import com.br.dynaccurate.dynaccuratesolution.model.Event;
import com.br.dynaccurate.dynaccuratesolution.repository.EventRepository;
import com.br.dynaccurate.dynaccuratesolution.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EventServiceImp implements EventService {
    private final EventRepository repository;
    @Override
    public Event save(Event event) {
        return repository.save(event);
    }

    @Override
    public Page<Event> findEventByUser(String nicknameUser, LocalDateTime toDate, LocalDateTime fromDate, Pageable pageable) {
        toDate = toDate == null ? LocalDateTime.now(): toDate;
        fromDate = fromDate == null ? LocalDateTime.now(): fromDate;

        return repository.findEventByUser(nicknameUser, toDate, fromDate, pageable);
    }
}
