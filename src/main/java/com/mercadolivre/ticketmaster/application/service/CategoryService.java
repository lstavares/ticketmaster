package com.mercadolivre.ticketmaster.application.service;

import com.mercadolivre.ticketmaster.domain.dto.CategoryDTO;
import com.mercadolivre.ticketmaster.domain.entity.Category;
import com.mercadolivre.ticketmaster.domain.exception.CategoryNotFoundException;
import com.mercadolivre.ticketmaster.domain.mapper.CategoryMapper;
import com.mercadolivre.ticketmaster.infrastructure.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.mercadolivre.ticketmaster.application.utils.Helper.LogHelper.putObjectInMDC;
import static org.slf4j.LoggerFactory.getLogger;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final Logger logger = getLogger(this.getClass());
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryDTO> list() {
        logger.info("Retrieving all categories");
        List<CategoryDTO> collectList = categoryRepository.findAll().stream().map(categoryMapper::toDto).collect(Collectors.toList());
        putObjectInMDC("payload", collectList);
        logger.info("retrieved all categories");
        return collectList;
    }

    public CategoryDTO get(Long categoryId) {
        logger.info("Retrieving category with id: {}", categoryId);
        CategoryDTO categoryDTO = categoryMapper.toDto(categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException(categoryId)));
        putObjectInMDC("payload", categoryDTO);
        logger.info("Retrieved category with id: {}", categoryId);
        return categoryDTO;
    }

    public CategoryDTO save(CategoryDTO categoryDTO) {
        logger.info("Saving category: {}", categoryDTO);
        Category category = categoryMapper.toEntity(categoryDTO);
        CategoryDTO categoryCreated = categoryMapper.toDto(categoryRepository.save(category));
        putObjectInMDC("payload", categoryCreated);
        logger.info("Category with id {} saved", categoryDTO.getId());
        return categoryCreated;
    }

    public CategoryDTO update(Long categoryId, CategoryDTO categoryDTO) {
        logger.info("Updating category with id {} with {}", categoryId, categoryDTO);
        Category categoryToUpdate = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));

        // Atualiza apenas os campos que foram modificados
        categoryMapper.mergeFromDto(categoryDTO, categoryToUpdate);

        CategoryDTO categoryUpdated = categoryMapper.toDto(categoryRepository.save(categoryToUpdate));
        putObjectInMDC("payload", categoryUpdated);
        logger.info("Category with id {} updated", categoryDTO.getId());
        return categoryUpdated;
    }

    public void delete(Long categoryId) {
        logger.info("Deleting category with id {}", categoryId);
        categoryRepository.deleteById(categoryId);
        logger.info("Category with id {} deleted", categoryId);
    }

    public List<CategoryDTO> getByParent(Long parentId) {
        logger.info("Getting categories by parent id {}", parentId);
        Category parent = categoryRepository.findById(parentId).orElseThrow(() -> new CategoryNotFoundException(parentId));
        List<CategoryDTO> categoriesList = categoryRepository.findByParent(parent).stream().map(categoryMapper::toDto).collect(Collectors.toList());
        putObjectInMDC("payload", categoriesList);
        logger.info("Categories by parent id {} found", parentId);
        return categoriesList;
    }
}
