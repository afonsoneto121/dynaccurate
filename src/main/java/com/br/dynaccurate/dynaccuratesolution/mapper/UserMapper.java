package com.br.dynaccurate.dynaccuratesolution.mapper;


import com.br.dynaccurate.dynaccuratesolution.dto.request.UserDTORequest;
import com.br.dynaccurate.dynaccuratesolution.dto.response.UserDTOResponse;
import com.br.dynaccurate.dynaccuratesolution.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final ObjectMapper objectMapper;

    public User dtoRequestToUser(UserDTORequest dtoRequest) {
        return objectMapper.convertValue(dtoRequest, User.class);
    }
    public UserDTOResponse userToDTOResponse(User user) {
        return objectMapper.convertValue(user, UserDTOResponse.class);
    }
}
