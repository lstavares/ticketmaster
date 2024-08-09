package com.mercadolivre.ticketmaster.domain.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class CategoryDTO {
    private Long id;
    @NotBlank
    private String name;
    @Valid
    private ParentDTO parent;
    private List<CategoryDTO> subcategories;

}
