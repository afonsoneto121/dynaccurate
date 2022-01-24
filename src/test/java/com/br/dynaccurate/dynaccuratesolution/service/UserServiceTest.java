package com.br.dynaccurate.dynaccuratesolution.service;

import com.br.dynaccurate.dynaccuratesolution.exception.NotFound;
import com.br.dynaccurate.dynaccuratesolution.exception.UserAlreadyExist;
import com.br.dynaccurate.dynaccuratesolution.model.User;
import com.br.dynaccurate.dynaccuratesolution.repository.UserRepository;
import com.br.dynaccurate.dynaccuratesolution.service.implementation.UserServiceImp;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserServiceImp service;

    private User user;
    @BeforeEach
    public void setup() {
        user = User.builder()
                .id("1")
                .nickname("Afonso")
                .registrationDate(LocalDate.now())
                .build();

    }
    /*
    Given a valid user
    When user save
    Then A user should be saved
     */
    @Test
    public void saveUser() throws UserAlreadyExist {
        Mockito.when(repository.findByNickname(user.getNickname())).thenReturn(Optional.empty());
        Mockito.when(repository.save(user)).thenReturn(user);

        User save = service.save(user);

        MatcherAssert.assertThat(save, Matchers.is(user));
    }
    /*
     Given a user with invalid nickname (nickname already exists)
     When user save
     Then A exception should be thrown
    */
    @Test
    public void saveInvalidUser() throws UserAlreadyExist {
        Mockito.when(repository.findByNickname(user.getNickname())).thenReturn(Optional.of(user));
        Assertions.assertThrows(UserAlreadyExist.class, () -> service.save(user));
    }
    /*
    Given a valid user
    When user update
    Then A user should be updated
     */
    @Test
    public void updateUser() throws UserAlreadyExist, NotFound {
        Mockito.when(repository.findById(user.getId())).thenReturn(Optional.of(user));
        Mockito.when(repository.findByNickname(user.getNickname())).thenReturn(Optional.empty());
        Mockito.when(repository.save(user)).thenReturn(user);

        User updateUser = service.updateUser(this.user.getId(), this.user);

        MatcherAssert.assertThat(updateUser, Matchers.is(user));
    }

    /*
    Given an invalid id
    When user update
    Then A exception should be thrown
     */
    @Test
    public void updateUserWithInvalidID() throws UserAlreadyExist, NotFound {
        Mockito.when(repository.findById(user.getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFound.class, () -> service.updateUser(user.getId(), user));
    }

    /*
    Given an invalid nickname
    When user update
    Then A exception should be thrown
     */
    @Test
    public void updateUserWithInvalidNickname() throws UserAlreadyExist, NotFound {
        Mockito.when(repository.findById(user.getId())).thenReturn(Optional.of(user));
        Mockito.when(repository.findByNickname(user.getNickname())).thenReturn(Optional.of(user));
        Assertions.assertThrows(UserAlreadyExist.class, () -> service.updateUser(user.getId(), user));
    }

    /*
    Given a valid ID
    When find user by id
    Then An user should be returned
     */
    @Test
    public void getUserByValidId() throws NotFound {
        Mockito.when(repository.findById(user.getId())).thenReturn(Optional.of(user));

        User userById = service.userById(this.user.getId());
        MatcherAssert.assertThat(userById, Matchers.is(user));
    }
    /*
        Given an invalid ID
        When find user by id
        Then A exception should be thrown
         */
    @Test
    public void getUserByInValidId() throws NotFound {
        Mockito.when(repository.findById(user.getId())).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFound.class, () -> service.userById(user.getId()));
    }

    /*

    When find all users
    Then A page should be returned
    */
    public void getAllUsers() {
        Pageable pageable = PageRequest.of(0,2);
        Mockito.when(repository.findAll(pageable)).thenReturn(
                new PageImpl(Arrays.asList(user,user,user,user), pageable, 4));

        Page<User> allUser = service.getAllUser(pageable);

        MatcherAssert.assertThat(allUser.getTotalElements(), Matchers.equalTo(4));
        MatcherAssert.assertThat(allUser.getTotalPages(), Matchers.equalTo(2));
        MatcherAssert.assertThat(allUser.getSize(), Matchers.equalTo(2));
    }

}
