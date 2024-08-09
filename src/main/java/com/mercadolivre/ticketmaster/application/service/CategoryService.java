package com.mercadolivre.ticketmaster.application.service;

import com.mercadolivre.ticketmaster.domain.dto.CategoryDTO;
import com.mercadolivre.ticketmaster.domain.entity.Category;
import com.mercadolivre.ticketmaster.domain.exception.CategoryNotFoundException;
import com.mercadolivre.ticketmaster.domain.mapper.CategoryMapper;
import com.mercadolivre.ticketmaster.infrastructure.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryDTO> list() {
        return categoryRepository.findAll().stream().map(categoryMapper::toDto).collect(Collectors.toList());
    }

    public CategoryDTO get(Long categoryId) {
        return categoryMapper.toDto(categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException(categoryId)));
    }

    public CategoryDTO save(CategoryDTO categoryDTO) {
        Category category = categoryMapper.toEntity(categoryDTO);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    public CategoryDTO update(Long categoryId, CategoryDTO categoryDTO) {
        Category categoryToUpdate = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));

        // Atualiza apenas os campos que foram modificados
        categoryMapper.mergeFromDto(categoryDTO, categoryToUpdate);

        return categoryMapper.toDto(categoryRepository.save(categoryToUpdate));
    }

    public void delete(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    public List<CategoryDTO> getByParent(Long parentId) {
        Category parent = categoryRepository.findById(parentId).orElseThrow(() -> new CategoryNotFoundException(parentId));
        return categoryRepository.findByParent(parent).stream().map(categoryMapper::toDto).collect(Collectors.toList());
    }
}
