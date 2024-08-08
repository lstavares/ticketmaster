package com.mercadolivre.ticketmaster.domain.mapper;


import com.mercadolivre.ticketmaster.domain.dto.CategoryDTO;
import com.mercadolivre.ticketmaster.domain.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toEntity(CategoryDTO categoryDTO);
    CategoryDTO toDto(Category category);
    void updateFromDto(CategoryDTO categoryDTO, @MappingTarget Category category);

}