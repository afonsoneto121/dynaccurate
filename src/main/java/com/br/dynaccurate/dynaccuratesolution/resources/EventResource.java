package com.br.dynaccurate.dynaccuratesolution.resources;

import com.br.dynaccurate.dynaccuratesolution.model.Event;
import com.br.dynaccurate.dynaccuratesolution.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
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

    @GetMapping("/user/{nicknameUser}")
    public ResponseEntity<Page<Event>> getAllEvents(@PathVariable String nicknameUser,
                                                    @RequestParam(required = false, name = "toDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime toDate,
                                                    @RequestParam(required = false, name = "fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromDate,
                                                    Pageable pageable) {
        Page<Event> allEvent = service.findEventByUser(nicknameUser,toDate,fromDate, pageable);
        return ResponseEntity.ok(allEvent);
    }
}
