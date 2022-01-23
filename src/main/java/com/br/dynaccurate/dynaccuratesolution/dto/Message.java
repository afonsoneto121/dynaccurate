package com.br.dynaccurate.dynaccuratesolution.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class Message {
    private String message;
    private String other;
}
