package com.br.dynaccurate.dynaccuratesolution.service.implementation;


import com.br.dynaccurate.dynaccuratesolution.exception.NotFound;
import com.br.dynaccurate.dynaccuratesolution.exception.UserAlreadyExist;
import com.br.dynaccurate.dynaccuratesolution.model.User;
import com.br.dynaccurate.dynaccuratesolution.repository.UserRepository;
import com.br.dynaccurate.dynaccuratesolution.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserRepository repository;

    public User save(User user) throws UserAlreadyExist {
        findUserAlreadyExist(user.getNickname());
        user.setRegistrationDate(LocalDate.now());
        return repository.save(user);
    }


    private void findUserAlreadyExist(String nickname) throws UserAlreadyExist {
        Optional<User> byNickname = repository.findByNickname(nickname);
        if (byNickname.isPresent()) {
            throw new UserAlreadyExist("User already exist");
        }

    }
    public User userById(String id) throws NotFound {
        Optional<User> byId = repository.findById(id);
        return byId.orElseThrow(() -> new NotFound("User not found"));
    }

    public Page<User> getAllUser(Pageable pageable) {
        return repository.findAll(pageable);
    }
    public User updateUser(String id, User user) throws NotFound, UserAlreadyExist {
        Optional<User> userOptional = repository.findById(id);
        User userDB = userOptional.orElseThrow(() -> new NotFound("User not found"));
        findUserAlreadyExist(user.getNickname());
        userDB.setNickname(user.getNickname());
        return repository.save(userDB);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

}
