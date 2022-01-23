package com.br.dynaccurate.dynaccuratesolution.service;

import com.br.dynaccurate.dynaccuratesolution.exception.NotFound;
import com.br.dynaccurate.dynaccuratesolution.exception.UserAlreadyExist;
import com.br.dynaccurate.dynaccuratesolution.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    User save(User user) throws UserAlreadyExist;
    User userById(String id) throws NotFound;
    Page<User> getAllUser(Pageable pageable);
    User updateUser(String id, User user) throws NotFound, UserAlreadyExist;
    void delete(String id);

}
