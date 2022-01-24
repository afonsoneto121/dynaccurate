package com.br.dynaccurate.dynaccuratesolution.util;

import com.br.dynaccurate.dynaccuratesolution.dto.request.UserDTORequest;
import com.br.dynaccurate.dynaccuratesolution.dto.response.UserDTOResponse;
import com.br.dynaccurate.dynaccuratesolution.model.User;

import java.time.LocalDate;

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

}