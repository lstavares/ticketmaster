package com.mercadolivre.ticketmaster.domain.dto;

import com.mercadolivre.ticketmaster.adapters.validation.ValidCategorySubcategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ValidCategorySubcategory
public class TicketDTO {
    public Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    private UserDTO user;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String category;
    private String subcategory;
    @NotNull
    private Long severityLevel;
    @NotNull
    private Long categoryId;
    @NotNull
    private Long subcategoryId;
}

