package com.mercadolivre.ticketmaster.adapters;


import com.mercadolivre.ticketmaster.application.service.CategoryService;
import com.mercadolivre.ticketmaster.domain.dto.CategoryDTO;
import com.mercadolivre.ticketmaster.domain.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDTO> save(@RequestBody CategoryDTO category, HttpRequest httpRequest) {
        return created(httpRequest.getURI()).body(categoryService.save(category));
    }

    @GetMapping
    public ResponseEntity<List<Category>> list() {
        return ok(categoryService.list());
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> get(@PathVariable Long categoryId) {
        return ok(categoryService.get(categoryId));
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> delete(@PathVariable Long categoryId) {
        categoryService.delete(categoryId);
        return noContent().build();
    }

    @GetMapping("/subcategories/{parentId}")
    public ResponseEntity<List<Category>> findByParent(@PathVariable Long parentId) {
        return ok(categoryService.getByParent(parentId));
    }
}
