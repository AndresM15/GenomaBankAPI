package com.BackendG1.GenomaBankAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder // Usamos Builder para una construcción más limpia
public class AuthOutDTO {
    private String accessToken;
}
