package com.br.dynaccurate.dynaccuratesolution.service;

import com.br.dynaccurate.dynaccuratesolution.model.Event;
import com.br.dynaccurate.dynaccuratesolution.repository.EventRepository;
import com.br.dynaccurate.dynaccuratesolution.service.implementation.EventServiceImp;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Arrays;

import static com.br.dynaccurate.dynaccuratesolution.util.GenerateObjects.getEvent;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {

    @Mock
    private EventRepository repository;

    @InjectMocks
    private EventServiceImp service;

    private Event event;

    @BeforeEach
    public void setup() {
        event = getEvent();
    }

    /*
    When find events by user
    Then A page should be returned
     */
    @Test
    public void getAllEventByUser() {
        String userName = "Afonso";
        LocalDateTime toDate = LocalDateTime.now();
        LocalDateTime fromDate = LocalDateTime.now().minusDays(1);

        Pageable pageable = PageRequest.of(0,2);
        Page<Event> page = new PageImpl(Arrays.asList(event,event,event,event), pageable, 4);
        Mockito.when(repository.findEventByUser(userName,toDate,fromDate,pageable)).thenReturn(page);

        Page<Event> eventByUser = service.findEventByUser(userName, toDate, fromDate, pageable);

        MatcherAssert.assertThat(eventByUser.getTotalPages(), Matchers.equalTo(2));
        MatcherAssert.assertThat(eventByUser.getSize(), Matchers.equalTo(2));
        MatcherAssert.assertThat(eventByUser.getTotalElements(), Matchers.equalTo(4l));

    }
}
