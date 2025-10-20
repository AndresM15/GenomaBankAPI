package com.BackendG1.GenomaBankAPI.entities;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
@Embeddable @Data @NoArgsConstructor @AllArgsConstructor
public class GenFuncionId implements Serializable {
    private Long genId;
    private Long funcionId;
}