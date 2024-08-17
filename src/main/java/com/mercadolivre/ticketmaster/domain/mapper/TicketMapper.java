package com.mercadolivre.ticketmaster.domain.mapper;

import com.mercadolivre.ticketmaster.domain.dto.TicketDTO;
import com.mercadolivre.ticketmaster.domain.entity.Category;
import com.mercadolivre.ticketmaster.domain.entity.Severity;
import com.mercadolivre.ticketmaster.domain.entity.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(source = "categoryId", target = "category.id")
    @Mapping(source = "subcategoryId", target = "subcategory.id")
    @Mapping(source = "severityLevel", target = "severity.id")
    Ticket toEntity(TicketDTO ticketDTO);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "user", target = "user")
    @Mapping(source = "category.name", target = "category")
    @Mapping(source = "subcategory.name", target = "subcategory")
    @Mapping(source = "severity.id", target = "severityLevel")
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "subcategory.id", target = "subcategoryId")
    TicketDTO toDto(Ticket ticket);

    default Category map(String categoryName) {
        return categoryName.isBlank() ? null : Category.builder().name(categoryName).build();
    }

    default void mergeFromDto(TicketDTO ticketDTO, @MappingTarget Ticket ticketToUpdate) {
        if (ticketDTO.getTitle() != null) {
            ticketToUpdate.setTitle(ticketDTO.getTitle());
        }
        if (ticketDTO.getDescription() != null) {
            ticketToUpdate.setDescription(ticketDTO.getDescription());
        }
        if (ticketDTO.getCategoryId() != null) {
            ticketToUpdate.setCategory(Category.builder().id(ticketDTO.getCategoryId()).build());
        }
        if (ticketDTO.getSubcategoryId() != null) {
            ticketToUpdate.setSubcategory(Category.builder().id(ticketDTO.getSubcategoryId()).build());
        }
        if (ticketDTO.getSeverityLevel() != null) {
            ticketToUpdate.setSeverity(Severity.builder().id(ticketDTO.getSeverityLevel()).build());
        }
    }

}
