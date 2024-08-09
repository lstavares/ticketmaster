package com.mercadolivre.ticketmaster.integration;

import com.mercadolivre.ticketmaster.application.service.CategoryService;
import com.mercadolivre.ticketmaster.domain.dto.CategoryDTO;
import com.mercadolivre.ticketmaster.domain.exception.CategoryNotFoundException;
import com.mercadolivre.ticketmaster.infrastructure.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.ANY;

@SpringBootTest
@AutoConfigureTestDatabase(replace = ANY)
@Transactional
@ActiveProfiles("test")
public class CategoryServiceIntegrationTest {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void testCreateAndRetrieveCategory() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName("New Category");

        CategoryDTO createdCategory = categoryService.save(categoryDTO);
        CategoryDTO retrievedCategory = categoryService.get(createdCategory.getId());

        assertEquals(createdCategory.getName(), retrievedCategory.getName());
    }

    @Test
    public void testUpdateCategory() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName("New Category");

        CategoryDTO createdCategory = categoryService.save(categoryDTO);
        createdCategory.setName("Updated Category");

        CategoryDTO updatedCategory = categoryService.update(createdCategory.getId(), createdCategory);
        assertEquals("Updated Category", updatedCategory.getName());
    }

    @Test
    public void testDeleteCategory() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName("New Category");

        CategoryDTO createdCategory = categoryService.save(categoryDTO);
        categoryService.delete(createdCategory.getId());

        assertThrows(CategoryNotFoundException.class, () -> categoryService.get(createdCategory.getId()));
    }

    @Test
    public void testRetrieveNonExistentCategory() {
        assertThrows(CategoryNotFoundException.class, () -> categoryService.get(999L));
    }
}
