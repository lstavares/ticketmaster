package com.mercadolivre.ticketmaster.application.service;

import com.mercadolivre.ticketmaster.domain.Category;
import com.mercadolivre.ticketmaster.domain.exception.CategoryNotFoundException;
import com.mercadolivre.ticketmaster.infrastructure.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> list() {
        return categoryRepository.findAll();
    }

    public Category get(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException(categoryId));
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public void delete(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    public List<Category> getByParent(Long parentId) {
        Category parent = get(parentId);
        return categoryRepository.findByParent(parent);
    }
}
