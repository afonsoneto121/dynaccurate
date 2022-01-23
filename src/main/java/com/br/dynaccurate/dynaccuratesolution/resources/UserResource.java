package com.br.dynaccurate.dynaccuratesolution.resources;

import com.br.dynaccurate.dynaccuratesolution.dto.request.UserDTORequest;
import com.br.dynaccurate.dynaccuratesolution.dto.response.UserDTOResponse;
import com.br.dynaccurate.dynaccuratesolution.exception.NotFound;
import com.br.dynaccurate.dynaccuratesolution.exception.UserAlreadyExist;
import com.br.dynaccurate.dynaccuratesolution.mapper.UserMapper;
import com.br.dynaccurate.dynaccuratesolution.model.User;
import com.br.dynaccurate.dynaccuratesolution.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserResource {

    private final UserMapper mapper;
    private final UserService service;

    @PostMapping
    public ResponseEntity<UserDTOResponse> createUser(@RequestBody @Valid UserDTORequest dtoRequest) throws UserAlreadyExist {
        User user = mapper.dtoRequestToUser(dtoRequest);
        User save = service.save(user);
        UserDTOResponse response = mapper.userToDTOResponse(save);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserDTOResponse> updateUser(@PathVariable String id,
                                                      @RequestBody @Valid UserDTORequest dtoRequest) throws UserAlreadyExist, NotFound {
        User user = mapper.dtoRequestToUser(dtoRequest);
        User save = service.updateUser(id, user);
        UserDTOResponse response = mapper.userToDTOResponse(save);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<UserDTOResponse>> getAllUser(Pageable pageable) {
        Page<User> allUser = service.getAllUser(pageable);
        Page<UserDTOResponse> mapResponse = allUser.map(value -> mapper.userToDTOResponse(value));
        return ResponseEntity.ok(mapResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTOResponse> getUserById(@PathVariable String id) throws NotFound {
        User user = service.userById(id);
        UserDTOResponse userDTOResponse = mapper.userToDTOResponse(user);
        return ResponseEntity.ok(userDTOResponse);
    }
}
