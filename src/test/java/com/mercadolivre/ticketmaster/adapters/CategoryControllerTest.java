package com.mercadolivre.ticketmaster.adapters;

import com.mercadolivre.ticketmaster.application.service.CategoryService;
import com.mercadolivre.ticketmaster.domain.dto.CategoryDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

    @InjectMocks
    private CategoryController categoryController;

    @Mock
    private CategoryService categoryService;

    @Mock
    private HttpServletRequest httpRequest;

    @Test
    void testSave() {
        CategoryDTO categoryDTO = new CategoryDTO();
        when(categoryService.save(any(CategoryDTO.class))).thenReturn(categoryDTO);
        when(httpRequest.getRequestURI()).thenReturn("/categories");

        ResponseEntity<CategoryDTO> response = categoryController.save(categoryDTO, httpRequest);

        assertEquals(CREATED, response.getStatusCode());
        assertEquals(categoryDTO, response.getBody());
    }

    @Test
    void testList() {
        CategoryDTO categoryDTO = new CategoryDTO();
        when(categoryService.list()).thenReturn(Collections.singletonList(categoryDTO));

        ResponseEntity<List<CategoryDTO>> response = categoryController.list();

        assertEquals(OK, response.getStatusCode());
        assertEquals(1, requireNonNull(response.getBody()).size());
        assertEquals(categoryDTO, response.getBody().get(0));
    }

    @Test
    void testGet() {
        Long categoryId = 1L;
        CategoryDTO categoryDTO = new CategoryDTO();
        when(categoryService.get(categoryId)).thenReturn(categoryDTO);

        ResponseEntity<CategoryDTO> response = categoryController.get(categoryId);

        assertEquals(OK, response.getStatusCode());
        assertEquals(categoryDTO, response.getBody());
    }

    @Test
    void testUpdate() {
        Long categoryId = 1L;
        CategoryDTO categoryDTO = new CategoryDTO();
        when(categoryService.update(categoryId, categoryDTO)).thenReturn(categoryDTO);

        ResponseEntity<CategoryDTO> response = categoryController.update(categoryId, categoryDTO);

        assertEquals(OK, response.getStatusCode());
        assertEquals(categoryDTO, response.getBody());
    }

    @Test
    void testDelete() {
        Long categoryId = 1L;

        ResponseEntity<Void> response = categoryController.delete(categoryId);

        assertEquals(NO_CONTENT, response.getStatusCode());
        verify(categoryService, times(1)).delete(categoryId);
    }

    @Test
    void testFindByParent() {
        Long parentId = 1L;
        CategoryDTO categoryDTO = new CategoryDTO();
        when(categoryService.getByParent(parentId)).thenReturn(Collections.singletonList(categoryDTO));

        ResponseEntity<List<CategoryDTO>> response = categoryController.findByParent(parentId);

        assertEquals(OK, response.getStatusCode());
        assertEquals(1, requireNonNull(response.getBody()).size());
        assertEquals(categoryDTO, response.getBody().get(0));
    }
}
