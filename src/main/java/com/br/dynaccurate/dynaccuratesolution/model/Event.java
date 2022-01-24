package com.br.dynaccurate.dynaccuratesolution.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "event")
public class Event {
    @Id
    private String id;
    private String type;
    private LocalDateTime dateTime;
    String idUser;
}
