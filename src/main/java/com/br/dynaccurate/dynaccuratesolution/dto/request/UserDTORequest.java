package com.br.dynaccurate.dynaccuratesolution.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTORequest {
    @NotBlank
    private String nickname;

}
