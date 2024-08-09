package com.mercadolivre.ticketmaster.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ParentDTO {

    @NotNull
    private Long id;
    @NotBlank
    private String name;
}
