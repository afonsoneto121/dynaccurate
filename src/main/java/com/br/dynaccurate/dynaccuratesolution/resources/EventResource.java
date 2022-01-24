package com.br.dynaccurate.dynaccuratesolution.resources;

import com.br.dynaccurate.dynaccuratesolution.model.Event;
import com.br.dynaccurate.dynaccuratesolution.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/v1/events")
@RequiredArgsConstructor
public class EventResource {
    private final EventService service;

    @GetMapping("/user/{idUser}")
    public ResponseEntity<Page<Event>> getAllEvents(@PathVariable String idUser,
                                                    @RequestParam LocalDateTime toDate,
                                                    @RequestParam LocalDateTime fromDate,
                                                    Pageable pageable) {
        Page<Event> allEvent = service.findEventByUser(idUser,toDate,fromDate, pageable);
        return ResponseEntity.ok(allEvent);
    }
}
