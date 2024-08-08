package com.mercadolivre.ticketmaster.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class CategoryDTO {
    private String name;
    private Long parentId; // ou CategoryDTO se necessário
    private List<Long> subcategoryIds; // ou List<CategoryDTO> se necessário
}
