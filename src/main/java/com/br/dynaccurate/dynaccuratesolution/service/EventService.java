package com.br.dynaccurate.dynaccuratesolution.service;

import com.br.dynaccurate.dynaccuratesolution.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface EventService {
    Event save(Event event);

    Page<Event> findEventByUser(String nicknameUser, LocalDateTime toDate, LocalDateTime fromDate, Pageable pageable);
}
