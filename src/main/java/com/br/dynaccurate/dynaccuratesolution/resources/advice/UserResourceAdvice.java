package com.br.dynaccurate.dynaccuratesolution.resources.advice;

import com.br.dynaccurate.dynaccuratesolution.dto.Message;
import com.br.dynaccurate.dynaccuratesolution.exception.NotFound;
import com.br.dynaccurate.dynaccuratesolution.exception.UserAlreadyExist;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class UserResourceAdvice {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFound.class)
    public Message notFound() {
        return Message.builder().message("User not found").build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserAlreadyExist.class)
    public Message userAlreadyExists() {
        return Message.builder().message("User Already Exist").build();
    }
}
