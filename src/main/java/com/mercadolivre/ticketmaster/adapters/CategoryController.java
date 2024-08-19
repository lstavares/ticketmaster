package com.mercadolivre.ticketmaster.adapters;


import com.mercadolivre.ticketmaster.application.service.CategoryService;
import com.mercadolivre.ticketmaster.domain.dto.CategoryDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.net.URI.create;
import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/categories")
@Validated
@RequiredArgsConstructor
public class CategoryController {

    private final Logger logger = getLogger(this.getClass());
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDTO> save(@Valid @RequestBody CategoryDTO category, HttpServletRequest httpRequest) {
        logger.info("Received request to save category: {} by user: {}", category, httpRequest.getUserPrincipal());
        return created(create(httpRequest.getRequestURI())).body(categoryService.save(category));
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> list() {
        logger.info("Received request to list all categories");
        return ok(categoryService.list());
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> get(@PathVariable Long categoryId) {
        logger.info("Received request to get category: {}", categoryId);
        return ok(categoryService.get(categoryId));
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> update(@PathVariable Long categoryId, @Valid @RequestBody CategoryDTO category) {
        logger.info("Received request to update category: {}", categoryId);
        return ok(categoryService.update(categoryId, category));
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> delete(@PathVariable Long categoryId) {
        logger.info("Received request to delete category: {}", categoryId);
        categoryService.delete(categoryId);
        return noContent().build();
    }

    @GetMapping("/subcategories/{parentId}")
    public ResponseEntity<List<CategoryDTO>> findByParent(@PathVariable Long parentId) {
        logger.info("Received request to get subcategories by parent: {}", parentId);
        return ok(categoryService.getByParent(parentId));
    }
}
