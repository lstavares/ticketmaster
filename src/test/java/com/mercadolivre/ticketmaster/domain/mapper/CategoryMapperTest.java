package com.mercadolivre.ticketmaster.domain.mapper;

import com.mercadolivre.ticketmaster.domain.dto.CategoryDTO;
import com.mercadolivre.ticketmaster.domain.entity.Category;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CategoryMapperTest {

    private final CategoryMapper categoryMapper = Mappers.getMapper(CategoryMapper.class);

    @Test
    void testToEntity() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName("Test Category");

        Category category = categoryMapper.toEntity(categoryDTO);

        assertEquals(categoryDTO.getName(), category.getName());
    }

    @Test
    void testToDto() {
        Category category = new Category();
        category.setName("Test Category");

        CategoryDTO categoryDTO = categoryMapper.toDto(category);

        assertEquals(category.getName(), categoryDTO.getName());
    }

    @Test
    void testMergeFromDto() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName("Updated Category");
        Category category = new Category();
        category.setName("Old Category");

        categoryMapper.mergeFromDto(categoryDTO, category);

        assertEquals(categoryDTO.getName(), category.getName());
    }
}
