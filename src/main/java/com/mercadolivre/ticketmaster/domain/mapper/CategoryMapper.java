package com.mercadolivre.ticketmaster.domain.mapper;


import com.mercadolivre.ticketmaster.domain.dto.CategoryDTO;
import com.mercadolivre.ticketmaster.domain.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toEntity(CategoryDTO categoryDTO);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "parent.id", target = "parent.id")
    @Mapping(source = "parent.name", target = "parent.name")
    @Mapping(source = "subcategories", target = "subcategories")
    CategoryDTO toDto(Category category);

    default List<CategoryDTO> mapSubcategories(List<Category> subcategories) {
        if (subcategories == null || subcategories.isEmpty()) {
            return Collections.emptyList();
        }
        return subcategories.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    default CategoryDTO mapParent(Category parent) {
        return parent != null ? toDto(parent) : null; 
    }

    default void mergeFromDto(CategoryDTO categoryDTO, @MappingTarget Category category) {

        if (!categoryDTO.getName().equals(category.getName())) {
            category.setName(categoryDTO.getName());
        }

        if (categoryDTO.getParent() != null) {
            category.setParent(Category.builder().id(categoryDTO.getParent().getId()).build());
        }
    }
}