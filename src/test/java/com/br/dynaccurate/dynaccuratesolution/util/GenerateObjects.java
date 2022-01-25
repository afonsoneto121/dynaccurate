package com.br.dynaccurate.dynaccuratesolution.util;

import com.br.dynaccurate.dynaccuratesolution.dto.request.UserDTORequest;
import com.br.dynaccurate.dynaccuratesolution.dto.response.EventDTOResponse;
import com.br.dynaccurate.dynaccuratesolution.dto.response.UserDTOResponse;
import com.br.dynaccurate.dynaccuratesolution.model.Event;
import com.br.dynaccurate.dynaccuratesolution.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class GenerateObjects {
    public static User getUser() {
        return User.builder()
                .id("1")
                .nickname("Afonso")
                .registrationDate(LocalDate.now())
                .build();
    }
    public static UserDTOResponse getUserDTOResponse() {
        return UserDTOResponse.builder()
                .id("1")
                .nickname("Afonso")
                .registrationDate(LocalDate.now())
                .build();
    }
    public static UserDTORequest getUserDTORequest() {
        return UserDTORequest.builder()
                .nickname("Afonso")
                .build();
    }
    public static Event getEvent() {
        return Event.builder()
                .id("2")
                .nicknameUser("Afonso")
                .type("event 1")
                .dateTime(LocalDateTime.now())
                .build();
    }

    public static EventDTOResponse getEventDTOResponse() {
        return EventDTOResponse.builder()
                .id("2")
                .nicknameUser("Afonso")
                .type("event 1")
                .dateTime(LocalDateTime.now())
                .build();
    }
}