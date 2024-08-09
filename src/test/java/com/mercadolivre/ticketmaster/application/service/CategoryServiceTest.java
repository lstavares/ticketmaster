package com.mercadolivre.ticketmaster.application.service;

import com.mercadolivre.ticketmaster.domain.dto.CategoryDTO;
import com.mercadolivre.ticketmaster.domain.entity.Category;
import com.mercadolivre.ticketmaster.domain.exception.CategoryNotFoundException;
import com.mercadolivre.ticketmaster.domain.mapper.CategoryMapper;
import com.mercadolivre.ticketmaster.infrastructure.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;

    @Test
    void testList() {
        Category category = new Category();
        CategoryDTO categoryDTO = new CategoryDTO();
        when(categoryRepository.findAll()).thenReturn(Collections.singletonList(category));
        when(categoryMapper.toDto(category)).thenReturn(categoryDTO);

        var result = categoryService.list();

        assertEquals(1, result.size());
        assertEquals(categoryDTO, result.get(0));
    }

    @Test
    void testGet() {
        Long categoryId = 1L;
        Category category = new Category();
        CategoryDTO categoryDTO = new CategoryDTO();
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));
        when(categoryMapper.toDto(category)).thenReturn(categoryDTO);

        var result = categoryService.get(categoryId);

        assertEquals(categoryDTO, result);
    }

    @Test
    void testGetNotFound() {
        Long categoryId = 1L;
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> categoryService.get(categoryId));
    }

    @Test
    void testSave() {
        CategoryDTO categoryDTO = new CategoryDTO();
        Category category = new Category();
        when(categoryMapper.toEntity(categoryDTO)).thenReturn(category);
        when(categoryRepository.save(category)).thenReturn(category);
        when(categoryMapper.toDto(category)).thenReturn(categoryDTO);

        var result = categoryService.save(categoryDTO);

        assertEquals(categoryDTO, result);
    }

    @Test
    void testUpdate() {
        Long categoryId = 1L;
        CategoryDTO categoryDTO = new CategoryDTO();
        Category category = new Category();
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));
        when(categoryRepository.save(category)).thenReturn(category);
        when(categoryMapper.toDto(category)).thenReturn(categoryDTO);

        var result = categoryService.update(categoryId, categoryDTO);

        assertEquals(categoryDTO, result);
    }

    @Test
    void testDelete() {
        Long categoryId = 1L;
        doNothing().when(categoryRepository).deleteById(categoryId);

        categoryService.delete(categoryId);

        verify(categoryRepository, times(1)).deleteById(categoryId);
    }
}
