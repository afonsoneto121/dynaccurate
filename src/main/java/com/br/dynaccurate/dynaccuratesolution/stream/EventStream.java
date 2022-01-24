package com.br.dynaccurate.dynaccuratesolution.stream;

import com.br.dynaccurate.dynaccuratesolution.model.Event;
import com.br.dynaccurate.dynaccuratesolution.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
@Slf4j
public class EventStream {
    private final EventService service;
    @Bean
    public Consumer<Event> eventsLog() {
        return (msg) -> {
            log.info("Event received");
            service.save(msg);
            log.info("Event saved [ " + msg +" ] ");
        };
    }
}
