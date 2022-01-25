package com.br.dynaccurate.dynaccuratesolution.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventDTOResponse {
    private String id;
    private String type;
    private LocalDateTime dateTime;
    String nicknameUser;
}
