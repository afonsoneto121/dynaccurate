package com.br.dynaccurate.dynaccuratesolution.service.implementation;

import com.br.dynaccurate.dynaccuratesolution.model.Event;
import com.br.dynaccurate.dynaccuratesolution.repository.EventRepository;
import com.br.dynaccurate.dynaccuratesolution.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
        fromDate = fromDate == null ? LocalDateTime.now().minusDays(1): fromDate;

        return repository.findEventByUser(nicknameUser, toDate, fromDate, pageable);
    }
}
