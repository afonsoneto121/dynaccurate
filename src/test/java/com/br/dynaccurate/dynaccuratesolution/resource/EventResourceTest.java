package com.br.dynaccurate.dynaccuratesolution.resource;

import com.br.dynaccurate.dynaccuratesolution.model.Event;
import com.br.dynaccurate.dynaccuratesolution.resources.EventResource;
import com.br.dynaccurate.dynaccuratesolution.service.EventService;
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

import java.time.LocalDateTime;
import java.util.Arrays;

import static com.br.dynaccurate.dynaccuratesolution.util.GenerateObjects.getEvent;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class EventResourceTest {
    MockMvc mvc;

    @Mock
    EventService service;

    @InjectMocks
    EventResource resource;

    private Event event;
    @BeforeEach
    public void setup() {

        mvc = MockMvcBuilders.standaloneSetup(resource)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
        event = getEvent();
    }

    /*
    When find events by user
    Then A page should be returned with status 200 OK
     */
    @Test
    public void getAllUser() throws Exception {
        String userName = "Afonso";
        LocalDateTime toDate = LocalDateTime.now();
        LocalDateTime fromDate = LocalDateTime.now().minusDays(1);

        Pageable pageable = PageRequest.of(0,3);
        Page<Event> page = new PageImpl(Arrays.asList(event,event,event,event), pageable, 4);

        Mockito.when(service.findEventByUser(userName,toDate,fromDate,pageable)).thenReturn(page);

        mvc.perform(get(String.format("/api/v1/events/user/%s?toDate=%s&fromDate=%s&page=0&size=3",userName,toDate,fromDate))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
