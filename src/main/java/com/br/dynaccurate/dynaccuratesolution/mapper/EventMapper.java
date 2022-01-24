package com.br.dynaccurate.dynaccuratesolution.mapper;

import com.br.dynaccurate.dynaccuratesolution.dto.response.EventDTOResponse;
import com.br.dynaccurate.dynaccuratesolution.model.Event;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {
    ObjectMapper mapper;

    public Event dtoResponseToModel(EventDTOResponse eventDTOResponse) {
        return mapper.convertValue(eventDTOResponse, Event.class);
    }

    public EventDTOResponse modelToTDOResponse(Event event) {
        return mapper.convertValue(event, EventDTOResponse.class);
    }
}
