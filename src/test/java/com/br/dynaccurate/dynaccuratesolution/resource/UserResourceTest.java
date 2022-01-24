package com.br.dynaccurate.dynaccuratesolution.resource;

import com.br.dynaccurate.dynaccuratesolution.dto.request.UserDTORequest;
import com.br.dynaccurate.dynaccuratesolution.dto.response.UserDTOResponse;
import com.br.dynaccurate.dynaccuratesolution.exception.NotFound;
import com.br.dynaccurate.dynaccuratesolution.exception.UserAlreadyExist;
import com.br.dynaccurate.dynaccuratesolution.mapper.UserMapper;
import com.br.dynaccurate.dynaccuratesolution.model.User;
import com.br.dynaccurate.dynaccuratesolution.resources.UserResource;
import com.br.dynaccurate.dynaccuratesolution.resources.advice.UserResourceAdvice;
import com.br.dynaccurate.dynaccuratesolution.service.UserService;
import com.br.dynaccurate.dynaccuratesolution.util.JsonUtil;
import org.hamcrest.Matchers;
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
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Arrays;

import static com.br.dynaccurate.dynaccuratesolution.util.GenerateObjects.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserResourceTest {
    private MockMvc mvc;
    @Mock
    private UserService service;

    @Mock
    private UserMapper mapper;

    @InjectMocks
    private UserResource resource;

    private User user;
    private UserDTORequest dtoRequest;
    private UserDTOResponse dtoResponse;
    @BeforeEach
    public void setup() {

        mvc = MockMvcBuilders.standaloneSetup(resource)
                .setControllerAdvice(new UserResourceAdvice())
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
        user = getUser();
        dtoRequest = getUserDTORequest();
        dtoResponse = getUserDTOResponse();
    }
    /*
    Given valid user
    When save user
    Then Should be return with status 201 CREATED
     */
    @Test
    public void saveValidUser() throws Exception {
        Mockito.when(service.save(user)).thenReturn(user);
        Mockito.when(mapper.dtoRequestToUser(dtoRequest)).thenReturn(user);
        Mockito.when(mapper.userToDTOResponse(user)).thenReturn(dtoResponse);
        mvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(dtoRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nickname", Matchers.equalTo(dtoResponse.getNickname())));
    }
    /*
    Given invalid user with nickname duplicated
    When save user
    Then Should be return with status 400 BAD_REQUEST
    */
    @Test
    public void saveInValidUser() throws Exception {
        Mockito.when(service.save(user)).thenThrow(new UserAlreadyExist("User already exists"));
        Mockito.when(mapper.dtoRequestToUser(dtoRequest)).thenReturn(user);
        mvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(dtoRequest)))
                .andExpect(status().isBadRequest());
    }
    /*
    Given valid user
    When update user
    Then Should be return with status 200 OK
     */
    @Test
    public void updateValidUser() throws Exception {
        Mockito.when(service.updateUser(user.getId(), user)).thenReturn(user);
        Mockito.when(mapper.dtoRequestToUser(dtoRequest)).thenReturn(user);
        Mockito.when(mapper.userToDTOResponse(user)).thenReturn(dtoResponse);
        mvc.perform(put(String.format("/api/v1/users/%s", user.getId()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(dtoRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nickname", Matchers.equalTo(dtoResponse.getNickname())));
    }

    /*
    Given invalid user with nickname duplicated
    When update user
    Then Should be return with status 400 BAD_REQUEST
    */
    @Test
    public void updateInValidUser() throws Exception {
        Mockito.when(service.updateUser(user.getId(), user)).thenThrow(new UserAlreadyExist("User already exists"));
        Mockito.when(mapper.dtoRequestToUser(dtoRequest)).thenReturn(user);
        mvc.perform(put(String.format("/api/v1/users/%s", user.getId()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(dtoRequest)))
                .andExpect(status().isBadRequest());
    }
    /*
    Given invalid id user
    When save user
    Then Should be return with status 404 NOT_FOUND
    */
    @Test
    public void updateInValidIdUser() throws Exception {
        Mockito.when(service.updateUser(user.getId(), user)).thenThrow(new NotFound("User Not found"));
        Mockito.when(mapper.dtoRequestToUser(dtoRequest)).thenReturn(user);
        mvc.perform(put(String.format("/api/v1/users/%s", user.getId()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(dtoRequest)))
                .andExpect(status().isNotFound());
    }
    /*
    Given  user id
    When delete user
    Then Should be return with status 204 NO CONTENT
     */
    @Test
    public void deleteUser() throws Exception {
        Mockito.doNothing().when(service).delete(user.getId());
        mvc.perform(delete(String.format("/api/v1/users/%s", user.getId()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(dtoRequest)))
                .andExpect(status().isNoContent());
    }
    /*
    Given a valid id
    When request user by id
    Then user should be returned with Status 200 OK
    */
    @Test
    public void getValidId() throws Exception {
        Mockito.when(mapper.userToDTOResponse(user)).thenReturn(dtoResponse);
        Mockito.when(service.userById(user.getId())).thenReturn(user);
        mvc.perform(get(String.format("/api/v1/users/%s", user.getId()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nickname", Matchers.equalTo(dtoResponse.getNickname())))
                .andExpect(jsonPath("$.id", Matchers.equalTo(dtoResponse.getId())));
    }
    /*
    Given an invalid id
    When request user by id
    Then user should be returned with Status 200 OK
     */
    @Test
    public void getInvalidId() throws Exception {
        Mockito.when(service.userById(user.getId())).thenThrow(new NotFound(""));
        mvc.perform(get(String.format("/api/v1/users/%s", user.getId()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    /*
    When request all users
    Then user should be returned with Status 200 OK
    */
    @Test
    public void getAllUser() throws Exception {
        Pageable pageable = PageRequest.of(0,3);
        Page<User> users = new PageImpl(Arrays.asList(user,user,user,user), pageable, 4);

        Mockito.when(mapper.userToDTOResponse(user)).thenReturn(dtoResponse);
        Mockito.when(service.getAllUser(pageable)).thenReturn(users);

        mvc.perform(get(String.format("/api/v1/users?page=0&size=3"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
