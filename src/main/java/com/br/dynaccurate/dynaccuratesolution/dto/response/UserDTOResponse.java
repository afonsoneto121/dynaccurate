package com.br.dynaccurate.dynaccuratesolution.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserDTOResponse {
    private String id;
    private String nickname;
    private LocalDate registrationDate;
}
